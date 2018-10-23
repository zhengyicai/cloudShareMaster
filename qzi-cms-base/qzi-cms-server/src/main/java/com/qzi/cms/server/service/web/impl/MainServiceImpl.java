/* 
 * 文件名：MainServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月14日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.vo.SysResourceVo;
import com.qzi.cms.server.mapper.SysResourceMapper;
import com.qzi.cms.server.service.web.MainService;

/**
 * 主界面业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年6月14日
 */
@Service
public class MainServiceImpl implements MainService{
	@Resource
	public SysResourceMapper resourceMapper;
	
	/**
	 * 加载菜单按钮列表
	 * @return 集合
	 */
	@Override
	public List<SysResourceVo> findMenu(String roleId){
		return resourceMapper.findMenus(roleId);
	}
}
