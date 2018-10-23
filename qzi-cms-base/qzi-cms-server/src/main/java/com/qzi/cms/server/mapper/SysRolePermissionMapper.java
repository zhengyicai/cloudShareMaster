/* 
 * 文件名：SysRolePermissionMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月21日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.SysRolePermissionPo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 角色权限DAO
 * @author qsy
 * @version v1.0
 * @date 2017年6月21日
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionPo>{

	/**
	 * @param roleId
	 */
	@Select("delete from sys_role_permission where roleId=#{roleId}")
	public void deleteByRoleId(@Param("roleId") String roleId);
	
}
