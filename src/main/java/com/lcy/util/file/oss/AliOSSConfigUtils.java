package com.lcy.util.file.oss;

import com.lcy.util.common.ProjectPathUtils;
import com.lcy.util.common.WebInfoPathUtils;
import jodd.props.Props;

import java.io.File;
import java.io.IOException;

/**
 * 读取配置文件工具
 * 
 * @author tjianqun@linewell.com 2016年10月14日
 */
public class AliOSSConfigUtils {

	// 文件名
	private static final String FILE_NAME = "oss.properties";

	// 配置目录
	private static final String FOLDER_CONFIG = "config";

	// 配置目录类型
	private static final String FOLDER_PROPS = "filesystem";

	// 获取单实例对象
	private static AliOSSConfigUtils instance = new AliOSSConfigUtils();

	// 配置文件的信息
	private Props configProps;

	/**
	 * 私有构造
	 */
	private AliOSSConfigUtils() {
		this.initConfigs();
	}

	/**
	 * 初始化配置
	 */
	private void initConfigs() {
		
		this.configProps = new Props();
//		String path = WebInfoPathUtils.getWebInfPath();
//		String propsPath = path + File.separator + FOLDER_CONFIG + File.separator + FOLDER_PROPS + File.separator
//				+ FILE_NAME;

		String propsPath = ProjectPathUtils.getPath(ProjectPathUtils.CONFIG_FOLDER)
				+ File.separator + "filesystem" + File.separator + "oss.properties";

		System.out.println("propsPath : "+ propsPath);

		File propsFile = new File(propsPath);
		if (!propsFile.exists()) {
			
			// 单元测试的是，请将配置文件写到web对应位置
			String fileName = AliOSSConfigUtils.class.getClassLoader().getResource("").getPath() +  File.separator + FOLDER_PROPS + File.separator + FILE_NAME;

			System.out.println("fileName："+fileName);
			propsFile = new File(fileName);
		}
		if (propsFile.exists() && propsFile.isFile()) {
			try {
				this.configProps.load(propsFile);
			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			System.out.println(FILE_NAME + "配置文件不存在！");
		}
	}

	/**
	 * 获取单实例对象
	 * 
	 * @return 单实例对象
	 */
	public static AliOSSConfigUtils getInstance() {
		return instance;
	}

	/*
	 * 获取主机地址
	 */
	public String getCommonHost() {
		return configProps.getValue("commonHost");
	}

	/**
	 * 获取上传图片地址
	 * 
	 * @return
	 */
	public String getUploadHost() {
		return configProps.getValue("uploadHost");
	}

	/**
	 * 获取大文件上传地址
	 * 
	 * @return
	 */
	public String getBigFileUploadHost() {
		return configProps.getValue("bigFileUploadHost");
	}

	/**
	 * 获取图片服务地址
	 * 
	 * @return
	 */
	public String getImgServiceHost() {
		return configProps.getValue("imgServiceHost");
	}

	/***
	 * 获取AccessKeyId
	 * 
	 * @return
	 */
	public String getAccessKeyId() {
		return configProps.getValue("accessKeyId");
	}

	/**
	 * 获取AccessKeySecret
	 * 
	 * @return
	 */
	public String getAccessKeySecret() {
		return configProps.getValue("accessKeySecret");
	}

	/**
	 * 获取数据存取区域
	 * 
	 * @return
	 */
	public String getBucketName() {
		return configProps.getValue("bucketName");
	}

	/**
	 * 获取超时时间
	 * 
	 * @return
	 */
	public long getexEireTime() {
		return Long.parseLong(configProps.getValue("expireTime"));
	}

	/**
	 * 常规文件夹
	 * 
	 * @return
	 */
	public String getCommonDir() {
		return configProps.getValue("commondir");
	}

	/**
	 * 获取其他参数
	 * 
	 * @param key
	 * @return
	 */
	public String getOtherParams(String key) {
		return configProps.getValue(key);
	}

	/*
	 * public static void main(String[] args) throws FileNotFoundException {
	 * FileInputStream is = new FileInputStream(new
	 * File("C:\\Users\\wds\\Downloads\\测试2.jpg"));
	 * System.out.println(AliOSSUtils.getInstance().upload(is,
	 * "test-tujianqun.jpg", "image/jpeg")); }
	 */
}
