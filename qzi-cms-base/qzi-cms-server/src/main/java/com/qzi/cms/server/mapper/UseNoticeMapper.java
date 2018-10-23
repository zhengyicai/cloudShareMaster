/* 
 * 文件名：UseNoticeMapper.java  
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

import com.qzi.cms.common.po.UseNoticePo;
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 公告DAO
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public interface UseNoticeMapper extends BaseMapper<UseNoticePo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select un.*,uc.communityName from use_notice un,use_community uc,use_community_user ucu "
			+ "where uc.id=un.communityId and ucu.communityId=un.communityId and ucu.userId=#{uid} "
			+ "order by createTime desc")
	public List<UseNoticeVo> findAll(RowBounds rwoBounds,@Param("uid") String userId);

	/**
	 * @return
	 */
	@Select("select count(1) from use_notice un,use_community_user ucu "
			+ "where un.communityId=ucu.communityId")
	public long findCount(@Param("uid") String userId);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT un.* from use_notice un,use_community_resident ucr "
			+ "where un.communityId = ucr.communityId and NOW()<un.endTime "
			+ "and un.state = '10' and ucr.residentId = #{rid} "
			+ "ORDER BY un.createTime desc LIMIT 1")
	public UseNoticeVo findTopNotice(@Param("rid") String residentId);

	/**
	 * @param rwoBounds
	 * @param id
	 * @return
	 */
	@Select("SELECT un.* from use_notice un,use_community_resident ucr "
			+ "where un.communityId = ucr.communityId and NOW()<un.endTime "
			+ "and un.state = '10' and ucr.residentId = #{rid} "
			+ "ORDER BY un.createTime desc")
	public List<UseNoticeVo> findAllByApp(RowBounds rwoBounds, @Param("rid") String residentId);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT count(1) from use_notice un,use_community_resident ucr "
			+ "where un.communityId = ucr.communityId and NOW()<un.endTime "
			+ "and un.state = '10' and ucr.residentId = #{rid} ")
	public long findCountByApp(@Param("rid") String residentId);

}
