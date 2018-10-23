/* 
 * 文件名：ContactWuyeService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年9月1日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.List;

import com.qzi.cms.common.vo.UseEquipmentVo;

/**
 * 联系物业业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年9月1日
 */
public interface ContactWuyeService {

	/**
	 * 查询用户管理机
	 * @return
	 * @throws Exception 
	 */
	public List<UseEquipmentVo> findMgrMachines() throws Exception;
	
}
