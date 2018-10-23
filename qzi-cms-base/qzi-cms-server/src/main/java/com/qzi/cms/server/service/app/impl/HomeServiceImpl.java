/* 
 * 文件名：HomeServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseBannerMapper;
import com.qzi.cms.server.mapper.UseMessageMapper;
import com.qzi.cms.server.mapper.UseNoticeMapper;
import com.qzi.cms.server.service.app.HomeService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 首页业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月30日
 */
@Service
public class HomeServiceImpl implements HomeService {
	@Resource
	private UseBannerMapper bannerMapper;
	@Resource
	private UseMessageMapper msgMapper;
	@Resource
	private UseNoticeMapper noticeMapper;
	@Resource
	private CommonService commonServcie;

	@Override
	public List<String> findBanners() {
		return bannerMapper.findBanners();
	}

	@Override
	public UseNoticeVo findTopNotice() throws Exception {
		//查找当前住户
		UseResidentVo residet = commonServcie.findResident();
		//调用dao
		return noticeMapper.findTopNotice(residet.getId());
	}

	@Override
	public UseMessageVo findTopMsg() throws Exception {
		//查找当前住户
		UseResidentVo residet = commonServcie.findResident();
		//调用dao
		return msgMapper.findTopMsg(residet.getId());
	}

	@Override
	public long findMsgCount() throws Exception {
		//查找当前住户
		UseResidentVo residet = commonServcie.findResident();
		//调用dao
		return msgMapper.findMsgCount(residet.getId());
	}

}
