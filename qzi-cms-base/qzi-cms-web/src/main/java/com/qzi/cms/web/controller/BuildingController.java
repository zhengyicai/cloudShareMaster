/* 
 * 文件名：BuildingController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月5日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

import com.qzi.cms.common.po.SysUnitPo;
import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.vo.SysUnitVo;
import com.qzi.cms.server.service.web.CommunityService;
import com.qzi.cms.server.service.web.UnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.server.service.web.BuildingService;

import java.util.List;

/**
 * 楼栋控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月5日
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
	@Resource
	private BuildingService buildService;

	@Resource
	private CommunityService communityService;


	@Resource
	private UnitService unitService;
	
	@GetMapping("/findTree")
	public RespBody findTree(){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户小区信息成功",buildService.findTree());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户小区信息异常");
			LogUtils.error("获取用户小区信息异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findBuilding")
	public RespBody findBuilding(String communityId,Paging paging){
		RespBody respBody = new RespBody();
		try {
			SysUnitPo po = new SysUnitPo();
			po.setCommunityId(communityId);

			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取楼栋信息成功",unitService.findAll(po));
			//保存分页对象
			paging.setTotalCount(buildService.findCount(communityId));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取楼栋信息异常");
			LogUtils.error("获取楼栋信息异常！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/updateState")
	@SystemControllerLog(description="修改状态")
	public RespBody updateState(@RequestBody UseBuildingVo buildingVo){
		RespBody respBody = new RespBody();
		try {
			SysUnitPo po = new SysUnitPo();
			po.setId(buildingVo.getId());
			po.setState(buildingVo.getState());
			unitService.update(po);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改状态成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改状态失败");
			LogUtils.error("修改状态失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/createRoom")
	@SystemControllerLog(description="生成房间")
	public RespBody createRoom(@RequestBody UseBuildingVo buildingVo){
		RespBody respBody = new RespBody();
		try {

			//List<UseBuildingPo> list =   buildService.findBuilding(buildingVo.getCommunityId());

			SysUnitPo po1 = new SysUnitPo();
			po1.setCommunityId(buildingVo.getCommunityId());

			List<SysUnitVo> list = unitService.findAll(po1);

			if(list == null ||"".equals(list)){
				
			}else{
				int count = 0;
				for(SysUnitVo po:list){
					  count+=po.getFloorNumber()*po.getRoomNumber();
				}
				count+=buildingVo.getFloorNumber()*buildingVo.getRoomNumber();
				//判断当前的设备号是否超过小区设定的总数
				UseCommunityPo communityPo =  communityService.findOne(buildingVo.getCommunityId());
				if(count> communityPo.getUserNum()){
					respBody.add(RespCodeEnum.ERROR.getCode(), "住户数已超出该小区设置的住户数");
					return respBody;
				}


				SysUnitPo po2 = new SysUnitPo();
				po1.setCommunityId(buildingVo.getCommunityId());

				List<SysUnitVo> list1 = unitService.findAll(po2);
				for(SysUnitVo vo:list1){
					if(String.format("%02d",Integer.parseInt(buildingVo.getBuildingNo())).equals(vo.getBuildingNo())  && buildingVo.getCommunityId().equals(vo.getCommunityId()) && String.format("%02d",Integer.parseInt(buildingVo.getUnitName())).equals(vo.getUnitNo()) && "10".equals(vo.getState())){
						respBody.add(RespCodeEnum.ERROR.getCode(), "同一楼栋不能添加相同单元名称");
						return respBody;
					}
				}

				unitService.findAll(po1);

			}

			buildService.createRoom(buildingVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "生成房间成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "生成房间失败");
			LogUtils.error("生成房间失败！",ex);
		}
		return respBody;
	}
	
}
