/* 
 * 文件名：NewResidentServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.qzi.cms.common.po.UseCommunityResidentPo;
import com.qzi.cms.common.po.UseResidentRoomPo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.mapper.UseCommunityResidentMapper;
import com.qzi.cms.server.mapper.UseResidentRoomMapper;
import com.qzi.cms.server.mapper.UseRoomMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.util.YzsClientUtils;
import com.qzi.cms.common.vo.ClientRespVo;
import com.qzi.cms.common.vo.ClientVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.service.web.NewResidentService;

/**
 * 新住户业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
@Service
public class NewResidentServiceImpl implements NewResidentService {
	@Resource
	private UseResidentMapper residentMapper;
	@Resource
	private YzsClientUtils clientUtils;

	@Resource
	private UseRoomMapper useRoomMapper;
	@Resource
	private UseResidentRoomMapper useResidentRoomMapper;
	@Resource
	private UseCommunityResidentMapper communityResidentMapper;

	@Override
	public List<UseResidentVo> findAll(Paging paging, String criteria) {
		//分页对象
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return residentMapper.findAllByCriteria(rwoBounds,criteria);
	}

	@Override
	public long findCount(String criteria) {
		return residentMapper.findCountByCriteria(criteria);
	}

	@Override
	public boolean existsMobile(UseResidentVo residentVo) {
		return residentMapper.existsMobile(residentVo.getMobile());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseResidentVo residentVo) throws Exception {
		//保存数据
		UseResidentPo residentPo = YBBeanUtils.copyProperties(residentVo, UseResidentPo.class);
		residentPo.setId(ToolUtils.getUUID());
		String salt = ToolUtils.getUUID();
		residentPo.setSalt(salt);
		String loginPw = CryptUtils.hmacSHA1Encrypt(residentVo.getPassword(), salt);
		residentPo.setPassword(loginPw);
		residentPo.setCreateTime(new Date());
		
		residentMapper.insert(residentPo);

		//查找房间       插入数据
		 UseRoomVo vo1 =  useRoomMapper.findRoom(residentVo.getBuildingId(),residentVo.getUnitId(),residentVo.getRoomName());

		 if(vo1!=null){
			 		UseResidentRoomPo usrRepo = new UseResidentRoomPo();
					usrRepo.setCommunityId(residentVo.getCommunityId());
					usrRepo.setOwner("10");
					usrRepo.setResidentId(residentPo.getId());
					usrRepo.setRoomId(vo1.getId());
					useResidentRoomMapper.insert(usrRepo);
			 //一个用户关联一个账号，如该用户和小区已经关联了， 就执行修改   ,未授权
			 		UseResidentPo residentPo1 = residentMapper.findMobile(residentVo.getMobile());
			 		if(residentPo1 != null){
			 			if(communityResidentMapper.existsCR(residentPo1.getId(),residentVo.getCommunityId())){
			 				UseCommunityResidentPo crPo = new UseCommunityResidentPo();
			 				crPo.setResidentId(residentPo.getId());
			 				crPo.setCommunityId(residentVo.getCommunityId());
			 				crPo.setState("30");
			 				communityResidentMapper.updateByPrimaryKey(crPo);

			 			}else{
			 				UseCommunityResidentPo crPo = new UseCommunityResidentPo();
			 				crPo.setResidentId(residentPo.getId());
			 				crPo.setCommunityId(residentVo.getCommunityId());
			 				crPo.setState("30");
			 				communityResidentMapper.insert(crPo);
			 			}

			 		}else{
			 			throw new CommException("手机号未注册,请先注册");
			 		}

		 }






		//注册client账号
		ClientVo client = new ClientVo();
		client.setUserId(residentVo.getMobile());
		client.setMobile(residentVo.getMobile());
		ClientRespVo clientResp = ToolUtils.toObject(clientUtils.createClient(client),ClientRespVo.class);
		if(clientResp.getResp().getRespCode().equals("000000")){
			residentPo.setClientNumber(clientResp.getResp().getClient().getClientNumber());
			residentPo.setClientPwd(clientResp.getResp().getClient().getClientPwd());
			residentPo.setLoginToken(clientResp.getResp().getClient().getLoginToken());
			residentMapper.updateByPrimaryKey(residentPo);
		}else{
			throw new CommException("注册Client账号失败["+clientResp.getResp().getRespCode()+"]");
		}
	}

	@Override
	public void update(UseResidentVo residentVo) throws Exception {
		UseResidentPo residentPo = YBBeanUtils.copyProperties(residentVo, UseResidentPo.class);
		//查找房间       插入数据
				 UseRoomVo vo1 =  useRoomMapper.findRoom(residentVo.getBuildingId(),residentVo.getUnitId(),residentVo.getRoomName());

				 if(vo1!=null){
					 		UseResidentRoomPo usrRepo = new UseResidentRoomPo();
							usrRepo.setCommunityId(residentVo.getCommunityId());
							usrRepo.setOwner("10");
							usrRepo.setResidentId(residentPo.getId());
							usrRepo.setRoomId(vo1.getId());
							useResidentRoomMapper.insert(usrRepo);
					 //一个用户关联一个账号，如该用户和小区已经关联了， 就执行修改   ,未授权

							if(communityResidentMapper.existsCR(residentVo.getId(),residentVo.getCommunityId())){
								UseCommunityResidentPo crPo = new UseCommunityResidentPo();
								crPo.setResidentId(residentPo.getId());
								crPo.setCommunityId(residentVo.getCommunityId());
								crPo.setState("30");
								communityResidentMapper.updateByPrimaryKey(crPo);

							}else{
								UseCommunityResidentPo crPo = new UseCommunityResidentPo();
								crPo.setResidentId(residentPo.getId());
								crPo.setCommunityId(residentVo.getCommunityId());
								crPo.setState("30");
								communityResidentMapper.insert(crPo);
							}



				 }
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseResidentVo residentVo) throws Exception {
		//注销云之讯账户
		ClientVo client = new ClientVo();
		client.setUserId(residentVo.getMobile());
		clientUtils.deleteClient(client);
		//删除住户信息
		residentMapper.deleteByPrimaryKey(residentVo.getId());
	}

	@Override
	public void delMobile(String mobile) throws Exception {
		//注销云之讯账户
			ClientVo client = new ClientVo();
			client.setUserId(mobile);
			clientUtils.deleteClient(client);

	}

}
