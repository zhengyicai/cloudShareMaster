package com.qzi.cms.server.service.app.impl;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.*;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.mapper.*;
import com.qzi.cms.server.service.app.RegisterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*import sun.jvm.hotspot.asm.Register;*/

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhengyicai on 2018/9/8.
 */
@Service("registerservice")
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private UseCommunityMapper useCommunityMapper;

    @Resource
    private UseBuildingMapper buildMapper;

    @Resource
    private UseResidentMapper useResidentMapper;

    @Resource
    private UseRoomMapper useRoomMapper;

    @Resource
    private UseResidentRoomMapper useResidentRoomMapper;

    @Override
    public List<UseCommunityPo> regfindAll(UseCommunityPo po) {
        return useCommunityMapper.regfindAll(po);
    }

    @Override
    public Integer regfindCount(UseCommunityPo po) {
        return useCommunityMapper.regfindCount(po);
    }

    @Override
    public List<UseBuildingPo> findBuilding(String communityId) {
        return buildMapper.findByCommunityId(communityId);
    }


    @Override
    public long findCount(String communityId) {
        return buildMapper.findByCount(communityId);
    }

    @Override
    public Boolean existsMobile(String mobile) {
        return useResidentMapper.existsMobile(mobile);
    }

    @Override
    public UseResidentPo findMobile(String mobile) {
        return useResidentMapper.findMobile(mobile);
    }

    @Override
    public UseRoomVo findRoom(String buildingId, String utilName, String roomName) {
        return useRoomMapper.findRoom(buildingId,utilName,roomName);
    }

    @Override
    public void updateRegister(UseResidentPo po) {
        useResidentMapper.updateByPrimaryKey(po);

    }


    @Override
    public void addCommunity(UseResidentVo residentVo) throws Exception {
        UseRoomVo vo1 =  useRoomMapper.findRoom(residentVo.getBuildingId(),residentVo.getUnitId(),residentVo.getRoomName());

        if(vo1!=null){
           UseResidentRoomPo useResidentRoomPo =   useResidentRoomMapper.existsRoom(vo1.getId(),residentVo.getId());
            if( useResidentRoomPo!=null){
                if("10".equals(useResidentRoomPo.getOwner())){
                    throw new CommException("请不要重复绑定同一房间号");
                }else if("20".equals(useResidentRoomPo.getOwner())){
                    throw new CommException("管理员正在审核中，请等待");
                }


            }else{

                 if("10".equals(residentVo.getIsTrue())){
                     useResidentRoomMapper.setDefault(residentVo.getId());
                 }

                 if( useResidentRoomMapper.findResidentExist(residentVo.getId()) ==null){
                        residentVo.setIsTrue("10");
                 }

                UseResidentRoomPo usrRepo = new UseResidentRoomPo();
                usrRepo.setId(ToolUtils.getUUID());
                usrRepo.setCommunityId(residentVo.getCommunityId());
                usrRepo.setOwner("20");
                usrRepo.setIsTrue(residentVo.getIsTrue());
                usrRepo.setRemark(residentVo.getRemark());
                usrRepo.setResidentId(residentVo.getId());
                usrRepo.setRoomId(vo1.getId());
                useResidentRoomMapper.insert(usrRepo);

            }

        }else{
            throw new CommException("该小区房间号不存在");
        }
    }

    @Override
    public void updateCommunityisTrue(UseResidentRoomVo useResidentRoomVo) {
        useResidentRoomMapper.setDefault(useResidentRoomVo.getResidentId());
        useResidentRoomMapper.setDefaultisTrue(useResidentRoomVo.getId());
    }


}
