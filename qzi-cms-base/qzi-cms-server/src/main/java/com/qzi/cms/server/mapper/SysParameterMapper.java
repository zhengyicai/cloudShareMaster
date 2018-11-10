/* 
 * 文件名：SysParameterMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月15日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.SysParameterPo;
import com.qzi.cms.common.vo.SysParameterVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 参数设置DAO
 * @author qsy
 * @version v1.0
 * @date 2017年6月15日
 */
public interface SysParameterMapper extends BaseMapper<SysParameterPo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select * from sys_parameter order by createTime desc")
	public List<SysParameterVo> findAll(RowBounds rwoBounds);

	@Select("select * from sys_parameter order by createTime desc")
	public List<SysParameterVo> findAllApp();

	/**
	 * @return
	 */
	@Select("select count(1) from sys_parameter")
	public long findCount();

	/**
	 * 查找参数值
	 * @param paramName 参数名
	 * @return 参数值
	 */
	@Select("SELECT paraValue from sys_parameter where paraName=#{paramName};")
	public String findParam(@Param("paramName") String paramName);

}
