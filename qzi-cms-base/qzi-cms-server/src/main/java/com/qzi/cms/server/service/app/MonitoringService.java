/* 
 * 文件名：MonitoringService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.List;

import com.qzi.cms.common.vo.UseEquipmentVo;

/**
 * 监控列表控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
public interface MonitoringService {

	/**
	 * 查询用户监控列表(所有门口机)
	 * @return
	 * @throws Exception 
	 */
	public List<UseEquipmentVo> findAll() throws Exception;

	/**
	 * @param equipmentVo
	 */
	public void changeOftenUse(UseEquipmentVo equipmentVo)throws Exception;

}
