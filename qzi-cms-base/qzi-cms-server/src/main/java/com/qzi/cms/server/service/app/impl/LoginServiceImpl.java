/* 
 * 文件名：LoginServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.qzi.cms.server.mapper.UseCommunityResidentMapper;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.LoginVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.service.app.LoginService;
import com.qzi.cms.server.service.web.NewResidentService;

/**
 * 登录业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
@Service("appLogin")
public class LoginServiceImpl implements LoginService {
	@Resource
	private UseResidentMapper residentMapper;
	@Resource
	private RedisService redisService;
	@Resource
	private ConfUtils confUtils;
	@Resource
	private NewResidentService newResidentService;
	@Resource
	private UseCommunityResidentMapper useCommunityResidentMapper;
	

	@Override
	public String LoginIn(LoginVo loginVo)throws CommException{
		String token = null;
		UseResidentPo resident = residentMapper.findMobile(loginVo.getLoginName());
		// 是否查找到用户信息
		if (resident == null) {
			// 不存在
			throw new CommException("登录用户不存在");
		} else {
			System.out.print(useCommunityResidentMapper.existsLoginCR(resident.getId()));
			if(useCommunityResidentMapper.existsLoginCR(resident.getId())){
				System.out.print(useCommunityResidentMapper.existsLoginCR(resident.getId()));
				//throw new CommException("该账号已被管理员取消，请重新注册！");
			}else if(useCommunityResidentMapper.existsLoginOutCR(resident.getId())){
				throw new CommException("该账号正在审核中");
			}else{
				throw new CommException("该账号已被管理员删除，请重新注册！");
			}
			// 用户有效，对输入密码进行加密
			String loginPw = CryptUtils.hmacSHA1Encrypt(loginVo.getPassword(), resident.getSalt());
			// 验证密码是否正确
			if (loginPw.equals(resident.getPassword())) {
				String key = ToolUtils.getUUID();
				//将对象转换成序列化对象
				// 登录成功,将用户信息存储到redis中
				if (!redisService.putObj(key, resident, confUtils.getSessionTimeout()).equalsIgnoreCase("ok")) {
					// 缓存用户信息失败
					throw new CommException("缓存用户信息失败！");
				} else {
					token = key;
				}
			} else {
				// 登录失败
				throw new CommException("登录密码错误！");
			}
		}
		return token;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void register(UseResidentVo residentVo) throws Exception{
		if(residentMapper.existsMobile(residentVo.getMobile())){
			//UseResidentPo po =  residentMapper.findMobile(residentVo.getMobile());

		}
		String smsCode = "";
		//读取redis中的短信验证码
		Object obj = redisService.getObj(residentVo.getMobile());
		if(obj != null && obj instanceof Map){
			Map<String, String> data = (Map<String, String>) obj;
			smsCode = data.get("smsCode");
		}
		if(!smsCode.equals(residentVo.getSmsCode())){
			throw new CommException("手机验证码输入有误");
		}
		newResidentService.add(residentVo);
	}

	public void registerUpdate(UseResidentVo residentVo) throws Exception{
			//if(residentMapper.existsMobile(residentVo.getMobile())){
				//UseResidentPo po =  residentMapper.findMobile(residentVo.getMobile());

			//}
			String smsCode = "";
			//读取redis中的短信验证码
			Object obj = redisService.getObj(residentVo.getMobile());
			if(obj != null && obj instanceof Map){
				Map<String, String> data = (Map<String, String>) obj;
				smsCode = data.get("smsCode");
			}
			if(!smsCode.equals(residentVo.getSmsCode())){
				throw new CommException("手机验证码输入有误");
			}
			newResidentService.update(residentVo);
		}

	@Override
	@SuppressWarnings("unchecked")
	public void findPwd(UseResidentVo residentVo) throws Exception{
		if(!residentMapper.existsMobile(residentVo.getMobile())){
			throw new CommException("手机号不存在,请先注册");
		}
		String smsCode = "";
		//读取redis中的短信验证码
		Object obj = redisService.getObj(residentVo.getMobile());
		if(obj != null && obj instanceof Map){
			Map<String, String> data = (Map<String, String>) obj;
			smsCode = data.get("smsCode");
		}
		if(!smsCode.equals(residentVo.getSmsCode())){
			throw new CommException("手机验证码输入有误");
		}
		String salt = ToolUtils.getUUID();
		// 用户有效，对输入密码进行加密
		String loginPw = CryptUtils.hmacSHA1Encrypt(residentVo.getPassword(), salt);
		//修改密码
		residentMapper.updatePwd(residentVo.getMobile(),loginPw,salt);
	}


}
