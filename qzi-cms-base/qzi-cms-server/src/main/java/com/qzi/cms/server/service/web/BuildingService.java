/* 
 * 文件名：BuildingService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月5日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseBuildingVo;

/**
 * 楼栋业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月5日
 */
public interface BuildingService {

	/**
	 * 查找用户对应小区构建树形菜单
	 * @return 树形菜单集合
	 * @throws Exception 
	 */
	public List<TreeVo> findTree() throws Exception;

	/**
	 * 获取楼栋列表数据
	 * @param communityId 小区编号
	 * @param paging 分页对象
	 * @return 楼栋列表
	 */
	public List<UseBuildingVo> findBuilding(String communityId, Paging paging);

	/**
	 * 总记录数
	 * @param communityId 小区编号
	 * @return 总数
	 */
	public long findCount(String communityId);

	/**
	 * 修改状态
	 * @param buildingVo
	 */
	public void updateState(UseBuildingVo buildingVo);

	/**
	 * 生成房间
	 * @param buildingVo
	 */
	public void createRoom(UseBuildingVo buildingVo) throws Exception;


	/**
	 * 获取当前小区所有楼栋数
	 * @param communityId
	 * @return
	 */

	public List<UseBuildingPo> findBuilding(String communityId);
}
