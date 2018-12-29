/* 
 * 文件名：BuildingServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月5日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.qzi.cms.common.po.SysUnitPo;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.server.mapper.UseUnitMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.enums.StateEnum;
import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.server.mapper.UseBuildingMapper;
import com.qzi.cms.server.mapper.UseCommunityMapper;
import com.qzi.cms.server.mapper.UseRoomMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.BuildingService;

/**
 * 楼栋业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月5日
 */
@Service
public class BuildingServiceImpl implements BuildingService {
	@Resource
	private UseCommunityMapper communityMapper;
	@Resource
	private UseBuildingMapper buildMapper;
	@Resource
	private UseRoomMapper roomMapper;



	@Resource
	private UseUnitMapper useUnitMapper;
	@Resource
	private CommonService commonService;

	@Override
	public List<TreeVo> findTree() throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		return communityMapper.findTree(userVo.getId());
	}


	@Override
	public List<UseBuildingVo> findBuilding(String communityId, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return buildMapper.findBuilding(communityId,rowBounds);
	}

	@Override
	public long findCount(String communityId) {
		return buildMapper.findCount(communityId);
	}


	@Override
	public void updateState(UseBuildingVo buildingVo) {
		UseBuildingPo buildingPo = buildMapper.selectByPrimaryKey(buildingVo.getId());
		buildingPo.setState(buildingVo.getState());
		buildMapper.updateByPrimaryKey(buildingPo);
	}

	@Override
	public void createRoom(UseBuildingVo buildingVo) throws Exception {
		//修改楼栋参数信息
//		UseBuildingPo buildingPo = buildMapper.selectByPrimaryKey(buildingVo.getId());
//		buildingPo.setUnitNumber(buildingVo.getUnitNumber());
//		buildingPo.setFloorNumber(buildingVo.getFloorNumber());
//		buildingPo.setRoomNumber(buildingVo.getRoomNumber());
//		buildMapper.updateByPrimaryKey(buildingPo);


		//生成新的楼栋
		UseBuildingVo vo1 = new UseBuildingVo();
		vo1.setCommunityId(buildingVo.getCommunityId());
		vo1.setBuildingNo(String.format("%02d",Integer.parseInt(buildingVo.getBuildingNo())));
		UseBuildingPo buildPo = new UseBuildingPo();


		UseBuildingVo vo2 =  buildMapper.findCount1(vo1);
		if(buildMapper.findCount1(vo1) != null ){
			buildPo =  YBBeanUtils.copyProperties( buildMapper.findCount1(vo1), UseBuildingPo.class);
		}else{
			//UseBuildingPo buildPo = new UseBuildingPo();
			buildPo.setId(ToolUtils.getUUID());
			buildPo.setBuildingName(buildingVo.getBuildingNo()+"栋");
			buildPo.setBuildingNo(String.format("%02d", Integer.parseInt(buildingVo.getBuildingNo())));
			buildPo.setCommunityId(buildingVo.getCommunityId());
			buildPo.setState(StateEnum.NORMAL.getCode());
			buildPo.setUnitNumber(0);
			buildPo.setUnitName(buildingVo.getUnitName());
			buildPo.setFloorNumber(buildingVo.getFloorNumber());
			buildPo.setRoomNumber(buildingVo.getRoomNumber());
			buildMapper.insert(buildPo);
		}



		//生成新的单元
		SysUnitPo unitPo = new SysUnitPo();
		unitPo.setId(ToolUtils.getUUID());
		unitPo.setUnitNo(String.format("%02d",Integer.parseInt(buildingVo.getUnitName())));
		unitPo.setUnitName(buildingVo.getUnitName()+"单元");
		unitPo.setBuildingId(buildPo.getId());
		unitPo.setCommunityId(buildingVo.getCommunityId());
		unitPo.setFloorNumber(buildingVo.getFloorNumber());
		unitPo.setRoomNumber(buildingVo.getRoomNumber());
		unitPo.setState("10");
		unitPo.setRemark("");
		unitPo.setCreateTime(new Date());
		useUnitMapper.insert(unitPo);


		

		//查找小区
		UseCommunityPo communityPo = communityMapper.selectByPrimaryKey(buildPo.getCommunityId());
		//生成房间
		for(int u=1;u<=buildingVo.getUnitNumber();u++){
			for(int f=1;f<=buildingVo.getFloorNumber();f++){
				for(int r=1;r<=buildingVo.getRoomNumber();r++){
					UseRoomPo roomPo = new UseRoomPo();
					roomPo.setUnitName(String.format("%02d", u));
					roomPo.setId(ToolUtils.getUUID());
					roomPo.setBuildingId(buildPo.getId());
					roomPo.setState(StateEnum.NORMAL.getCode());
					roomPo.setRoomNo(communityPo.getCommunityNo()+String.format("%02d", Integer.parseInt(buildingVo.getBuildingNo()))+String.format("%02d", Integer.parseInt(buildingVo.getUnitName()))+String.format("%02d", f)+String.format("%02d", r));
					roomPo.setRoomName(f+String.format("%02d", r));
					roomPo.setCommunityId(buildPo.getCommunityId());
					roomPo.setUnitId(unitPo.getId());

					roomMapper.insert(roomPo);
				}
			}
		}
	}

	@Override
	public List<UseBuildingPo> findBuilding(String communityId) {
		return buildMapper.findByCommunityId(communityId);
	}

}
