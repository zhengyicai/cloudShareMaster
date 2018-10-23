/* 
 * 文件名：BannerServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
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

import com.qzi.cms.common.po.UseBannerPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ScpUtil;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.UseBannerVo;
import com.qzi.cms.server.mapper.UseBannerMapper;
import com.qzi.cms.server.service.web.BannerService;

/**
 * 手机广告轮播图业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
@Service
public class BannerServiceImpl implements BannerService {
	@Resource
	private UseBannerMapper bannerMapper;
	@Resource
	private ScpUtil scpUtil;

	@Override
	public List<UseBannerVo> findAll(Paging paging) throws Exception {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return bannerMapper.findAll(rwoBounds);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseBannerVo bannerVo) throws Exception {
		String imgName = ToolUtils.getUUID()+".jpg";
		//转换图片
		String img = bannerVo.getImg().substring(bannerVo.getImg().indexOf(";base64,")+8);
		//上传图片
		scpUtil.uploadFile(Base64.getDecoder().decode(img),scpUtil.getRemoteRootDir()+"/banner", imgName);
		//保存数据
		UseBannerPo bannerPo = YBBeanUtils.copyProperties(bannerVo, UseBannerPo.class);
		bannerPo.setId(ToolUtils.getUUID());
		bannerPo.setCreateTime(new Date());
		bannerPo.setImg("banner/"+imgName);
		bannerMapper.insert(bannerPo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(UseBannerVo bannerVo) throws Exception {
		int imgIdx = bannerVo.getImg().indexOf(";base64,");
		String imgName = ToolUtils.getUUID()+".jpg";
		if(imgIdx>0){//图片有修改
			UseBannerPo bannerPo = bannerMapper.selectByPrimaryKey(bannerVo.getId());
			//删除图片
			scpUtil.delFile(scpUtil.getRemoteRootDir()+"/"+bannerPo.getImg());
			//转换图片
			String img = bannerVo.getImg().substring(bannerVo.getImg().indexOf(";base64,")+8);
			//上传图片
			scpUtil.uploadFile(Base64.getDecoder().decode(img),scpUtil.getRemoteRootDir()+"/banner", imgName);
			bannerVo.setImg("banner/"+imgName);
		}
		UseBannerPo bannerPo = YBBeanUtils.copyProperties(bannerVo, UseBannerPo.class);
		bannerMapper.updateByPrimaryKey(bannerPo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseBannerVo bannerVo) throws Exception {
		//删除图片
		scpUtil.delFile(scpUtil.getRemoteRootDir()+"/"+bannerVo.getImg());
		//删除数据
		bannerMapper.deleteByPrimaryKey(bannerVo.getId());
	}

	@Override
	public long findCount() {
		return bannerMapper.findCount();
	}

}
