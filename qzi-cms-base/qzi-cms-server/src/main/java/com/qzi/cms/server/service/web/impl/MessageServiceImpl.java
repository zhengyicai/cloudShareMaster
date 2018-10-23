/* 
 * 文件名：MessageServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.enums.MsgStateEnum;
import com.qzi.cms.common.po.UseMessagePo;
import com.qzi.cms.common.po.UseResidentMessagePo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ScpUtil;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.server.mapper.UseMessageMapper;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.mapper.UseResidentMessageMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.MessageService;

/**
 * 消息业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
@Service("webMessage")
public class MessageServiceImpl implements MessageService{
	@Resource
	private UseMessageMapper messageMapper;
	@Resource
	private UseResidentMessageMapper rmMapper;
	@Resource
	private UseResidentMapper residentMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private ScpUtil scpUtil;

	@Override
	public List<UseMessageVo> findAll(Paging paging) throws Exception {
		SysUserVo userVo = commonService.findUser();
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return messageMapper.findAll(rwoBounds,userVo.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseMessageVo messageVo) throws Exception {
		//将vo转换成Po
		UseMessagePo messagePo = YBBeanUtils.copyProperties(messageVo, UseMessagePo.class);
		messagePo.setId(ToolUtils.getUUID());
		messagePo.setCreateTime(new Date());
		if(messageVo.getImg() != null && messageVo.getImg().length()>0){
			String imgName = ToolUtils.getUUID()+".jpg";
			//转换图片
			String img = messageVo.getImg().substring(messageVo.getImg().indexOf(";base64,")+8);
			//将图片上传到服务器
			scpUtil.uploadFile(Base64.getDecoder().decode(img),scpUtil.getRemoteRootDir()+"/message", imgName);
			messagePo.setImg("message/"+imgName);
		}
		//保存消息
		messageMapper.insert(messagePo);
		//发送消息(消息住户)
		List<String> rids = residentMapper.findResident(messageVo);
		for(String rid:rids){
			UseResidentMessagePo rmp = new UseResidentMessagePo();
			rmp.setId(ToolUtils.getUUID());
			rmp.setMessageId(messagePo.getId());
			rmp.setResidentId(rid);
			rmp.setState(MsgStateEnum.UNREAD.getCode());
			rmMapper.insert(rmp);
		}
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseMessageVo messageVo) throws Exception {
		if(messageVo.getImg() != null && messageVo.getImg().length()>0){
			//删除图片
			scpUtil.delFile(scpUtil.getRemoteRootDir()+"/"+messageVo.getImg());
		}
		messageMapper.deleteByPrimaryKey(messageVo.getId());
		//删除绑定的个人消息
		rmMapper.deleteByMsgId(messageVo.getId());
	}

	@Override
	public long findCount() throws Exception {
		SysUserVo userVo = commonService.findUser();
		return messageMapper.findCount(userVo.getId());
	}

}
