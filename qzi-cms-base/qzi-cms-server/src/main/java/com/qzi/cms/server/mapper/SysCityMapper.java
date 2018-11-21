/* 
 * 文件名：SysCityMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.SysCityPo;
import com.qzi.cms.common.vo.SysCityVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 中国城市DAO接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月27日
 */
public interface SysCityMapper extends BaseMapper<SysCityPo>{
	
	/**
	 * 查找城市
	 * @return
	 */
	@Select("SELECT * from sys_city where parentCode=#{code} ORDER BY code")
	public List<SysCityVo> findCitys(@Param("code") String parentCode);


	@Select("SELECT * from sys_city where level=#{level} ORDER BY code")
	public  List<SysCityVo> findLevel(@Param("level") String level);
	
}
