/* 
 * 文件名：MessageServcie.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.common.vo.UseResidentMessageVo;

/**
 * 个人消息业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
public interface MessageServcie {

	/**
	 * 分页查找个人消息数据
	 * @param paging
	 * @return 消息列表
	 * @throws Exception 
	 */
	public List<UseMessageVo> findAll(Paging paging) throws Exception;

	/**
	 * 查找个人消息总记录数
	 * @return 总记录数
	 * @throws Exception 
	 */
	public long findCount() throws Exception;

	/**
	 * 批量删除个人消息
	 * @param urmVos 消息数组 
	 * @return
	 */
	public void delete(UseResidentMessageVo[] urmVos);

}
