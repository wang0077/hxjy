/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.lcy.util.common.rsa;


import com.lcy.util.common.codec.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author runzhi
 */
public class RsaUtils {

    /** RSA最大加密明文大小  */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** RSA最大解密密文大小   */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    public static final String SIGN_TYPE        = "sign_type";

    public static final String SIGN_TYPE_RSA    = "RSA";

    public static final String SIGN_ALGORITHMS  = "SHA1WithRSA";


    /**
     * 
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (RsaStringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    public static String rsaSign(String content, String privateKey, String charset) throws Exception {
       
    
        PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA,
            new ByteArrayInputStream(privateKey.getBytes()));

        java.security.Signature signature = java.security.Signature
            .getInstance(SIGN_ALGORITHMS);

        signature.initSign(priKey);

        if (RsaStringUtils.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }

        byte[] signed = signature.sign();

        return new String(Base64.encodeBase64(signed));
      
    }

    public static String rsaSign(Map<String, String> params, String privateKey, String charset) throws Exception{
        String signContent = getSignContent(params);
        return rsaSign(signContent, privateKey, charset);

    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || RsaStringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = StreamUtil.readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static String getSignCheckContentV1(Map<String, String> params) {
        if (params == null) {
            return null;
        }

        params.remove("sign");
        params.remove("sign_type");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    public static String getSignCheckContentV2(Map<String, String> params) {
        if (params == null) {
            return null;
        }

        params.remove("sign");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    public static boolean rsaCheckV1(Map<String, String> params, String publicKey, String charset) throws Exception {
        String sign = params.get("sign");
        String content = getSignCheckContentV1(params);

        return rsaCheckContent(content, sign, publicKey, charset);
    }

    public static boolean rsaCheckV2(Map<String, String> params, String publicKey, String charset) throws Exception{
        String sign = params.get("sign");
        String content = getSignCheckContentV2(params);

        return rsaCheckContent(content, sign, publicKey, charset);
    }

    public static boolean rsaCheckContent(String content, String sign, String publicKey,
                                          String charset) throws Exception  {
       
	    PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey
	        .getBytes()));
	
	    java.security.Signature signature = java.security.Signature
	        .getInstance(SIGN_ALGORITHMS);
	
	    signature.initVerify(pubKey);
	
	    if (RsaStringUtils.isEmpty(charset)) {
	        signature.update(content.getBytes());
	    } else {
	        signature.update(content.getBytes(charset));
	    }
	
	    return signature.verify(Base64.decodeBase64(sign.getBytes()));
       
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     * 验签并解密
     * <p>
     * <b>目前适用于公众号</b><br>
     * params参数示例：
     * <br>{
     *    <br>biz_content=M0qGiGz+8kIpxe8aF4geWJdBn0aBTuJRQItLHo9R7o5JGhpic/MIUjvXo2BLB++BbkSq2OsJCEQFDZ0zK5AJYwvBgeRX30gvEj6eXqXRt16/IkB9HzAccEqKmRHrZJ7PjQWE0KfvDAHsJqFIeMvEYk1Zei2QkwSQPlso7K0oheo/iT+HYE8aTATnkqD/ByD9iNDtGg38pCa2xnnns63abKsKoV8h0DfHWgPH62urGY7Pye3r9FCOXA2Ykm8X4/Bl1bWFN/PFCEJHWe/HXj8KJKjWMO6ttsoV0xRGfeyUO8agu6t587Dl5ux5zD/s8Lbg5QXygaOwo3Fz1G8EqmGhi4+soEIQb8DBYanQOS3X+m46tVqBGMw8Oe+hsyIMpsjwF4HaPKMr37zpW3fe7xOMuimbZ0wq53YP/jhQv6XWodjT3mL0H5ACqcsSn727B5ztquzCPiwrqyjUHjJQQefFTzOse8snaWNQTUsQS7aLsHq0FveGpSBYORyA90qPdiTjXIkVP7mAiYiAIWW9pCEC7F3XtViKTZ8FRMM9ySicfuAlf3jtap6v2KPMtQv70X+hlmzO/IXB6W0Ep8DovkF5rB4r/BJYJLw/6AS0LZM9w5JfnAZhfGM2rKzpfNsgpOgEZS1WleG4I2hoQC0nxg9IcP0Hs+nWIPkEUcYNaiXqeBc=,
     *    <br>sign=rlqgA8O+RzHBVYLyHmrbODVSANWPXf3pSrr82OCO/bm3upZiXSYrX5fZr6UBmG6BZRAydEyTIguEW6VRuAKjnaO/sOiR9BsSrOdXbD5Rhos/Xt7/mGUWbTOt/F+3W0/XLuDNmuYg1yIC/6hzkg44kgtdSTsQbOC9gWM7ayB4J4c=,
     *    sign_type=RSA,
     *    <br>charset=UTF-8
     * <br>}
     * </p>
     * @param params
     * @param alipayPublicKey 支付宝公钥
     * @param cusPrivateKey   商户私钥
     * @param isCheckSign     是否验签
     * @param isDecrypt       是否解密
     * @return 解密后明文，验签失败则异常抛出
     * @throws Exception 
     * @throws MobileApiException 
     */
    public static String checkSignAndDecrypt(Map<String, String> params, String alipayPublicKey,
                                             String cusPrivateKey, boolean isCheckSign,
                                             boolean isDecrypt) throws Exception  {
        String charset = params.get("charset");
        String bizContent = params.get("biz_content");
        if (isCheckSign) {
           rsaCheckV2(params, alipayPublicKey, charset);
        }

        if (isDecrypt) {
            return rsaDecrypt(bizContent, cusPrivateKey, charset);
        }

        return bizContent;
    }


    /**
     * 公钥加密
     * 
     * @param content   待加密内容
     * @param publicKey 公钥
     * @param charset   字符集，如UTF-8, GBK, GB2312
     * @return 密文内容
     * @throws Exception 
     * @throws MobileApiException
     */
    public static String rsaEncrypt(String content, String publicKey, String charset) throws Exception {
       
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA,
                new ByteArrayInputStream(publicKey.getBytes()));
            Cipher cipher = Cipher.getInstance(SIGN_TYPE_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] data = RsaStringUtils.isEmpty(charset) ? content.getBytes() : content
                .getBytes(charset);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密  
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = Base64.encodeBase64(out.toByteArray());
            out.close();

            return RsaStringUtils.isEmpty(charset) ? new String(encryptedData) : new String(
                encryptedData, charset);
       
    }

    /**
     * 私钥解密
     * 
     * @param content    待解密内容
     * @param privateKey 私钥
     * @param charset    字符集，如UTF-8, GBK, GB2312
     * @return 明文内容
     * @throws Exception 
     * @throws MobileApiException
     */
    public static String rsaDecrypt(String content, String privateKey, String charset) throws Exception{
            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA,
                new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance(SIGN_TYPE_RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = RsaStringUtils.isEmpty(charset) ? Base64.decodeBase64(content
                .getBytes()) : Base64.decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密  
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return RsaStringUtils.isEmpty(charset) ? new String(decryptedData) : new String(
                decryptedData, charset);
       
    }
    
    public static void main(String[] args){
    
//			String testStr = RsaUtils.rsaEncrypt("aaaaasdfsadfsadf", MobileConstants.LOGIN_RSA_PUBLIC_KEY, "UTF-8");
//			System.out.println(RsaUtils.rsaDecrypt("NYSpsZyj5mj2acUT6gB0m8/rKfXonTBi3cTnt0sIcArZFkg+AHUNp8OpcX8B3KhKInuRRwvqdrKxoVrU4TS3A3o7KP+DSxgFVA8TGjfETyleWjU3wcvFMTbad7trvvXCaTvcgEeKfFDyt+iRQeCdqVdlapC10JdLuL5f45bdZ0F/KiKtc86s16qSY2hbnoWIzt08cVZXVCuiS8tYjoOtwrje6KdswdXFr/FbjUshoKGETgrdMv0qPxW7GvSp9hnSmz3wP/sdFd66uIjD2ZHJkYmdK+lxbU4XK38uGI7y99shm0blBcChQ56Vqku9mRD+1tfU84Bh4XEHy9+oIk+9/w==", MobileConstants.LOGIN_RSA_PRIVATE_KEY, "UTF-8"));
		
    }

}
