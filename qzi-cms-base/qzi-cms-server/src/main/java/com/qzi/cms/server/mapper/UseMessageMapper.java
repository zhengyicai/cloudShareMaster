/* 
 * 文件名：UseMessageMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseMessagePo;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 消息DAO
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public interface UseMessageMapper extends BaseMapper<UseMessagePo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select um.* from use_message um,use_community_user ucu "
			+ "where ucu.communityId = um.communityId and ucu.userId=#{uid} "
			+ "order by createTime")
	public List<UseMessageVo> findAll(RowBounds rwoBounds, @Param("uid")String userId);

	/**
	 * @return
	 */
	@Select("select count(1) from use_message um,use_community_user ucu "
			+ "where ucu.communityId = um.communityId and ucu.userId=#{uid}")
	public long findCount(@Param("uid")String userId);

	/**
	 * 查询住户最新消息
	 * @param redidentId
	 * @return
	 */
	@Select("SELECT um.* from use_message um,use_resident_message urm "
			+ "where um.id = urm.messageId and urm.residentId=#{rid} "
			+ "ORDER BY um.createTime DESC limit 1")
	public UseMessageVo findTopMsg(@Param("rid") String residentId);

	/**
	 * 查找住户未读消息数
	 * @param residentId
	 * @return
	 */
	@Select("select count(1) from use_resident_message where residentId=#{rid} and state='10'")
	public long findMsgCount(@Param("rid") String residentId);

	/**
	 * @param rwoBounds
	 * @param id
	 * @return
	 */
	@Select("SELECT urm.id,um.* from use_resident_message urm,use_message um "
			+ "where um.id = urm.messageId and urm.residentId=#{rid} "
			+ "ORDER BY urm.state ASC,um.createTime desc")
	public List<UseMessageVo> findAllByApp(RowBounds rwoBounds,@Param("rid") String residentId);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT count(1) from use_resident_message where residentId=#{rid} ")
	public long findCountByApp(@Param("rid") String residentId);

}
