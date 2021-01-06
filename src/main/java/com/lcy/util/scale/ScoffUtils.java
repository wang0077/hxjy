package com.lcy.util.scale;

import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.util.common.GsonUtils;
import org.modelmapper.TypeToken;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScoffUtils {

	// 配置目录类型
	private static final String FILE_NAME = "config/scale/scoff.json";
	
	// 获取单实例对
	private static ScoffUtils instance = new ScoffUtils();

	private static List<ProblemMiniDTO> PROBLEM_MINI_DTO_LIST;
 
	/**
	 * 私有构
	 */
	private ScoffUtils(){
		this.initConfigs();
	}
	
	/**
	 * 初始化配置
	 */
	private void initConfigs(){
		URL url = null;
		String path = null;
		
		try{
			
			path = System.getProperty("user.dir") + File.separator + FILE_NAME;
			File file = new File(path);

			if (!file.exists()) {
				url = ScoffUtils.class.getClassLoader().getResource(
						FILE_NAME);
				path = url.getPath();
				file = new File(path);
			}

			StringBuffer laststr = new StringBuffer();
			BufferedReader reader = null;
			try {
				FileInputStream in = new FileInputStream(file);
				reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));// 读取文件
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					laststr = laststr.append(tempString);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException el) {
					}
				}
			}

			PROBLEM_MINI_DTO_LIST = GsonUtils.jsonToBean(laststr.toString(), new TypeToken<List<ProblemMiniDTO>>(){}.getType());

		}catch(Exception e){
			System.out.println(FILE_NAME+" 文件不存在");
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * @return 单实例对象
	 */
	public static ScoffUtils getInstance(){
		return instance;
	}

	public static List<ProblemMiniDTO> getProblemList(){
		return PROBLEM_MINI_DTO_LIST;
	}
}
