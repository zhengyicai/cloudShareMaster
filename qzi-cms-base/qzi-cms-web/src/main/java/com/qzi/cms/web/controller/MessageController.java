/* 
 * 文件名：MessageController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
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
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.server.service.web.MessageService;

/**
 * 消息控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
@RestController
@RequestMapping("/message")
public class MessageController {
	@Resource(name="webMessage")
	private MessageService messageService;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "消息查找所有数据成功", messageService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(messageService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "消息查找所有数据失败");
			LogUtils.error("消息查找所有数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增消息")
	public RespBody add(@RequestBody UseMessageVo messageVo){
		RespBody respBody = new RespBody();
		try {
			messageService.add(messageVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "消息保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "消息保存失败");
			LogUtils.error("消息保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除消息")
	public RespBody delete(@RequestBody UseMessageVo messageVo){
		RespBody respBody = new RespBody();
		try {
			messageService.delete(messageVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "消息删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "消息删除失败");
			LogUtils.error("消息删除失败！",ex);
		}
		return respBody;
	}

}
