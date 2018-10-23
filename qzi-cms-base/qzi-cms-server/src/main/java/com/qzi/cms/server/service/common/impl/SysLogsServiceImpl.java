/* 
 * 文件名：BaseSysLogsServiceImpl.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月26日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.po.SysLogsPo;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysLogsVo;
import com.qzi.cms.server.mapper.SysLogsMapper;
import com.qzi.cms.server.service.common.SysLogsService;

/**
 * 日志信息业务层实现
 * 
 * @author qsy
 * @version v1.0
 * @date 2016年11月26日
 */
@Service
public class SysLogsServiceImpl implements SysLogsService {
	@Resource
	private SysLogsMapper mapper;

	/**
	 * 保存数据
	 * 
	 * @param bsl
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void save(SysLogsVo vo) throws Exception {
		try {
			SysLogsPo po = YBBeanUtils.copyProperties(vo, SysLogsPo.class);
			po.setId(ToolUtils.getUUID());
			po.setOptDate(new Date());
			if(po.getLogDetail() != null && po.getLogDetail().length()>1024){
				po.setLogDetail(po.getLogDetail().substring(0, 1023));
			}
			mapper.insert(po);
		} catch (Exception ex) {
			LogUtils.error("日志信息保存失败！", ex);
			throw new Exception("日志信息保存失败！");
		}
	}

}
