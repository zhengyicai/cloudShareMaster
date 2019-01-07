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
 * 厂商关联表（权限显示用）
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
@Table(name="use_firm_admin")
public class UseFirmAdminPo {
	/**
	 *厂商id
	 */
	private String firmId;
	/**
	 * adminId
	 */
	private String userId;

	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
