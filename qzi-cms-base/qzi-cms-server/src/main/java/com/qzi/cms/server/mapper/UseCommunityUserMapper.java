/* 
 * 文件名：UseCommunityUserMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseCommunityUserPo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住宅小区用户关系DAO
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
public interface UseCommunityUserMapper extends BaseMapper<UseCommunityUserPo>{

	/**
	 * 删除数据
	 * @param communityId 住宅小区编号
	 */
	@Select("delete from use_community_user where communityId = #{cid}")
	public void deleteForRid(@Param("cid") String communityId);


	@Select("select * from use_community_user where userId = #{userId} limit 1")
	public  UseCommunityUserPo  findOne(@Param("userId") String userId	);

}
