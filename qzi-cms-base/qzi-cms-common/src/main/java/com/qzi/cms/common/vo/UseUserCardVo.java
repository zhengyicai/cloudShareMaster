package com.qzi.cms.common.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/2/27.
 */
public class UseUserCardVo {
    private String id;

    private String userId;
    private String communityId;
    private String cardNo;
    private String cardName;
    private String equipmentId;
    private String state;
    private Date createTime;
    private String[]  choId;
    private String countSum;

    public String getCountSum() {
        return countSum;
    }

    public void setCountSum(String countSum) {
        this.countSum = countSum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

    public String[] getChoId() {
        return choId;
    }

    public void setChoId(String[] choId) {
        this.choId = choId;
    }
}
