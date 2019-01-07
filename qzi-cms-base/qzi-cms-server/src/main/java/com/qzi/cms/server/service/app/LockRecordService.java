/* 
 * 文件名：HomeService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import com.qzi.cms.common.po.UseLockRecordPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.*;

import java.util.List;

/**
 * 首页业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月30日
 */
public interface LockRecordService {

		public List<UseLockRecordVo> findAll(UseLockRecordVo useLockRecordVo,Paging paging);

		public long findCount(UseLockRecordVo useLockRecordVo);

		public void add(UseLockRecordPo po);

		public void remove(UseLockRecordPo useLockRecordPo);




}
