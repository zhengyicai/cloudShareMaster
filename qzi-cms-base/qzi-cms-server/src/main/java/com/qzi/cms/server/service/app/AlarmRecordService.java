/* 
 * 文件名：AlarmRecordService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.Date;
import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseAlarmRecordVo;

/**
 * 报警记录业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
public interface AlarmRecordService {

	/**
	 * 查询报警记录
	 * @return 集合
	 * @throws Exception 
	 */
	public List<UseAlarmRecordVo> findAlarmRecords(Date nowTime,Paging paging) throws Exception;

	/**
	 * 查询报警记录总数
	 * @return
	 * @throws Exception 
	 */
	public long findAlarmRecordCount(Date nowTime) throws Exception;

}
