/* 
 * 文件名：ParameterController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月15日
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
import com.qzi.cms.common.vo.SysParameterVo;
import com.qzi.cms.server.service.web.ParameterService;

/**
 * 参数设置控制器
 * @author qsy
 * @version v1.0
 * @date 2017年6月15日
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController {
	@Resource
	private ParameterService parameterService;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数设置查找所有数据成功", parameterService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(parameterService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "参数设置查找所有数据失败");
			LogUtils.error("参数设置查找所有数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增参数设置")
	public RespBody add(@RequestBody SysParameterVo parameterVo){
		RespBody respBody = new RespBody();
		try {
			parameterService.add(parameterVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数设置保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "参数设置保存失败");
			LogUtils.error("参数设置保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	@SystemControllerLog(description="修改参数设置")
	public RespBody update(@RequestBody SysParameterVo parameterVo){
		RespBody respBody = new RespBody();
		try {
			parameterService.update(parameterVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数设置保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "参数设置保存失败");
			LogUtils.error("参数设置保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除参数设置")
	public RespBody delete(@RequestBody SysParameterVo parameterVo){
		RespBody respBody = new RespBody();
		try {
			parameterService.delete(parameterVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "参数设置删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "参数设置删除失败");
			LogUtils.error("参数设置删除失败！",ex);
		}
		return respBody;
	}

}
