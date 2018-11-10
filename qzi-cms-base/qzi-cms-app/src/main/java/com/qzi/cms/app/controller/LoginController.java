/* 
 * 文件名：LoginController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月31日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.server.service.app.RegisterService;
import com.qzi.cms.server.service.common.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.LoginVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.service.app.LoginService;

/**
 * 登录控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	@Resource(name="appLogin")
	private LoginService loginService;

	@Resource
	private RegisterService registerService;
	@Resource
	private CommonService commonService;



	@PostMapping("/loginIn")
	@SystemControllerLog(description="用户登录")
	public RespBody longIn(@RequestBody LoginVo loginVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			//验证FormBean
			if(hasErrors(loginVo,respBody)){
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"用户登录成功",loginService.LoginIn(loginVo));
			}
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("用户登录失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户登录失败");
			LogUtils.error("用户登录失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/register")
	@SystemControllerLog(description="用户登录")
	public RespBody register(@RequestBody UseResidentVo residentVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			//验证FormBean
			if(hasErrors(residentVo,respBody)){
				loginService.register(residentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"用户注册成功");
			}
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("用户注册失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户注册失败");
			LogUtils.error("用户注册失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/findPwd")
	@SystemControllerLog(description="找回密码")
	public RespBody findPwd(@RequestBody UseResidentVo residentVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			//验证FormBean
			if(hasErrorsByFindPwd(residentVo,respBody)){
				loginService.findPwd(residentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"找回密码成功");
			}
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("找回密码失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "找回密码失败");
			LogUtils.error("找回密码失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/updatePwd")
	@SystemControllerLog(description="修改密码")
	public RespBody updatePwd(@RequestBody UseResidentVo residentVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {


			UseResidentVo vo = commonService.findResident();

			   String lowPwd = CryptUtils.hmacSHA1Encrypt(residentVo.getOldPwd(),vo.getSalt());
			   if(vo !=null){
				   if(lowPwd.equals(vo.getPassword())){
				   		residentVo.setMobile(vo.getMobile());
					   loginService.updatePwd(residentVo);
					   respBody.add(RespCodeEnum.SUCCESS.getCode(),"修改密码成功");
				   }else{
					   respBody.add(RespCodeEnum.ERROR.getCode(), "用户原密码输入有误");
				   }
			   }

		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("修改密码失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改密码失败");
			LogUtils.error("修改密码失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/updateName")
	@SystemControllerLog(description="修改名称")
	public RespBody updateName(@RequestBody UseResidentVo residentVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {


			UseResidentVo vo = commonService.findResident();

			if(vo !=null){

					residentVo.setMobile(vo.getMobile());
					loginService.updateName(residentVo);
					respBody.add(RespCodeEnum.SUCCESS.getCode(),"修改昵称成功");
			}

		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("修改昵称失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改昵称失败");
			LogUtils.error("修改昵称失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 找回密码数据验证
	 * @param residentVo 用户
	 * @param respBody
	 * @return
	 */
	private boolean hasErrorsByFindPwd(UseResidentVo residentVo, RespBody respBody) {
		if(StringUtils.isEmpty(residentVo.getMobile())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号不能为空");
			return false;
		}
		if(!ToolUtils.isMobile(residentVo.getMobile())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号输入有误");
			return false;
		}
		if(StringUtils.isEmpty(residentVo.getSmsCode())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码不能为空");
			return false;
		}
		if(residentVo.getSmsCode().length()!=6){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码必须输入6位数字");
			return false;
		}
		if(StringUtils.isEmpty(residentVo.getPassword())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "密码不能为空");
			return false;
		}
		if(residentVo.getPassword().length()<6  || residentVo.getPassword().length()>20){
			respBody.add(RespCodeEnum.ERROR.getCode(), "密码必须输入6～20个字符或数字");
			return false;
		}
		return true;
	}

	/**
	 *  验证用户注册信息
	 * @param residentVo 新用户
	 * @param respBody
	 * @return
	 */
	private boolean hasErrors(UseResidentVo residentVo, RespBody respBody) {
		if(StringUtils.isEmpty(residentVo.getName())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "姓名不能为空");
			return false;
		}
		if(residentVo.getName().length()<2  || residentVo.getName().length()>6){
			respBody.add(RespCodeEnum.ERROR.getCode(), "姓名必须输入2～6个字符");
			return false;
		}
		if(StringUtils.isEmpty(residentVo.getMobile())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号不能为空");
			return false;
		}
		if(!ToolUtils.isMobile(residentVo.getMobile())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号输入有误");
			return false;
		}
		if(StringUtils.isEmpty(residentVo.getSmsCode())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码不能为空");
			return false;
		}
		if(residentVo.getSmsCode().length()!=6){
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码必须输入6位数字");
			return false;
		}
		if(StringUtils.isEmpty(residentVo.getPassword())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "密码不能为空");
			return false;
		}
		if(residentVo.getPassword().length()<6  || residentVo.getPassword().length()>20){
			respBody.add(RespCodeEnum.ERROR.getCode(), "密码必须输入6～20个字符或数字");
			return false;
		}
		return true;
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
		return true;
	}
	
}
