package com.qzi.cms.common.vo;

import java.util.List;

/**
 * 首页用户数据Vo
 * Created by Administrator on 2018/11/22.
 */
public class HomeUserCommunityVo {
    private Integer csCode;  //厂商代码
    private  String areaCode; //项目代码
    private String community; //项目代码Id
    private List<UseEquipmentVo> equList;  //设备列表
    private String communityName; //小区名字
    private String roomId; //房间Id
    private  String  userName; //用户名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public List<UseEquipmentVo> getEquList() {
        return equList;
    }

    public void setEquList(List<UseEquipmentVo> equList) {
        this.equList = equList;
    }

    public Integer getCsCode() {
        return csCode;
    }

    public void setCsCode(Integer csCode) {
        this.csCode = csCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

}
