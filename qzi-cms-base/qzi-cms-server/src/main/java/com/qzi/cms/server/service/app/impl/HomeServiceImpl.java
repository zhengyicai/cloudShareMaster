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
import javax.servlet.http.HttpServletRequest;

import com.qzi.cms.common.po.SysUserPo;
import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.po.UseResidentRoomPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.*;
import com.qzi.cms.server.mapper.*;
import com.qzi.cms.server.service.web.UserService;
import org.springframework.stereotype.Service;

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
	@Resource
	private SysParameterMapper sysParameterMapper;


	@Resource
	private UseCommunityMapper useCommunityMapper;

	@Resource
	private UseEquipmentMapper useEquipmentMapper;

	@Resource
	private   UseResidentRoomMapper useResidentRoomMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	@Resource
	private RedisService redisService;

	@Resource
	private UseRoomMapper useRoomMapper;


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

	@Override
	public List<SysParameterVo> paramfindAll() throws Exception {
		return sysParameterMapper.findAllApp();
	}

	@Override
	public HomeUserCommunityVo findHomeUser() throws Exception {


		HomeUserCommunityVo homeVo = new HomeUserCommunityVo();
		String token = request.getHeader("token");
		UseResidentVo residentVo = null;
		SysUserVo sysUserVo = null;
		Object obj = redisService.getObj(token);
		if(obj != null && obj instanceof UseResidentPo){
			residentVo = YBBeanUtils.copyProperties(obj, UseResidentVo.class);
			homeVo.setUserName(residentVo.getName());
			UseResidentRoomPo useResidentRoomPo =   useResidentRoomMapper.findResidentDefault(residentVo.getId());
			if(useResidentRoomPo != null){
				UseCommunityPo useVo = useCommunityMapper.findOne(useResidentRoomPo.getCommunityId());
				homeVo.setAreaCode(useVo.getCommunityNo());
				homeVo.setCsCode(useVo.getCode());
				homeVo.setCommunity(useVo.getId());
				homeVo.setCommunityName(useVo.getCommunityName());


				homeVo.setRoomId(useRoomMapper.findOne(useResidentRoomPo.getRoomId()).getRoomNo());
			}else{

			}

		}else if(obj != null && obj instanceof SysUserVo){
		    sysUserVo = YBBeanUtils.copyProperties(obj, SysUserVo.class);
		    UseCommunityVo useVo =   useCommunityMapper.findUser(sysUserVo.getId());
			if(useVo!=null){
				homeVo.setAreaCode(useVo.getCommunityNo());
				homeVo.setCsCode(useVo.getCode());
				homeVo.setCommunity(useVo.getId());
				homeVo.setCommunityName(useVo.getCommunityName());
				homeVo.setEquList(useEquipmentMapper.communityIdList(useVo.getId()));
				homeVo.setUserName(sysUserVo.getLoginName());
			}

		}


		return homeVo;
	}

}
