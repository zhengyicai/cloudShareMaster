/* 
 * 文件名：UseCommunityResident.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 住户小区Po
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
@Table(name="use_community_resident")
public class UseCommunityResidentPo {
	/**
	 * 小区编号
	 */
	@Id
	private String communityId;
	/**
	 * 住户编号
	 */
	@Id
	private String residentId;
	/**
	 * 状态
	 */
	private String state;
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
}
