package com.lcy.controller.user.impl;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.api.common.SmsRuleAPI;
import com.lcy.api.manage.ManageLoginApi;
import com.lcy.api.user.UserApi;
import com.lcy.api.user.UserLoginApi;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.contant.ThirdPartyExtranetContants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.controller.user.IUserRestService;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.user.LoginResultDTO;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.user.*;
import com.lcy.type.common.BooleanType;
import com.lcy.type.user.AuthRoleTypeEnum;
import com.lcy.util.excel.ExportUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import com.lcy.util.manage.OperationLoginUtils;
import com.lcy.util.user.UserLoginTokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserRestService extends BaseController implements
		IUserRestService {

	@Autowired
	private UserApi userAPI;
	
	@Autowired
	UserLoginApi userLoginApi ;

	@Autowired
	private SmsRuleAPI smsRuleAPI;

//	@Autowired
//	AliOssApi aliOssApi;

//	@Autowired
//	ImApi imAPI;
//
//	@Autowired
//	UserAuthAPI userAuthAPI;
//
//	@Autowired
//	EnterpriseAuthAPI enterpriseAuthAPI;
//
//	@Autowired
//	IUserAuthenticationService userAuthDAO;
//
//	@Autowired
//	IEnterpriseAuthenticationService enterAuthDAO;
	
	@Autowired
	IUserService userDAO;
	
	@Autowired
	ManageLoginApi loginApi;
	
	private static ModelMapper modelMapper = new ModelMapper();

	@RequestMapping("/changePassword")
	@ResponseBody
	public ResponseResult<Boolean> changePassword(
			@RequestBody ChangePwdParams changePwdParams) {

		String userId = UserLoginTokenUtils.getUserId(changePwdParams
				.getAuthParams().getToken(), changePwdParams.getClientParams()
				.getDeviceId(), changePwdParams.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		changePwdParams.setId(userId);

		if (StringUtils.isEmpty(changePwdParams.getNewPwd())
				|| StringUtils.isEmpty(changePwdParams.getNewPwd())) {
			return unloginInvalid();
		}

		return renderSuccess(userAPI.changePassword(changePwdParams));
	}

	@RequestMapping("/validatePhone")
	@ResponseBody
	public ResponseResult<Boolean> validatePhone(
			@RequestBody PhoneParams phoneParams) {

		if (StringUtils.isEmpty(phoneParams.getPhone())) {
			return renderInvalidArgument();
		}

		// 获取用户信息
		boolean flag = userAPI.exsitPhone(phoneParams);

		// 存在返回空
		return renderSuccess(flag);

	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public ResponseResult<Boolean> resetPassword(
			@RequestBody ResetPasswordParams resetPasswordParams) {

		if (StringUtils.isEmpty(resetPasswordParams.getPhone())) {
			return renderInvalidArgument();
		}

		if (StringUtils.isEmpty(resetPasswordParams.getNewPwd())) {
			return renderInvalidArgument();
		}

		if (StringUtils.isEmpty(resetPasswordParams.getVertifyCode())) {
			return renderInvalidArgument();
		}

		boolean flag = SmsRuleAPI.validateCode(resetPasswordParams.getPhone(),
				resetPasswordParams.getVertifyCode());

		if (!flag) {
			return this.renderError("验证码出错，请重新输入");
		}

		return renderSuccess(userAPI.resetPassword(resetPasswordParams));

	}

	@RequestMapping("/changeNickname")
	@ResponseBody
	public ResponseResult<Boolean> changeNickname(
			@RequestBody EditFieldValueParams editFieldValueParams) {

		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams
				.getAuthParams().getToken(), editFieldValueParams
				.getClientParams().getDeviceId(), editFieldValueParams
				.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeNickname(editFieldValueParams));

	}

	@Autowired
	OperationLoginUtils loginUtils;

	@Override
	@RequestMapping("/changeName")
	@ResponseBody
	public ResponseResult<Boolean> changeName(@RequestBody EditFieldValueParams params) {

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (org.apache.commons.lang3.StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		if (StringUtils.isEmpty(params.getId())) {
			return unloginInvalid();
		}

		if (StringUtils.isEmpty(params.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeName(params));
	}

	@Override
	@RequestMapping("/changeLabel")
	@ResponseBody
	public ResponseResult<Boolean> changeLabel(@RequestBody EditFieldValueParams params) {

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (org.apache.commons.lang3.StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		if (StringUtils.isEmpty(params.getId())) {
			return unloginInvalid();
		}

		if (params.getValue() == null) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeLabel(params));
	}

	@Override
	@RequestMapping("/updateBgPhotoId")
	@ResponseBody
	public ResponseResult<Boolean> updateBgPhotoId(@RequestBody EditFieldValueParams params) {

		String userId = UserLoginTokenUtils.getUserId(params
				.getAuthParams().getToken(), params.getClientParams()
				.getDeviceId(), params.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}
		params.setId(userId);
		if (StringUtils.isEmpty(params.getId())) {
			return unloginInvalid();
		}

		if (params.getValue() == null) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.updateBgPhotoId(params));
	}

	@Override
	@RequestMapping("/changeLabelBatch")
	@ResponseBody
	public ResponseResult<Boolean> changeLabelBatch(@RequestBody EditFieldValueParams params) {

		String operatorId = loginUtils.getOperatorId(params.getAppId(), params.getAuthParams().getToken());
		if (org.apache.commons.lang3.StringUtils.isEmpty(operatorId)) {
			return unloginInvalid();
		}
		if (StringUtils.isEmpty(params.getId())) {
			return unloginInvalid();
		}

		if (params.getValue() == null) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeLabelBatch(params));
	}

	@RequestMapping("/changePhoto")
	@ResponseBody
	public ResponseResult<Boolean> changePhoto(
			@RequestBody EditFieldValueParams fieldsParams) {

		String userId = UserLoginTokenUtils.getUserId(fieldsParams
				.getAuthParams().getToken(), fieldsParams.getClientParams()
				.getDeviceId(), fieldsParams.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		if (StringUtils.isEmpty(fieldsParams.getValue())) {
			return renderInvalidArgument();
		}

		fieldsParams.setId(userId);

		return renderSuccess(userAPI.changePhoto(fieldsParams));

	}

	@RequestMapping("/phoneRegister")
	@ResponseBody
	public ResponseResult<UserBaseDTO> phoneRegister(
			@RequestBody RegisterParams registerParams) {

		UserBaseDTO userBaseDTO = userAPI.getUserByPhone(registerParams);

		if (userBaseDTO != null) {
			return this.renderError("手机号已注册");
		}

		if (StringUtils.isEmpty(registerParams.getPhone())) {
			return renderInvalidArgument();
		}

		if (StringUtils.isEmpty(registerParams.getPwd())) {
			return renderInvalidArgument();
		}

		if (StringUtils.isEmpty(registerParams.getVertifyCode())) {
			return renderInvalidArgument();
		}

		boolean flag = SmsRuleAPI.validateCode(registerParams.getPhone(),
				registerParams.getVertifyCode());

		if (!flag) {
			return this.renderError("验证码出错，请重新输入");
		}

		return renderSuccess(userAPI.userRegister(registerParams));

	}

	@RequestMapping("/phoneRegisterAndLogin")
	@ResponseBody
	public ResponseResult<LoginResultDTO> phoneRegisterAndLogin(
			@RequestBody RegisterParams registerParams) {

		ResponseResult<UserBaseDTO> userBaseDTOResponseResult = phoneRegister(registerParams);

		if (userBaseDTOResponseResult.getStatus() == BooleanType.TRUE.getCode()) {
			LoginParams params = modelMapper.map(registerParams, LoginParams.class);
			UserBaseDTO dto = userBaseDTOResponseResult.getContent();
			params.setPwd(dto.getPassword());
			params.setPhone(dto.getPhone());
			return renderSuccess(userLoginApi.login(params));

		}
		return this.renderError(userBaseDTOResponseResult.getCode(),
				userBaseDTOResponseResult.getMessage());

	}

	@RequestMapping("/getChangePhonePwdVertifyCode")
	@ResponseBody
	public ResponseResult<Boolean> getChangePhonePwdVertifyCode(
			@RequestBody PhoneParams phoneParams) {

		if (StringUtils.isEmpty(phoneParams.getPhone())) {
			return renderInvalidArgument();
		}

		String userId = UserLoginTokenUtils.getUserId(phoneParams
				.getAuthParams().getToken(), phoneParams.getClientParams()
				.getDeviceId(), phoneParams.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		// 获取用户信息
		UserBaseDTO userBaseDTO = userAPI.getUserByPhone(phoneParams);
		phoneParams.setPhone(userBaseDTO.getPhone());

		smsRuleAPI
				.sendIdentifyCode(
						phoneParams,
						ThirdPartyExtranetContants.SMS_TEMPLATE_CHANGE_PHONE_VERTIFY_CODE);

		return renderSuccess(true);

	}

	@RequestMapping("/getForgotPwdVertifyCode")
	@ResponseBody
	public ResponseResult<Boolean> getForgotPwdVertifyCode(
			@RequestBody PhoneParams phoneParams) {

		if (StringUtils.isEmpty(phoneParams.getPhone())) {
			return renderInvalidArgument();
		}
		
		UserBaseDTO userBaseDTO = userAPI.getUserByPhone(phoneParams);
		
		if(userBaseDTO == null){
			return this.renderError("手机号未注册");
		}

		smsRuleAPI
				.sendIdentifyCode(
						phoneParams,
						ThirdPartyExtranetContants.SMS_TEMPLATE_FORGET_PWD_VERTIFY_CODE);

		return renderSuccess(true);

	}

	@RequestMapping("/getRegisterVertifyCode")
	@ResponseBody
	public ResponseResult<Boolean> getRegisterVertifyCode(
			@RequestBody PhoneParams phoneParams) {

		boolean flag = userAPI.exsitPhone(phoneParams);

		if (flag) {
			
			return this.renderError(101005, "手机号已注册");
		}

		smsRuleAPI.sendIdentifyCode(phoneParams,
				ThirdPartyExtranetContants.SMS_TEMPLATE_REGISTER_VERTIFY_CODE);

		return renderSuccess(true);

	}

	@RequestMapping("/getUserBaseDTO")
	@ResponseBody
	public ResponseResult<UserBaseDTO> getUserBaseDTO(@RequestBody IDParams idParams) {

		String operUserId = UserLoginTokenUtils.getUserId(idParams);
		if (StringUtils.isEmpty(idParams.getId()) && StringUtils.isEmpty(operUserId)) {
			return renderInvalidArgument();
		}
		if (StringUtils.isEmpty(idParams.getId())) {
			idParams.setId(operUserId);
		}

		UserBaseDTO dto = userAPI.getUserById(idParams);
		dto = setUserBaseDTO(idParams, dto);

		return renderSuccess(dto);

	}
	
	private UserBaseDTO setUserBaseDTO(BaseParams baseparams, UserBaseDTO dto){
		if (dto != null) {

			IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
			if (StringUtils.isNotEmpty(dto.getPhotoId())) {
				dto.setPhotoUrl(fileSystemInstance.getFilePathById(dto.getPhotoId()));
			}

			dto.setBgPhotoUrl(fileSystemInstance.getFilePathById(dto.getBgPhotoId()));
			
			IDParams idParams = new IDParams();
			idParams.setId(dto.getId());
			
//			// 设置认证信息
//			EnterAuthManageDetailDTO enterAuthManageDetailDTO = enterpriseAuthAPI.getByUserId(idParams);
//			if(null != enterAuthManageDetailDTO){
//				if(enterAuthManageDetailDTO.getStatus() == AuthenticationStatusType.SUCCESS.getCode()) {
//					dto.setPerfectEnterpriseInfo(BooleanType.TRUE.getCode());
//					dto.setIsEnterprise(BooleanType.TRUE.getCode());
//				}
//			}
//
//			UserAuthDetailDTO authDetailDTO = userAuthAPI.getByUserId(idParams);
//			if(null != authDetailDTO){
//				if(authDetailDTO.getStatus() == AuthenticationStatusType.SUCCESS.getCode()) {
//					dto.setPerfectPersonalInfo(BooleanType.TRUE.getCode());
//					dto.setIsUserAuth(BooleanType.TRUE.getCode());
//				}
//			}
			
			//是否可切换身份
			if(dto.getIsUserAuth()==BooleanType.TRUE.getCode()&&dto.getIsEnterprise()==BooleanType.TRUE.getCode()){
				dto.setCanSwitchRole(BooleanType.TRUE.getCode());
			}else{
				dto.setCanSwitchRole(BooleanType.FALSE.getCode());
			}
			
			if(dto.getCurAuthRole()==null){
				dto.setCurAuthRole(0);
			}
			if(dto.getCurAuthRole()==0){
				//当前没有设置身份
				if(dto.getIsUserAuth()==BooleanType.TRUE.getCode()){
					//有个人身份就显示个人身份
					dto.setCurAuthRole(AuthRoleTypeEnum.PERSONAL.getCode());
				}
				if(dto.getIsEnterprise()==BooleanType.TRUE.getCode()){
					//有企业身份就显示企业身份
					dto.setCurAuthRole(AuthRoleTypeEnum.ENTERPRISE.getCode());
				}
			}
			
			//设置当前身份名称
			dto.setCurAuthRoleStr(AuthRoleTypeEnum.getEnum(dto.getCurAuthRole()).getNameCn());
			
		}
		return dto;
	}

	@Override
	@RequestMapping("/enable")
	@ResponseBody
	public ResponseResult<Boolean> enable(IDParams idParams)
			throws ServiceException {
//		return renderSuccess(userAPI.enable(idParams));
		return renderSuccess(false);
	}

	@Override
	@RequestMapping("/disable")
	@ResponseBody
	public ResponseResult<Boolean> disable(@RequestBody IDParams idParams)
			throws ServiceException {
//		return renderSuccess(userAPI.disable(idParams));
		return renderSuccess(false);
	}

	@Override
	@RequestMapping("/changePhone")
	@ResponseBody
	public ResponseResult<Boolean> changePhone(
			@RequestBody PhoneParams phoneParams) {

		String userId = UserLoginTokenUtils.getUserId(phoneParams);

		if (StringUtils.isEmpty(userId)) {
			return renderInvalidArgument();
		}

		if (StringUtils.isEmpty(phoneParams.getPhone())) {
			return renderInvalidArgument();
		}

		phoneParams.setId(userId);

		// 获取用户信息
		UserBaseDTO userBaseDTO = userAPI.getUserByPhone(phoneParams);

		if (userBaseDTO != null) {
			return this.renderError("手机号已注册");
		}

		if (StringUtils.isEmpty(phoneParams.getVertifyCode())) {
			return renderInvalidArgument();
		}

		boolean flag = SmsRuleAPI.validateCode(phoneParams.getPhone(),
				phoneParams.getVertifyCode());

		if (flag) {
			flag = userAPI.changePhone(phoneParams);
		} else {
			return this.renderError("验证码错误");
		}

		if (!flag) {
			return this.renderError("修改手机号失败");
		}

		return renderSuccess(flag);
	}

	@Override
	@RequestMapping("/changeIntroduction")
	@ResponseBody
	public ResponseResult<Boolean> changeIntroduction(
			@RequestBody EditFieldValueParams editFieldValueParams) throws ServiceException {
		
		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams);

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeIntroduction(editFieldValueParams));
		
	}

	@Override
	@RequestMapping("/changeGender")
	@ResponseBody
	public ResponseResult<Boolean> changeGender(
			@RequestBody EditFieldValueParams editFieldValueParams) throws ServiceException {
		
		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams);

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeGender(editFieldValueParams));
	}
	
	@Override
	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<UserBaseDTO> save(@RequestBody UserParams userParams)
			throws ServiceException {
		
		return renderSuccess(userAPI.save(userParams));
	}


	@Override
	@RequestMapping("/getList")
	@ResponseBody
	public ResponseResult<PageResult<UserBaseDTO>> getList(@RequestBody UserListParams params)
			throws ServiceException {
		return renderSuccess(userAPI.getList(params));
	}

	@Override
	@RequestMapping("/update")
	@ResponseBody
	public ResponseResult<UserBaseDTO> update(@RequestBody UserParams userParams)
			throws ServiceException {
		String userId = UserLoginTokenUtils.getUserId(userParams);

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}
		
		userParams.setId(userId);
		userParams.setAccountStatus(null);
		userParams.setIsAgent(null);
		userParams.setCurAuthRole(null);
		userParams.setOfficialType(null);
		userParams.setUserGrade(null);
		userParams.setUserType(null);
		userParams.setName(null);
		userParams.setSiteId(null);
		return renderSuccess(userAPI.update(userParams));
	}

	@Override
	@RequestMapping("/changeProvinceAndCity")
	@ResponseBody
	public ResponseResult<Boolean> changeProvinceAndCity(@RequestBody UserParams userParams)
			throws ServiceException {
		
		String userId = UserLoginTokenUtils.getUserId(userParams);

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		userParams.setId(userId);

		if (StringUtils.isEmpty(userParams.getResidentProvince())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeProvinceAndCity(userParams));
	}

	@Override
	@RequestMapping("/setPwd")
	@ResponseBody
	public ResponseResult<Boolean> setPwd(
			@RequestBody EditFieldValueParams editFieldValueParams) throws ServiceException {
		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams);

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return unloginInvalid();
		}
		
		editFieldValueParams.setId(userId);

		return renderSuccess(userAPI.setPwd(editFieldValueParams));
	}

//	@Override
//	@RequestMapping("/initUserInfo")
//	@ResponseBody
//	public ResponseResult<Boolean> initUserInfo(@RequestBody BaseParams params)
//			throws ServiceException {
//
//		if (params.getAuthParams() == null) {
//			return renderInvalidArgument();
//		}
//
//		String appId = params.getAppId();
//		String token = params.getAuthParams().getToken();
//
//		// 判断运营授权登录
//		LoginOperatorDTO operator = loginApi.getOperator(appId, token);
//		if (operator == null) {
//			return unloginInvalid();
//		}
//
//		List<User> updateUserList = new ArrayList<User>();
//
//		// 查询所有个人认证信息
//		EntityWrapper<UserAuthentication> userWrapper = new EntityWrapper<UserAuthentication>();
//		userWrapper.eq("DELETABLE", BooleanType.FALSE.getCode());
//
//		List<UserAuthentication> userAuthList = userAuthDAO.selectList(userWrapper);
//
//		if(userAuthList != null && userAuthList.size() > 0) {
//
//			for(UserAuthentication userAuth : userAuthList) {
//
//				if(userAuth == null) {
//					continue;
//				}
//
//				String userId = userAuth.getUserId();
//				User updateUser = userDAO.selectById(userId);
//
//				if(updateUser == null) {
//					continue;
//				}
//
//				updateUser.setAuthStatus(userAuth.getCertificateStatus());
//				updateUser.setAuthType(AuthType.PERSONAL.getCode());
//
//				if(userAuth.getCertificateStatus() == AuthenticationStatusType.SUCCESS.getCode()) {
//					updateUser.setName(userAuth.getRealName());
//				}
//
//				updateUserList.add(updateUser);
//			}
//		}
//
//		// 查询所有企业认证信息
//		EntityWrapper<EnterpriseAuthentication> enterWrapper = new EntityWrapper<EnterpriseAuthentication>();
//		userWrapper.eq("DELETABLE", BooleanType.FALSE.getCode());
//
//		List<EnterpriseAuthentication> enterAuthList = enterAuthDAO.selectList(enterWrapper);
//
//		if(enterAuthList != null && enterAuthList.size() > 0) {
//
//			for(EnterpriseAuthentication enterAuth : enterAuthList) {
//
//				if(enterAuth == null) {
//					continue;
//				}
//
//				String userId = enterAuth.getUserId();
//				User updateUser = userDAO.selectById(userId);
//
//				if(updateUser == null) {
//					continue;
//				}
//
//				updateUser.setAuthStatus(enterAuth.getStatus());
//				updateUser.setAuthType(AuthType.ENTERPRISE.getCode());
//
//				if(enterAuth.getStatus() == AuthenticationStatusType.SUCCESS.getCode()) {
//					updateUser.setName(enterAuth.getName());
//				}
//
//				updateUserList.add(updateUser);
//			}
//		}
//
//		// 更新数据库并初始化缓存
//		if(updateUserList != null && updateUserList.size() > 0) {
//
//			userDAO.updateBatchById(updateUserList);
//
//			RedisCache cache = RedisCacheUtils.getInnoCache();
//
//			for(int i = 0; i < updateUserList.size() ; i++) {
//				User user = updateUserList.get(i);
//				String key = UserServiceCacheKeyUtils.getObjectCacheKey(user.getId());
//				cache.put(key, user);
//			}
//		}
//
//		return renderSuccess(true);
//	}

	@Override
	@RequestMapping("/changeTelephone")
	@ResponseBody
	public ResponseResult<Boolean> changeTelephone(@RequestBody EditFieldValueParams editFieldValueParams) {

		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams
				.getAuthParams().getToken(), editFieldValueParams
				.getClientParams().getDeviceId(), editFieldValueParams
				.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeTelephone(editFieldValueParams));

	}

	@Override
	@RequestMapping("/changeEmail")
	@ResponseBody
	public ResponseResult<Boolean> changeEmail(@RequestBody EditFieldValueParams editFieldValueParams) {

		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams
				.getAuthParams().getToken(), editFieldValueParams
				.getClientParams().getDeviceId(), editFieldValueParams
				.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeEmail(editFieldValueParams));

	}
	

	@Override
	@RequestMapping("/changeZipCode")
	@ResponseBody
	public ResponseResult<Boolean> changeZipCode(@RequestBody EditFieldValueParams editFieldValueParams) {

		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams
				.getAuthParams().getToken(), editFieldValueParams
				.getClientParams().getDeviceId(), editFieldValueParams
				.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeZipCode(editFieldValueParams));

	}
	
	@Override
	@RequestMapping("/changeAddressDetail")
	@ResponseBody
	public ResponseResult<Boolean> changeAddressDetail(@RequestBody EditFieldValueParams editFieldValueParams) {

		String userId = UserLoginTokenUtils.getUserId(editFieldValueParams
				.getAuthParams().getToken(), editFieldValueParams
				.getClientParams().getDeviceId(), editFieldValueParams
				.getClientParams().getOs());

		if (StringUtils.isEmpty(userId)) {
			return unloginInvalid();
		}

		editFieldValueParams.setId(userId);

		if (StringUtils.isEmpty(editFieldValueParams.getValue())) {
			return renderInvalidArgument();
		}

		return renderSuccess(userAPI.changeAddressDetail(editFieldValueParams));

	}

	@Override
	@RequestMapping("/switchRole")
	@ResponseBody
	public ResponseResult<UserBaseDTO> switchRole(@RequestBody BaseParams params) throws ServiceException {
		String operUserId = UserLoginTokenUtils.getUserId(params);
		if (StringUtils.isEmpty(operUserId)) {
			return renderInvalidArgument();
		}

		IDParams idParams = new IDParams();
		idParams.setId(operUserId);

		UserBaseDTO dto = userAPI.getUserById(idParams);
		dto = setUserBaseDTO(idParams, dto);
		
		if(dto.getCanSwitchRole()==BooleanType.FALSE.getCode()){
			//不能切换身份
			this.renderError("不能切换身份");
		}
		
		
		UserParams userParams = new UserParams();
		userParams.setId(operUserId);
		if(dto.getCurAuthRole()==AuthRoleTypeEnum.PERSONAL.getCode()){
			//当前是个人用户,切换为企业用户
			userParams.setCurAuthRole(AuthRoleTypeEnum.ENTERPRISE.getCode());
		}else{
			//切换为个人用户
			userParams.setCurAuthRole(AuthRoleTypeEnum.PERSONAL.getCode());
		}
		
		UserBaseDTO result = userAPI.update(userParams);
		result = setUserBaseDTO(idParams, result);
		return renderSuccess(result);
	}

	@Override
	@RequestMapping("/update-name-place-address")
	@ResponseBody
	public ResponseResult<Boolean> updateNamePlaceAddress(@RequestBody UserParams userParams) throws ServiceException {
		if (userParams.getAuthParams() == null) {
			return renderInvalidArgument();
		}
		
		String appId = userParams.getAppId();
		String token = userParams.getAuthParams().getToken();
		
		// 登录验证
		String userId = UserLoginTokenUtils.getUserId(userParams);
		if (userId == null) {
			return unloginInvalid();
		}
		userParams.setId(userId); 
		return renderSuccess(userAPI.updateNamePlaceAddress(userParams));
	}

	@Override
	@RequestMapping(value="/exportExcel",method =  {RequestMethod.GET,RequestMethod.POST})
	public String exportExcel(String phone, String name, String nickName, Integer gender, String label, long startTime,
							  long endTime, HttpServletRequest request, HttpServletResponse response) {

		UserListParams listParams = new UserListParams();
		listParams.setPhone(phone);
		listParams.setName(name);
		listParams.setNickName(nickName);
		listParams.setGender(gender);
		listParams.setLabel(label);
		listParams.setPageSize(Integer.MAX_VALUE);
		PageResult<UserBaseDTO> list = userAPI.getList(listParams);
		ExportUtils.exportExcelUser(response, list.getList());
		return null;
	}

	@Override
	@RequestMapping(value="/saveByOtherOrg",method =  {RequestMethod.POST})
	@ResponseBody
	public ResponseResult<String> saveByOtherOrg(@RequestBody UserParams userParams) {
		BaseParams baseParams = new BaseParams();
		userParams.setAppId(baseParams.getAppId());
//		userParams.setSiteId(baseParams.getSiteId());
//		userParams.setMiniId(baseParams.getMiniId());
		UserBaseDTO user = userAPI.getUserByPhone(new PhoneParams(userParams.getPhone()));
		if (user != null){
			EditFieldValueParams editFieldValueParams = new EditFieldValueParams();
			editFieldValueParams.setId(user.getId());
			editFieldValueParams.setValue(userParams.getImportSource());
			userAPI.updateImportSource(editFieldValueParams);
			return renderSuccess(user.getId());
		}
		user = userAPI.saveByThird(userParams);
		return renderSuccess(user.getId());
	}


}
