/* 
 * 文件名：BannerController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
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
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseBannerVo;
import com.qzi.cms.server.service.web.BannerService;

/**
 * 手机广告轮播图控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
	@Resource
	private BannerService bannerService;
	
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "手机广告轮播图查找所有数据成功", bannerService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(bannerService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机广告轮播图查找所有数据失败");
			LogUtils.error("手机广告轮播图查找所有数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增手机广告轮播图")
	public RespBody add(@RequestBody UseBannerVo bannerVo){
		RespBody respBody = new RespBody();
		try {
			bannerService.add(bannerVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "手机广告轮播图保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机广告轮播图保存失败");
			LogUtils.error("手机广告轮播图保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	@SystemControllerLog(description="修改手机广告轮播图")
	public RespBody update(@RequestBody UseBannerVo bannerVo){
		RespBody respBody = new RespBody();
		try {
			bannerService.update(bannerVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "手机广告轮播图保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机广告轮播图保存失败");
			LogUtils.error("手机广告轮播图保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除手机广告轮播图")
	public RespBody delete(@RequestBody UseBannerVo bannerVo){
		RespBody respBody = new RespBody();
		try {
			bannerService.delete(bannerVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "手机广告轮播图删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "手机广告轮播图删除失败");
			LogUtils.error("手机广告轮播图删除失败！",ex);
		}
		return respBody;
	}
}
