/* 
 * 文件名：ContactWuyeServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年9月1日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseEquipmentMapper;
import com.qzi.cms.server.service.app.ContactWuyeService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 联系物业业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年9月1日
 */
@Service
public class ContactWuyeServiceImpl implements ContactWuyeService {
	@Resource
	private UseEquipmentMapper equipMapper;
	@Resource
	private CommonService commonServcie;

	@Override
	public List<UseEquipmentVo> findMgrMachines() throws Exception {
		//住户对象
		UseResidentVo residentVo = commonServcie.findResident();
		return equipMapper.findMgrMachines(residentVo.getId());
	}

}
