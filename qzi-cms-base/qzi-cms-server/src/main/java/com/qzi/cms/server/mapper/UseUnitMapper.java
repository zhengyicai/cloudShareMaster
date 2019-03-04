/* 
 * 文件名：UseBuildingMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月4日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.SysUnitPo;
import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.SysUnitVo;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 单元DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月4日
 */
public interface UseUnitMapper extends BaseMapper<SysUnitPo>{


	public List<SysUnitVo> findAll(@Param("model") SysUnitPo SysUnitPo);

	@Select("select * from use_unit where id = #{id}")
	public SysUnitPo findOne(@Param("id") String id);



	@Select("select * from use_unit where buildingId = #{buildingId} and unitNo=#{unitNo} limit 1")
	public SysUnitVo findAllUnit(@Param("buildingId") String buildingId, @Param("unitNo") String unitNo);

	@Update("update use_unit set state = #{model.state} where id = #{model.id}")
	public void update(@Param("model") SysUnitPo SysUnitPo);



	
}
