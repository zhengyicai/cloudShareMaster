/* 
 * 文件名：FymServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.UpdatePwVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.service.app.FymService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 我的业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
@Service
public class FymServiceImpl implements FymService {
	@Resource
	private CommonService commonServcie;
	@Resource
	private UseResidentMapper residentMapper;
	
	@Override
	public void updatePwd(UpdatePwVo updatePwVo) throws Exception {
		//住户对象
		UseResidentVo residentVo = commonServcie.findResident();
		//数据库查找住户对象
		UseResidentPo residentPo = residentMapper.selectByPrimaryKey(residentVo.getId());
		//解密旧密码
		String loginOldPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getOldPw(), residentPo.getSalt());
		//判断旧密码输入是否正确
		if(loginOldPw.equals(residentPo.getPassword())){
			//旧密码正确,可以修改密码
			String salt = ToolUtils.getUUID();
			//加密新密码
			String loginNewPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getNewPw(), salt);
			//设置新密码
			residentPo.setPassword(loginNewPw);
			//设置新加密盐
			residentPo.setSalt(salt);
			//调用DAO修改数据
			residentMapper.updateByPrimaryKey(residentPo);
		}else{
			//旧密码错误
			throw new CommException("旧密码输入不正确");
		}
	}

	@Override
	public void updateOpenPwd(UpdatePwVo updatePwVo) throws Exception {
		//住户对象
		UseResidentVo residentVo = commonServcie.findResident();
		//数据库查找住户对象
		UseResidentPo residentPo = residentMapper.selectByPrimaryKey(residentVo.getId());
		residentPo.setOpenPwd(updatePwVo.getNewPw());
		residentMapper.updateByPrimaryKey(residentPo);
	}

}
