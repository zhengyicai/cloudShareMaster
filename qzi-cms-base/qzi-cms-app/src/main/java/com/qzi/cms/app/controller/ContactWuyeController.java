/* 
 * 文件名：ContactWuyeController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年9月1日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.app.ContactWuyeService;

/**
 * 联系物业控制器
 * @author qsy
 * @version v1.0
 * @date 2017年9月1日
 */
@RestController
@RequestMapping("/contactWuye")
public class ContactWuyeController {
	@Resource
	private ContactWuyeService contactWuyeService;
	
	/**
	 * 获取用户管理机
	 * @return 响应数据
	 */
	@GetMapping("/findMgrMachines")
	public RespBody findMgrMachines(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户管理机成功", contactWuyeService.findMgrMachines());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户管理机失败");
			LogUtils.error("获取用户管理机失败！",ex);
		}
		return respBody;
	}
	
}
