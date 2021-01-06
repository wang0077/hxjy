package com.lcy.util.file;

import com.lcy.controller.common.ServiceException;
import com.lcy.util.file.fast.FastResult;
import com.lcy.dto.common.FileListDTO;
import com.lcy.util.file.fast.LoadFile;
import com.lcy.util.file.fast.org.source.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 文件系统抽象类
 *
 * @author zjc
 */
public interface IFileSystem {

    /**
     * 根据文件标识获取文件路径
     *
     * @param fileId
     * @return
     */
    String getFilePathById(String fileId);

    /**
     * fastDfs批量上传图片
     *
     * @param files     文件
     * @param fileNames 文件名称
     * @return 每个文件上传的结果
     */
    List<FastResult> uploadPic(List<File> files, List<String> fileNames) throws MyException;

    /**
     * fastDfs批量上传图片
     *
     * @param files     文件
     * @param fileNames 文件名称
     * @return 每个文件上传的结果
     */
    List<FastResult> uploadPicWithInfo(List<File> files, List<String> fileNames, List<Integer> heights, List<Integer> widths) throws MyException;

    /**
     * fastDfs批量上传文件
     *
     * @param files     文件
     * @param fileNames 文件名称
     * @return 每个文件上传的结果
     */
    List<FastResult> uploadFile(List<File> files, List<String> fileNames) throws MyException;

    /**
     * fast上传文件
     *
     * @param bytes            文件内容字节数组
     * @param fileExtName      文件扩展名
     * @param nameValuePairMap 文件相关的信息（文件名称、大小、需要存储的内容）
     */
    FastResult uploadFile(byte[] bytes, String fileExtName,
                          Map<String, String> nameValuePairMap);

    /**
     * fast上传文件
     *
     * @param bytes            文件内容字节数组
     * @param fileExtName      文件扩展名
     * @param nameValuePairMap 文件相关的信息（文件名称、大小、需要存储的内容）
     * @param ifZoom
     */
    FastResult uploadFile(byte[] bytes, String fileExtName,
                          Map<String, String> nameValuePairMap, boolean ifZoom);

    /**
     * 流上传
     *
     * @param is
     * @param realFileName
     * @param fileName
     * @param contentType
     * @return
     */
    FastResult upload(InputStream is, String realFileName, String fileName, String contentType);

    /**
     * 图片裁剪
     *
     * @param fileId 图片路径
     * @param x      x坐标
     * @param y      y坐标
     * @param width  宽
     * @param height 高
     * @return
     */
    FastResult uploadCut(String fileId, int x, int y, int width, int height) throws Exception;

    /**
     * 下载文件
     *
     * @param fileId
     * @return
     */
    LoadFile downFile(String fileId);

    /**
     * 获取文件名称
     *
     * @param loadFile
     * @return
     */
    String getFileName(LoadFile loadFile);

    /**
     * 把数据库的JSONSTR转化为FileListDTO文件列表格式
     * @param fileJsonArrayStr
     * @return
     */
    List<FileListDTO> getFileDTOList(String fileJsonArrayStr);

    /**
     * 把前端传入的JSONSTR解析宽高出来转化为FileListDTO文件列表格式
     * @param fileJsonArrayStr
     * @return
     */
    List<FileListDTO> getFileInfoList(String fileJsonArrayStr) throws ServiceException;

    /**
     * 获取文件信息
     * @param fileId
     * @return
     * @throws ServiceException
     */
    FileListDTO getFileInfo(String fileId) throws ServiceException ;

    /**
     * 压缩图片至指定宽高
     * @param fileId
     * @param width
     * @param height
     * @return
     * @throws ServiceException
     */
    void zoom(File file, String fileId, int width, int height) throws ServiceException ;

    /**
     * 分片上传
     * @param multipartFile
     * @return
     */
    boolean patchUpload(MultipartFile multipartFile);

    /**
     * 根据文件标识获取文件路径
     *
     * @param fileId
     * @return
     */
    String getFirstFilePath(String fileId);

    /**
     * 下载图片并上传到oos
     * @param appId
     * @param url
     * @param fileName
     * @param contentType
     * @return
     */
    public String downUrlAndUpLoad(String appId,String url,String fileName,String contentType);
}
