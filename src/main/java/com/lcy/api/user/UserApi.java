package com.lcy.api.user;

import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.bll.user.IUserInfoEditServiceBLL;
import com.lcy.bll.user.IUserLoginServiceBLL;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.user.UserBaseDTO;
import com.lcy.dto.user.UserLoginInfoDTO;
import com.lcy.dto.user.UserThirdInfoDTO;
import com.lcy.params.common.EditFieldValueParams;
import com.lcy.params.common.FieldsParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PhoneParams;
import com.lcy.params.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用戶接口
 *
 * @author cjianyan@linewell.com
 */
@Service
public class UserApi{

    @Autowired
    IUserInfoEditServiceBLL userInfoEditServiceBLL;

    @Autowired
    IUserLoginServiceBLL userLoginServiceBLL;


    /**
     * 获取或者保存第三方资料
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO saveUserThirdInfo(UserThirdInfoParams params) throws ServiceException {
        return userInfoEditServiceBLL.saveUserThirdInfo(params);
    }

    /**
     * 获取微信第三方登陆信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO getWxThridInfo(IDParams params) throws ServiceException {
        return userInfoEditServiceBLL.getWxThridInfo(params);
    }

    /**
     * 获取微信小程序第三方登陆信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO getMiniThridInfo(IDParams params) throws ServiceException {
        return userInfoEditServiceBLL.getMiniThridInfo(params);
    }

    /**
     * 根据主键第三方登陆信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO getThirdInfoById(IDParams params) throws ServiceException {
        return userInfoEditServiceBLL.getThirdInfoById(params);
    }

    /**
     * 根据主键第三方登陆信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfo getUserThridInfoById(IDParams params) throws ServiceException {
        return userInfoEditServiceBLL.getUserThridInfoById(params);
    }

    /**
     * 获取用户第三方登陆信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO getUserThridInfo(UserThirdTypeParams params) throws ServiceException {
        return userInfoEditServiceBLL.getUserThridInfo(params);
    }

    /**
     * 更新小程序sessionKey和unionid
     *
     * @param id
     * @param sessionKey
     * @param unionid
     * @return
     * @throws ServiceException
     */
    public boolean updateCodeResult(String id, String sessionKey, String unionid) throws ServiceException {
        return userInfoEditServiceBLL.updateCodeResult(id, sessionKey, unionid);
    }

    /**
     * 用户绑定第三方信息
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public UserThirdInfoDTO bindUserThirdInfo(UserThirdInfoParams params) throws ServiceException {
        return userInfoEditServiceBLL.bindUserThirdInfo(params);
    }

    /**
     * 设置密码
     *
     * @param userParams
     * @return
     * @throws ServiceException
     */
    public boolean setPwd(EditFieldValueParams editFieldValueParams) throws ServiceException {
        return userInfoEditServiceBLL.setPwd(editFieldValueParams);
    }

    /**
     * 获取用户登陆信息
     *
     * @return
     */
    public UserLoginInfoDTO getUserLoginInfoDTO(IDParams params) throws ServiceException {
        return userLoginServiceBLL.getUserLoginInfoDTO(params);
    }

    /**
     * 编辑性别
     *
     * @param editFieldValueParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeGender(EditFieldValueParams editFieldValueParams) throws ServiceException {
        return userInfoEditServiceBLL.changeGender(editFieldValueParams);
    }


    /**
     * 修改所在省市县
     *
     * @param userParams
     * @return
     * @throws ServiceException
     */
    public boolean changeProvinceAndCity(UserParams userParams) throws ServiceException {
        return userInfoEditServiceBLL.changeProvinceAndCity(userParams);
    }

    /**
     * 用户注册
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO userRegister(RegisterParams registerParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .userRegister(registerParams);

    }

    /**
     * 修改个人资料
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeIntroduction(EditFieldValueParams editFieldValueParams) throws ServiceException {
        return userInfoEditServiceBLL.changeIntroduction(editFieldValueParams);
    }

    /**
     * 更改密码
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changePassword(ChangePwdParams params)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changePassword(params);


    }

    /**
     * 更改手机号
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changePhone(PhoneParams phoneParams) throws ServiceException {

        return userInfoEditServiceBLL.changePhone(phoneParams);

    }

    /**
     * 验证手机是否存在
     *
     * @param phoneParams
     * @return
     * @throws ServiceException
     */
    public boolean exsitPhone(PhoneParams phoneParams)
            throws ServiceException {
        return userInfoEditServiceBLL.exsitPhone(phoneParams);
    }

    /**
     * 通过手机号获取用户对象
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO getUserByPhone(PhoneParams phoneParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .getUserByPhone(phoneParams);


    }


    public boolean updateImportSource(EditFieldValueParams editFieldValueParams) throws ServiceException{
        return userInfoEditServiceBLL.updateImportSource(editFieldValueParams);
    }
    /**
     * 重置密码
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean resetPassword(ResetPasswordParams resetPasswordParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .resetPassword(resetPasswordParams);


    }

    /**
     * 通过用户标识获取对象
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO getUserById(IDParams idParams) throws ServiceException {
        return userInfoEditServiceBLL
                .getUserById(idParams);

    }

    /**
     * 通过昵称获取用户
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO getUserByNickname(FieldsParams fieldParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .getUserByNickname(fieldParams);


    }

    /**
     * 修改用户昵称
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeNickname(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeNickname(editFieldValueParams);

    }

    /**
     * 修改用户昵称
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeName(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeName(editFieldValueParams);

    }

    public Boolean changeLabel(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeLabel(editFieldValueParams);

    }

    public Boolean updateBgPhotoId(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .updateBgPhotoId(editFieldValueParams);

    }

    public Boolean changeLabelBatch(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeLabelBatch(editFieldValueParams);

    }


    /**
     * 修改用户电话号码
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeTelephone(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeTelephone(editFieldValueParams);

    }

    /**
     * 修改用户电子邮箱
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeEmail(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeEmail(editFieldValueParams);

    }


    /**
     * 修改用户邮政编码
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeZipCode(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeZipCode(editFieldValueParams);

    }

    /**
     * 修改用户详细地址
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changeAddressDetail(EditFieldValueParams editFieldValueParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changeAddressDetail(editFieldValueParams);

    }

    /**
     * 重置密码
     *
     * @param fieldParams
     * @return
     * @throws ServiceException
     */
    public Boolean changePhoto(EditFieldValueParams fieldsParams)
            throws ServiceException {

        return userInfoEditServiceBLL
                .changePhoto(fieldsParams);

    }

    /**
     * 启用账户
     *
     * @param idParams
     * @return
     * @throws ServiceException
     */
    public Boolean enable(IDParams idParams) throws ServiceException {

        return userInfoEditServiceBLL.enable(idParams);

    }

    /**
     * 禁用账户
     *
     * @param idParams
     * @return
     * @throws ServiceException
     */
    public Boolean disable(IDParams idParams) throws ServiceException {

        return userInfoEditServiceBLL.disable(idParams);

    }

    /**
     * 保存用户信息
     *
     * @param userParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO save(UserParams userParams) throws ServiceException {
        return userInfoEditServiceBLL.save(userParams);
    }

    /**
     * 保存用户信息(第三方登录用)
     *
     * @param userParams
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO saveByThird(UserParams userParams) throws ServiceException {
        return userInfoEditServiceBLL.saveByThird(userParams);
    }

    /**
     * 获取用户类别
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public PageResult<UserBaseDTO> getList(UserListParams params) throws ServiceException {
        return userInfoEditServiceBLL.getList(params);
    }

    /**
     * 更新用户信息
     *
     * @return
     * @throws ServiceException
     */
    public UserBaseDTO update(UserParams userParams) throws ServiceException {
        return userInfoEditServiceBLL.update(userParams);
    }

    /**
     * 获取用户数量
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    public long countUsers(UserCountParams params) throws ServiceException {
        return userInfoEditServiceBLL.countUsers(params);
    }

    public Integer countUser() throws ServiceException {
        return userInfoEditServiceBLL.countUser();
    }

    public Integer countIdentifyUser() throws ServiceException {
        return userInfoEditServiceBLL.countIdentifyUser();
    }

	public Boolean updateDetailedAddress(UserParams userParams) {
		return userInfoEditServiceBLL.updateDetailedAddress(userParams);
	}

	public Boolean updateNamePlaceAddress(UserParams userParams) {
		 
		return userInfoEditServiceBLL.updateNamePlaceAddress(userParams);
	}

    public UserThirdInfo getUserThridInfoByUnionId(String thirdUserId) {
        return userLoginServiceBLL.getUserThridInfoByUnionId(thirdUserId);
    }
}
