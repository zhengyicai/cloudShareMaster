/* 
 * 文件名：UseCommunityMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseCommunityVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住宅小区DAO接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月27日
 */
public interface UseCommunityMapper extends BaseMapper<UseCommunityPo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select c.*,u.userName as userName,u.state as userStatus from use_community c left join sys_user u on u.id = c.sysUserId  order by c.createTime desc")
	public List<UseCommunityVo> findAll(RowBounds rwoBounds);

	/**
	 * 查询用户
	 */
	@Select("select * from use_community where id = #{id}")
	public UseCommunityPo findOne(@Param("id") String id);

	/**
	 * @return
	 */
	@Select("select count(1) from use_community")
	public long findCount();

	/**
	 * @return
	 */
	@Select("select max(communityNo) from use_community")
	public String findMaxCommunityNo();

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT uc.id id,uc.communityName value from use_community uc,use_community_user ucu where uc.id = ucu.communityId and ucu.userId=#{userId} and uc.state='10'")
	public List<TreeVo> findTree(@Param("userId") String id);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT uc.id value,uc.communityName name from use_community uc,use_community_user ucu where uc.id = ucu.communityId and ucu.userId=#{userId} and uc.state='10'")
	public List<OptionVo> findAllByUserId(@Param("userId") String id);

	public List<UseCommunityPo> regfindAll(@Param("model") UseCommunityPo po);
	public Integer regfindCount(@Param("model") UseCommunityPo po);

}
