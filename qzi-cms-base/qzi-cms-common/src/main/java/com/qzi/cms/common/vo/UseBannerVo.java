/* 
 * 文件名：UseBannerVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.Date;

/**
 * 手机广告轮播图VO
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
public class UseBannerVo {
	/**
	 * 主键编号
	 */
	private String id;
	/**
	 * 广告图标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 顺序
	 */
	private int bannerIdx;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the bannerIdx
	 */
	public int getBannerIdx() {
		return bannerIdx;
	}
	/**
	 * @param bannerIdx the bannerIdx to set
	 */
	public void setBannerIdx(int bannerIdx) {
		this.bannerIdx = bannerIdx;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
