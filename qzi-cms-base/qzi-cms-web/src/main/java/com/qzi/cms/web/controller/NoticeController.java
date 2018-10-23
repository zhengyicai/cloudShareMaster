/* 
 * 文件名：NoticeController.java  
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
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.server.service.web.NoticeServcie;

/**
 * 公告管理控制器
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Resource(name="webNotice")
	private NoticeServcie noticeServcie;
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "公告管理查找所有数据成功", noticeServcie.findAll(paging));
			//保存分页对象
			paging.setTotalCount(noticeServcie.findCount());
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "公告管理查找所有数据失败");
			LogUtils.error("公告管理查找所有数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增公告管理")
	public RespBody add(@RequestBody UseNoticeVo noticeVo){
		RespBody respBody = new RespBody();
		try {
			noticeServcie.add(noticeVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "公告管理保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "公告管理保存失败");
			LogUtils.error("公告管理保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	@SystemControllerLog(description="修改公告管理")
	public RespBody update(@RequestBody UseNoticeVo noticeVo){
		RespBody respBody = new RespBody();
		try {
			noticeServcie.update(noticeVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "公告管理保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "公告管理保存失败");
			LogUtils.error("公告管理保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除公告管理")
	public RespBody delete(@RequestBody UseNoticeVo noticeVo){
		RespBody respBody = new RespBody();
		try {
			noticeServcie.delete(noticeVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "公告管理删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "公告管理删除失败");
			LogUtils.error("公告管理删除失败！",ex);
		}
		return respBody;
	}
}
