/* 
 * 文件名：UseBannerMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseBannerPo;
import com.qzi.cms.common.vo.UseBannerVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 手机广告轮播图DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
public interface UseBannerMapper extends BaseMapper<UseBannerPo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("SELECT * from use_banner order by bannerIdx")
	public List<UseBannerVo> findAll(RowBounds rwoBounds);

	/**
	 * @return
	 */
	@Select("SELECT count(1) from use_banner")
	public long findCount();

	/**
	 * @return
	 */
	@Select("SELECT img from use_banner where state='10' order by bannerIdx")
	public List<String> findBanners();
	
}
