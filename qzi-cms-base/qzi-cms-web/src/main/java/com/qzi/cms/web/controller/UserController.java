/* 
 * 文件名：UserController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月12日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.tools.Tool;

import com.qzi.cms.common.po.UseCommunityUserPo;
import com.qzi.cms.common.po.UseUserCardEquipmentPo;
import com.qzi.cms.common.po.UseUserCardPo;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.UseUserCardVo;
import com.qzi.cms.server.mapper.UseCommunityUserMapper;
import com.qzi.cms.server.mapper.UseUserCardEquipmentMapper;
import com.qzi.cms.server.mapper.UseUserCardMapper;
import com.qzi.cms.server.service.web.CommunityService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UpdatePwVo;
import com.qzi.cms.server.service.web.UserService;

import java.util.Date;

/**
 * 用户控制器
 * @author qsy
 * @version v1.0
 * @date 2017年6月12日
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;//用户业务层
	@Resource
	private HttpServletRequest request;
	@Resource
	private ConfUtils confUtils;
	@Resource
	private RedisService redisService;
	@Resource
	private CommunityService communityService;

	@Resource
	private UseCommunityUserMapper useCommunityUserMapper;

	@Resource
	private UseUserCardMapper useUserCardMapper;


	@Resource
	private UseUserCardEquipmentMapper useUserCardEquipmentMapper;

	@GetMapping("/findUser")
	private RespBody findUser(){
		RespBody respBody = new RespBody();
		try {
			String token = request.getHeader("token");
			//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);

			//用户是否存在
			if(userVo != null){
				userVo.setPassword("");
				userVo.setSalt("");


				UseCommunityUserPo useCpo =  useCommunityUserMapper.findOne(userVo.getId());

				if(useCpo == null){
					userVo.setCommunityId("");
				}else{
					userVo.setCommunityId( useCommunityUserMapper.findOne(userVo.getId()).getCommunityId());
				}

				//存在
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户信息成功",userVo);
			}else{
				//不存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息失败");
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户信息异常");
			LogUtils.error("获取用户信息异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", userService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(userService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}




	@GetMapping("/findAllChild")
	public RespBody findAllChild(String parentId,Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", userService.findRoleAll(paging));
			//保存分页对象
			paging.setTotalCount(userService.findAllChildCount(parentId));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}

	@GetMapping("/findMax")
	public RespBody findMax(String parentId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", userService.maxCode(parentId));

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}


	@GetMapping("/findAllWorkChild")
	public RespBody findAllWorkChild(String parentId,Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", userService.findAllChild(parentId,paging));
			//保存分页对象
			paging.setTotalCount( userService.findAllChild(parentId,paging).size());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}


	/**
	 * 查询厂商列表
	 * @return
	 */

	@GetMapping("/findTree")
	public RespBody findTree(){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户小区信息成功",userService.findTree());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户小区信息异常");
			LogUtils.error("获取用户小区信息异常！",ex);
		}
		return respBody;
	}
	@GetMapping("/findRoles")
	public RespBody findRoles(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有角色数据成功", userService.findRoles());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有角色数据失败");
			LogUtils.error("查找所有角色数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	public RespBody add(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			//判断用户是否存在
			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());
			if(findUser == null){
				userService.add(userVo);
				
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息保存成功");
			}else{
				respBody.add(RespCodeEnum.ERROR.getCode(), "登录名已经存在");
			}
			
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息保存失败");
			LogUtils.error("用户信息保存失败！",ex);
		}
		return respBody;
	}

	@PostMapping("/workAdd")
	public RespBody workAdd(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			//判断用户是否存在
			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());
			if(findUser == null){


				SysUserVo vo =  userService.findOne(userVo.getParentId());
			    userVo.setCode(vo.getCode());
				userService.add(userVo);

				respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息保存成功");
			}else{
				respBody.add(RespCodeEnum.ERROR.getCode(), "登录名已经存在");
			}

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息保存失败");
			LogUtils.error("用户信息保存失败！",ex);
		}
		return respBody;
	}


	@PostMapping("/firmAdd")
	public RespBody firmAdd(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			//判断用户是否存在
			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());
		     Integer codeCount =   userService.findCodeExist(userVo.getCode());


			if(findUser == null){
				if(codeCount >0){
					respBody.add(RespCodeEnum.ERROR.getCode(), "厂商编号已经存在");
				}else{
					userService.firmAdd(userVo);
					respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息保存成功");
				}

			}else{
				respBody.add(RespCodeEnum.ERROR.getCode(), "登录名已经存在");
			}

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息保存失败");
			LogUtils.error("用户信息保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	public RespBody update(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {

			SysUserVo findUser = userService.findByLoginName(userVo.getLoginName());

			if(findUser == null){
				userService.update(userVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息修改成功");
			}else{
				if(findUser.getId().equals(userVo.getId())){
					userService.update(userVo);
					respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息修改成功");
				}else{
					respBody.add(RespCodeEnum.ERROR.getCode(), "登录名已经存在");
				}

			}

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息修改失败");
			LogUtils.error("用户信息修改失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	public RespBody delete(@RequestBody SysUserVo userVo){
		RespBody respBody = new RespBody();
		try {
			userService.delete(userVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "用户信息删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户信息删除失败");
			LogUtils.error("用户信息删除失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/updatePw")
	public RespBody updatePw(@RequestBody UpdatePwVo updatePwVo){
		RespBody respBody = new RespBody();
		try {
			if(!updatePwVo.getNewPw().equals(updatePwVo.getOkPw())){
				respBody.add(RespCodeEnum.ERROR.getCode(), "新密码和确认码不一致");
				return respBody;
			}
			String token = request.getHeader("token");
			//读取用户信息
			SysUserVo userVo = userService.SysUserVo(token);
			// 对输入密码进行加密
			String oldPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getOldPw(), userVo.getSalt());
			if(userVo.getPassword().equals(oldPw)){
				//对新密码进行加密
				String newPw = CryptUtils.hmacSHA1Encrypt(updatePwVo.getNewPw(), userVo.getSalt());
				//旧密码正确，调用业务层执行密码更新
				userService.updatePw(newPw,userVo.getId());
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改密码成功");
				//更新redis数据
				userVo.setPassword(newPw);
				redisService.putObj(token, userVo, confUtils.getSessionTimeout());
			}else{
				//旧密码输入有误
				respBody.add(RespCodeEnum.ERROR.getCode(), "旧密码输入不正确");
			}
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改密码失败");
			LogUtils.error("修改密码失败！",ex);
		}
		return respBody;
	}

	/**
	 * 查看物业房卡列表
	 * @param paging
	 * @return
	 */
	@GetMapping("/cardFindAll")
	public RespBody cardFindAll(Paging paging,String userId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有用户信息数据成功", useUserCardMapper.findUserId(rwoBounds,userId));
			//保存分页对象
			paging.setTotalCount(userService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有用户信息数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}

	/**
	 * 添加物业房卡列表
	 * @param
	 * @return
	 */
	@PostMapping("/addUserCard")
	public RespBody addUserCard(@RequestBody UseUserCardVo useUserCardVo){
		RespBody respBody = new RespBody();

		if(useUserCardVo.getChoId().length>0){

			UseUserCardPo  po = new UseUserCardPo();
			String id = ToolUtils.getUUID();
			po.setId(id);
			po.setCommunityId(useUserCardVo.getCommunityId());
			po.setCreateTime(new Date());
			po.setState("10");
			po.setUserId(useUserCardVo.getUserId());
			po.setCardName(useUserCardVo.getCardName());
			po.setCardNo(useUserCardVo.getCardNo());
			useUserCardMapper.insert(po);
			UseUserCardEquipmentPo useUserCardEquipmentPo = new UseUserCardEquipmentPo();
			for(int i  = 0;i<useUserCardVo.getChoId().length;i++){
				useUserCardEquipmentPo.setId(ToolUtils.getUUID());
				useUserCardEquipmentPo.setCommunityId(useUserCardVo.getCommunityId());
				useUserCardEquipmentPo.setCreateTime(new Date());
				useUserCardEquipmentPo.setState("20");
				useUserCardEquipmentPo.setUserCardId(id);
				useUserCardEquipmentPo.setEquipmentId(useUserCardVo.getChoId()[i]);
				useUserCardEquipmentMapper.insert(useUserCardEquipmentPo);
			}

		}
		return respBody;
	}


	/**
	 * 查看物业房卡设备列表
	 * @param
	 * @return
	 */
	@GetMapping("/cardEquipmentFindAll")
	public RespBody cardEquipmentFindAll(String cardId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有管理员卡号数据成功", useUserCardEquipmentMapper.findRoomCard(cardId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有管理员卡号数据失败");
			LogUtils.error("查找所有用户信息数据失败！",ex);
		}
		return respBody;
	}





	/**
	 * 修改物业房卡列表
	 * @param
	 * @return
	 */
	@PostMapping("/updateUserCard")
	public RespBody updateUserCard(@RequestBody UseUserCardVo useUserCardVo){
		RespBody respBody = new RespBody();

		if(useUserCardVo.getChoId().length>0){


			if(useUserCardEquipmentMapper.selectCardId(useUserCardVo.getId())>0){
				respBody.add(RespCodeEnum.ERROR.getCode(), "该卡号应该绑定过设备，请先解绑");
				return respBody;
			}

			useUserCardEquipmentMapper.deleteCardId(useUserCardVo.getId());
			UseUserCardEquipmentPo useUserCardEquipmentPo = new UseUserCardEquipmentPo();
			for(int i  = 0;i<useUserCardVo.getChoId().length;i++){
				useUserCardEquipmentPo.setId(ToolUtils.getUUID());
				useUserCardEquipmentPo.setCommunityId(useUserCardVo.getCommunityId());
				useUserCardEquipmentPo.setCreateTime(new Date());
				useUserCardEquipmentPo.setState("20");
				useUserCardEquipmentPo.setUserCardId(useUserCardVo.getId());
				useUserCardEquipmentPo.setEquipmentId(useUserCardVo.getChoId()[i]);
				useUserCardEquipmentMapper.insert(useUserCardEquipmentPo);
			}

		}
		return respBody;
	}


	/**
	 * 删除物业房卡列表
	 * @param
	 * @return
	 */
	@PostMapping("/deleteUserCard")
	public RespBody deleteUserCard(@RequestBody UseUserCardVo useUserCardVo){
		RespBody respBody = new RespBody();

		if(useUserCardEquipmentMapper.selectCardId(useUserCardVo.getId())>0){
			respBody.add(RespCodeEnum.ERROR.getCode(), "该卡号应该绑定过设备，请先解绑");
			return respBody;
		}
		useUserCardMapper.deleteId(useUserCardVo.getId());
			//useUserCardEquipmentMapper.deleteCardId(useUserCardVo.getId());
		respBody.add(RespCodeEnum.SUCCESS.getCode(), "删除成功");

		return respBody;
	}







	
}
