/* 
 * 文件名：LoginService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.vo.LoginVo;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseResidentVo;

/**
 * 登录业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
public interface LoginService {

	/**
	 * 用户登录
	 * @param loginVo 用户信息
	 * @return token
	 * @throws CommException 
	 */
	public String LoginIn(LoginVo loginVo) throws CommException;




	public String LoginInSys(LoginVo loginVo) throws  CommException;

	/**
	 * 新用户注册
	 * @param residentVo 新用户对象
	 * @return 
	 * @throws Exception 
	 */
	public void register(UseResidentVo residentVo) throws Exception;
	public void registerUpdate(UseResidentVo residentVo) throws Exception;

	/**
	 * 通过手机号找回密码
	 * @param residentVo 用户对象
	 * @throws Exception 
	 */
	public void findPwd(UseResidentVo residentVo) throws Exception;


	/**
	 * 修改密码
	 * @param residentVo 用户对象
	 * @throws Exception
	 */
	public void updatePwd(UseResidentVo residentVo) throws Exception;

	public void updateName(UseResidentVo residentVo) throws  Exception;


}
