/* 
 * 文件名：RoomController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月10日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qzi.cms.common.po.UseRoomCardPo;
import com.qzi.cms.common.vo.UseRoomCardVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.service.web.RoomService;
import com.qzi.cms.server.service.web.UserService;

/**
 * 房间管理控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月10日
 */
@RestController
@RequestMapping("/room")
public class RoomController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	@Resource
	private RoomService roomService;
	
	@GetMapping("/findTree")
	public RespBody findTree(){
		RespBody respBody = new RespBody();
		try {
			String token = request.getHeader("token");
			//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取楼栋树菜单成功",roomService.findTree(userVo.getId()));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取楼栋树菜单异常");
			LogUtils.error("获取楼栋树菜单异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findRooms")
	public RespBody findRooms(String buildingId,String unitId,Paging paging){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取房间信息成功",roomService.findBuilding(buildingId,unitId,paging));
			//保存分页对象
			paging.setTotalCount(roomService.findCount(buildingId,unitId));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取房间信息异常");
			LogUtils.error("获取房间信息异常！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	public RespBody update(@RequestBody UseRoomVo roomVo){
		RespBody respBody = new RespBody();
		try {
			roomService.update(roomVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "房间修改成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "房间修改失败");
			LogUtils.error("房间修改失败！",ex);
		}
		return respBody;
	}


	@GetMapping("/findCards")
	public RespBody findCards(String roomId){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取房卡信息成功",roomService.cardList(roomId));

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取房卡信息异常");
			LogUtils.error("获取房卡信息异常！",ex);
		}
		return respBody;
	}

	@PostMapping("/updateCard")
	public RespBody updateCard(@RequestBody UseRoomCardPo useRoomCardPo){
		RespBody respBody = new RespBody();
		try {
			roomService.updateCard(useRoomCardPo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "房卡修改成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "房卡修改失败");
			LogUtils.error("房卡修改失败！",ex);
		}
		return respBody;
	}

	@PostMapping("/deleteCard")
	public RespBody deleteCard(@RequestBody UseRoomCardPo useRoomCardPo){
		RespBody respBody = new RespBody();
		try {
			roomService.deleteCard(useRoomCardPo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "房卡删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "房卡删除失败");
			LogUtils.error("房卡删除失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/addCard")
	public RespBody addCard(@RequestBody UseRoomCardVo useRoomCardVo){
		RespBody respBody = new RespBody();
		try {

			UseRoomCardPo  po = new UseRoomCardPo();
			roomService.deleteRoomId(useRoomCardVo.getRoomId());

			String[] roomList = useRoomCardVo.getCardNos().split(",");
			for(int i = 0 ;i<roomList.length;i++){
				if(!("".equals(roomList[i]))){
					po.setRoomId(useRoomCardVo.getRoomId());
					po.setCardNo(Integer.parseInt(roomList[i]) );
					po.setBuildingId(useRoomCardVo.getBuildingId());
					po.setCommunityId(useRoomCardVo.getCommunityId());
					po.setUnitId(useRoomCardVo.getUnitId());
					roomService.addCard(po);
				}

			}
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "房卡添加成功");

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "房卡添加失败");
			LogUtils.error("房卡添加失败！",ex);
		}
		return respBody;
	}








	
}
