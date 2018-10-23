/* 
 * 文件名：UseMessageVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.Date;

/**
 * 消息Vo类
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public class UseMessageVo {
	/**
	 * 主键编号
	 */
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private Date createTime;
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 楼栋编号
	 */
	private String buildingId;
	/**
	 * 单元编号
	 */
	private String unitId;
	/**
	 * 房间编号
	 */
	private String roomId;
	/**
	 * 接收对象
	 */
	private String sendee;
	
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return the sendee
	 */
	public String getSendee() {
		return sendee;
	}
	/**
	 * @param sendee the sendee to set
	 */
	public void setSendee(String sendee) {
		this.sendee = sendee;
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
	 * @return the unitId
	 */
	public String getUnitId() {
		return unitId;
	}
	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
}
