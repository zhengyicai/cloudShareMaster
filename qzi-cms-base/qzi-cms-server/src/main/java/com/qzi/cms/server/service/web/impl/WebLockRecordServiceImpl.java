package com.qzi.cms.server.service.web.impl;

import com.qzi.cms.common.po.UseLockRecordPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.UseLockRecordVo;
import com.qzi.cms.server.mapper.UseLockRecordMapper;
import com.qzi.cms.server.service.app.LockRecordService;
import com.qzi.cms.server.service.web.WebLockRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/30.
 */


@Service
public class WebLockRecordServiceImpl  implements WebLockRecordService{



    @Resource
    private UseLockRecordMapper useLockRecordMapper;

    @Override
    public List<UseLockRecordVo> findAll(UseLockRecordVo useLockRecordVo, Paging paging) {
        int startRow=0;int pageSize=0;
        if(null!=paging){
            startRow=(paging.getPageNumber()>0)?(paging.getPageNumber()-1)*paging.getPageSize():0;
            pageSize=paging.getPageSize();
        }else{
            pageSize=Integer.MAX_VALUE;
        }
        return useLockRecordMapper.findAll(useLockRecordVo, startRow, pageSize);
    }

    @Override
    public long findCount(UseLockRecordVo useLockRecordVo) {
        return useLockRecordMapper.findCount(useLockRecordVo);
    }

    @Override
    public void add(UseLockRecordPo po) {
            po.setId(ToolUtils.getUUID());
            po.setCreateTime(new Date());
            po.setState("10");
            useLockRecordMapper.insert(po);


    }

    @Override
    public void remove(UseLockRecordPo useLockRecordPo) {

        useLockRecordMapper.delete(useLockRecordPo);
    }
}
