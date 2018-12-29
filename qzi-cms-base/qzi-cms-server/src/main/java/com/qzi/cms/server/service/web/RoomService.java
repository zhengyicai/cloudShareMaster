/* 
 * 文件名：RoomService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月10日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.po.UseRoomCardPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseRoomVo;

/**
 * 房间管理业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月10日
 */
public interface RoomService {

	/**
	 * 查找楼栋单元树型菜单
	 * @param id
	 * @return
	 */
	public List<TreeVo> findTree(String id);

	/**
	 * 查找房间列表信息
	 * @param buildingId 楼栋编号
	 * @param unitId 单元编号
	 * @param paging 分页
	 * @return 集合
	 */
	public List<UseRoomVo> findBuilding(String buildingId, String unitId, Paging paging);

	/**
	 * 房间列表分页
	 * @param buildingId 楼栋编号
	 * @param unitId 单元编号
	 * @return 总记录数
	 */
	public long findCount(String buildingId, String unitId);

	/**
	 * 修改房间信息
	 * @param roomVo 房间对象
	 * @throws Exception 异常
	 */
	public void update(UseRoomVo roomVo) throws Exception;


	/**
	 *
	 */
	public void addCard(UseRoomCardPo useRoomCardPo);
	public void updateCard(UseRoomCardPo useRoomCardPo);
	public void deleteCard(UseRoomCardPo useRoomCardPo);
	public void deleteRoomId(String roomId);
	public List<UseRoomCardPo> cardList(String  roomId);


}
