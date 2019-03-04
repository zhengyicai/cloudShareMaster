package com.qzi.cms.common.vo;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/4.
 */
public class UseRoomCardVo {
    /**
     * 住户编号
     */
    @Id
    private String id;

    private Integer cardNo;

    private String roomId;

    private String communityId;

    private String buildingId;

    private String    unitId;


    private String state;

    private Date createTime;

    private String cardNos;
    private String oldcardNos;

    private String countSum;

    private String linkState;

    private String roomNo;


    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getLinkState() {
        return linkState;
    }

    public void setLinkState(String linkState) {
        this.linkState = linkState;
    }

    public String getCountSum() {
        return countSum;
    }

    public void setCountSum(String countSum) {
        this.countSum = countSum;
    }

    public String getOldcardNos() {
        return oldcardNos;
    }

    public void setOldcardNos(String oldcardNos) {
        this.oldcardNos = oldcardNos;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getCardNos() {
        return cardNos;
    }

    public void setCardNos(String cardNos) {
        this.cardNos = cardNos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
