package com.lcy.params.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.lcy.params.common.BaseParams;

import java.io.Serializable;

/**
 * 老师参数
 *
 * @author: lchunyi@linewell.com
 * @since: 2019/3/28 9:33
 */
public class TeacherParams extends BaseParams {

    private static final long serialVersionUID = 7581515504179678251L;

    /**
     * 主键
     */
    private String id;

    /**
     * 头像
     */
    private String photoId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private int gender;

    /**
     * 是否推荐到首页
     */
    private Integer isIndex;
    /**
     * 简介
     */
    private String intro;

    /**
     * 详情
     */
    private String introduce;

    /**
     * 服务地点
     */
    private String servicePlace;
    /**
     * 服务地区
     */
    private String serviceArea;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 服务时间
     */
    private String serviceTime;
    /**
     * 状态
     */
    private Integer status;

    private String recommendProblemSetIds;

    public String getRecommendProblemSetIds() {
        return recommendProblemSetIds;
    }

    public void setRecommendProblemSetIds(String recommendProblemSetIds) {
        this.recommendProblemSetIds = recommendProblemSetIds;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getServicePlace() {
        return servicePlace;
    }

    public void setServicePlace(String servicePlace) {
        this.servicePlace = servicePlace;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }
}
