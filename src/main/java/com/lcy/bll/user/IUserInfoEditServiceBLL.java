package com.lcy.bll.user;


import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.FieldsParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.user.*;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserThirdInfoDTO;

/**
 * 用户编辑接口登陆接口
 * @author cjianyan@linewell.com
 * @since 2017-08-08
 *
 */
public interface IUserInfoEditServiceBLL {

	
	/**
	 * 获取微信第三方登陆信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO getWxThridInfo(IDParams params)throws ServiceException;
	
	/**
	 * sessionKey涉及的比较的保密数据，不能返回给前端
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfo getUserThridInfoById(IDParams params)throws ServiceException;
	
	/**
	 * 更新sessionKey和unionid
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateCodeResult(String id, String sessionKey, String unionid) throws ServiceException;
	
	/**
	 * 获取微信小程序第三方登陆信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO getMiniThridInfo(IDParams params)throws ServiceException;
	
	/**
	 * 根据主键获取第三方登录信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO getThirdInfoById(IDParams params)throws ServiceException;
	
	/**
	 * 获取或者保存第三方资料
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO saveUserThirdInfo(UserThirdInfoParams params) throws ServiceException;
	
	/**
	 * 获取用户微信第三方登陆信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO getUserThridInfo(UserThirdTypeParams params)throws ServiceException;
	
	/**
	 * 用户绑定第三方信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO bindUserThirdInfo(UserThirdInfoParams params) throws ServiceException;
	
	/**
	 * 设置密码
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public boolean setPwd(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 保存用户信息
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO save(UserParams userParams) throws ServiceException;
	
	/**
	 * 保存用户信息(第三方登录用)
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO saveByThird(UserParams userParams) throws ServiceException;

	boolean updateImportSource(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 修改所在省市县
	 * @param userParams
	 * @return
	 * @throws ServiceException
	 */
	public boolean changeProvinceAndCity(UserParams userParams) throws ServiceException;
	
	/**
	 * 用户注册
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO userRegister(RegisterParams registerParams)  throws ServiceException;
	
	/**
	 * 更改密码
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changePassword(ChangePwdParams changePwdParams)  throws ServiceException;
	
	/**
	 * 更改手机号
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changePhone(PhoneParams phoneParams)  throws ServiceException;
	
	/**
	 * 验证手机是否存在
	 * @param phoneParams
	 * @return
	 * @throws ServiceException
	 */
	public boolean exsitPhone(PhoneParams phoneParams)
			throws ServiceException;
	
	/**
	 * 通过手机号获取用户对象
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO getUserByPhone(PhoneParams phoneParams) throws ServiceException;
	
	/**
	 * 重置密码
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean resetPassword(ResetPasswordParams resetPasswordParams) throws ServiceException;
	
	/**
	 * 通过用户标识获取对象
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO getUserById(IDParams idParams) throws ServiceException;
	
	/**
	 * 通过昵称获取用户
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO getUserByNickname(FieldsParams fieldParams) throws ServiceException;
	
	/**
	 * 修改用户昵称
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeNickname(EditFieldValueParams editFieldValueParams) throws ServiceException;

	/**
	 * 修改用户昵称
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeName(EditFieldValueParams editFieldValueParams) throws ServiceException;

	public Boolean changeLabel(EditFieldValueParams editFieldValueParams) throws ServiceException;

	public Boolean updateBgPhotoId(EditFieldValueParams editFieldValueParams) throws ServiceException;

	public Boolean changeLabelBatch(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 修改用户电话号码
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeTelephone(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 修改用户电子邮箱
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeEmail(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	
	/**
	 * 修改用户邮政编码
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeZipCode(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 修改用户详细地址
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeAddressDetail(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	
	/**
	 * 修改个人资料
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeIntroduction(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	
	/**
	 * 修改性别
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changeGender(EditFieldValueParams editFieldValueParams) throws ServiceException;
	
	/**
	 * 重置密码
	 * @param fieldParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean changePhoto(EditFieldValueParams fieldsParams) throws ServiceException;
	
	/**
	 * 启用账户
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean enable(IDParams idParams) throws ServiceException;
	
	
	/**
	 * 禁用账户
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Boolean disable(IDParams idParams) throws ServiceException;	
	
	
	/**
	 * 获取用户类别
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public PageResult<UserBaseDTO> getList(UserListParams params) throws ServiceException;
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws ServiceException
	 */
	public UserBaseDTO update(UserParams userParams) throws ServiceException;
	
	/**
	 * 统计用户数量
	 * @param params	过滤参数
	 * @return
	 * @throws ServiceException
	 */
	public long countUsers(UserCountParams params) throws ServiceException;

    /**
     * 获取用户数量
     *
     * @return
     */
    Integer countUser();

    /**
     * 获取实名认证用户数量
     *
     * @return
     */
    Integer countIdentifyUser();
    /**
     * 
     * 前端用户异步保存或更新户籍所在地信息
     * 
     * @param userParams
     * @return
     */
	public Boolean updateDetailedAddress(UserParams userParams);
	/**
     * 
     * h5保存用户昵称、籍贯、详细地址3信息
     * 
     * @param userParams
     * @return
     */
	public Boolean updateNamePlaceAddress(UserParams userParams);

	/**
	 * 根据unionId获取第三方用户信息
	 * @param unionId
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfo getUserThirdInfoByUnionId(String unionId, String appId)throws ServiceException;

	/**
	 * 获取用户微信第三方登陆信息
	 * @param miniId
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfoDTO getUserThirdInfo(String miniId, String userId, String appId)throws ServiceException;

	/**
	 * 获取用户微信第三方登陆信息
	 * @param miniId
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public UserThirdInfo getUserThirdInfoBean(String miniId, String userId, String appId)throws ServiceException;

	/**
	 * 更新第三方用户信息
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateUserThirdInfo(UserThirdInfoParams params) throws ServiceException;
}
