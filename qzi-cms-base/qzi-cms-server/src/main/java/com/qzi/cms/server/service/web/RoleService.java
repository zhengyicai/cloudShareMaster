/* 
 * 文件名：RoleService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月21日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.vo.SysResourceVo;
import com.qzi.cms.common.vo.SysRolePermissionVo;

/**
 * 权限管理业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月21日
 */
public interface RoleService {

	/**
	 * 获取所有菜单列表
	 * @param roleId 
	 * @return
	 */
	public List<SysResourceVo> findMenus(String roleId);

	/**
	 * 保存角色权限关系
	 * @param rolePerms
	 * @return
	 * @throws Exception 
	 */
	public void saveRolePerm(SysRolePermissionVo rolePerm) throws Exception;

}
