package com.lcy.autogenerator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 地区对象
 *
 * @author lshengda@linewell.com
 * @since 2017年5月22日
 */
@TableName("area_config")
public class AreaConfig extends Model<AreaConfig> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("REGION_INFO_ID")
	private String regionInfoId;
	/**
	 * 区域名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 区域名称详情
	 */
	@TableField("MERGER_NAME")
	private String mergerName;
	/**
	 * 区域名称简写
	 */
	@TableField("SHORT_NAME")
	private String shortName;
	/**
	 * 详情简写
	 */
	@TableField("MERGER_SHORT_NAME")
	private String mergerShortName;
	/**
	 * 层级标识（中国 0，省份 1，市 2，县 3）
	 */
	@TableField("LEVEL_TYPE")
	private Integer levelType;
	/**
	 * 城市代号
	 */
	@TableField("CITY_CODE")
	private String cityCode;
	/**
	 * 邮编
	 */
	@TableField("ZIP_CODE")
	private String zipCode;
	/**
	 * 拼音
	 */
	@TableField("PINYIN")
	private String pinyin;
	/**
	 * 首字母拼音
	 */
	@TableField("JIANPIN")
	private String jianpin;
	/**
	 * 首字母
	 */
	@TableField("FIRST_CHAR")
	private String firstChar;
	/**
	 * 经度
	 */
	@TableField("LNG")
	private String lng;
	/**
	 * 纬度
	 */
	@TableField("LAT")
	private String lat;
	/**
	 * 备注
	 */
	@TableField("REMARKS")
	private String remarks;
	/**
	 * 父节点
	 */
	@TableField("PARENT_ID")
	private String parentId;
	/**
	 * 当前状态(热门、非热门)
	 */
	@TableField("STATUS")
	private Integer status;
	/**
	 * 排序
	 */
	@TableField("SORT")
	private Integer sort;

	/**
	 * 中国天气城市编码
	 */
	@TableField("WEATHER_CODE")
	private String weatherCode;


	/**获取地区id
	 *
	 * @return
	 */
	public String getRegionInfoId() {
		return   regionInfoId;
	}

	/**
	 * 设置地区id
	 * @param regionInfoId
	 */
	public void setRegionInfoId(String regionInfoId) {
		this.regionInfoId = regionInfoId;
	}

	/**
	 * 获取地区名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置地区名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取区域名称详情
	 * @return
	 */
	public String getMergerName() {
		return mergerName;
	}

	/**
	 * 设置区域名称详情
	 * @param mergerName
	 */
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	/**
	 * 获取区域名称简写
	 * @return
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置区域名称简写
	 * @param shortName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 获取详情简写
	 * @return
	 */
	public String getMergerShortName() {
		return mergerShortName;
	}

	/**
	 * 设置 详情简写
	 * @param mergerShortName
	 */
	public void setMergerShortName(String mergerShortName) {
		this.mergerShortName = mergerShortName;
	}

	/**
	 * 获取 层级标识
	 * @return
	 */
	public Integer getLevelType() {
		return levelType;
	}

	/**
	 * 设置 层级标识
	 * @param levelType
	 */
	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	/**
	 * 获取 城市代码
	 * @return
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * 设置城市代码
	 * @param cityCode
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 获取 邮编
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * 设置  邮编
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取拼音
	 * @return
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * 设置拼音
	 * @param pinyin
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * 获取首字母拼音
	 * @return
	 */
	public String getJianpin() {
		return jianpin;
	}

	/**
	 * 设置首字母拼音
	 * @param jianpin
	 */
	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	/**
	 * 获取首字母
	 * @return
	 */
	public String getFirstChar() {
		return firstChar;
	}

	/**
	 * 设置首字母
	 * @param firstChar
	 */
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	/**
	 * 获取 经度
	 * @return
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * 设置 经度
	 * @param lng
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * 获取纬度
	 * @return
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 * @param lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * 获取备注
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置 备注
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 获取父节点
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父节点
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 设置状态
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 获取状态
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取排序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.regionInfoId;
	}

}
