/* 
 * 文件名：NewResidentController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.service.web.NewResidentService;

/**
 * 新住户控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
@RestController
@RequestMapping("/newResident")
public class NewResidentController {
	@Resource
	private NewResidentService newResidentService;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging,String criteria){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", newResidentService.findAll(paging,criteria));
			//保存分页对象
			paging.setTotalCount(newResidentService.findCount(criteria));
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
			if(newResidentService.existsMobile(residentVo)){
				//已存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "此手机号已被注册");
			}else{
				//不存在
				newResidentService.add(residentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户数据保存成功");
			}
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("云之讯调用异常！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户据保存失败");
			LogUtils.error("住户据保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	@SystemControllerLog(description="修改住户")
	public RespBody update(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			newResidentService.update(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户保存失败");
			LogUtils.error("住户保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除住户")
	public RespBody delete(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			newResidentService.delete(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户删除失败");
			LogUtils.error("住户删除失败！",ex);
		}
		return respBody;
	}
	
}
