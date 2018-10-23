package com.qzi.cms.server.service.app.impl;

import com.qzi.cms.server.mapper.UseCommunityResidentMapper;
import com.qzi.cms.server.service.app.UseCommunityResidentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/9/16.
 */

@Service("useCommunityResidentService")
public class UseCommunityResidentServiceImpl implements UseCommunityResidentService {

    @Resource
    private UseCommunityResidentMapper useCommunityResidentMapper;

    @Override
    public boolean existsLoginCR(String residentId) {
        return useCommunityResidentMapper.existsLoginOutCR(residentId);
    }
}
