/* 
 * 文件名：UserService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月12日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.SysRoleVo;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.TreeVo;

/**
 * 用户业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月12日
 */
public interface UserService {

	/**
	 * 获取用户信息
	 * @param token 令牌
	 * @return 用户对象
	 * @throws Exception 
	 */
	public SysUserVo SysUserVo(String token) throws Exception;

	/**
	 * 获取所有数据
	 * @param paging
	 * @return
	 */
	public List<SysUserVo> findAll(Paging paging);

	public List<SysUserVo> findAllChild(String parentId,Paging paging);
	public long findAllChildCount(String parentId);

	public List<SysUserVo> findRoleAll(Paging paging) throws  Exception;


	public List<TreeVo> findTree() throws Exception;

	public long findRoleCount() throws  Exception;

	/**
	 * 查找总记录数
	 * @return
	 */
	public long findCount();

	/**
	 * 新增用户
	 * @param parameterVo
	 * @throws Exception 
	 */
	public void add(SysUserVo userVo) throws Exception;



	public void firmAdd(SysUserVo userVo) throws Exception;



	/**
	 * 新增物业用户
	 */
	public void addCommun(SysUserVo userVo) throws  Exception;
	/**
	 * 修改用户
	 * @param userVo
	 * @throws Exception 
	 */
	public void update(SysUserVo userVo) throws Exception;

	/**
	 * 修改该用户的密码
	 * @param password
	 * @param id
	 * @throws Exception
	 */

	public void updatePassword(String password,String id) throws  Exception;

	/**
	 * 删除用户
	 * @param userVo
	 */
	public void delete(SysUserVo userVo);

	/**
	 * 查找所有角色
	 * @return
	 * @throws Exception 
	 */
	public List<SysRoleVo> findRoles() throws Exception;

	/**
	 * 查找登录名是否存在
	 * @param loginName
	 * @return
	 */
	public SysUserVo findByLoginName(String loginName);


	/**
	 * 判断code是否存在
	 * @param code
	 * @return
	 */

	public Integer findCodeExist(Integer code);

	/**
	 * 修改密码
	 * @param newPw
	 * @param id
	 */
	public void updatePw(String newPw, String id);


	public SysUserVo findOne(String id);

	public Integer maxCode(String parentId);

}
