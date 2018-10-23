/* 
 * 文件名：NewResidentService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseResidentVo;

/**
 * 新住户业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
public interface NewResidentService {

	/**
	 *  查找新注册住户信息
	 * @param paging 分页
	 * @param criteria 查询条件
	 * @return 集合
	 */
	public List<UseResidentVo> findAll(Paging paging, String criteria);

	/**
	 * 分页总记录数
	 * @param criteria 条件
	 * @return 数字
	 */
	public long findCount(String criteria);

	/**
	 * 手机号是否已被注册
	 * @param residentVo 新住户信息
	 * @return 是|否
	 */
	public boolean existsMobile(UseResidentVo residentVo);

	/**
	 * 保存住户
	 * @param residentVo 新住户信息
	 * @throws Exception 
	 */
	public void add(UseResidentVo residentVo) throws Exception;

	/**
	 * 修改住户信息
	 * @param residentVo 新住户信息
	 */
	public void update(UseResidentVo residentVo) throws Exception;

	/**
	 * 删除住户信息
	 * @param residentVo 新住户信息
	 * @throws Exception 
	 */
	public void delete(UseResidentVo residentVo) throws Exception;

	/**
	 * 删除云之讯的手机号
	 */
	public void  delMobile(String mobile) throws Exception;
	 
}
