/* 
 * 文件名：LoginVo.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 用户登录信息
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
public class LoginVo{
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 验证码key
	 */
	private String imgKey;
	/**
	 * 验证码
	 */
	private String picCode;


	/**
	 * token
	 */
	private  String token;
	/**
	 * 权限id
	 */
	private String roleId;

	/**
	 * 默认小区
	 */

	private String communityId;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	/**
	 * @return the imgKey
	 */
	public String getImgKey() {
		return imgKey;
	}
	/**
	 * @param imgKey the imgKey to set
	 */
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}
	/**
	 * @return the picCode
	 */
	public String getPicCode() {
		return picCode;
	}
	/**
	 * @param picCode the picCode to set
	 */
	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
