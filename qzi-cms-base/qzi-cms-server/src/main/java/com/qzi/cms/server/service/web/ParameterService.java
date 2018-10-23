/* 
 * 文件名：SysParameterService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月15日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.SysParameterVo;

/**
 * 参数设置业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月15日
 */
public interface ParameterService {

	/**
	 * 查找所有数据
	 * @param paging 分页
	 * @return 集合
	 * @throws Exception 
	 */
	public List<SysParameterVo> findAll(Paging paging) throws Exception;

	/**
	 * 新增
	 * @param parameterVo
	 * @throws Exception 
	 */
	public void add(SysParameterVo parameterVo) throws Exception;

	/**
	 * 修改
	 * @param parameterVo
	 * @throws Exception 
	 */
	public void update(SysParameterVo parameterVo) throws Exception;

	/**
	 * 删除
	 * @param parameterVo
	 */
	public void delete(SysParameterVo parameterVo);

	/**
	 * 查找总记录数
	 * @return
	 */
	public long findCount();
	
}
