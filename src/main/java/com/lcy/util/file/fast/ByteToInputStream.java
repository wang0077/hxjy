package com.lcy.util.file.fast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节转流
 * @author cjianyan@linewell.com
 * @since 2014-7-30
 *
 */
public class ByteToInputStream {

	/**
	 * 字节转字节流
	 * @param buf	字节数组
	 * @return		返回字节流对象
	 */
	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	/**
	 * 流转字节数组
	 * @param inStream		流对象
	 * @return
	 * @throws IOException		字节数组
	 */
	public static final byte[] input2byte(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		inStream.close();
		swapStream.close();
		return in2b;
	}
	
}
