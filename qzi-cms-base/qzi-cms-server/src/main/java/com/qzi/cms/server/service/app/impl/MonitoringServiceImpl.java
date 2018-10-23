/* 
 * 文件名：MonitoringServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.enums.EquipmentEnum;
import com.qzi.cms.common.po.UseCommonEquipmentPo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseCommonEquipmentMapper;
import com.qzi.cms.server.mapper.UseEquipmentMapper;
import com.qzi.cms.server.mapper.UseRoomMapper;
import com.qzi.cms.server.service.app.MonitoringService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 监控列表业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
@Service
public class MonitoringServiceImpl implements MonitoringService{
	@Resource
	private UseEquipmentMapper equipmentMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private UseCommonEquipmentMapper commonEquiMapper;
	@Resource
	private UseRoomMapper roomMapper;

	@Override
	public List<UseEquipmentVo> findAll() throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		List<UseEquipmentVo> lis = equipmentMapper.findMonitorList(residentVo.getId());
		for(UseEquipmentVo ueVo:lis){
			if(ueVo.getEquipmentType().equals(EquipmentEnum.UNIT.getCode())){
				String equipmentId = ueVo.getEquipmentId().substring(0, 10);
				ueVo.setRooms(roomMapper.findEquRooms(residentVo.getId(),equipmentId));
			}
		}
		return lis;
	}

	@Override
	public void changeOftenUse(UseEquipmentVo equipmentVo) throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		UseCommonEquipmentPo ucepo = new UseCommonEquipmentPo();
		ucepo.setEquipmentId(equipmentVo.getId());
		ucepo.setResidentId(residentVo.getId());
		if(equipmentVo.isOftenUse()){
			commonEquiMapper.delete(ucepo);
			commonEquiMapper.insert(ucepo);
		}else{
			//删除
			commonEquiMapper.delete(ucepo);
		}
	}

}
