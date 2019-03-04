/* 
 * 文件名：HomeServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qzi.cms.common.po.*;
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
	@Resource
	private UseBuildingMapper buildMapper;

	@Resource
	private UseUnitMapper useUnitMapper;


	@Resource
	private UseRoomCardMapper useRoomCardMapper;

	@Resource
	private SysUserMapper sysUserMapper;

	@Resource
	private UseCardEquipmentMapper useCardEquipmentMapper;

	@Resource
	private UseUserCardEquipmentMapper useUserCardEquipmentMapper;

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
				homeVo.setUserId(useVo.getId());
				homeVo.setResidentId(residentVo.getId());
				if(useVo.getSysUserId() != null){
					SysUserVo sys =  sysUserMapper.findOne(useVo.getSysUserId());
					homeVo.setAdminMobile(sys.getMobile());
				}

				UseRoomPo useRoomPo =  useRoomMapper.findOne(useResidentRoomPo.getRoomId());
				homeVo.setRoomId(useRoomPo.getRoomNo());

				homeVo.setEquRoomState(useCardEquipmentMapper.findRoomIdCount(useRoomPo.getId()));

				homeVo.setDefaultRoomId(useRoomPo.getId());

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
				homeVo.setUserId(sysUserVo.getId());
				homeVo.setAdminMobile(sysUserVo.getMobile());
				homeVo.setResidentId(sysUserVo.getId());
				homeVo.setEquRoomState(useUserCardEquipmentMapper.findCommunityCount(useVo.getId()));
			}

		}


		return homeVo;
	}

	@Override
	public List<TreeVo> findTree(String id) {

		TreeVo buildingTV = null;
		TreeVo unitTV = null;

		TreeVo treeVo = new TreeVo();
		List<UseBuildingPo> builds = buildMapper.findByCommunityId(id);
		List<TreeVo> buildtvs = new ArrayList<>();
		treeVo.setChildren(buildtvs);
		//楼栋
		if(builds.size()>0){
			for(UseBuildingPo ubp:builds){
				buildingTV = new TreeVo();
				buildingTV.setId(ubp.getId());
				buildingTV.setValue(ubp.getBuildingName());
				buildingTV.setLabel(ubp.getBuildingName());
				List<TreeVo> unittvs = new ArrayList<>();

				//单元
				SysUnitPo unitPo = new SysUnitPo();
				unitPo.setBuildingId(ubp.getId());
				List<SysUnitVo> unit = useUnitMapper.findAll(unitPo);
				if(unit.size()>0){
					List<TreeVo> utiltvs = new ArrayList<>();
					for(int u=0;u<unit.size();u++){

						unitTV = new TreeVo();
						unitTV.setId(unit.get(u).getId());
						unitTV.setParentId(ubp.getId());
						unitTV.setValue(unit.get(u).getUnitName());
						unitTV.setLabel(unit.get(u).getUnitName());
						unitTV.setChildren(useRoomCardMapper.findUnitRoomCard(unit.get(u).getId()));
						unittvs.add(unitTV);

					}

				}
				buildingTV.setChildren(unittvs);

				buildtvs.add(buildingTV);
			}
		}

		return buildtvs;
	}

}
