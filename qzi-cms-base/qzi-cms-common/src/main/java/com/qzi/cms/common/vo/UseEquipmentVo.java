/* 
 * 文件名：UseEquipmentVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.Date;
import java.util.List;

/**
 * 设备Vo类
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
public class UseEquipmentVo {
	/**
	 * 主键编号
	 */
	private String id;
	/**
	 * 设备编号
	 */
	private String equipmentId;
	/**
	 * 设备名称
	 */
	private String equipmentName;
	/**
	 * 设备类型
	 */
	private String equipmentType;
	/**
	 * 小区主键编号
	 */
	private String communityId;
	/**
	 * 小区名称
	 */
	private String communityName;
	/**
	 * 楼宇主键编号
	 */
	private String buildingId;
	/**
	 * 楼栋名称
	 */
	private String buildingName;
	/**
	 * 单元名称
	 */
	private Integer unitName;

	/**
		 * 单元id
		 */
		private String unitId;
	/**
	 * 云之讯clientNumber
	 */
	private String clientNumber;
	/**
	 * 云之讯loginToken
	 */
	private String loginToken;
	/**
	 * 云之讯密码
	 */
	private String clientPwd;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 是否常用
	 */
	private boolean oftenUse;
	/**
	 * 房间号
	 */
	private List<String> rooms;


	/**
	 * 当前请求时间
	 */
	private Date nowDate;

	/**
	 * 当前门磁状态
	 */
	private String nowState;
	/**
	 * 设备掉线
	 */
	private String nowDateStatus;


	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getNowDateStatus() {
		return nowDateStatus;
	}

	public void setNowDateStatus(String nowDateStatus) {
		this.nowDateStatus = nowDateStatus;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public String getNowState() {
		return nowState;
	}

	public void setNowState(String nowState) {
		this.nowState = nowState;
	}

	/**
	 * @return the rooms
	 */
	public List<String> getRooms() {
		return rooms;
	}
	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<String> rooms) {
		this.rooms = rooms;
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
	 * @return the oftenUse
	 */
	public boolean isOftenUse() {
		return oftenUse;
	}
	/**
	 * @param oftenUse the oftenUse to set
	 */
	public void setOftenUse(boolean oftenUse) {
		this.oftenUse = oftenUse;
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
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}
	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
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
	 * @return the equipmentId
	 */
	public String getEquipmentId() {
		return equipmentId;
	}
	/**
	 * @param equipmentId the equipmentId to set
	 */
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	/**
	 * @return the equipmentName
	 */
	public String getEquipmentName() {
		return equipmentName;
	}
	/**
	 * @param equipmentName the equipmentName to set
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	/**
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
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
	 * @return the buildingId
	 */
	public String getBuildingId() {
		return buildingId;
	}
	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * @return the unitName
	 */
	public Integer getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(Integer unitName) {
		this.unitName = unitName;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
}
