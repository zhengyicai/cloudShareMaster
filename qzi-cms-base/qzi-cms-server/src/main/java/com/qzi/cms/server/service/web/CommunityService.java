/* 
 * 文件名：CommunityService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月28日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.*;

/**
 * 住宅小区业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月28日
 */
public interface CommunityService {

	/**
	 * 查找所有数据
	 * @param paging
	 * @return
	 */
	public List<UseCommunityVo> findAll(Paging paging) throws Exception ;

	/**
	 * 获取当前每个小区的数据
	 */
	public UseCommunityPo  findOne(String communityId);

	/**
	 * 查找总记录数
	 * @return
	 */
	public long findCount() throws Exception ;

	/**
	 * 保存
	 * @param communityVo
	 * @throws Exception 
	 */
	public void add(UseCommunityVo communityVo) throws Exception;


	/**
	 * 保存
	 * @param communityVo
	 * @throws Exception
	 */
	public void wordAdd(UseCommunityVo communityVo) throws Exception;


	/**
	 * 修改
	 * @param communityVo
	 * @throws Exception 
	 */
	public void update(UseCommunityVo communityVo) throws Exception;

	/**
	 * 查找中国城市
	 * @param parentCode
	 * @return
	 */
	public List<SysCityVo> findCitys(String parentCode);

	/**
	 * @param communityId
	 * @return
	 */
	public List<CommunityAdminVo> findAdmin(String communityId);

	/**
	 * @param adminVo
	 */
	public void add(AdminVo adminVo);

	/**
	 * 删除物业
	 * @param sysUserVo
	 */
	public void delete(SysUserVo sysUserVo);




}
