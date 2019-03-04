/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseCardEquipmentPo;
import com.qzi.cms.common.po.UseRoomCardPo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseRoomCardVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseCardEquipmentMapper extends BaseMapper<UseCardEquipmentPo>{


	//获取当前卡号是否有权限
	@Select("SELECT * from use_card_equipment where cardId=#{cardId}")
	public List<UseCardEquipmentPo> findRoomCard(@Param("cardId") String cardId);

	@Select("SELECT count(1) from use_card_equipment where cardId=#{cardId} and state = '10'")
	public Integer findRoomCardCount(@Param("cardId") String cardId);

	@Select("SELECT * from use_card_equipment where roomId=#{roomId}")
	public List<UseCardEquipmentPo> findCountList(@Param("roomId") String roomId);

	@Select("SELECT count(1) from use_card_equipment where roomId=#{roomId} and state='20'")
	public Integer findRoomIdCount(@Param("roomId") String roomId);



	//获取设备对应的房卡
	@Select("select c.*,r.roomNo,e.state as linkState from use_room_card c  left join use_room r on c.roomId = r.id  left join (select cardId,state from use_card_equipment where equipmentId = #{equipmentId}) e on c.id = e.cardId  where c.roomid = #{roomId}  order by c.createTime")
	public List<UseRoomCardVo>  findCardList(@Param("roomId") String roomId,@Param("equipmentId") String equipmentId);


	//修改设备房卡绑定数据
	@Update("update use_card_equipment set state =#{state} where cardId=#{cardId} and equipmentId=#{equipmentId} ")
	public void updateCardEquipment(@Param("cardId") String cardId,@Param("equipmentId") String equipmentId,@Param("state") String state);

	//修改设备全部房卡绑定数据
	@Update("update use_card_equipment set state =#{state} where  equipmentId=#{equipmentId} ")
	public void updateAllCardEquipment(@Param("state") String state,@Param("equipmentId") String equipmentId);


}