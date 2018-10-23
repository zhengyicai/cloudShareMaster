/* 
 * 文件名：LoginService.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.vo.LoginVo;

/**
 * 用户登录业务层接口
 * 
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
public interface LoginService {

	/**
	 * 用户登录
	 * @param loginVo 登录信息
	 * @return
	 * @throws Exception
	 */
	public RespBody LoginIn(LoginVo loginVo) throws Exception;
	


}
