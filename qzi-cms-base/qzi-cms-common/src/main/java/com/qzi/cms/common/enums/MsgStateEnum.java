/* 
 * 文件名：MsgStateEnum.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.enums;

/**
 * 消息状态
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
public enum MsgStateEnum {
	UNREAD("10","未读"),
	READ("20","已读");
	private String code;
	private String name;
	
	private MsgStateEnum(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static String getName(String code){
		if (code == null) {
			return "";
		}
		for(MsgStateEnum enums : MsgStateEnum.values()){
			if (enums.getCode().equals(code)) {
				return enums.getName();
			}
		}
		return code;
	}
}
