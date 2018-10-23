/* 
 * 文件名：SysParameterServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月15日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.po.SysParameterPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysParameterVo;
import com.qzi.cms.server.mapper.SysParameterMapper;
import com.qzi.cms.server.service.web.ParameterService;

/**
 * 参数设置业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年6月15日
 */
@Service
public class ParameterServiceImpl implements ParameterService {
	@Resource
	private SysParameterMapper parameterMapper;

	@Override
	public List<SysParameterVo> findAll(Paging paging) throws Exception {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return parameterMapper.findAll(rwoBounds);
	}

	@Override
	public void add(SysParameterVo parameterVo) throws Exception {
		//将vo转换成Po
		SysParameterPo parameterPo = YBBeanUtils.copyProperties(parameterVo, SysParameterPo.class);
		parameterPo.setCreateTime(new Date());
		parameterPo.setId(ToolUtils.getUUID());
		parameterPo.setUpdateTime(new Date());
		//调用dao保存数据
		parameterMapper.insert(parameterPo);
	}

	@Override
	public void update(SysParameterVo parameterVo) throws Exception {
		//将vo转换成Po
		SysParameterPo parameterPo = YBBeanUtils.copyProperties(parameterVo, SysParameterPo.class);
		parameterPo.setUpdateTime(new Date());
		//调用dao修改数据
		parameterMapper.updateByPrimaryKey(parameterPo);
	}

	@Override
	public void delete(SysParameterVo parameterVo) {
		//调用dao删除数据
		parameterMapper.deleteByPrimaryKey(parameterVo.getId());
	}

	@Override
	public long findCount() {
		return parameterMapper.findCount();
	}

}
