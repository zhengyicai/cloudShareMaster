/* 
 * 文件名：HomeController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import com.mysql.jdbc.Blob;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.util.SoundwavUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.*;
import com.qzi.cms.server.mapper.SysParameterMapper;
import org.springframework.web.bind.annotation.*;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.app.HomeService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 首页控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月30日
 */
@RestController
@RequestMapping("/home")
public class HomeController {
	@Resource
	private HomeService homeService;

	/**
	 * 查找首页轮播图
	 * @return
	 */
	@GetMapping("/findBanners")
	public RespBody findAll(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找手机广告轮播图数据成功", homeService.findBanners());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找手机广告轮播图数据失败");
			LogUtils.error("查找手机广告轮播图数据失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 查找最新公告
	 * @return
	 */
	@GetMapping("/findTopNotice")
	public RespBody findTopNotice(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找最新公告数据成功", homeService.findTopNotice());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找最新公告数据失败");
			LogUtils.error("查找最新公告数据失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 查找最新消息
	 * @return
	 */
	@GetMapping("/findTopMsg")
	public RespBody findTopMsg(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找最新消息数据成功", homeService.findTopMsg());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找最新消息数据失败");
			LogUtils.error("查找最新消息数据失败！",ex);
		}
		return respBody;
	}
	
	/**
	 * 查找个人消息记录数
	 * @return
	 */
	@GetMapping("/findMsgCount")
	public RespBody findMsgCount(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找个人消息记录数成功", homeService.findMsgCount());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找个人消息记录数失败");
			LogUtils.error("查找个人消息记录数失败！",ex);
		}
		return respBody;
	}

	/**
	 * 获取系统参数
	 * @return
	 */
	@GetMapping("/findSysParam")
	public RespBody findSysParam(){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			//respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找个人消息记录数成功", homeService.findMsgCount());
			List<SysParameterVo> list =  homeService.paramfindAll();
			ParamObjectVo po = new ParamObjectVo();
			if(list.size()>0){
				for(SysParameterVo vo:list){
						if(vo.getParaName().equals("androidAddr")){
							po.setAndroidAddr(vo.getParaValue());
						}else if(vo.getParaName().equals("iosAddr")){
							po.setIosAddr(vo.getParaValue());
						}else if(vo.getParaName().equals("androidAppVersion")){
							po.setAndroidAppVersion(vo.getParaValue());
						}else if(vo.getParaName().equals("iosAppVersion")){
							po.setIosAppVersion(vo.getParaValue());
						}else if(vo.getParaName().equals("androidForceUpdate")){
							po.setAndroidForceUpdate(vo.getParaValue());
						}else if(vo.getParaName().equals("iosForceUpdate")){
							po.setIosForceUpdate(vo.getParaValue());
						}else if(vo.getParaName().equals("serviceUpdate")){
							po.setServiceUpdate(vo.getParaValue());
						}else if(vo.getParaName().equals("buttonShow")){
							po.setButtonShow(vo.getParaValue());
						}
				}
			}
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数查找成功",po);

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找个人消息记录数失败");
			LogUtils.error("查找个人消息记录数失败！",ex);
		}
		return respBody;
	}

	/**
	 * 获取用户参数
	 * @return
	 */
	@GetMapping("/findCommunityData")
	public RespBody findCommunityData(){
		RespBody respBody = new RespBody();
		try {
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数查找成功",homeService.findHomeUser());

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找个人消息记录数失败");
			LogUtils.error("查找个人消息记录数失败！",ex);
		}
		return respBody;
	}


	@GetMapping("/findRoomCardData")
	public RespBody findRoomCardData(String communityId){
		RespBody respBody = new RespBody();
		try {

			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取楼栋树菜单成功",homeService.findTree(communityId));

		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找个人消息记录数失败");
			LogUtils.error("查找个人消息记录数失败！",ex);
		}
		return respBody;
	}



	@GetMapping("/testStatus")
	public RespBody testStatus() throws  Exception{
		RespBody respBody = new RespBody();

		byte[] bt ={(byte)176,(byte)7, (byte)164,(byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)35, (byte)142, (byte)202, (byte)144};

		SoundwavUtils.PlaySound(bt);

		return respBody;
	}

	//上传字符串数组
	@PostMapping("/upload")
	public RespBody upload(@RequestBody int[] blob){
		RespBody respBody = new RespBody();
		try {


            byte[] bt = new byte[blob.length];
		    for(int i = 0 ;i<blob.length;i++){
		        bt[i] = (byte) blob[i];
            }

			String str=    SoundwavUtils.PlaySound(bt);
		    respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数查找成功",str);





		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "参数查找成功");
			LogUtils.error("参数查找成功！",ex);
		}
		return respBody;
	}




	
	
	
}
