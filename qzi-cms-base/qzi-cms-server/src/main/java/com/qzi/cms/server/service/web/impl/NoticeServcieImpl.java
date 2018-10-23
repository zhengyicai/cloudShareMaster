/* 
 * 文件名：NoticeServcieImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.po.UseNoticePo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ScpUtil;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.server.mapper.UseNoticeMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.NoticeServcie;

/**
 * 公告业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
@Service("webNotice")
public class NoticeServcieImpl implements NoticeServcie {
	@Resource
	private UseNoticeMapper noticeMapper;
	@Resource
	private ScpUtil scpUtil;
	@Resource
	private CommonService commonService;

	@Override
	public List<UseNoticeVo> findAll(Paging paging) throws Exception {
		SysUserVo userVo =  commonService.findUser();
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return noticeMapper.findAll(rwoBounds,userVo.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseNoticeVo noticeVo) throws Exception {
		//将vo转换成Po
		UseNoticePo noticePo = YBBeanUtils.copyProperties(noticeVo, UseNoticePo.class);
		noticePo.setId(ToolUtils.getUUID());
		noticePo.setCreateTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(noticeVo.getEndTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		noticePo.setEndTime(calendar.getTime());
		if(noticeVo.getImg() != null && noticeVo.getImg().length()>0){
			String imgName = ToolUtils.getUUID()+".jpg";
			//转换图片
			String img = noticeVo.getImg().substring(noticeVo.getImg().indexOf(";base64,")+8);
			//将图片上传到服务器
			scpUtil.uploadFile(Base64.getDecoder().decode(img),scpUtil.getRemoteRootDir()+"/notice", imgName);
			noticePo.setImg("notice/"+imgName);
		}
		//调用dao保存数据
		noticeMapper.insert(noticePo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(UseNoticeVo noticeVo) throws Exception {
		int imgIdx = noticeVo.getImg()!=null?noticeVo.getImg().indexOf(";base64,"):0;
		if(imgIdx>0){//图片有修改
			String imgName = ToolUtils.getUUID()+".jpg";
			UseNoticePo noticePo = noticeMapper.selectByPrimaryKey(noticeVo.getId());
			if(noticePo.getImg() != null && noticePo.getImg().length()>0){
				//删除图片
				scpUtil.delFile(scpUtil.getRemoteRootDir()+"/"+noticePo.getImg());
			}
			//转换图片
			String img = noticeVo.getImg().substring(noticeVo.getImg().indexOf(";base64,")+8);
			//上传图片
			scpUtil.uploadFile(Base64.getDecoder().decode(img),scpUtil.getRemoteRootDir()+"/banner", imgName);
			noticeVo.setImg("banner/"+imgName);
		}
		//将vo转换成Po
		UseNoticePo noticePo = YBBeanUtils.copyProperties(noticeVo, UseNoticePo.class);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(noticeVo.getEndTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		noticePo.setEndTime(calendar.getTime());
		//调用dao修改数据
		noticeMapper.updateByPrimaryKey(noticePo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseNoticeVo noticeVo) throws Exception {
		if(noticeVo.getImg() != null && noticeVo.getImg().length()>0){
			//删除图片
			scpUtil.delFile(scpUtil.getRemoteRootDir()+"/"+noticeVo.getImg());
		}
		//调用dao删除图片
		noticeMapper.deleteByPrimaryKey(noticeVo.getId());
	}

	@Override
	public long findCount() throws Exception {
		SysUserVo userVo = commonService.findUser();
		return noticeMapper.findCount(userVo.getId());
	}

}
