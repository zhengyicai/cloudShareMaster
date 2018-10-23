/* 
 * 文件名：UseResidentVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.Date;

/**
 * 住户信息VO类
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
public class UseResidentVo {
	/**
	 * 主键编号
	 */
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 小区名称
	 */
	private String communityName;
	/**
	 * 云之讯clientNumber
	 */
	private String clientNumber;
	/**
	 * 云之讯密码
	 */
	private String clientPwd;
	/**
	 * 第三方token
	 */
	private String loginToken;
	/**
	 * 开门密码
	 */
	private String openPwd;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 手机短信验证码
	 */
	private String smsCode;


	private String  buildingId;
	private String utilName;
	private String roomName;


	private String remark;
	private String unitId;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getUtilName() {
		return utilName;
	}

	public void setUtilName(String utilName) {
		this.utilName = utilName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @return the loginToken
	 */
	public String getLoginToken() {
		return loginToken;
	}
	/**
	 * @param loginToken the loginToken to set
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	/**
	 * @return the smsCode
	 */
	public String getSmsCode() {
		return smsCode;
	}
	/**
	 * @param smsCode the smsCode to set
	 */
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
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
	 * @return the openPwd
	 */
	public String getOpenPwd() {
		return openPwd;
	}
	/**
	 * @param openPwd the openPwd to set
	 */
	public void setOpenPwd(String openPwd) {
		this.openPwd = openPwd;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the communityId
	 */
	public String getCommunityId() {
		return communityId;
	}
	/**
	 * @param communityId the communityId to set
	 */
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
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
	 * @return the clientNumber
	 */
	public String getClientNumber() {
		return clientNumber;
	}
	/**
	 * @param clientNumber the clientNumber to set
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	/**
	 * @return the clientPwd
	 */
	public String getClientPwd() {
		return clientPwd;
	}
	/**
	 * @param clientPwd the clientPwd to set
	 */
	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}
}
