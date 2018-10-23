/* 
 * 文件名：EquipmentService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.UseEquipmentVo;

/**
 * 设备管理业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
public interface EquipmentService {

	/**
	 * 获取用户管理小区
	 * @return
	 * @throws Exception 
	 */
	public List<OptionVo> findCommunitys() throws Exception;

	/**
	 * 查找楼栋集合
	 * @param communityId 小区编号
	 * @return 楼栋集合
	 */
	public List<OptionVo> findBuildings(String communityId);

	/**
	 * 查找单元集合
	 * @param buildingId 楼栋编号
	 * @return 单元集合
	 */
	public List<OptionVo> findUnits(String unitNo,String buildingId);

	/**
	 * 返回当前用户对应小区的设备集合
	 * @param paging 分页对象
	 * @param criteria 条件
	 * @return 设备集合
	 * @throws Exception 
	 */
	public List<UseEquipmentVo> findAll(Paging paging, String criteria) throws Exception;

	/**
	 * 总记录数据，适用于分页
	 * @param criteria 查询条件
	 * @return 总记录数
	 * @throws Exception 
	 */
	public long findCount(String criteria) throws Exception;

	/**
	 * 新增设备
	 * @param equipmentVo 设备信息
	 */
	public void add(UseEquipmentVo equipmentVo) throws Exception;

	/**
	 * 删除设备
	 * @param equipmentVo 设备信息
	 * @throws Exception 
	 */
	public void delete(UseEquipmentVo equipmentVo) throws Exception;


	/**
	 * 修改当前时间和门磁状态
	 * @param equipmentVo
	 * @throws Exception
	 */
	public void update(UseEquipmentVo equipmentVo) throws  Exception;

	public Integer findCommunityCount(String communityId);

}
