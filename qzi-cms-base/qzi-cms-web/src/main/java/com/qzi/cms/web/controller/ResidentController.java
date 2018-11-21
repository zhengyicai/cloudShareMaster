/* 
 * 文件名：ResidentController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.server.mapper.UseBuildingMapper;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.mapper.UseResidentRoomMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.BuildingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.enums.YNEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.service.web.ResidentService;

import java.util.List;

/**
 * 住户信息控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {
	@Resource
	private ResidentService residentService;

	@Resource
	private UseBuildingMapper useBuildingMapper;


	@Resource
	private UseResidentMapper useResidentMapper;

	@Resource
	private BuildingService buildService;

	@Resource
	private CommonService commonService;


	@GetMapping("/findCommunitys")
	public RespBody findCommunitys(){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户小区信息成功",residentService.findCommunitys());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户小区信息异常");
			LogUtils.error("获取用户小区信息异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging,String criteria){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", residentService.findAll(paging,criteria));
			//保存分页对象
			paging.setTotalCount(residentService.findCount(criteria));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}


	/**
	 * 2018-11-20 1.0.0
	 * @param paging
	 * @param criteria
	 * @return
	 */

	@GetMapping("/residentList")
	public RespBody residentList(Paging paging,String criteria){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", residentService.residentList(paging,criteria));
			//保存分页对象
			paging.setTotalCount(residentService.residentCount(criteria));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}


	@GetMapping("/authListDetail")
	public RespBody authListDetail(String residentId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", useResidentMapper.authListDetail(residentId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}



	/**
	 * 小区授权管理
	 * 2018-11-20 1.0.0
	 * @param paging
	 * @param criteria
	 * @return
	 */

	@GetMapping("/authList")
	public RespBody authList(Paging paging,String criteria){
		RespBody respBody = new RespBody();
		try {

			String  communityId = "";
			  List<TreeVo> tv =  buildService.findTree();
			  if(tv.size()==1){
				  communityId = tv.get(0).getId();
			  }else if(tv.size()>1){

			  }


			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", useResidentMapper.authList(paging,criteria,communityId));
			//保存分页对象
			paging.setTotalCount(useResidentMapper.authCount(criteria,communityId));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}








	
	@PostMapping("/add")
	@SystemControllerLog(description="新增住户信息")
	public RespBody add(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			residentService.add(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户数据保存成功");
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户据保存失败");
			LogUtils.error("住户据保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/updateState")
	@SystemControllerLog(description="修改住户状态")
	public RespBody updateState(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			residentService.updateState(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改住户状态成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改住户状态失败");
			LogUtils.error("修改住户状态失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除住户")
	public RespBody delete(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			residentService.delete(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户删除失败");
			LogUtils.error("住户删除失败！",ex);
		}
		return respBody;
	}

	@PostMapping("/authDelete")
	@SystemControllerLog(description="删除授权")
	public RespBody authDelete(@RequestBody UseResidentRoomVo useResidentRoomVo){
		RespBody respBody = new RespBody();
		try {
			useResidentMapper.delAuth(useResidentRoomVo.getId());
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "授权删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "授权删除失败");
			LogUtils.error("授权删除失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/authUpdate")
	@SystemControllerLog(description="修改授权")
	public RespBody authUpdate(@RequestBody UseResidentRoomVo useResidentRoomVo){
		RespBody respBody = new RespBody();
		try {
			useResidentMapper.updateAuth(useResidentRoomVo.getId(),useResidentRoomVo.getOwner());
			//useResidentMapper.delAuth(useResidentRoomVo.getId());
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改授权成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改授权失败");
			LogUtils.error("修改授权失败！",ex);
		}
		return respBody;
	}


	
	@GetMapping("/findBuildings")
	public RespBody findBuildings(String communityId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找楼栋数据成功", residentService.findBuildings(communityId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找楼栋数据失败");
			LogUtils.error("查找楼栋数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findUnits")
	public RespBody findUnits(String buildingId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找单元数据成功", useBuildingMapper.findAllUnits(buildingId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找单元数据失败");
			LogUtils.error("查找单元数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findRooms")
	public RespBody findRooms(String buildingId,String unitNo){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找房间数据成功", residentService.findRooms(buildingId,unitNo));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找房间数据失败");
			LogUtils.error("查找房间数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/addRelation")
	@SystemControllerLog(description="保存住户房间关系")
	public RespBody addRelation(@RequestBody UseResidentRoomVo residentRoomVo){
		RespBody respBody = new RespBody();
		try {
			if(residentService.existsRelation(residentRoomVo)){
				//已存在绑定房间
				respBody.add(RespCodeEnum.ERROR.getCode(), "用户已绑定了此房间");
				return respBody;
			}
			if(residentRoomVo.getOwner().equals(YNEnum.YES.getCode()) && residentService.existsOwner(residentRoomVo)){
				//已存在户主
				respBody.add(RespCodeEnum.ERROR.getCode(), "此房间已存在户主");
				return respBody;
			}
			residentService.addRelation(residentRoomVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "保存住户房间关系成功");
			
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "保存住户房间关系失败");
			LogUtils.error("保存住户房间关系失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delRelation")
	@SystemControllerLog(description="删除住户房间关系")
	public RespBody delRelation(@RequestBody UseResidentRoomVo residentRoomVo){
		RespBody respBody = new RespBody();
		try {
			residentService.delRelation(residentRoomVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "删除住户房间关系成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "删除住户房间关系失败");
			LogUtils.error("删除住户房间关系失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findResidentRooms")
	public RespBody findResidentRooms(String communityId,String residentId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户房间数据成功", residentService.findResidentRooms(residentId,communityId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户房间数据失败");
			LogUtils.error("查找所有住户房间数据失败！",ex);
		}
		return respBody;
	}
	
}
