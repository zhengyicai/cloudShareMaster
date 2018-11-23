package com.qzi.cms.server.service.app;

import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.po.UseCommunityPo;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.common.vo.UseRoomVo;

import java.util.List;

/**
 * 注册模块
 * Created by zhengyicai on 2018/9/8.
 */
public interface RegisterService {
    /**
     * 根据三级连表查询小区数据
     * @param po
     * @return
     */
    public List<UseCommunityPo> regfindAll(UseCommunityPo po);

    public Integer regfindCount(UseCommunityPo po);
    /**
     * 获取楼栋列表数据
     * @param communityId 小区编号
     * @param paging 分页对象
     * @return 楼栋列表
     */
    public List<UseBuildingPo> findBuilding(String communityId);

    /**
     * 总记录数
     * @param communityId 小区编号
     * @return 总数
     */
    public long findCount(String communityId);

    public Boolean existsMobile(String mobile);

    public UseResidentPo findMobile(String mobile);

    public UseRoomVo findRoom(String buildingId,String utilName,String roomName);

    public void updateRegister(UseResidentPo po);


    /**
     * 添加小区
     * @param residentVo
     * @throws Exception
     */
    public void addCommunity(UseResidentVo residentVo) throws Exception;

    public void updateCommunityisTrue(UseResidentRoomVo useResidentRoomVo);

}
