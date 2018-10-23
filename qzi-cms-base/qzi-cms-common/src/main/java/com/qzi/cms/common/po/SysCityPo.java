/* 
 * 文件名：SysCity.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 中国城市Po类
 * @author qsy
 * @version v1.0
 * @date 2017年6月27日
 */
@Table(name="sys_city")
public class SysCityPo {
	/**
	 * 代码
	 */
	@Id
	private String code;
	/**
	 * 城市名
	 */
	private String name;
	/**
	 * 上级编码
	 */
	private String parentCode;
	/**
	 * 城市简称
	 */
	private String shortName;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 区域码
	 */
	private String areaCode;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 拼音
	 */
	private String zhName;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the zhName
	 */
	public String getZhName() {
		return zhName;
	}
	/**
	 * @param zhName the zhName to set
	 */
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
}
