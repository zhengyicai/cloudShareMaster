/* 
 * 文件名：NoticeServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseNoticeMapper;
import com.qzi.cms.server.service.app.NoticeService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 小区公告业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
@Service("appService")
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private UseNoticeMapper noticeMapper;
	@Resource
	private CommonService commonService;

	@Override
	public List<UseNoticeVo> findAll(Paging paging) throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return noticeMapper.findAllByApp(rwoBounds,residentVo.getId());
	}

	@Override
	public long findCount() throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		return noticeMapper.findCountByApp(residentVo.getId());
	}

}
