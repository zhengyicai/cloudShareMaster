/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseRoomCardPo;
import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseRoomCardMapper extends BaseMapper<UseRoomCardPo>{



	@Select("SELECT * from use_room_card where roomId=#{roomId}")
	public List<UseRoomCardPo> findRoomCard(@Param("roomId") String  roomId);



	@Select("SELECT c.id as id,c.cardNo as  value,c.unitId as unitId,r.roomNo as label from use_room_card c left join use_room r on c.roomId = r.id  where c.unitId=#{unitId}")
	public List<TreeVo> findUnitRoomCard(@Param("unitId") String  unitId);


	@Delete("DELETE FROM use_room_card WHERE roomId = #{roomId}")
	public void deleteRoomId(@Param("roomId") String roomId);





}