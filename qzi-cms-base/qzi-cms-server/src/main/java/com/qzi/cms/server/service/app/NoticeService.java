/* 
 * 文件名：NoticeService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseNoticeVo;

/**
 * 小区公告业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
public interface NoticeService {

	/**
	 * 查询小区公告列表
	 * @param paging
	 * @return
	 * @throws Exception 
	 */
	public List<UseNoticeVo> findAll(Paging paging) throws Exception;

	/**
	 * 查询小区公告总记录数
	 * @return
	 * @throws Exception 
	 */
	public long findCount() throws Exception;

}
