/* 
 * 文件名：UseEquipmentMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseEquipmentPo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 设备管理DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
public interface UseEquipmentMapper extends BaseMapper<UseEquipmentPo>{

	/**
	 * @param rwoBounds
	 * @param criteria
	 * @param id
	 * @return
	 */
	public List<UseEquipmentVo> findAll(RowBounds rwoBounds,@Param("criteria") String criteria,@Param("uid") String userId);

	/**
	 * @param criteria
	 * @param id
	 * @return
	 */
	public long findCount(@Param("criteria") String criteria,@Param("uid") String userId);

	/**
	 * @param communityId
	 * @param equipmentType
	 * @return
	 */
	@Select("SELECT MAX(equipmentId) from use_equipment where communityId=#{cid} and equipmentType=#{etype}")
	public String findByCondition(@Param("cid")String communityId,@Param("etype") String equipmentType);

	/**
	 * @param communityId
	 * @param equipmentType
	 * @param buildingId
	 * @param unitName
	 * @return
	 */
	@Select("SELECT MAX(equipmentId) from use_equipment where communityId=#{cid} and equipmentType=#{etype} and buildingId=#{bid} and unitName=#{uno}")
	public String findByExample(@Param("cid") String communityId,@Param("etype") String equipmentType,@Param("bid") String buildingId,@Param("uno") int unitName);

	/**
	 * @param id
	 * @return
	 */
	@Select("select tmp.*,uc.communityName from use_community uc,("
			+ "SELECT equ.*,uce.residentId is not NULL oftenUse from ("
			+"SELECT eu.* from use_community_resident ucr,use_equipment eu " 
			+"where ucr.communityId = eu.communityId and (eu.equipmentType='10' OR eu.equipmentType='20') " 
			+"and eu.state = '10' and ucr.residentId=#{rid} "
			+"UNION "
			+"SELECT ue.* from use_equipment ue INNER JOIN (SELECT ur.*,urr.residentId from use_room ur,use_resident_room urr where ur.id = urr.roomId) ur "
			+"on ue.communityId = ur.communityId and ue.buildingId = ur.buildingId and ue.unitName = ur.unitName and ur.residentId=#{rid} "
			+") equ LEFT JOIN use_common_equipment uce on uce.equipmentId=equ.id and uce.residentId=#{rid}) tmp "
			+ "where tmp.communityId = uc.id")
	public List<UseEquipmentVo> findMonitorList(@Param("rid") String residentId);

	/**
	 * 查找设备列表
	 * @param communityNo 小区编号
	 * @param equipmentType 设备类型
	 * @return 集合
	 */
	@Select("SELECT ue.* from use_equipment ue,use_community uc where uc.id = ue.communityId and uc.communityNo=#{cno} and ue.equipmentType=#{etype}")
	public List<UseEquipmentVo> findEquipments(@Param("cno")String communityNo,@Param("etype") String equipmentType);

	/**
	 * @param communityNo 小区编号
	 * @return 集合
	 */
	@Select("SELECT ue.* from use_equipment ue,use_community uc where uc.id = ue.communityId and uc.communityNo=#{cno}")
	public List<UseEquipmentVo> findAllEquipments(@Param("cno")String communityNo);

	/**
	 * 查找设备信息
	 * @param equipmentId 设备编号
	 * @return 设备信息
	 */
	@Select("SELECT * from use_equipment where equipmentId=#{equipmentId}")
	public UseEquipmentVo findEquipmentInfo(@Param("equipmentId") String equipmentId);

	/**
	 * 查询用户管理机
	 * @param
	 * @return
	 */
	@Select("SELECT DISTINCT ue.* from use_community_resident ucr,use_equipment ue "
			+ "where ucr.communityId = ue.communityId and ue.equipmentType='10' and ucr.residentId=#{rid}")
	public List<UseEquipmentVo> findMgrMachines(@Param("rid") String residentId);


	/**
	 * 查询小区已绑定的设备号
	 */
	@Select("select count(1) from use_equipment where communityId = #{communityId} and state = '10'")
	public Integer communityIdCount(@Param("communityId") String communityId);

	@Select("SELECT e.*,   IFNULL(e1.cnum,0) as unlockCount from use_equipment e left join (select count(1)  as cnum ,equipmentId from use_userCard_equipment u where state = '20' group by equipmentId) e1 on e.id = e1.equipmentId  where e.communityId=#{communityId} and e.state = '10'")
	public List<UseEquipmentVo> communityIdList(@Param("communityId") String communityId);




	@Select("SELECT e.*,IFNULL(u.count1,0) as unlockCount from use_equipment e  left join (select count(1) as count1,equipmentId from  use_card_equipment where roomId=#{roomId} and state='20' group by equipmentId )  u on e.id = u.equipmentId where e.equipmentId like '%${equipmentId}%'")
	public List<UseEquipmentVo> appFindUseEquipmentNo(@Param("roomId") String roomId,@Param("equipmentId") String equipmentId);



	@Select("SELECT * from use_equipment where equipmentId like '%${equipmentId}%'")
	public List<UseEquipmentPo> findUseEquipmentNo(@Param("equipmentId") String equipmentId);




	@Select("SELECT e.*,IFNULL(u.count1,0) as unlockCount from use_equipment e  left join (select count(1) as count1,equipmentId from  use_card_equipment where roomId=#{roomId} and state='20' group by equipmentId )  u on e.id = u.equipmentId where e.equipmentId like '%${equipmentId}%' and e.equipmentType='20'")
	public List<UseEquipmentVo> appFindUseEquipmentNo1(@Param("roomId") String roomId,  @Param("equipmentId") String equipmentId);


	//查询围墙机的数据
	@Select("SELECT * from use_equipment where equipmentId like '%${equipmentId}%' and equipmentType='20'")
	public List<UseEquipmentPo> findUseEquipmentNo1(@Param("equipmentId") String equipmentId);


}
