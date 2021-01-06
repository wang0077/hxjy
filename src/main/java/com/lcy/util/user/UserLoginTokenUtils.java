package com.lcy.util.user;

import com.lcy.bll.user.IUserInfoEditServiceBLL;
import com.lcy.contant.UserLoginConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.IDParams;
import com.lcy.type.user.AccountStatusType;
import com.lcy.type.user.UserCodeType;
import com.lcy.util.common.rsa.RsaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录的Token的工具类
 * @author xhuatang
 * @since 2016年5月9日
 */
@Component
public class UserLoginTokenUtils {
	
	@Autowired
	static IUserInfoEditServiceBLL userInfoEditServiceBLL;
	
    
    @Autowired
    private IUserInfoEditServiceBLL userService2;
    
    @PostConstruct
    public void beforeInit() {
    	userInfoEditServiceBLL = userService2;
    }
	
	/**
	 * 登录token的key
	 */
	public static final String DEVICEID = "deviceId";
	
	/**
	 * 登录密码的key
	 */
	public static final String PHONE = "phone";
	
	/**
	 * 访问终端，android ios h5 pc
	 */
	public static final String OSTYPE = "ostype";
	
	/**
	 * 用户标识的key
	 */
	public static final String USERUNID_KEY = "userId";

	/**
	 * 构建Token的信息
	 * @param token      原始的token
	 * @param username   用户名
	 * @param password   密码
	 * @param deviceId   设备标识
	 * @param systemType 系统类型
	 * @param userUnid   用户的标识
	 * @return           加密后的token信息
	 * @throws Exception 
	 */
	public static String buildToken(String userId,String phone,long loginTime,String deviceId,String ostype) throws Exception{
		
		String encryptContent = userId + UserLoginConstants.SPLIT_CHAR + phone + UserLoginConstants.SPLIT_CHAR
				+ loginTime + UserLoginConstants.SPLIT_CHAR + deviceId + UserLoginConstants.SPLIT_CHAR + ostype;
		
		String ecncryptedToken = RsaUtils.rsaEncrypt(encryptContent, UserLoginConstants.LOGIN_RSA_PUBLIC_KEY, UserLoginConstants.DEFAULT_CHARSET);
			
		return ecncryptedToken;
		
	}
	
	
	/**
	 * 用户用户标识
	 * @param ecncryptedToken
	 * @param deviceId
	 * @param ostype
	 * @return
	 * @throws Exception
	 */
	public static String getUserId(String ecncryptedToken,String deviceId,String ostype) throws ServiceException {
		
		Map<String, String> map;
		try {
			map = getTokenInfo(ecncryptedToken, deviceId, ostype);
			return map == null?null:map.get(USERUNID_KEY);
		} catch (Exception e) {
			System.err.println("【无效token】：" + deviceId + "---" + ostype + "---" + ecncryptedToken); // add by llj 打印token，跟踪bug
			if(e instanceof ServiceException){
				throw (ServiceException)e;
			}
		}
		
		return null;
	
		
	}
	
	/**
	 * 获取用户信息
	 * @param baseParams
	 * @return
	 */
	public static String getUserId(BaseParams baseParams){
		
		if(baseParams.getAuthParams() == null || StringUtils.isEmpty(baseParams.getAuthParams().getToken())){
			return null;
		}
		
		if(baseParams.getClientParams() == null){
			return null;
		}
		
		return getUserId(baseParams.getAuthParams().getToken(), baseParams.getClientParams().getDeviceId(), baseParams.getClientParams().getOs());
	}
	
	/**
	 * 获取token的信息
	 * @param ecncryptedToken 加密过的token
	 * @param deviceId        设备的标识
	 * @return                token的信息
	 * @throws Exception 
	 */
	public static Map<String, String> getTokenInfo(String ecncryptedToken,String deviceId,String ostype) throws Exception{
	
		if(StringUtils.isEmpty(ecncryptedToken)){
			return null;
		}
		
			// 解包rsa的密钥
			String rsaConetnt = RsaUtils.rsaDecrypt(ecncryptedToken, 
					UserLoginConstants.LOGIN_RSA_PRIVATE_KEY, UserLoginConstants.DEFAULT_CHARSET);
			
			if(StringUtils.isEmpty(rsaConetnt)){
				return null;
			}
			
			String[] rsaContentArry = rsaConetnt.split(UserLoginConstants.SPLIT_CHAR);
			
			// 判断是否包含必要的信息
			if(rsaContentArry.length < 5){
				return null;
			}
			
			// 2018-6-12 lyx modify 去掉deviceId的验证
//			if(!rsaContentArry[3].equals(deviceId)){
//				return null;
//			}
			
			if(!rsaContentArry[4].equals(ostype)){
				return null;
			}
			
			Map<String, String> loginMap = new HashMap<String, String>();
			loginMap.put(USERUNID_KEY, rsaContentArry[0]);
			loginMap.put(PHONE, rsaContentArry[1]);
			loginMap.put(DEVICEID, rsaContentArry[3]);
			loginMap.put(OSTYPE, rsaContentArry[4]);
			

			//验证user是否被锁定
			IDParams idParams = new IDParams();
			idParams.setId(rsaContentArry[0]);
			UserBaseDTO user = userInfoEditServiceBLL.getUserById(idParams);
			
			if(user==null){
				System.err.println("【无效用户】：" + rsaContentArry[0] ); // add by lyx 打印userid，跟踪bug
				throw new ServiceException(UserCodeType.GET_USER_EXCEPTION.getName(), UserCodeType.GET_USER_EXCEPTION.getNo());
				
			}
			
			if(user!=null&&user.getAccountStatus()==AccountStatusType.DISABLE.getCode()){
				//账户不被锁定,返回用户信息
				throw new ServiceException(UserCodeType.USER_DISABLE.getName(), UserCodeType.USER_DISABLE.getNo());
			}
			
			return loginMap;
	
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(buildToken("0012cf6f676e44b59c04d1234eb7cca5", "19959593000", System.currentTimeMillis(), "Test", "Test"));
//	String end=	RsaUtils.rsaEncrypt("23412342134523452345", LoginConstants.LOGIN_RSA_PUBLIC_KEY, LoginConstants.DEFAULT_CHARSET);
//	System.out.println(end);
//	System.out.println(RsaUtils.rsaDecrypt(end, LoginConstants.LOGIN_RSA_PRIVATE_KEY, LoginConstants.DEFAULT_CHARSET));
	}
}
