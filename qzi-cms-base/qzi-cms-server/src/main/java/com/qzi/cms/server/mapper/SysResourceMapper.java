/* 
 * 文件名：SysResourceMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月14日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.SysResourcePo;
import com.qzi.cms.common.vo.SysResourceVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 资源DAO接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月14日
 */
public interface SysResourceMapper extends BaseMapper<SysResourcePo>{
	
	@Select("SELECT sr.* from sys_resource sr INNER JOIN sys_role_permission srp on sr.id = srp.resourceId and sr.state = '10' and srp.roleId=#{roleId} order by sr.resSort")
	public List<SysResourceVo> findMenus(@Param("roleId") String roleId);

	@Select("SELECT sr.* from sys_resource sr INNER JOIN sys_role_permission srp on sr.id = srp.resourceId and sr.state = '10' and sr.parentId = #{parentId} and srp.roleId=#{roleId} order by sr.resSort")
	public List<SysResourceVo> findVueMenus(@Param("roleId") String roleId,@Param("parentId") String parentId);


	/**
	 * @param roleId 
	 * @return
	 */
	@Select("SELECT res.*,srp.resourceId is not NULL selected FROM sys_resource res LEFT JOIN sys_role_permission srp on srp.resourceId=res.id and srp.roleId=#{roleId} and res.state='10' order by res.resSort")
	public List<SysResourceVo> findRoleMenus(@Param("roleId") String roleId);


}
