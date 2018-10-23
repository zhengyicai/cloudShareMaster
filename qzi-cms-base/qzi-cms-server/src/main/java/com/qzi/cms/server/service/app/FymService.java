/* 
 * 文件名：FymService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import com.qzi.cms.common.vo.UpdatePwVo;

/**
 * 我的业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
public interface FymService {

	/**
	 * 修改用户密码
	 * @param updatePwVo 修改密码对象
	 * @throws Exception 
	 */
	public void updatePwd(UpdatePwVo updatePwVo) throws Exception;

	/**
	 * @param updatePwVo
	 * @throws Exception 
	 */
	public void updateOpenPwd(UpdatePwVo updatePwVo) throws Exception;

}
