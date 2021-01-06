package com.lcy.util.user;


import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserIdentityBaseDetailDTO;
import com.lcy.dto.user.UserIdentityBaseListDTO;

import java.util.Map;

/**
 * 用户对象转换
 * @author cjianyan@linewell.com
 * @since 2017-08-02
 *
 */
public class UserTransDTOUtils {
	
	
	/**
	 * 获取用户标识
	 * @param userTokenStr
	 * @param deviceId
	 * @param osType
	 * @return
	 */
	public static String getUserIdByUserToken(String userTokenStr,String deviceId,String osType){
		
		try {
			
			Map<String, String> map = UserLoginTokenUtils.getTokenInfo(userTokenStr, deviceId, userTokenStr);						
			return  map.get(UserLoginTokenUtils.USERUNID_KEY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 用户登陆信息传输对象转换
	 * @param user
	 * @return
	 */
	public static LoginResultDTO getLoginResultDTO(UserBaseDTO userBaseDTO, String deviceId, String ostype){
		
		LoginResultDTO dto = null;
		
		if(userBaseDTO != null) {
			
			dto = new LoginResultDTO();
			
			dto.setNickname(userBaseDTO.getNickname());
			dto.setPhone(userBaseDTO.getPhone());
			dto.setUserId(userBaseDTO.getId());
			dto.setUserHeaderPicUrl(userBaseDTO.getPhotoId());
			dto.setDefaultPwd(userBaseDTO.getDefaultPwd());
			dto.setUserType(userBaseDTO.getUserType());
			
			try {
				dto.setUserTokenId(UserLoginTokenUtils.buildToken(userBaseDTO.getId(), userBaseDTO.getPhone(), System.currentTimeMillis(), deviceId, ostype));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	/**
	 * 统一设置用户身份(详情)
	 * @param userBaseDTO 用户基础dto
	 * @param identityDTO 业务dto
	 */
	public static void setUserIdentityDetail(UserBaseDTO userBaseDTO, UserIdentityBaseDetailDTO identityDTO) {
		
		identityDTO.setOfficialType(userBaseDTO.getOfficialType());
	}
	
	/**
	 * 统一设置用户身份(列表)
	 * @param userBaseDTO 用户基础dto
	 * @param identityDTO 业务dto
	 */
	public static void setUserIdentityList(UserBaseDTO userBaseDTO, UserIdentityBaseListDTO identityDTO) {
		
		identityDTO.setOfficialType(userBaseDTO.getOfficialType());
	}
}
