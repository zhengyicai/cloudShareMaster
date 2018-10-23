/* 
 * 文件名：CallVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 呼叫Vo
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
public class CallVo {
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 户主
	 */
	private String owner;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 开门密码
	 */
	private String openPwd;
	
	/**
	 * @return the openPwd
	 */
	public String getOpenPwd() {
		return openPwd;
	}
	/**
	 * @param openPwd the openPwd to set
	 */
	public void setOpenPwd(String openPwd) {
		this.openPwd = openPwd;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
}
