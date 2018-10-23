/* 
 * 文件名：OptionVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月20日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 下拉数据VO
 * @author qsy
 * @version v1.0
 * @date 2017年7月20日
 */
public class OptionVo {
	private String value;//值
	private String name;//名
	
	public OptionVo() {
		super();
	}
	public OptionVo(String value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
}
