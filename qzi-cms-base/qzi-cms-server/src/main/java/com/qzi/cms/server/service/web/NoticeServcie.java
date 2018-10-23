/* 
 * 文件名：NoticeServcie.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseNoticeVo;

/**
 * 公告管理业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public interface NoticeServcie {
	/**
	 * 查找所有数据
	 * @param paging 分页
	 * @return 集合
	 * @throws Exception 
	 */
	public List<UseNoticeVo> findAll(Paging paging) throws Exception;

	/**
	 * 新增
	 * @param noticeVo
	 * @throws Exception 
	 */
	public void add(UseNoticeVo noticeVo) throws Exception;

	/**
	 * 修改
	 * @param noticeVo
	 * @throws Exception 
	 */
	public void update(UseNoticeVo noticeVo) throws Exception;

	/**
	 * 删除
	 * @param noticeVo
	 * @throws Exception 
	 */
	public void delete(UseNoticeVo noticeVo) throws Exception;

	/**
	 * 查找总记录数
	 * @return
	 * @throws Exception 
	 */
	public long findCount() throws Exception;
}
