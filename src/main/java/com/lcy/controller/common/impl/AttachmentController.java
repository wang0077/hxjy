package com.lcy.controller.common.impl;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.lcy.contant.HuiShiConstants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.IAttachmentController;
import com.lcy.controller.common.ServiceException;
import com.lcy.util.file.fast.FastResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.fast.org.source.MyException;
import com.lcy.util.file.oss.AliOSSClientUtils;
import com.lcy.util.file.oss.AliOSSConfigUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 附件
 *
 * @author: lchunyi@linewell.com
 * @since: 2019/3/8 16:51
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController extends BaseController implements IAttachmentController {
    @Override
    @ResponseBody
    @RequestMapping(value = "getToken")
    public ResponseResult<Map<String, String>> getToken() {

        String dir = HuiShiConstants.OSS_DIR;

        Map<String, String> map = getToken("",dir);
        if(map != null && map.size() > 0){
            map.put("filename", dir + "/" + UUIDGenerator.getUUID());
        }
        return renderSuccess(map);
    }

    private Map<String, String> getToken(String appId, String dir) {
        long expireTime = AliOSSConfigUtils.getInstance().getexEireTime();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        Map<String, String> respMap = new LinkedHashMap<String, String>();

        try {
            if (StringUtils.isEmpty(dir)) {
                dir = AliOSSConfigUtils.getInstance().getCommonDir();
            }

            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(
                    PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith,
                    PolicyConditions.COND_KEY, dir);
            String postPolicy = AliOSSClientUtils.getOSSClient()
                    .generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = AliOSSClientUtils.getOSSClient()
                    .calculatePostSignature(postPolicy);

            respMap.put("accessid", AliOSSConfigUtils.getInstance()
                    .getAccessKeyId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);

            respMap.put("dir", dir);
            respMap.put("host", AliOSSConfigUtils.getInstance().getUploadHost());
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMap;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "uploadPics")
    public ResponseResult<List<FastResult>> uploadPics(HttpServletRequest request, HttpServletResponse response) {
        return getListResponseResult((MultipartHttpServletRequest) request, 1);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "uploadFiles")
    public ResponseResult<List<FastResult>> uploadFiles(HttpServletRequest request, HttpServletResponse response) {
        return getListResponseResult((MultipartHttpServletRequest) request, 2);
    }

    /**
     * 文件流获取
     * @param request
     * @param type
     * @return
     */
    private ResponseResult<List<FastResult>> getListResponseResult(MultipartHttpServletRequest request, int type) {

        MultipartHttpServletRequest multipartRequest = request;
        List<MultipartFile> multipartFiles = multipartRequest.getFiles("files");// 获取多个文件流

        return getListResponseResult(multipartFiles, type);
    }

    /**
     * 文件流处理
     * @param multipartFiles
     * @param type
     * @return
     */
    private ResponseResult<List<FastResult>> getListResponseResult(List<MultipartFile> multipartFiles, int type) {
        List<File> files = new ArrayList<>();        // 多个文件
        List<String> fileFileNames = new ArrayList<>();  // 多个文件名
        List<String> fileContentTypes = new ArrayList<>();  // 多个文件类型
        List<Integer> heights = new ArrayList<Integer>();    //多个文件的高度
        List<Integer> widths = new ArrayList<Integer>();    //多个文件的宽度

        if(type == 1){

            //图片临时文件处理
            createPicTemp(multipartFiles, files, fileFileNames, fileContentTypes, heights, widths);
        }else{

            //文件临时文件处理
            createTemp(multipartFiles, files, fileFileNames,fileContentTypes);
        }


        List<FastResult> fastResults = null;

        try {
            //上传图片
            if (type == 1) {

                fastResults = FileSystemFactory.createFileSystemInstance().uploadPicWithInfo(files, fileFileNames,heights,widths);

            } else {

                //上传文件
                fastResults = FileSystemFactory.createFileSystemInstance().uploadFile(files, fileFileNames);
            }

            // 上传成功，删除本地缓冲区文件
            for (File deleteFile : files) {
                deleteFile.delete();
            }
        } catch (MyException e) {
            return renderError(new ServiceException(e.getMessage()));
        }

        return renderSuccess(fastResults);
    }

    /**
     * 创建临时文件
     *
     * @param multipartFiles
     * @param files
     * @param fileFileNames
     * @param fileContentTypes
     */
    private void createTemp(List<MultipartFile> multipartFiles, List<File> files, List<String> fileFileNames, List<String> fileContentTypes) {
        for (MultipartFile multipartFile : multipartFiles) {
            try {

                // 创建缓冲区临时文件
                File file = File.createTempFile("tmp", null);
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                files.add(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileFileNames.add(multipartFile.getOriginalFilename());
            fileContentTypes.add(multipartFile.getContentType());
        }
    }


    /**
     * 创建图片临时文件
     *
     * @param multipartFiles
     * @param files
     * @param fileFileNames
     * @param fileContentTypes
     */
    private void createPicTemp(List<MultipartFile> multipartFiles, List<File> files, List<String> fileFileNames, List<String> fileContentTypes, List<Integer> heights, List<Integer> widths) {
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                //
                BufferedImage image = ImageIO.read(multipartFile.getInputStream());
                heights.add(image.getHeight());
                widths.add(image.getWidth());
                // 创建缓冲区临时文件
                File file = File.createTempFile("tmp", null);
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                files.add(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileFileNames.add(multipartFile.getOriginalFilename());
            fileContentTypes.add(multipartFile.getContentType());
        }
    }
}
