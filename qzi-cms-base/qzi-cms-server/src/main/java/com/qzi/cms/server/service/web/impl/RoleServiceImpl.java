/* 
 * 文件名：RoleServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月21日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.po.SysRolePermissionPo;
import com.qzi.cms.common.vo.SysResourceVo;
import com.qzi.cms.common.vo.SysRolePermissionVo;
import com.qzi.cms.common.vo.SysRolePermissionVo.RolePermVo;
import com.qzi.cms.server.mapper.SysResourceMapper;
import com.qzi.cms.server.mapper.SysRolePermissionMapper;
import com.qzi.cms.server.service.web.RoleService;

/**
 * 权限管理业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年6月21日
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private SysResourceMapper resMapper;
	@Resource
	private SysRolePermissionMapper rolePermMapper;


	@Override
	public List<SysResourceVo> findMenus(String roleId) {
		return resMapper.findRoleMenus(roleId);
	}


	@Override
	public void saveRolePerm(SysRolePermissionVo rolePerm) throws Exception {
		//删除数据
		rolePermMapper.deleteByRoleId(rolePerm.getRoleId());
		//保存数据
		for(RolePermVo spVo:rolePerm.getLis()){
			SysRolePermissionPo rolePermPo = new SysRolePermissionPo();
			rolePermPo.setRoleId(rolePerm.getRoleId());
			rolePermPo.setResourceId(spVo.getResourceId());
			rolePermMapper.insert(rolePermPo);
		}
	}
	
}
