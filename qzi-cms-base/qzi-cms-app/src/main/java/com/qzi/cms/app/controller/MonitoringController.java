/* 
 * 文件名：MonitoringController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.server.service.app.MonitoringService;

/**
 * 监控列表控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
@RestController
@RequestMapping("/monitor")
public class MonitoringController {
	@Resource
	private MonitoringService monitorServcie;
	
	@GetMapping("/findAll")
	public RespBody findAll(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找个人监控列表成功", monitorServcie.findAll());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找个人监控列表失败");
			LogUtils.error("查找个人监控列表失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/changeOftenUse")
	public RespBody changeOftenUse(@RequestBody UseEquipmentVo equipmentVo){
		RespBody respBody = new RespBody();
		try {
			//调用dao
			monitorServcie.changeOftenUse(equipmentVo);
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改常用设置成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改常用设置失败");
			LogUtils.error("修改常用设置失败！",ex);
		}
		return respBody;
	}

}
