/* 
 * 文件名：UseResidentRoomPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月20日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 住户房间Po类
 * @author qsy
 * @version v1.0
 * @date 2017年7月20日
 */
@Table(name="use_resident_room")
public class UseResidentRoomPo {


	private String id;
	/**
	 * 住户编号
	 */
	@Id
	private String residentId;
	/**
	 * 房间编号
	 */
	@Id
	private String roomId;
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 是否授权 10通过 20授权
	 */
	private String owner;
	/**
	 *
	 */

	private String isTrue;


	private  String remark;


	public String getId(String uuid) {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(String isTrue) {
		this.isTrue = isTrue;
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
