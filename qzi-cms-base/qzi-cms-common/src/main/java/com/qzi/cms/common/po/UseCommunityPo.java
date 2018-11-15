/* 
 * 文件名：UseCommunityPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 住宅小区PO类
 * @author qsy
 * @version v1.0
 * @date 2017年6月27日
 */
@Table(name="use_community")
public class UseCommunityPo {
	/**
	 * 主键编号
	 */
	@Id
	private String id;
	/**
	 * 小区名称
	 */
	private String communityName;
	/**
	 * 小区编号
	 */
	private String communityNo;
	/**
	 * 楼栋数量
	 */
	private int buildingNum;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 省份代码
	 */
	private String provinceCode;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 城市代码
	 */
	private String cityCode;
	/**
	 * 区域
	 */
	private String area;
	/**
	 * 区域代码
	 */
	private String areaCode;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态	
	 */
	private String state;


	/**
	 * 主机数量
	 */

	private Integer masterNum;
	/**
	 * user数量
	 */

	private  Integer userNum;

	/**
	 * 物业员id
	 */
	private String sysUserId;

	/**
	 * 厂商代码
	 * @return
	 */

	private Integer code;


	/**
	 * 工程商id
	 */
	private String sysWorkId;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getSysWorkId() {
		return sysWorkId;
	}

	public void setSysWorkId(String sysWorkId) {
		this.sysWorkId = sysWorkId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Integer getMasterNum() {
		return masterNum;
	}

	public void setMasterNum(Integer masterNum) {
		this.masterNum = masterNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the buildingNum
	 */
	public int getBuildingNum() {
		return buildingNum;
	}
	/**
	 * @param buildingNum the buildingNum to set
	 */
	public void setBuildingNum(int buildingNum) {
		this.buildingNum = buildingNum;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the communityName
	 */
	public String getCommunityName() {
		return communityName;
	}
	/**
	 * @param communityName the communityName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	/**
	 * @return the communityNo
	 */
	public String getCommunityNo() {
		return communityNo;
	}
	/**
	 * @param communityNo the communityNo to set
	 */
	public void setCommunityNo(String communityNo) {
		this.communityNo = communityNo;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
