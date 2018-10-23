/* 
 * 文件名：CommunityAdminVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月29日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 小区管理员关系Vo
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
public class CommunityAdminVo {
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 是否关联
	 */
	private boolean checked;
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
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
