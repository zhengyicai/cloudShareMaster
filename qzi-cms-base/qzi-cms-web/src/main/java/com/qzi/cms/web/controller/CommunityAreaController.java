/* 
 * 文件名：CommunityAreaController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月28日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UpdatePwVo;
import com.qzi.cms.server.service.web.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.common.vo.UseCommunityVo;
import com.qzi.cms.server.service.web.CommunityService;

/**
 * 住宅小区控制器
 * @author qsy
 * @version v1.0
 * @date 2017年6月28日
 */
@RestController
@RequestMapping("/community")
public class CommunityAreaController {
	@Resource
	private CommunityService communityService;
	@Resource
	private UserService userService;//用户业务层
	@Resource
	private HttpServletRequest request;
	@Resource
	private ConfUtils confUtils;
	@Resource
	private RedisService redisService;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住宅小区数据成功", communityService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(communityService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住宅小区数据失败");
			LogUtils.error("查找所有住宅小区数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findCitys")
	public RespBody findCitys(String parentCode){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找中国城市数据成功", communityService.findCitys(parentCode));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找中国城市数据失败");
			LogUtils.error("查找中国城市数据失败！",ex);
		}
		return respBody;
	}
	
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增住宅小区")
	public RespBody add(@RequestBody UseCommunityVo communityVo){
		RespBody respBody = new RespBody();
		try {
			communityService.add(communityVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住宅小区保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住宅小区保存失败");
			LogUtils.error("住宅小区保存失败！",ex);
		}
		return respBody;
	}

	/**
	 * 2018/11/15
	 * @param communityVo
	 * @return
	 */
	@PostMapping("/workAdd")
	@SystemControllerLog(description="新增住宅小区")
	public RespBody workAdd(@RequestBody UseCommunityVo communityVo){
		RespBody respBody = new RespBody();
		try {
			communityService.wordAdd(communityVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住宅小区保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住宅小区保存失败");
			LogUtils.error("住宅小区保存失败！",ex);
		}
		return respBody;
	}

	
	@PostMapping("/update")
	@SystemControllerLog(description="修改住宅小区")
	public RespBody update(@RequestBody UseCommunityVo communityVo){
		RespBody respBody = new RespBody();
		try {
			communityService.update(communityVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住宅小区保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住宅小区保存失败");
			LogUtils.error("住宅小区保存失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findAdmin")
	public RespBody findAdmin(String communityId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找小区绑定管理员数据成功", communityService.findAdmin(communityId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找小区绑定管理员数据失败");
			LogUtils.error("查找小区绑定管理员数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/saveAdmin")
	@SystemControllerLog(description="保存住宅小区管理员关系")
	public RespBody add(@RequestBody AdminVo adminVo){
		RespBody respBody = new RespBody();
		try {
			communityService.add(adminVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "保存住宅小区管理员关系成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "保存住宅小区管理员关系失败");
			LogUtils.error("保存住宅小区管理员关系失败！",ex);
		}
		return respBody;
	}


	/**
	 * 添加小区物业消息
	 * @param userVo
	 * @return
	 */
	@PostMapping("/addUser")
	public RespBody add(@RequestBody SysUserVo  userVo){
		RespBody respBody = new RespBody();
		try {
			//判断用户是否存在
			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());
			if(findUser == null){
				userService.addCommun(userVo);

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

	@PostMapping("/updatePw")
	public RespBody updatePw(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {

			String token = request.getHeader("token");
			//SysUserVo userVo1 = userService.SysUserVo(token);

			SysUserVo sv =   userService.findOne(userVo.getId());
		/*	//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);*/
			// 对输入密码进行加密


				//对新密码进行加密
				String newPw = CryptUtils.hmacSHA1Encrypt(userVo.getPassword(), sv.getSalt());
				//旧密码正确，调用业务层执行密码更新
				userService.updatePw(newPw,userVo.getId());
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改密码成功");


		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改密码失败");
			LogUtils.error("修改密码失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/userDelete")
	public RespBody delete(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			communityService.delete(userVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息删除失败");
			LogUtils.error("用户信息删除失败！",ex);
		}
		return respBody;
	}


	
}
