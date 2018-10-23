/* 
 * 文件名：CommonController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月31日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 通用控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
@RestController
@RequestMapping("/common")
public class CommonController {
	@Resource
	private CommonService commonServcie;
	
	
	@GetMapping("/findUser")
	public RespBody findUser() {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取用户信息成功",commonServcie.findResident());
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("获取用户信息失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息失败");
			LogUtils.error("获取用户信息失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/sms")
	@SystemControllerLog(description="获取手机短信验证码")
	public RespBody sendSms(String mobile) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			commonServcie.sendSms(mobile);
			respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取手机短信验证码成功");
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("获取手机短信验证码失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取手机短信验证码失败");
			LogUtils.error("获取手机短信验证码失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 获取用户参数
	 * @param paramName 参数名
	 * @return 参数值
	 */
	@GetMapping("/findParam")
	public RespBody findParam(String paramName) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取用户信息成功",commonServcie.findParam(paramName));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息失败");
			LogUtils.error("获取用户信息失败！",ex);
		}
		return respBody;
	}
	
	

}
