/* 
 * 文件名：UserServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月12日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.po.UseCommunityUserPo;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.server.mapper.UseCommunityMapper;
import com.qzi.cms.server.mapper.UseCommunityUserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.po.SysUserPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysRoleVo;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.server.mapper.SysRoleMapper;
import com.qzi.cms.server.mapper.SysUserMapper;
import com.qzi.cms.server.service.web.UserService;

/**
 * 用户业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年6月12日
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private RedisService redisService;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysRoleMapper roleMapper;
	@Resource
	private UseCommunityUserMapper communityUserMapper;
	@Resource
	private UseCommunityMapper communityMapper;

	@Override
	public SysUserVo SysUserVo(String token) throws Exception {
		SysUserVo userVo = null;
		Object obj = redisService.getObj(token);
		if(obj != null && obj instanceof SysUserVo){
			userVo = (SysUserVo) obj;
		}
		return userVo;
	}

	@Override
	public List<SysUserVo> findAll(Paging paging) {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return userMapper.findAll(rwoBounds);
	}

	@Override
	public long findCount() {
		return userMapper.findCount();
	}

	@Override
	public void add(SysUserVo userVo) throws Exception {
		SysUserPo userPo = YBBeanUtils.copyProperties(userVo, SysUserPo.class);
		String salt = ToolUtils.getUUID();
		String loginPw = CryptUtils.hmacSHA1Encrypt(userVo.getPassword(), salt);
		userPo.setPassword(loginPw);
		userPo.setSalt(salt);
		userPo.setId(ToolUtils.getUUID());
		userPo.setCreateTime(new Date());
		userMapper.insert(userPo);

		


	}


	/**
	 * 新增物业绑定小区
	 * 2018-09-02
	 * @param userVo
	 * @throws Exception
	 */

	@Override
	public void addCommun(SysUserVo userVo) throws Exception {
		SysUserPo userPo = YBBeanUtils.copyProperties(userVo, SysUserPo.class);
				String salt = ToolUtils.getUUID();
				String loginPw = CryptUtils.hmacSHA1Encrypt(userVo.getPassword(), salt);
				userPo.setPassword(loginPw);
				userPo.setSalt(salt);
				userPo.setId(ToolUtils.getUUID());
				userPo.setCreateTime(new Date());
				userMapper.insert(userPo);


				/*添加物业管理（物业账号和admin账号）*/
				UseCommunityUserPo ucuPo = new UseCommunityUserPo();
				ucuPo.setCommunityId(userVo.getCommunityArea());
				ucuPo.setUserId(userPo.getId());
				communityUserMapper.insert(ucuPo);


				SysUserVo sysUserVo =  userMapper.findByloginName("admin");
				UseCommunityUserPo sysPo = new UseCommunityUserPo();
				sysPo.setCommunityId(userVo.getCommunityArea());
				sysPo.setUserId(sysUserVo.getId());
				communityUserMapper.insert(sysPo);

				UseCommunityPo po =  communityMapper.findOne(userVo.getCommunityArea());
			    po.setSysUserId(userPo.getId());
			    communityMapper.updateByPrimaryKey(po);


	}

	@Override
	public void update(SysUserVo userVo) throws Exception {
		SysUserPo userPo = userMapper.selectByPrimaryKey(userVo.getId());
		userPo.setUserName(userVo.getUserName());
		userPo.setMobile(userVo.getMobile());
		userPo.setRoleId(userVo.getRoleId());
		userPo.setRoleName(userVo.getRoleName());
		userPo.setState(userVo.getState());
		userMapper.updateByPrimaryKey(userPo);
	}

	@Override
	public void updatePassword(String password, String id) throws Exception {

	}


	@Override
	public void delete(SysUserVo userVo) {
		userMapper.deleteByPrimaryKey(userVo.getId());
	}

	@Override
	public List<SysRoleVo> findRoles() throws Exception {
		return YBBeanUtils.copyList(roleMapper.selectAll(), SysRoleVo.class);
	}

	@Override
	public SysUserVo findByLoginName(String loginName) {
		return userMapper.findByloginName(loginName);
	}

	@Override
	public void updatePw(String newPw,String id) {
		SysUserPo userPo = userMapper.selectByPrimaryKey(id);
		userPo.setPassword(newPw);
		userMapper.updateByPrimaryKey(userPo);
	}

}
