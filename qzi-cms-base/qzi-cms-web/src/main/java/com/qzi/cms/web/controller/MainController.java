/* 
 * 文件名：MainController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月14日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

import com.qzi.cms.common.vo.SysResourceVo;
import com.qzi.cms.server.mapper.SysResourceMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.web.MainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面控制器
 * @author qsy
 * @version v1.0
 * @date 2017年6月14日
 */
@RestController
@RequestMapping(value = "/main")
public class MainController {
	@Resource
	private MainService mainService;

	@Resource
	private SysResourceMapper sysResourceMapper;
	
	@GetMapping("/findMenu")
	public RespBody findMenu(String roleId){
		RespBody respBody = new RespBody();
		try {
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "加载菜单成功",mainService.findMenu(roleId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "加载菜单失败");
			LogUtils.error("加载菜单失败！",ex);
		}
		return respBody;
	}
	@GetMapping("/findVueMenus")
	public RespBody findVueMenus(String roleId){
		RespBody respBody = new RespBody();
		try {

			List<SysResourceVo> list  =mainService.findMenu(roleId);
			List<SysResourceVo> listOne = new ArrayList<SysResourceVo>();
			if(list.size()>0){
				for(SysResourceVo one:list){
					if("#".equals(one.getResPath())){
					one.setChildren(sysResourceMapper.findVueMenus(roleId,one.getId()));
					listOne.add(one);
					}
				}
			}
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有菜单数据成功", listOne);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有菜单数据失败");
			LogUtils.error("查找所菜单数据失败！",ex);
		}
		return respBody;
	}
}
