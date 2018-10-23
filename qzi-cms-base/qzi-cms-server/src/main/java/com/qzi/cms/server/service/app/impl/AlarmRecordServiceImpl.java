/* 
 * 文件名：AlarmRecordServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseAlarmRecordVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseAlarmRecordMapper;
import com.qzi.cms.server.service.app.AlarmRecordService;
import com.qzi.cms.server.service.common.CommonService;

/**
 * 报警记录业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {
	@Resource
	private CommonService commonServcie;
	@Resource
	private UseAlarmRecordMapper alarmRecordMapper;

	@Override
	public List<UseAlarmRecordVo> findAlarmRecords(Date nowTime,Paging paging) throws Exception {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		//住户对象
		UseResidentVo residentVo = commonServcie.findResident();
		return alarmRecordMapper.findAlarmRecords(residentVo.getId(),nowTime,rwoBounds);
	}

	@Override
	public long findAlarmRecordCount(Date nowTime) throws Exception {
		//住户对象
		UseResidentVo residentVo = commonServcie.findResident();
		return alarmRecordMapper.findAlarmRecordCount(residentVo.getId(),nowTime);
	}

}
