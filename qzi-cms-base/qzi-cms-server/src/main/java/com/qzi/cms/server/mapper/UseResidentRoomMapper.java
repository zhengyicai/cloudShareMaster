/* 
 * 文件名：UseResidentRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月22日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseResidentRoomPo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.server.base.BaseMapper;

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
