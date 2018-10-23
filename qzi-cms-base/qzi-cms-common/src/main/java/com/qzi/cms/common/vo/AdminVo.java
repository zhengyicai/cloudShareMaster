/* 
 * 文件名：AdminVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月29日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.List;

/**
 * 住宅小区管理员Vo
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
public class AdminVo {
	/**
	 * 住宅小区编号
	 */
	private String communityId;
	/**
	 * 用户编号集合
	 */
	private List<String> userIds;
	

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
	 * @return the userIds
	 */
	public List<String> getUserIds() {
		return userIds;
	}
	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	} 
}
