/* 
 * 文件名：BaseUrlRecordServiceImpl.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.po.SysUrlRecordPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.SysUrlRecordVo;
import com.qzi.cms.server.mapper.SysUrlRecordMapper;
import com.qzi.cms.server.service.common.SysUrlRecordService;

/**
 * URL不拦截记录操作业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2016年11月29日
 */
@Service
public class SysUrlRecordServiceImpl implements SysUrlRecordService{
	@Resource
	private SysUrlRecordMapper recordMapper;

	@Override
	public List<String> findUrl() {
		return recordMapper.findUrl();
	}

	/**
	 * 分页查询URL记录信息
	 */
	@Override
	public void loadRecords(Paging page, RespBody respBody) throws Exception {
		//创建分页插件对象
		RowBounds rowBounds = new RowBounds(page.getPageNumber(),page.getPageSize());
		List<SysUrlRecordPo> recordPos = recordMapper.loadRecords(rowBounds);
		//将po持久化类转换为vo类并保存到respBody对象中
		respBody.add(RespCodeEnum.SUCCESS.getCode(),"查询URL记录信息成功",YBBeanUtils.copyList(recordPos, SysUrlRecordVo.class));
		//查总记录树
		int totalCount = recordMapper.findAllCount();
		page.setTotalCount(totalCount);
		respBody.setPage(page);
	}

	/**
	 * 新增URL记录
	 */
	@Transactional
	@Override
	public void addRecord(SysUrlRecordVo recordVo,RespBody respBody) throws Exception {
		//将vo类转换为po持久化类
		SysUrlRecordPo recordPo = YBBeanUtils.copyProperties(recordVo, SysUrlRecordPo.class);
		//获取id
		recordPo.setId(ToolUtils.getUUID());
		//执行新增URL记录操作
		recordMapper.insert(recordPo);
		respBody.add(RespCodeEnum.SUCCESS.getCode(), "新增URL记录成功");
	}

	/**
	 * 修改URL记录
	 */
	@Transactional
	@Override
	public void updateRecord(SysUrlRecordVo recordVo,RespBody respBody) throws Exception {
		//将Vo转换成Po
		SysUrlRecordPo recordPo = YBBeanUtils.copyProperties(recordVo, SysUrlRecordPo.class);
		//执行修改URL记录操作
		recordMapper.updateRecord(recordPo);
		respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改URL记录成功");		
	}
	
	/**
	 * 修改URL记录
	 */
	@Transactional
	@Override
	public void deleteRecord(SysUrlRecordVo recordVo,RespBody respBody) throws Exception {
		//将Vo转换成Po
		SysUrlRecordPo recordPo = YBBeanUtils.copyProperties(recordVo, SysUrlRecordPo.class);
		//执行删除URL记录操作
		recordMapper.deleteRecord(recordPo);
		respBody.add(RespCodeEnum.SUCCESS.getCode(), "修改URL记录成功");		
	}

	/**
	 * 删除多条URL记录
	 */
	@Transactional
	@Override
	public void deleteRecords(List<String> delIds,RespBody respBody) throws Exception {
		
		//执行删除URL记录操作
		int count = recordMapper.deleteRecords(delIds);
		if(count<=0){
			respBody.add(RespCodeEnum.ERROR.getCode(), "删除URL记录失败");
		}
		respBody.add(RespCodeEnum.SUCCESS.getCode(), "删除"+count+"条URL记录成功");
	}

}
