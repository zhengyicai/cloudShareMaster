/* 
 * 文件名：AlarmRecordController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.app.AlarmRecordService;

/**
 * 报警记录页面控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
@RestController
@RequestMapping("/alarmRecord")
public class AlarmRecordController {
	@Resource
	private AlarmRecordService alarmRecordService;
	
	/**
	 * 获取报警记录列表
	 * @param equipmentId 设备编号
	 * @return 响应数据
	 */
	@GetMapping("/findAlarmRecords")
	public RespBody findAlarmRecords(Date nowTime,Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取报警记录列表成功", alarmRecordService.findAlarmRecords(nowTime,paging));
			//保存分页对象
			paging.setTotalCount(alarmRecordService.findAlarmRecordCount(nowTime));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取报警记录列表失败");
			LogUtils.error("获取报警记录列表失败！",ex);
		}
		return respBody;
	}
	
}
