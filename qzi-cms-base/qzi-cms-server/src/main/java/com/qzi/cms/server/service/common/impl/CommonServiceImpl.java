/* 
 * 文件名：CommonServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.util.YzsClientUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.common.vo.YzxSmsRespVo;
import com.qzi.cms.server.mapper.SysParameterMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.UserService;

/**
 * 通用业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	@Resource
	private RedisService redisService;
	@Resource
	private YzsClientUtils yzxUtils;
	@Resource
	private SysParameterMapper paramMapper;

	@Override
	public SysUserVo findUser() throws Exception {
		String token = request.getHeader("token");
		//读取用户信息
		return userService.SysUserVo(token);
	}

	@Override
	public UseResidentVo findResident() throws Exception {
		String token = request.getHeader("token");
		UseResidentVo residentVo = null;
		Object obj = redisService.getObj(token);
		if(obj != null && obj instanceof UseResidentPo){
			residentVo = YBBeanUtils.copyProperties(obj, UseResidentVo.class);
		}
		return residentVo;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public void sendSms(String mobile) throws Exception {
		Map<String,String> data = null;
		//判断是否2分钟内已经发送过消息
		Object obj = redisService.getObj(mobile);
		if(obj != null && obj instanceof Map){
			data = (Map<String, String>) obj;
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.valueOf(data.get("createTime")));
			calendar.add(Calendar.MINUTE, 2);
			if(calendar.getTimeInMillis()>new Date().getTime()){
				throw new CommException("操作过于频繁，请2分钟后再试");
			}
		}
		//生成验证码
		String smsCode = ToolUtils.getCode();
		data = new HashMap<>();
		data.put("smsCode", smsCode);
		data.put("createTime", String.valueOf(new Date().getTime()));
		//发送验证码到手机
		YzxSmsRespVo respVo = ToolUtils.toObject(yzxUtils.sendSMS(mobile, smsCode),YzxSmsRespVo.class);
		if(!respVo.getResp().getRespCode().equals("000000")){
			throw new CommException("获取短信验证码失败["+respVo.getResp().getRespCode()+"]");
		}
		//将验证码保存到redis中
		if (!redisService.putObj(mobile, data, 1800).equalsIgnoreCase("ok")) {
			// 缓存用户信息失败
			throw new CommException("缓存手机验证码失败！");
		}
	}

	@Override
	public String findParam(String paramName) {
		return paramMapper.findParam(paramName);
	}
	
	
}
