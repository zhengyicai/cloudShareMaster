/* 
 * 文件名：TreeVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月5日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

import java.util.List;

/**
 * 树型菜单
 * @author qsy
 * @version v1.0
 * @date 2017年7月5日
 */
public class TreeVo {
	/**
	 * 节点编号
	 */
	private String id;
	/**
	 * 节点名称
	 */
	private String value;
	/**
	 * 子节点
	 */
	private List<TreeVo> children;


	/**
	 *
	 */
	private String label;


	private String parentId;


	/**
	 * 
	 */
	public TreeVo() {
		super();
	}
	
	/**
	 * @param id
	 * @param value
	 */
	public TreeVo(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the children
	 */
	public List<TreeVo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}
}
