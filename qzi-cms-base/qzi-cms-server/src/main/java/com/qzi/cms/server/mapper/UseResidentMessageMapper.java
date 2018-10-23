/* 
 * 文件名：UseResidentMessageMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseResidentMessagePo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住户消息DAO
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public interface UseResidentMessageMapper extends BaseMapper<UseResidentMessagePo>{

	/**
	 * @param id
	 */
	@Select("delete from use_resident_message where messageId=#{mid}")
	public void deleteByMsgId(@Param("mid") String messageId);

}
