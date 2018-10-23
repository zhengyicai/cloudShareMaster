/* 
 * 文件名：LoginController.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

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
import com.qzi.cms.common.vo.LoginVo;
import com.qzi.cms.server.service.common.KaptchaService;
import com.qzi.cms.server.service.web.LoginService;

/**
 * 用户登陆控制器
 * 
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
	@Resource(name="webLogin")
	private LoginService loginService;
	@Resource
	private KaptchaService kaptchaService;

	@PostMapping("/loginIn")
	@SystemControllerLog(description="用户登录")
	public RespBody longIn(@RequestBody LoginVo loginVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			//验证FormBean
			if(hasErrors(loginVo,respBody)){
				// 验证通过，调用业务层，实现登录验证处理
				respBody = loginService.LoginIn(loginVo);
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户登录失败");
			LogUtils.error("用户登录失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 加载图片验证码
	 * @return
	 */
	@GetMapping("/loadImgCode")
	public RespBody loadImgCode() {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取图片验证码成功", kaptchaService.createCodeImg());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取图片验证码失败");
			LogUtils.error("用户登录失败！",ex);
		}
		return respBody;
	}
	
	
	
	/**
	 * 验证formBean
	 * @param loginVo
	 * @param respBody
	 * @return
	 */
	private boolean hasErrors(LoginVo loginVo,RespBody respBody){
		if(StringUtils.isEmpty(loginVo.getLoginName())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户名不能为空！");
			return false;
		}
		if(StringUtils.isEmpty(loginVo.getPassword())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "密码不能为空！");
			return false;
		}
		if(StringUtils.isEmpty(loginVo.getPicCode())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "验证码不能为空！");
			return false;
		}
		return true;
	}

}
