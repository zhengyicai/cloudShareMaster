/* 
 * 文件名：EquipmentServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.qzi.cms.common.po.*;
import com.qzi.cms.common.vo.*;
import com.qzi.cms.server.mapper.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.enums.EquipmentEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.util.YzsClientUtils;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.EquipmentService;

/**
 * 设备管理业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Resource
	private CommonService commonService;
	@Resource
	private UseCommunityMapper communityMapper;
	@Resource
	private UseBuildingMapper buildMapper;
	@Resource
	private UseEquipmentMapper equipmentMapper;
	@Resource
	private YzsClientUtils clientUtils;
	@Resource
	private UseUnitMapper useUnitMapper;
	@Resource
	private UseRoomCardMapper useRoomCardMapper;

	@Resource
	private UseCardEquipmentMapper useCardEquipmentMapper;


	@Resource
	private UseUserCardEquipmentMapper useUserCardEquipmentMapper;

	@Override
	public List<OptionVo> findCommunitys() throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		return communityMapper.findAllByUserId(userVo.getId());
	}

	@Override
	public List<OptionVo> findBuildings(String communityId) {
		return buildMapper.findOneBuildings(communityId);
	}

	@Override
	public List<OptionVo> findUnits(String unitNo,String buildingId) {
		List<OptionVo> ropts = new ArrayList<>();
		List<SysUnitPo> buildingPoList = buildMapper.findAllUnits(buildingId);
		for(int u=0;u<buildingPoList.size();u++){
			ropts.add(new OptionVo(buildingPoList.get(u).getUnitNo(), buildingPoList.get(u).getUnitName()));
		}
		return ropts;
	}

	@Override
	public List<UseEquipmentVo> findAll(Paging paging, String criteria) throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		//分页对象
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return equipmentMapper.findAll(rwoBounds,criteria,userVo.getId());
	}

	@Override
	public long findCount(String criteria) throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		return equipmentMapper.findCount(criteria,userVo.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseEquipmentVo equipmentVo) throws Exception {
		//保存设备信息
		UseEquipmentPo equipmentPo = YBBeanUtils.copyProperties(equipmentVo, UseEquipmentPo.class);
		String equIe = ToolUtils.getUUID();
		equipmentPo.setId(equIe);
		equipmentPo.setCreateTime(new Date());
		equipmentPo.setEquipmentId(generatorEquId(equipmentVo));
		if(!EquipmentEnum.UNIT.getCode().equals(equipmentVo.getEquipmentType())){
			equipmentPo.setBuildingId(null);
			equipmentPo.setUnitName(null);
		}

		//设置围墙机的数据
		if("20".equals(equipmentPo.getEquipmentType())){
			List<UseRoomCardPo> cardList =  useRoomCardMapper.findCommunityId(equipmentPo.getCommunityId());
			if(cardList.size()>0){
				UseCardEquipmentPo equpo = new UseCardEquipmentPo();
				for(UseRoomCardPo po2:cardList){

					equpo.setId(ToolUtils.getUUID());
					equpo.setRoomId(po2.getRoomId());
					equpo.setCardId(po2.getId());
					equpo.setEquipmentId(equIe);
					equpo.setCreateTime(new Date());
					equpo.setState("20");
					equpo.setEquId(equipmentPo.getEquId());
					useCardEquipmentMapper.insert(equpo);
				}
			}

		//设置单元机的数据
		}else if("30".equals(equipmentPo.getEquipmentType())){
			SysUnitVo sysUnitVo =  useUnitMapper.findAllUnit(equipmentPo.getBuildingId(),String.format("%02d",equipmentPo.getUnitName()));
			if(sysUnitVo!=null){

				List<UseRoomCardPo> list = useRoomCardMapper.findUnitId(sysUnitVo.getId());
				if(list.size()>0){
					UseCardEquipmentPo equpo = new UseCardEquipmentPo();
					for(UseRoomCardPo po1:list){
						equpo.setId(ToolUtils.getUUID());
						equpo.setRoomId(po1.getRoomId());
						equpo.setCardId(po1.getId());
						equpo.setEquipmentId(equIe);
						equpo.setCreateTime(new Date());
						equpo.setState("20");
						equpo.setEquId(equipmentPo.getEquId());
						useCardEquipmentMapper.insert(equpo);

					}
				}



			}
		}





		equipmentMapper.insert(equipmentPo);


	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseEquipmentVo equipmentVo) throws Exception {
		//注销云之讯账户
	//	ClientVo client = new ClientVo();
	//	client.setUserId(equipmentVo.getEquipmentId());
    //		clientUtils.deleteClient(client);
		//删除设备
		equipmentMapper.deleteByPrimaryKey(equipmentVo.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(UseEquipmentVo equipmentVo) throws Exception {
		//UseEquipmentVo vo = equipmentMapper.findEquipmentInfo(equipmentVo.getEquipmentId());
		//保存设备信息
		UseEquipmentPo equipmentPo = YBBeanUtils.copyProperties(equipmentVo, UseEquipmentPo.class);
		equipmentPo.setNowDate(new Date());
		equipmentPo.setNowState(equipmentVo.getNowState());
		equipmentMapper.updateByPrimaryKey(equipmentPo);

		//修改设备与房卡的绑定状态
		useUserCardEquipmentMapper.updateAllUserCardEquipment(equipmentVo.getEquCardState(),equipmentVo.getId());
		useCardEquipmentMapper.updateAllCardEquipment(equipmentVo.getEquCardState(),equipmentVo.getId());
	}

	@Override
	public Integer findCommunityCount(String communityId) {
		return equipmentMapper.communityIdCount(communityId);
	}

	/**
	 * 自动生成设备编号
	 * @param equipmentVo 设备信息
	 * @return 设备编号
	 */
	private String generatorEquId(UseEquipmentVo equipmentVo){
		if(EquipmentEnum.MANAGE.getCode().equals(equipmentVo.getEquipmentType())){
			//安卓管理机
			String eid = equipmentMapper.findByCondition(equipmentVo.getCommunityId(),equipmentVo.getEquipmentType());
			if(eid == null){
				//没有存在设备
				//获取小区对象
				UseCommunityPo cpo = communityMapper.selectByPrimaryKey(equipmentVo.getCommunityId());
				eid = cpo.getCommunityNo()+"000001";
			}else{
				//已存在设备
				eid = String.format("%012d",Integer.parseInt(eid)+1);
			}
			return eid;
		}else if(EquipmentEnum.WALL.getCode().equals(equipmentVo.getEquipmentType())){
			//围墙机
			String eid = equipmentMapper.findByCondition(equipmentVo.getCommunityId(),equipmentVo.getEquipmentType());
			if(eid == null){
				//没有存在设备
				//获取小区对象
				UseCommunityPo cpo = communityMapper.selectByPrimaryKey(equipmentVo.getCommunityId());
				eid = cpo.getCommunityNo()+"000100";
			}else{
				//已存在设备
				eid = String.format("%012d",Integer.parseInt(eid)+100);
			}
			return eid;
		}else{
			//单元门口机
			String eid = equipmentMapper.findByExample(equipmentVo.getCommunityId(),equipmentVo.getEquipmentType(),equipmentVo.getBuildingId(),equipmentVo.getUnitName());
			if(eid == null){
				//没有存在设备
				//获取小区对象
				UseCommunityPo cpo = communityMapper.selectByPrimaryKey(equipmentVo.getCommunityId());
				//获取楼栋对象
				UseBuildingPo bpo = buildMapper.selectByPrimaryKey(equipmentVo.getBuildingId());
				eid = cpo.getCommunityNo()+bpo.getBuildingNo()+String.format("%02d", equipmentVo.getUnitName())+"01";
			}else{
				//已存在设备
				eid = String.format("%012d",Integer.parseInt(eid)+1);
			}
			return eid;
		}
	}
	
	

}
