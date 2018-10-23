/* 
 * 文件名：UserController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月12日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qzi.cms.server.service.web.CommunityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UpdatePwVo;
import com.qzi.cms.server.service.web.UserService;

/**
 * 用户控制器
 * @author qsy
 * @version v1.0
 * @date 2017年6月12日
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;//用户业务层
	@Resource
	private HttpServletRequest request;
	@Resource
	private ConfUtils confUtils;
	@Resource
	private RedisService redisService;
	@Resource
	private CommunityService communityService;
	
	@GetMapping("/findUser")
	private RespBody findUser(){
		RespBody respBody = new RespBody();
		try {
			String token = request.getHeader("token");
			//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);
			//用户是否存在
			if(userVo != null){
				userVo.setPassword("");
				userVo.setSalt("");
				//存在
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户信息成功",userVo);
			}else{
				//不存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息失败");
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息异常");
			LogUtils.error("获取用户信息异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", userService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(userService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findRoles")
	public RespBody findRoles(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有角色数据成功", userService.findRoles());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有角色数据失败");
			LogUtils.error("查找所有角色数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	public RespBody add(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			//判断用户是否存在
			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());
			if(findUser == null){
				userService.add(userVo);
				
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息保存成功");
			}else{
				respBody.add(RespCodeEnum.ERROR.getCode(), "登录名已经存在");
			}
			
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息保存失败");
			LogUtils.error("用户信息保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	public RespBody update(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			userService.update(userVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息修改成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息修改失败");
			LogUtils.error("用户信息修改失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	public RespBody delete(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			userService.delete(userVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息删除失败");
			LogUtils.error("用户信息删除失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/updatePw")
	public RespBody updatePw(@RequestBody UpdatePwVo updatePwVo){
		RespBody respBody = new RespBody();
		try {
			if(!updatePwVo.getNewPw().equals(updatePwVo.getOkPw())){
				respBody.add(RespCodeEnum.ERROR.getCode(), "新密码和确认码不一致");
				return respBody;
			}
			String token = request.getHeader("token");
			//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);
			// 对输入密码进行加密
			String oldPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getOldPw(), userVo.getSalt());
			if(userVo.getPassword().equals(oldPw)){
				//对新密码进行加密
				String newPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getNewPw(), userVo.getSalt());
				//旧密码正确，调用业务层执行密码更新
				userService.updatePw(newPw,userVo.getId());
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改密码成功");
				//更新redis数据
				userVo.setPassword(newPw);
				redisService.putObj(token, userVo, confUtils.getSessionTimeout());
			}else{
				//旧密码输入有误
				respBody.add(RespCodeEnum.ERROR.getCode(), "旧密码输入不正确");
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改密码失败");
			LogUtils.error("修改密码失败！",ex);
		}
		return respBody;
	}


	
}
