/* 
 * 文件名：NoticeController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月10日
 * 版本号：v1.0
*/
package com.qzi.cms.app.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.server.service.app.NoticeService;

/**
 * 小区公告控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月10日
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Resource(name="appService")
	private NoticeService noticeService;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找小区公告数据成功", noticeService.findAll(paging));
			//保存分页对象
			paging.setTotalCount(noticeService.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找小区公告数据失败");
			LogUtils.error("查找小区公告数据失败！",ex);
		}
		return respBody;
	}
}
