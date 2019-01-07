/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import com.qzi.cms.common.po.UseLockRecordPo;
import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.UseLockRecordVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseLockRecordMapper extends BaseMapper<UseLockRecordPo>{



	 List<UseLockRecordVo> findAll(@Param("model") UseLockRecordVo  useLockRecordVo,@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	 long findCount(@Param("model") UseLockRecordVo useLockRecordVo);


}