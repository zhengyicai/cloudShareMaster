/* 
 * 文件名：UseResidentRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月22日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseResidentPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseResidentRoomPo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * 住户房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月22日
 */
public interface UseResidentRoomMapper extends BaseMapper<UseResidentRoomPo>{

	/**
	 * @param residentRoomVo
	 * @return
	 */
	@Select("SELECT count(1)>0 from use_resident_room where residentId =#{vo.residentId} and roomId =#{vo.roomId}")
	public boolean existsRelation(@Param("vo") UseResidentRoomVo residentRoomVo);

	@Select("SELECT * from use_resident_room where residentId =#{residentId} and roomId =#{roomId} limit 1")
	public UseResidentRoomPo existsRoom(@Param("roomId") String roomId, @Param("residentId") String residentId);

	@Select("SELECT * from use_resident_room where residentId =#{residentId} and isTrue ='10' and owner='10' limit 1")
	public UseResidentRoomPo findResidentDefault(@Param("residentId") String residentId);

	@Update("update  use_resident_room set isTrue ='20' where residentId = #{residentId}")
	public void setDefault(@Param("residentId") String residentId);



	@Update("update  use_resident_room set isTrue ='10' where id = #{id}")
	public void setDefaultisTrue(@Param("id") String id);





	@Select("SELECT * from use_resident_room where residentId =#{residentId} limit 1")
	public UseResidentRoomPo findResidentExist(@Param("residentId") String residentId);


	/**
	 * @param id
	 * @param communityId
	 */
	@Select("delete from use_resident_room where residentId=#{rid} and communityId=#{cid}")
	public void deleteByCriteria(@Param("rid") String residentId,@Param("cid") String communityId);

	/**
	 * @param residentRoomVo
	 * @return
	 */
	@Select("SELECT count(1)>0 from use_resident_room where roomId =#{vo.roomId} and owner='10'")
	public boolean existsOwner(@Param("vo") UseResidentRoomVo residentRoomVo);
	
	/**
	 * @param id
	 * @param communityId
	 */
	@Select("delete from use_resident_room where residentId=#{vo.residentId} and roomId=#{vo.roomId}")
	public void deleteByResidentRoom(@Param("vo") UseResidentRoomVo residentRoomVo);

}
