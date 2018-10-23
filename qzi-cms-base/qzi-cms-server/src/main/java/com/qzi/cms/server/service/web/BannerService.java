/* 
 * 文件名：BannerService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseBannerVo;

/**
 * 手机广告轮播图业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
public interface BannerService {
	/**
	 * 查找所有数据
	 * @param paging 分页
	 * @return 集合
	 * @throws Exception 
	 */
	public List<UseBannerVo> findAll(Paging paging) throws Exception;

	/**
	 * 新增
	 * @param bannerVo
	 * @throws Exception 
	 */
	public void add(UseBannerVo bannerVo) throws Exception;

	/**
	 * 修改
	 * @param bannerVo
	 * @throws Exception 
	 */
	public void update(UseBannerVo bannerVo) throws Exception;

	/**
	 * 删除
	 * @param bannerVo
	 * @throws Exception 
	 */
	public void delete(UseBannerVo bannerVo) throws Exception;

	/**
	 * 查找总记录数
	 * @return
	 */
	public long findCount();
}
