/* 
 * 文件名：ManagerMachineServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.enums.YNEnum;
import com.qzi.cms.common.po.UseAlarmRecordPo;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.CallVo;
import com.qzi.cms.common.vo.UseAlarmRecordVo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.mapper.UseAlarmRecordMapper;
import com.qzi.cms.server.mapper.UseEquipmentMapper;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.service.app.ManagerMachineService;

/**
 * 管理机业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
@Service
public class ManagerMachineServiceImpl implements ManagerMachineService {
	@Resource
	private UseEquipmentMapper equipmentMapper;
	@Resource
	private UseAlarmRecordMapper alarmRecordMapper;
	@Resource
	private UseResidentMapper residentMapper;

	@Override
	public List<UseEquipmentVo> findEquipments(String communityNo, String equipmentType) {
		if(equipmentType ==null || equipmentType.trim().length()==0){
			return equipmentMapper.findAllEquipments(communityNo);
		}else{
			return equipmentMapper.findEquipments(communityNo, equipmentType);
		}
	}

	@Override
	public UseEquipmentVo findEquipmentInfo(String equipmentId) {
		return equipmentMapper.findEquipmentInfo(equipmentId);
	}


	@Override
	public void addAlarmRecord(UseAlarmRecordVo alarmRecordVo) throws Exception {
		UseAlarmRecordPo alarmRecordPo = YBBeanUtils.copyProperties(alarmRecordVo, UseAlarmRecordPo.class);
		alarmRecordPo.setId(ToolUtils.getCode());
		alarmRecordPo.setCreateTime(new Date());
		alarmRecordMapper.insert(alarmRecordPo);
	}

	@Override
	public List<CallVo> call(String equipmentId, String houseId) {
		String roomId = equipmentId.substring(0, 10)+houseId;
		return residentMapper.findCall(roomId);
	}

	@Override
	public boolean validOpenPwd(String equipmentId, String houseId, String openPwd) {
		List<CallVo> lis = call(equipmentId,houseId);
		for(CallVo callVo:lis){
			//验证当前用户是否是业主并密码匹配正确
			if(callVo.getOwner().equals(YNEnum.YES.getCode()) && callVo.getOpenPwd().equals(openPwd)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<UseRoomVo> findRooms(String mobile) {
		return residentMapper.findRooms(mobile);
	}

	@Override
	public UseRoomVo findRoomById(String roomId) {
		return residentMapper.findRoomById(roomId);
	}

}
