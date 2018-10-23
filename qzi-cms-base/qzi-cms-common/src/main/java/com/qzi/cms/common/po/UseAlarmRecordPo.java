/* 
 * 文件名：UseAlarmRecordPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报警记录Po
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
@Table(name="use_alarm_record")
public class UseAlarmRecordPo {
	/**
	 * 主键编号
	 */
	@Id
	private String id;
	/**
	 * 门口机编号
	 */
	private String equipmentId;
	/**
	 * 探头类型
	 */
	private String probeType;
	/**
	 * 房区
	 */
	private String houseArea;
	/**
	 * 房号
	 */
	private String houseId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the probeType
	 */
	public String getProbeType() {
		return probeType;
	}
	/**
	 * @param probeType the probeType to set
	 */
	public void setProbeType(String probeType) {
		this.probeType = probeType;
	}
	/**
	 * @return the houseArea
	 */
	public String getHouseArea() {
		return houseArea;
	}
	/**
	 * @param houseArea the houseArea to set
	 */
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	/**
	 * @return the houseId
	 */
	public String getHouseId() {
		return houseId;
	}
	/**
	 * @param houseId the houseId to set
	 */
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
