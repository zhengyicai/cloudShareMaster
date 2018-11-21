/* 
 * 文件名：UseResidentRoomVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月22日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 住户房间Vo
 * @author qsy
 * @version v1.0
 * @date 2017年7月22日
 */
public class UseResidentRoomVo {
	/**
	 * 住户编号
	 */
	private String residentId;
	/**
	 * 房间编号
	 */
	private String roomId;
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 授权
	 */
	private String owner;

	private String id;


	/**
	 * 是否默认
	 */
	private String isTrue;
	private String remark;
	private String name;
	private String mobile;
	private String communityName;
	private String buildingName;
	private String unitName;
	private String roomName;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(String isTrue) {
		this.isTrue = isTrue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the residentId
	 */
	public String getResidentId() {
		return residentId;
	}
	/**
	 * @param residentId the residentId to set
	 */
	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}
	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
}
