/* 
 * 文件名：UseBuilding.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月4日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 楼栋PO类
 * @author qsy
 * @version v1.0
 * @date 2017年7月4日
 */
@Table(name="use_building")
public class UseBuildingPo {
	/**
	 * 主键编号
	 */
	@Id
	private String id;
	/**
	 * 楼栋编号
	 */
	private String buildingNo;
	/**
	 * 楼栋名称
	 */
	private String buildingName;
	/**
	 * 单元数
	 */
	private int unitNumber;
	/**
	 * 层数
	 */
	private int floorNumber;
	/**
	 * 层户数
	 */
	private int roomNumber;
	/**
	 * 所属小区主键编号
	 */
	private String communityId;
	/**
	 * 状态
	 */
	private String state;

	/**
	 * 单元名
	 */
	private String unitName;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	/**
	 * @return the unitNumber
	 */
	public int getUnitNumber() {
		return unitNumber;
	}
	/**
	 * @param unitNumber the unitNumber to set
	 */
	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
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
	 * @return the buildingNo
	 */
	public String getBuildingNo() {
		return buildingNo;
	}
	/**
	 * @param buildingNo the buildingNo to set
	 */
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
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
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	/**
	 * @param floorNumber the floorNumber to set
	 */
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
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
