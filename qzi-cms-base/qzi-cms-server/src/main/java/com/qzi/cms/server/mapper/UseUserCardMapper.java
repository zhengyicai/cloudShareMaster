/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseCardEquipmentPo;
import com.qzi.cms.common.po.UseUserCardPo;
import com.qzi.cms.common.vo.UseRoomCardVo;
import com.qzi.cms.common.vo.UseUserCardVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 管理端房卡DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseUserCardMapper extends BaseMapper<UseUserCardPo>{

	@Select("SELECT c.*,IFNULL(c1.count1,0) as countSum from use_user_card c LEFT JOIN(select count(1) as count1,userCardId from use_userCard_equipment where  state='10' GROUP BY  userCardId) c1 on  c.id = c1.userCardId where c.userId=#{userId}")
	public List<UseUserCardVo> findUserId(RowBounds rwoBounds, @Param("userId") String userId);

	@Select("select count(1) from use_user_card where userId=#{userId}")
	public Integer findCountUserId(@Param("userId") String userId);


	@Delete("delete from use_user_card  where id=#{id}")
	public void deleteId(@Param("id") String id);

}