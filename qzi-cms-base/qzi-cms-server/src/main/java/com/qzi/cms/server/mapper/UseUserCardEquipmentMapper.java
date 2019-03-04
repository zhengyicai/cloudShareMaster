/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseCardEquipmentPo;
import com.qzi.cms.common.po.UseUserCardEquipmentPo;
import com.qzi.cms.common.vo.UseRoomCardVo;
import com.qzi.cms.common.vo.UseUserCardEquipmentVo;
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
public interface UseUserCardEquipmentMapper extends BaseMapper<UseUserCardEquipmentPo>{



	@Select("SELECT u.*,e.equipmentName,e.equipmentType from use_userCard_equipment u left join use_equipment  e on u.equipmentId = e.id   where u.userCardId=#{userCardId}")
	public List<UseUserCardEquipmentVo> findRoomCard(@Param("userCardId") String userCardId);


	@Delete("delete from  use_userCard_equipment where userCardId = #{userCardId}")
	public void deleteCardId(@Param("userCardId") String userCardId);


	@Select("select count(1) from use_userCard_equipment where userCardId=#{userCardId}  and state ='10'")
	public Integer selectCardId(@Param("userCardId") String userCardId);


	@Select("select count(1) from use_userCard_equipment where communityId=#{communityId}  and state ='20'")
	public Integer findCommunityCount(@Param("communityId") String communityId);


	@Select("select e.*,c.cardNo,c.cardName from use_userCard_equipment e left join use_user_card c on   e.userCardId = c.id where e.equipmentId = #{equipmentId}")
	public List<UseUserCardEquipmentVo> findCardList(@Param("equipmentId") String equipmentId);




	//修改设备房卡绑定数据

	@Update("update use_userCard_equipment set state =#{state} where id=#{id} ")
	public void updateUserCardEquipment(@Param("id") String id,@Param("state") String state);

	@Update("update use_userCard_equipment set state =#{state} where equipmentId=#{equipmentId} ")
	public void updateAllUserCardEquipment(@Param("state") String state, @Param("equipmentId") String equipmentId);

}