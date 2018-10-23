/* 
 * 文件名：SysLogsService.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月26日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common;

import com.qzi.cms.common.vo.SysLogsVo;

/**
 * 日志信息业务层接口
 * @author qsy
 * @version v1.0
 * @date 2016年11月26日
 */
public interface SysLogsService {

	/**
	 * 保存数据
	 * @param bsl 系统日志对象
	 * @throws Exception 
	 */
	public void save(SysLogsVo vo) throws Exception;

}
