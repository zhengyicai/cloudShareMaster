/* 
 * 文件名：ManagerMachineController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.server.service.web.EquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseAlarmRecordVo;
import com.qzi.cms.server.service.app.ManagerMachineService;

/**
 * 管理机
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
@RestController
@RequestMapping("/mgrMachine")
public class ManagerMachineController {
	@Resource
	private ManagerMachineService mgrMachineService;

	@Resource
	private EquipmentService equipmentService;
	/**
	 * 获取小区门口机
	 * @param communityNo 小区编号
	 * @param equipmentType 设备类型
	 * @return 响应数据
	 */
	@GetMapping("/findEquipments")
	public RespBody findEquipments(String communityNo,String equipmentType){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找小区门口机成功", mgrMachineService.findEquipments(communityNo,equipmentType));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找小区门口机失败");
			LogUtils.error("查找小区门口机失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 呼叫
	 * @param equipmentId 设备编号
	 * @param houseId 房间编号
	 * @return 响应数据
	 */
	@GetMapping("/call")
	public RespBody call(String equipmentId,String houseId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "呼叫成功", mgrMachineService.call(equipmentId,houseId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "呼叫失败");
			LogUtils.error("呼叫失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 验证开门密码
	 * @param equipmentId 设备编号
	 * @param houseId 房间编号
	 * @param openPwd 密码
	 * @return 响应数据
	 */
	@GetMapping("/validOpenPwd")
	public RespBody validOpenPwd(String equipmentId,String houseId,String openPwd){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			if(mgrMachineService.validOpenPwd(equipmentId,houseId,openPwd)){
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "开门密码正确");
			}else{
				respBody.add(RespCodeEnum.ERROR.getCode(), "开门密码错误");
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "开门密码错误");
			LogUtils.error("开门密码错误！",ex);
		}
		return respBody;
	}
	
	/**
	 * 获取设备信息
	 * @param equipmentId 设备编号
	 * @return 响应数据
	 */
	@GetMapping("/findEquipmentInfo")
	public RespBody findEquipmentInfo(String equipmentId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取设备信息成功", mgrMachineService.findEquipmentInfo(equipmentId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取设备信息失败");
			LogUtils.error("获取设备信息失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 获取手机号对应的房号
	 * @param mobile 设备编号
	 * @return 响应数据
	 */
	@GetMapping("/findRooms")
	public RespBody findRooms(String mobile){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取房间号成功", mgrMachineService.findRooms(mobile));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取房间号失败");
			LogUtils.error("获取房间号失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/addAlarmRecord")
	@SystemControllerLog(description="新增报警记录")
	public RespBody addAlarmRecord(@RequestBody UseAlarmRecordVo alarmRecordVo){
		RespBody respBody = new RespBody();
		try {
			if(StringUtils.isEmpty(alarmRecordVo.getEquipmentId())){
				respBody.add(RespCodeEnum.ERROR.getCode(), "设备编号不能为空");
				return respBody;
			}
			//调用业务层
			mgrMachineService.addAlarmRecord(alarmRecordVo);
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "新增报警记录成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "新增报警记录失败");
			LogUtils.error("新增报警记录失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 获取房间对应信息
	 * @param roomId 房间编号
	 * @return 响应数据
	 */
	@GetMapping("/findRoomById")
	public RespBody findRoomById(String roomId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取房间号成功", mgrMachineService.findRoomById(roomId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取房间号失败");
			LogUtils.error("获取房间号失败！",ex);
		}
		return respBody;
	}


	/**
	 * equipmentId and nowState
	 * @param equipmentVo
	 * @return
	 */
	@PostMapping("/nowStatus")
	@SystemControllerLog(description="当前设备门磁状态")
	public RespBody nowStatus(@RequestBody UseEquipmentVo equipmentVo){
			RespBody respBody = new RespBody();
			try {
				equipmentService.update(equipmentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "设备门磁状态修改成功");
			} catch (Exception ex) {
				respBody.add(RespCodeEnum.ERROR.getCode(), "设备门磁状态修改成功");
				LogUtils.error("设备删除失败！",ex);
			}
			return respBody;
		}
}
