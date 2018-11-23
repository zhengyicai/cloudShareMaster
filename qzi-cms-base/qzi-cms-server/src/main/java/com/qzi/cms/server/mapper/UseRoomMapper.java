/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseRoomMapper extends BaseMapper<UseRoomPo>{

	/**
	 * @param buildingId
	 * @param unitId
	 * @param rwoBounds
	 * @return
	 */
	@Select("SELECT * from use_room where unitId=#{unitId} and buildingId = #{buildingId} ORDER BY roomNo")
	public List<UseRoomVo> findBuilding(@Param("buildingId") String buildingId, @Param("unitId") String unitId, RowBounds rwoBounds);


	@Select("SELECT * from use_room where unitId=#{unitId} and buildingId = #{buildingId} and roomName=#{roomName} and state = '10'  ORDER BY roomNo")
	public UseRoomVo findRoom(@Param("buildingId") String buildingId, @Param("unitId") String unitId,  @Param("roomName") String roomName);


	/**
	 * @param buildingId
	 * @param unitId
	 * @return
	 */
	@Select("SELECT count(1) from use_room where unitName=#{unitId} and buildingId = #{buildingId}")
	public long findCount(@Param("buildingId") String buildingId, @Param("unitId") String unitId);

	/**
	 * @param buildingId
	 * @param unitNo
	 * @return
	 */
	@Select("SELECT id value,roomName name from use_room where unitId=#{unitNo} and buildingId = #{buildingId} ORDER BY roomNo")
	public List<OptionVo> findRooms(@Param("buildingId")String buildingId,@Param("unitNo") String unitNo);

	/**
	 * @param residentId
	 * @return
	 */
	@Select("SELECT ur.*,ub.buildingName,urr.owner from use_room ur,use_resident_room urr,use_building ub "
			+ "where ur.buildingId = ub.id and urr.roomId= ur.id and urr.residentId=#{rid} and urr.communityId=#{cid}")
	public List<UseRoomVo> findResidentRooms(@Param("rid") String residentId,@Param("cid") String communityId);

	/**
	 * 查找门口机对应的房间号
	 * @param id 住户编号
	 * @param equipmentId 门口机编号
	 * @return 集合
	 */
	@Select("SELECT uro.roomName from use_resident_room urr,use_room uro where urr.roomId = uro.id and urr.residentId=#{rid} and roomNo LIKE CONCAT(#{eid},'%') and uro.state='10'")
	public List<String> findEquRooms(@Param("rid") String residentId,@Param("eid") String equipmentId);

	@Select("select * from use_room where id = #{id}")
	public UseRoomPo findOne(@Param("id") String id);
}