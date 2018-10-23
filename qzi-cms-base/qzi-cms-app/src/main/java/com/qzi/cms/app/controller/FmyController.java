/* 
 * 文件名：FmyController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

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
import com.qzi.cms.common.vo.UpdatePwVo;
import com.qzi.cms.server.service.app.FymService;

/**
 * 我的控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
@RestController
@RequestMapping("/fmy")
public class FmyController {
	@Resource
	private FymService fmyServcie;
	
	@PostMapping("/updatePwd")
	@SystemControllerLog(description="修改密码")
	public RespBody updatePwd(@RequestBody UpdatePwVo updatePwVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			//验证FormBean
			if(hasErrorsByFindPwd(updatePwVo,respBody)){
				fmyServcie.updatePwd(updatePwVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"修改密码成功");
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
	
	@PostMapping("/updateOpenPwd")
	@SystemControllerLog(description="设置开门密码")
	public RespBody updateOpenPwd(@RequestBody UpdatePwVo updatePwVo) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {
			if(StringUtils.isEmpty(updatePwVo.getNewPw())){
				respBody.add(RespCodeEnum.ERROR.getCode(), "密码不能为空");
				return respBody;
			}
			fmyServcie.updateOpenPwd(updatePwVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(),"修改密码成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "找回密码失败");
			LogUtils.error("找回密码失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 修改密码数据验证
	 * @param updatePwVo 修改密码对象
	 * @param respBody
	 * @return
	 */
	private boolean hasErrorsByFindPwd(UpdatePwVo updatePwVo, RespBody respBody) {
		if(StringUtils.isEmpty(updatePwVo.getOldPw())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "旧密码不能为空");
			return false;
		}
		if(StringUtils.isEmpty(updatePwVo.getNewPw())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "新密码不能为空");
			return false;
		}
		if(updatePwVo.getNewPw().length()<6  || updatePwVo.getNewPw().length()>20){
			respBody.add(RespCodeEnum.ERROR.getCode(), "新密码必须输入6～20个字符或数字");
			return false;
		}
		if(StringUtils.isEmpty(updatePwVo.getOkPw())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "确认密码不能为空");
			return false;
		}
		if(!updatePwVo.getOkPw().equals(updatePwVo.getNewPw())){
			respBody.add(RespCodeEnum.ERROR.getCode(), "新密码和确认密码输入不一致");
			return false;
		}
		return true;
	}
}
