package com.lcy.util.file.oss;

import com.aliyun.oss.model.ObjectMetadata;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lcy.controller.common.ServiceException;
import com.lcy.util.file.fast.*;
import com.lcy.dto.common.FileListDTO;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.file.fast.org.source.MyException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * oss文件系统
 * @author zjc
 *
 */
public class OssFileSystem implements IFileSystem {

	@Override
	public String getFilePathById(String fileId) {
		
		return AliOSSUtils.getInstance().getFilePath(fileId);
	}

	@Override
	public List<FastResult> uploadFile(List<File> files, List<String> fileNames) throws MyException {
        List<FastResult> fastResults = null;

        // 文件校验
        boolean flag = FastDfsUtils.validateFile(files, fileNames, PictureUtils.CONTRIBUTION_FILE_LENGTH,
                PictureUtils.FILE_EXTS, true, FastDfsUtils.COMMON_FILE_TYPE_CN);

        // 上传
        if (flag) {
            fastResults = AliOSSUtils.getInstance().uploadFile(files, fileNames);
        }

        return fastResults;
	}

	@Override
	public List<FastResult> uploadPic(List<File> files, List<String> fileNames) throws MyException {

        List<FastResult> fastResults = null;

        // 文件校验
        boolean flag = FastDfsUtils.validateFile(files, fileNames, PictureUtils.PICTURE_MAX_SIZE,
                PictureUtils.PICTURE_EXTS, true, FastDfsUtils.PICTURE_FILE_TYPE_CN);

        // 上传
        if (flag) {
            fastResults = AliOSSUtils.getInstance().uploadFile(files, fileNames);
        }

		return fastResults;
	}

	@Override
	public List<FastResult> uploadPicWithInfo(List<File> files, List<String> fileNames, List<Integer> heights, List<Integer> widths) throws MyException {

        List<FastResult> fastResults = null;

		// 文件校验
		boolean flag = FastDfsUtils.validateFile(files, fileNames, PictureUtils.PICTURE_MAX_SIZE,
				PictureUtils.PICTURE_EXTS, true, FastDfsUtils.PICTURE_FILE_TYPE_CN);

        // 上传
        if (flag) {
            fastResults = AliOSSUtils.getInstance().uploadFile(files, fileNames);
        }

        return fastResults;
	}

	@Override
	public FastResult uploadFile(byte[] bytes, String fileExtName, Map<String, String> nameValuePairMap) {

		InputStream is = ByteToInputStream.byte2Input(bytes);

		FastResult fastResult = AliOSSUtils.getInstance().uploadFile(is, fileExtName, nameValuePairMap);

		return fastResult;
	}

    @Override
    public FastResult uploadFile(byte[] bytes, String fileExtName, Map<String, String> nameValuePairMap, boolean ifZoom) {

        InputStream is = ByteToInputStream.byte2Input(bytes);

        FastResult fastResult = AliOSSUtils.getInstance().uploadFile(is, fileExtName, nameValuePairMap);

        return fastResult;
    }

    @Override
	public FastResult upload(InputStream is, String fileId, String fileName, String contentType) {

		String size = null;
		try {
			size = String.valueOf(is.available());
		} catch (IOException e) {
			e.printStackTrace();
		}

	    // OSS上传
		AliOSSUtils.getInstance().upload(is, fileId, fileName, contentType);

		//数据拼装
        FastResult fastResult = new FastResult();
		fastResult.setFileId(fileId);
		fastResult.setFileName(fileName);
		fastResult.setFilePath(getFilePathById(fileId));
		fastResult.setSize(size);

		return fastResult;
	}

	@Override
	public FastResult uploadCut(String fileId, int x, int y, int width, int height) throws Exception {
		return null;
	}

	@Override
	public LoadFile downFile(String fileId) {


		AliOSSUtils instance = AliOSSUtils.getInstance();
		//从阿里下载文件下来
		InputStream inputStream = AliOSSUtils.getInstance().download(fileId);

		//获取图片信息
		FileListDTO fileDTO = getFileInfo(fileId);

		String fileName = "";
		String height = "";
		String width = "";

		if(fileDTO != null){
			//图片信息
			fileName = fileDTO.getFileName();
			height = String.valueOf(fileDTO.getHeight());
			width = String.valueOf(fileDTO.getWidth());
		}else{
			//获取文件元信息
			ObjectMetadata obj = instance.getObjectMetadata(fileId);
			fileName = obj.getUserMetadata().get("filename");
		}

		LoadFile dto = new LoadFile();
		//拼接参数
		Map<String, String> nameValuePairMap = new HashMap<String, String>();
		nameValuePairMap.put(AliOSSUtils.FILE_NAME,fileName);
		nameValuePairMap.put(AliOSSUtils.HEIGHT, height);
		nameValuePairMap.put(AliOSSUtils.WIDTH,width);

		//组装LoadFile
		try {
			dto.setBytes(ByteToInputStream.input2byte(inputStream));
			dto.setNameValuePairMap(nameValuePairMap);
			dto.setFlag(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public String getFileName(LoadFile loadFile) {

        Map<String, String> nameValuePairMap = loadFile.getNameValuePairMap();
        if (nameValuePairMap != null && nameValuePairMap.size() > 0) {
            String fileName = nameValuePairMap.get(AliOSSUtils.FILE_NAME);
            if (StringUtils.isNotEmpty(fileName)) {
                return fileName;
            }
            String fileExtName = nameValuePairMap.get(AliOSSUtils.FILE_EXT_NAME);
            if (StringUtils.isNotEmpty(fileExtName)) {
                return System.currentTimeMillis() + "." + fileExtName;
            }
        }
        return String.valueOf(System.currentTimeMillis());
	}

	@Override
	public List<FileListDTO> getFileDTOList(String fileJsonArrayStr) {
		if (StringUtils.isEmpty(fileJsonArrayStr)
				|| "[]".equals(fileJsonArrayStr)) {
			return null;
		}

		List<FileListDTO> fileDTOList = GsonUtils.jsonToBean(fileJsonArrayStr,
				new TypeToken<List<FileListDTO>>() {
				}.getType());
		for (FileListDTO fileDTO : fileDTOList) {
			fileDTO.setFilePath(getFilePathById(fileDTO.getFileId()));
		}

		return fileDTOList;
	}

	/**
	 * 图像信息对象
	 */
	private class ImageInfoObject {
		private String value;

		public String getValue() {
			return value;
		}

		@SuppressWarnings("unused")
		public void setValue(String value) {
			this.value = value;
		}
	}

	@Override
	public FileListDTO getFileInfo(String fileId)
			throws ServiceException {

		if (StringUtils.isEmpty(fileId)) {
			return null;
		}

		int idx = fileId.indexOf("?x-oss-process");
		if (idx > 0) {
			fileId = fileId.substring(0, idx);
		}

		String filePath = AliOSSConfigUtils.getInstance().getUploadHost() + "/"
				+ fileId + "?x-oss-process=image/info";

		// 下载图片并上传到oss start
		URL url = null;
		URLConnection con = null;
		InputStream is = null;
		BufferedReader reader = null;

		try {
			// 构造URL
			url = new URL(filePath);
			// 打开连接
			con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			is = con.getInputStream();

			StringBuffer jsonStr = new StringBuffer();
			String line = null;
			if (is != null) {
				reader = new BufferedReader(new InputStreamReader(is));
				while ((line = reader.readLine()) != null) {
					jsonStr.append(line);
				}
			}
			if (StringUtils.isEmpty(jsonStr.toString())) {
				return null;
			}

			Gson gson = new Gson();

			Map<String, ImageInfoObject> imageInfos = gson.fromJson(
					jsonStr.toString(),
					new TypeToken<Map<String, ImageInfoObject>>() {
					}.getType());

			FileListDTO targetInfo = new FileListDTO();

			// 这里值返回有用到的属性
			String tempStr = imageInfos.get("FileSize").getValue();
			targetInfo.setSize(Long.valueOf(tempStr));
			tempStr = imageInfos.get("ImageWidth").getValue();
			targetInfo.setWidth(Integer.parseInt(tempStr));
			tempStr = imageInfos.get("ImageHeight").getValue();
			targetInfo.setHeight(Integer.parseInt(tempStr));
			targetInfo.setFormat(imageInfos.get("Format").getValue());
			return targetInfo;

		} catch (Exception e) {
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		} // end
	}

	@Override
	public void zoom(File file, String fileId, int width, int height) throws ServiceException {

		if(StringUtils.isEmpty(fileId)){
			throw new ServiceException("文件标识为空");
		}
		String zoomFileId = fileId + "?x-oss-process=image/resize,limit_0,m_fill,w_" + width + ",h_" + height;
		String zommFilePath = this.getFilePathById(zoomFileId);
		URL url = null;
		HttpURLConnection httpUrl = null;
		InputStream inputStream = null;
		try {
			url = new URL(zommFilePath);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			inputStream = httpUrl.getInputStream();
			FileUtils.copyInputStreamToFile(inputStream, file);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean patchUpload(MultipartFile multipartFile) {
		return false;
	}

	@Override
	public String getFirstFilePath(String fileId) {

		if (StringUtils.isEmpty(fileId) || "[]".equals(fileId)) {
			return StringUtils.EMPTY;
		}

		List<FileListDTO> fileList = null;
		try {
			fileList = GsonUtils.jsonToBean(fileId,
					new TypeToken<List<FileListDTO>>() {
					}.getType());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if (fileList == null || fileList.size() < 1) {
			return StringUtils.EMPTY;
		}

		return this.getFilePathById(fileList.get(0).getFileId());
	}

	@Override
	public String downUrlAndUpLoad(String appId, String url, String fileName, String contentType) {

		InputStream is = null;
		HttpURLConnection conn = null;
		URL urlfile = null;

		try {

			urlfile = new URL(url);
			conn = (HttpURLConnection) urlfile.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6 * 1000);

			// 根据文件创建文件的输入流
			is = conn.getInputStream();
			upload(is, fileName, fileName, contentType);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != conn) {
				conn.disconnect();
			}
		}

		return null;
	}

	@Override
	public List<FileListDTO> getFileInfoList(String fileJsonArrayStr) throws ServiceException {
		if (StringUtils.isEmpty(fileJsonArrayStr)
				|| "[]".equals(fileJsonArrayStr)) {
			return null;
		}

		FileListDTO dto = null;
		List<FileListDTO> fileInfoList = new ArrayList<FileListDTO>();
		List<FileListDTO> idList = GsonUtils.jsonToBean(fileJsonArrayStr,
				new TypeToken<List<FileListDTO>>() {
				}.getType());
		for (FileListDTO idDTO : idList) {
			dto = this.getFileInfo(idDTO.getFileId());
			if (dto != null) {
				dto.setFileId(idDTO.getFileId());
				dto.setFilePath(getFilePathById(idDTO.getFileId()));
				fileInfoList.add(dto);
			} else {
				fileInfoList.add(idDTO);
			}
		}

		return fileInfoList;
	}
}
