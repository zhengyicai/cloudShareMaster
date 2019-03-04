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
import javax.tools.Tool;

import com.qzi.cms.common.po.UseCardEquipmentPo;
import com.qzi.cms.common.po.UseEquipmentPo;
import com.qzi.cms.common.po.UseRoomCardPo;
import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.common.vo.UseRoomCardVo;
import com.qzi.cms.server.mapper.UseCardEquipmentMapper;
import com.qzi.cms.server.mapper.UseEquipmentMapper;
import com.qzi.cms.server.mapper.UseRoomCardMapper;
import com.qzi.cms.server.mapper.UseRoomMapper;
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

import java.util.Date;
import java.util.List;

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
	@Resource
	private UseCardEquipmentMapper useCardEquipmentMapper;

	@Resource
	private UseRoomCardMapper useRoomCardMapper;

	@Resource
	private UseRoomMapper useRoomMapper;

	@Resource
	private UseEquipmentMapper useEquipmentMapper;
	
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
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取房卡信息成功",useRoomCardMapper.findRoomCardVo(roomId));

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取房卡信息异常");
			LogUtils.error("获取房卡信息异常！",ex);
		}
		return respBody;
	}


	@GetMapping("/findCardEquipment")
	public RespBody findCardEquipment(String cardId){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取设备号信息成功",useCardEquipmentMapper.findRoomCard(cardId));

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取设备号信息异常");
			LogUtils.error("获取设备号信息异常！",ex);
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



			//string split的方法需要加一个，-1才能和js split方法一样，不然会自动忽略空的值
			String[] roomList = useRoomCardVo.getCardNos().split(",",-1);
			String[] oldroomList = useRoomCardVo.getOldcardNos().split(",",-1);


			for(int i = 0;i<oldroomList.length;i++){
				if(oldroomList[i].equals(roomList[i])){
					//UseRoomCardPo po1 =   useRoomCardMapper.findCardName(useRoomCardVo.getRoomId(),roomList[i]);
					//					if(po1!=null){
					//						po1.set
					//					}
				}else{
					//roomService.deleteRoomId(useRoomCardVo.getRoomId());

					if(!("".equals(roomList[i]))) {


						String id = ToolUtils.getUUID();
						po.setId(id);
						po.setRoomId(useRoomCardVo.getRoomId());
						po.setCardNo(Integer.parseInt(roomList[i]));
						po.setBuildingId(useRoomCardVo.getBuildingId());
						po.setCommunityId(useRoomCardVo.getCommunityId());
						po.setUnitId(useRoomCardVo.getUnitId());
						try {
							roomService.addCard(po);
						}catch (Exception e){
							respBody.add(RespCodeEnum.ERROR.getCode(), "房卡重复,请重新添加");
							return respBody;

						}
						useRoomCardMapper.deleteCardId(useRoomCardVo.getRoomId(), oldroomList[i]);

						UseRoomPo useRoomPo =  useRoomMapper.findOne(useRoomCardVo.getRoomId());
						//System.out.print("@@@@@@@@@@@"+useRoomPo.getRoomNo().substring(0,10));
						List<UseEquipmentPo> liste =   useEquipmentMapper.findUseEquipmentNo(useRoomPo.getRoomNo().substring(0,10));

						if(liste.size()>0){
							UseCardEquipmentPo  useCardPo = new UseCardEquipmentPo();
							for(UseEquipmentPo epo:liste){


								useCardPo.setId(ToolUtils.getUUID());
								useCardPo.setEquId(epo.getEquId());
								useCardPo.setEquipmentId(epo.getId());
								useCardPo.setRoomId(useRoomCardVo.getRoomId());
								useCardPo.setCardId(id);
								useCardPo.setCreateTime( new Date());
								useCardPo.setState("20");
								useCardEquipmentMapper.insert(useCardPo);


							}
						}

						//添加围墙机的数据
						List<UseEquipmentPo> liste1 =   useEquipmentMapper.findUseEquipmentNo1(useRoomPo.getRoomNo().substring(0,6));
						if(liste1.size()>0){
							UseCardEquipmentPo  useCardPo = new UseCardEquipmentPo();
							for(UseEquipmentPo epo:liste1){
								useCardPo.setId(ToolUtils.getUUID());
								useCardPo.setEquId(epo.getEquId());
								useCardPo.setEquipmentId(epo.getId());
								useCardPo.setRoomId(useRoomCardVo.getRoomId());
								useCardPo.setCardId(id);
								useCardPo.setCreateTime( new Date());
								useCardPo.setState("20");
								useCardEquipmentMapper.insert(useCardPo);


							}
						}








					}else{
						UseRoomCardPo useRoomCardPo =  useRoomCardMapper.findCardName(useRoomCardVo.getRoomId(), oldroomList[i]);
						if(useRoomCardPo!=null){
							if(useCardEquipmentMapper.findRoomCardCount(useRoomCardPo.getId())>0){
								respBody.add(RespCodeEnum.ERROR.getCode(), "该卡号已绑定过设备，请先解绑");
								return respBody;
							}else{
								useRoomCardMapper.deleteCardId(useRoomCardVo.getRoomId(), oldroomList[i]);
							}


						}

					}


				}
			}


//			for(int i = 0 ;i<roomList.length;i++){
//				if(!("".equals(roomList[i]))){
//					po.setRoomId(useRoomCardVo.getRoomId());
//					po.setCardNo(Integer.parseInt(roomList[i]) );
//					po.setBuildingId(useRoomCardVo.getBuildingId());
//					po.setCommunityId(useRoomCardVo.getCommunityId());
//					po.setUnitId(useRoomCardVo.getUnitId());
//					roomService.addCard(po);
//				}
//
//			}
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "房卡添加成功");

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "房卡添加失败");
			LogUtils.error("房卡添加失败！",ex);
		}
		return respBody;
	}








	
}
