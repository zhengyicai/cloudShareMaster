/* 
 * 文件名：UseCommunityUserPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月29日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Table;

/**
 * 住宅小区管理员关系Po
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
@Table(name="use_community_user")
public class UseCommunityUserPo {
	/**
	 * 住宅小区编号
	 */
	private String communityId;
	/**
	 * 用户编号
	 */
	private String userId;
	
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
