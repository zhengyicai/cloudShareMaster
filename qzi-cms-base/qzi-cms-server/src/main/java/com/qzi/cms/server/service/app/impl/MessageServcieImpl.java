/* 
 * 文件名：MessageServcieImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.common.vo.UseResidentMessageVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseMessageMapper;
import com.qzi.cms.server.service.app.MessageServcie;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 个人消息业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月18日
 */
@Service
public class MessageServcieImpl implements MessageServcie {
	@Resource
	private UseMessageMapper messageMapper;
	@Resource
	private CommonService commonService;

	@Override
	public List<UseMessageVo> findAll(Paging paging) throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return messageMapper.findAllByApp(rwoBounds, residentVo.getId());
	}

	@Override
	public long findCount() throws Exception {
		UseResidentVo residentVo = commonService.findResident();
		return messageMapper.findCountByApp(residentVo.getId());
	}

	@Override
	@Transactional
	public void delete(UseResidentMessageVo[] urmVos) {
		for(UseResidentMessageVo urm:urmVos){
			messageMapper.deleteByPrimaryKey(urm.getId());
		}
	}

}
