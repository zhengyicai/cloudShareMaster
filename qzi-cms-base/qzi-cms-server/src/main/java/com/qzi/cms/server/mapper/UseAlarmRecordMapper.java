/* 
 * 文件名：UseAlarmRecordMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseAlarmRecordPo;
import com.qzi.cms.common.vo.UseAlarmRecordVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 报警记录DAO接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
public interface UseAlarmRecordMapper extends BaseMapper<UseAlarmRecordPo>{

	/**
	 * 查找当前用户的所有报警记录
	 * @param residentId 住户编号
	 * @return 集合列表
	 */
	@Select("SELECT uar.* from use_resident_room urr,use_room uro,use_alarm_record uar "
			+ "where urr.roomId = uro.id and uro.roomNo = uar.houseId and urr.residentId=#{residentId} "
			+ "and uar.createTime<=#{nowTime} order by uar.createTime desc")
	public List<UseAlarmRecordVo> findAlarmRecords(@Param("residentId") String residentId,@Param("nowTime") Date nowTime,RowBounds rwoBounds);

	/**
	 * 查找当前用户报警记录
	 * @param residentId 住户编号
	 * @param nowTime 当前时间
	 * @return 集合
	 */
	@Select("select count(1) from use_resident_room urr,use_room uro,use_alarm_record uar "
			+ "where urr.roomId = uro.id and uro.roomNo = uar.houseId and urr.residentId=#{residentId} "
			+ "and uar.createTime<=#{nowTime}")
	public long findAlarmRecordCount(@Param("residentId") String residentId,@Param("nowTime") Date nowTime);

}
