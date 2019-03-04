/* 
 * 文件名：RoomServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.qzi.cms.common.po.*;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.SysUnitVo;
import com.qzi.cms.server.mapper.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.service.web.RoomService;

/**
 * 房间管理业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月10日
 */
@Service
public class RoomServiceImpl implements RoomService {
	@Resource
	private UseCommunityMapper communityMapper;
	@Resource
	private UseBuildingMapper buildMapper;

	@Resource
	private UseUnitMapper useUnitMapper;
	@Resource
	private UseRoomMapper roomMapper;

	@Resource
	private UseRoomCardMapper  useRoomCardMapper;

	@Override
	public List<TreeVo> findTree(String userId) {
		List<TreeVo> rlis = communityMapper.findTree(userId);
		TreeVo buildingTV = null;
		TreeVo unitTV = null;

		for(TreeVo treeVo:rlis){
			List<UseBuildingPo> builds = buildMapper.findByCommunityId(treeVo.getId());
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
									//List<TreeVo> utiltvs = new ArrayList<>();
									for(int u=0;u<unit.size();u++){
										//unittvs.add(new TreeVo(String.format("%02d",Integer.parseInt(unit.get(u).getUnitName())),Integer.parseInt(unit.get(u).getUnitName())+"单元"));
										unitTV = new TreeVo();
										unitTV.setId(unit.get(u).getId());
										unitTV.setParentId(ubp.getId());
										unitTV.setValue(unit.get(u).getUnitName());
										unitTV.setLabel(unit.get(u).getUnitName());
										unitTV.setChildren(new ArrayList<>());
										unittvs.add(unitTV);
									}

								}
								buildingTV.setChildren(unittvs);

								buildtvs.add(buildingTV);
							}
			}

		}

		//小区
//		for(TreeVo treeVo:rlis){
//			List<UseBuildingPo> builds = buildMapper.findByCommunityId(treeVo.getId());
//			List<TreeVo> buildtvs = new ArrayList<>();
//			treeVo.setChildren(buildtvs);
//			//楼栋
//			for(UseBuildingPo ubp:builds){
//				buildingTV = new TreeVo();
//				buildingTV.setId(ubp.getId());
//				buildingTV.setValue(ubp.getBuildingName());
//				List<TreeVo> unittvs = new ArrayList<>();
//				buildingTV.setChildren(unittvs);
//				//单元
//				for(int u=1;u<=ubp.getUnitNumber();u++){
//					unittvs.add(new TreeVo(String.format("%02d",u),u+"单元"));
//				}
//				buildtvs.add(buildingTV);
//			}
//		}
		return rlis;
	}

	@Override
	public List<UseRoomVo> findBuilding(String buildingId, String unitId, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return roomMapper.findBuilding(buildingId,unitId,rowBounds);
	}


	@Override
	public long findCount(String buildingId, String unitId) {
		return roomMapper.findCount(buildingId,unitId);
	}

	@Override
	public void update(UseRoomVo roomVo) throws Exception {
		UseRoomPo roomPo = YBBeanUtils.copyProperties(roomVo, UseRoomPo.class);
		roomMapper.updateByPrimaryKey(roomPo);
	}

	@Override
	public void addCard(UseRoomCardPo useRoomCardPo) {

		useRoomCardPo.setState("10");
		//useRoomCardPo.setId(ToolUtils.getUUID());
		useRoomCardPo.setCreateTime(new Date());



		useRoomCardMapper.insert(useRoomCardPo);
	}

	@Override
	public void updateCard(UseRoomCardPo useRoomCardPo) {
		useRoomCardMapper.updateByPrimaryKey(useRoomCardPo);

	}

	@Override
	public void deleteCard(UseRoomCardPo useRoomCardPo) {
		useRoomCardMapper.deleteByPrimaryKey(useRoomCardPo);
	}

	@Override
	public void deleteRoomId(String roomId) {
		useRoomCardMapper.deleteRoomId(roomId);
	}

	@Override
	public List<UseRoomCardPo> cardList(String roomId) {
		return useRoomCardMapper.findRoomCard(roomId);
	}

}
