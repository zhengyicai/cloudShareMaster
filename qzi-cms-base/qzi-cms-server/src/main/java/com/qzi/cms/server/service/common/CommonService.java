/* 
 * 文件名：CommonService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common;

import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseResidentVo;

/**
 * 通用业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
public interface CommonService {

	/**
	 * 获取登录的用户信息
	 * @return 用户信息
	 * @throws Exception 
	 */
	public SysUserVo findUser() throws Exception;

	/**
	 * 获取用户信息
	 * @return 住户信息
	 * @throws Exception 
	 */
	public UseResidentVo findResident() throws Exception;

	/**
	 * @param mobile
	 * @return
	 * @throws Exception 
	 */
	public void sendSms(String mobile) throws Exception;

	/**
	 * 查找参数
	 * @param paramName 参数名
	 * @return 参数值
	 */
	public String findParam(String paramName);
	
}
