/* 
 * 文件名：UseCommonEquipmentPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 常用列表
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
@Table(name="use_common_equipment")
public class UseCommonEquipmentPo {
	/**
	 * 设备编号
	 */
	@Id
	private String equipmentId;
	/**
	 * 用户编号
	 */
	@Id
	private String residentId;
	/**
	 * @return the equipmentId
	 */
	public String getEquipmentId() {
		return equipmentId;
	}
	/**
	 * @param equipmentId the equipmentId to set
	 */
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
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
