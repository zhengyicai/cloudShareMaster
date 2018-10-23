/* 
 * 文件名：UseCommunityResidentVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 住户小区VO类
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
public class UseCommunityResidentVo {
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 住户编号
	 */
	private String residentId;
	/**
	 * 状态
	 */
	private String state;
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
