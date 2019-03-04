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
    private  String userId;  //用户id
    private String adminMobile; //物业电话

    private String residentId; //用户id




    private String defaultRoomId; //默认房间Id
    private String  equipmentId;  //设备id
    private String cardId; //房卡Id
    private String linkState; //设备发卡状态  10：已发 20:未发

    private Integer equRoomState;//设备状态 10：已发，20：未发



    public Integer getEquRoomState() {
        return equRoomState;
    }

    public void setEquRoomState(Integer equRoomState) {
        this.equRoomState = equRoomState;
    }

    public String getLinkState() {
        return linkState;
    }

    public void setLinkState(String linkState) {
        this.linkState = linkState;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDefaultRoomId() {
        return defaultRoomId;
    }

    public void setDefaultRoomId(String defaultRoomId) {
        this.defaultRoomId = defaultRoomId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
