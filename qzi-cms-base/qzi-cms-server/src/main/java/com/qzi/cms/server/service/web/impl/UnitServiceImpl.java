package com.qzi.cms.server.service.web.impl;

import com.qzi.cms.common.po.SysUnitPo;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.SysUnitVo;
import com.qzi.cms.server.mapper.UseUnitMapper;
import com.qzi.cms.server.service.web.UnitService;
import com.qzi.cms.server.service.web.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/10/1.
 */
@Service
public class UnitServiceImpl implements UnitService {

    @Resource
    private UseUnitMapper useUnitMapper;
    @Override
    public void insert(SysUnitPo sysUnitPo) {
        sysUnitPo.setCreateTime(new Date());
        sysUnitPo.setState("10");
        sysUnitPo.setRemark("");
        sysUnitPo.setId(ToolUtils.getUUID());
        useUnitMapper.insert(sysUnitPo);
    }

    @Override
    public List<SysUnitVo> findAll(SysUnitPo sysUnitPo) {
        return useUnitMapper.findAll(sysUnitPo);
    }

    @Override
    public void update(SysUnitPo sysUnitPo) {

        useUnitMapper.update(sysUnitPo);
    }
}
