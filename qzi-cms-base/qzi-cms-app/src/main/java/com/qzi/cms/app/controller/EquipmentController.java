package com.qzi.cms.app.controller;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.po.UseEquipmentPo;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.HomeUserCommunityVo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.server.mapper.UseCardEquipmentMapper;
import com.qzi.cms.server.mapper.UseEquipmentMapper;
import com.qzi.cms.server.mapper.UseRoomCardMapper;
import com.qzi.cms.server.mapper.UseUserCardEquipmentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/2/25.
 */



@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Resource
    private UseRoomCardMapper useRoomCardMapper;
    @Resource
    private UseEquipmentMapper useEquipmentMapper;

    @Resource
    private UseCardEquipmentMapper useCardEquipmentMapper;

    @Resource
    private UseUserCardEquipmentMapper useUserCardEquipmentMapper;


    /**
     * 获取设备列表
     * @return
     */
    @GetMapping("/getlist")
    public RespBody getlist(HomeUserCommunityVo homeUserCommunityVo){
        RespBody respBody = new RespBody();
        try {

            List<UseEquipmentVo> list = useEquipmentMapper.appFindUseEquipmentNo(homeUserCommunityVo.getDefaultRoomId(),homeUserCommunityVo.getRoomId().substring(0,10));
            List<UseEquipmentVo> list1 = useEquipmentMapper.appFindUseEquipmentNo1(homeUserCommunityVo.getDefaultRoomId(),homeUserCommunityVo.getRoomId().substring(0,6));


            if(list1.size()>0){
                for(UseEquipmentVo epo:list1){
                    list.add(epo);
                }
            }


            respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取设备列表成功",list);

        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "获取设备列表失败");
            LogUtils.error("获取设备列表失败！",ex);
        }
        return respBody;
    }

    /**
     * 获取卡号列表
     * @return
     */
    @GetMapping("/getCardlist")
    public RespBody getCardlist(HomeUserCommunityVo homeUserCommunityVo){
        RespBody respBody = new RespBody();
        try {
            respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取卡号列表成功",useCardEquipmentMapper.findCardList(homeUserCommunityVo.getDefaultRoomId(),homeUserCommunityVo.getEquipmentId()));
        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "获取卡号列表失败");
            LogUtils.error("获取卡号列表失败！",ex);
        }
        return respBody;
    }


    @PostMapping("/updateCardState")
    public RespBody updateCardState(@RequestBody HomeUserCommunityVo homeUserCommunityVo){
        RespBody respBody = new RespBody();
        try {
            useCardEquipmentMapper.updateCardEquipment(homeUserCommunityVo.getCardId(),homeUserCommunityVo.getEquipmentId(),homeUserCommunityVo.getLinkState());
            respBody.add(RespCodeEnum.SUCCESS.getCode(), "发卡成功");
        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "发卡失败");
            LogUtils.error("发卡失败！",ex);
        }
        return respBody;

    }


    //获取小区对应的设备
    @GetMapping("/getCommunity")
    public RespBody getCommunity(String  communityId){
        RespBody respBody = new RespBody();

        try {
            List<UseEquipmentVo> useEquipmentPoList =   useEquipmentMapper.communityIdList(communityId);
            respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取卡号列表成功",useEquipmentPoList);
        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "发卡失败");
            LogUtils.error("发卡失败！",ex);
        }
        return respBody;
    }

    //获取该设备的房卡列表

    @GetMapping("/getUserCardList")
    public RespBody getUserCardList(String  equipmentId){
        RespBody respBody = new RespBody();

        try {
            respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取卡号列表成功",useUserCardEquipmentMapper.findCardList(equipmentId));
        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "发卡失败");
            LogUtils.error("发卡失败！",ex);
        }
        return respBody;
    }


    @PostMapping("/updateUserCardState")
    public RespBody updateUserCardState(@RequestBody HomeUserCommunityVo homeUserCommunityVo){
        RespBody respBody = new RespBody();
        try {
            useUserCardEquipmentMapper.updateUserCardEquipment(homeUserCommunityVo.getCardId(),homeUserCommunityVo.getLinkState());
            respBody.add(RespCodeEnum.SUCCESS.getCode(), "发卡成功");
        } catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "发卡失败");
            LogUtils.error("发卡失败！",ex);
        }
        return respBody;

    }






}
