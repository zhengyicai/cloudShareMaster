/* 
 * 文件名：ResidentService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.common.vo.UseRoomVo;

/**
 * 住户业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
public interface ResidentService {

	/**
	 * 查找数据
	 * @param paging 分页
	 * @param criteria 条件
	 * @return 集合
	 * @throws Exception 
	 */
	public List<UseResidentVo> findAll(Paging paging, String criteria) throws Exception;

	/**
	 * 总记录数
	 * @param criteria 条件
	 * @return 总记录数
	 * @throws Exception 
	 */
	public long findCount(String criteria) throws Exception;



	/**
	 * 查找数据  2018-11-20  1.0.0
	 * @param paging 分页
	 * @param criteria 条件
	 * @return 集合
	 * @throws Exception
	 */
	public List<UseResidentVo> residentList(Paging paging, String criteria) throws Exception;

	/**
	 * 总记录数  2018-11-20  1.0.0
	 * @param criteria 条件
	 * @return 总记录数
	 * @throws Exception
	 */
	public long residentCount(String criteria) throws Exception;

	/**
	 * 新增住户
	 * @param residentVo 住户信息
	 * @throws Exception 
	 */
	public void add(UseResidentVo residentVo) throws Exception;

	/**
	 * 获取用户管理小区
	 * @return
	 * @throws Exception 
	 */
	public List<TreeVo> findCommunitys() throws Exception;

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
	public List<OptionVo> findUnits(String buildingId);

	/**
	 * 查找房间集合
	 * @param buildingId 楼栋编号
	 * @param unitNo 单元编号
	 * @return 房间集合
	 */
	public List<OptionVo> findRooms(String buildingId, String unitNo);

	/**
	 * 查找房间集合
	 * @param residentId 住户编号
	 * @param communityId 小区编号
	 * @return 住户关联房间信息集合
	 */
	public List<UseRoomVo> findResidentRooms(String residentId,String communityId);

	/**
	 * 住房房间是否存在
	 * @param residentRoomVo 住户房间
	 * @return true|false
	 */
	public boolean existsRelation(UseResidentRoomVo residentRoomVo);

	/**
	 * 保存住户房间关系
	 * @param residentRoomVo 住户房间
	 * @throws Exception 
	 */
	public void addRelation(UseResidentRoomVo residentRoomVo) throws Exception;

	/**
	 * 删除住户房间关系
	 * @param residentRoomVo 住户房间
	 */
	public void delRelation(UseResidentRoomVo residentRoomVo) throws Exception;

	/**
	 * 删除住户信息
	 * @param residentVo 住户
	 * @throws Exception
	 */
	public void delete(UseResidentVo residentVo) throws Exception;

	/**
	 * 修改户主状态
	 * @param residentVo 住户
	 */
	public void updateState(UseResidentVo residentVo);


	/**
	 * 修改户主信息
	 */
	public void update(UseResidentVo useResidentVo) throws Exception;


	/**
	 * 修改注册时间
	 */
	public void updateCreateTime(String residentId) throws  Exception;
	/**
	 * 是否户主
	 * @param residentRoomVo 住户
	 * @return
	 */
	public boolean existsOwner(UseResidentRoomVo residentRoomVo);

	public boolean exist(String mobile);

}
