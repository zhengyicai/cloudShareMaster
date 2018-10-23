/* 
 * 文件名：LoginServiceImpl.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.enums.StateEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.LoginVo;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.server.mapper.SysUserMapper;
import com.qzi.cms.server.service.web.LoginService;

/**
 * 用户登录业务层实现类
 * 
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
@Service("webLogin")
public class LoginServiceImpl implements LoginService {
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private RedisService redisService;
	@Resource
	private ConfUtils confUtils;

	/**
	 * 用户登录验证
	 * 
	 * @param loginVo 登录信息
	 * @return token
	 * @throws Exception
	 */
	@Override
	public RespBody LoginIn(LoginVo loginVo) throws Exception {
		RespBody respBody = new RespBody();
		// 产生token
		String token = ToolUtils.getUUID();
		//验证码是否正确
		String picCode = redisService.getString(loginVo.getImgKey());
		if(picCode != null && picCode.equals(loginVo.getPicCode())){
			//验证码正确,查询用户信息
			SysUserVo userVo = userMapper.findByloginName(loginVo.getLoginName());
			// 是否查找到用户信息
			if (userVo == null) {
				// 不存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "登录用户不存在");
			} else {
				// 存在用户，判断是否有效
				if (userVo.getState().equals(StateEnum.DISABLE.getCode())) {
					// 无效用户
					respBody.add(RespCodeEnum.ERROR.getCode(), "登录用户已被禁用，请联系管理员！");
				} else {
					// 用户有效，对输入密码进行加密
					String loginPw = CryptUtils.hmacSHA1Encrypt(loginVo.getPassword(), userVo.getSalt());
					// 验证密码是否正确
					if (loginPw.equals(userVo.getPassword())) {
						//将对象转换成序列化对象
						// 登录成功,将用户信息存储到redis中
						if (!redisService.putObj(token, userVo, confUtils.getSessionTimeout()).equalsIgnoreCase("ok")) {
							// 缓存用户信息失败
							respBody.add(RespCodeEnum.ERROR.getCode(), "缓存用户信息失败！");
						} else {
							respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户登录成功", token);
						}
					} else {
						// 登录失败
						respBody.add(RespCodeEnum.ERROR.getCode(), "登录密码错误！");
					}
				}
			}
		}else{
			//验证码输入有误或失效
			respBody.add(RespCodeEnum.ERROR.getCode(), "验证码输入有误或已失效");
		}
		return respBody;
	}
	
}
