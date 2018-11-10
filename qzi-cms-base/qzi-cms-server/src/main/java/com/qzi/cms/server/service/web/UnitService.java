package com.qzi.cms.server.service.web;

import com.qzi.cms.common.po.SysUnitPo;
import com.qzi.cms.common.vo.SysUnitVo;

import java.util.List;

/**
 * Created by Administrator on 2018/10/1.
 */
public interface UnitService {
    public void  insert(SysUnitPo sysUnitPo);
    public List<SysUnitVo> findAll(SysUnitPo sysUnitPo);
    public void update(SysUnitPo sysUnitPo);

}
