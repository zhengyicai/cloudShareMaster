/* 
 * 文件名：EquipmentEnum.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.enums;

/**
 * 设备类型枚举
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
public enum EquipmentEnum {
	MANAGE("10","安卓管理机"),
	WALL("20","围墙机"),
	UNIT("30","单元门口机");
	
	private String code;
	private String name;
	
	private EquipmentEnum(String code, String name) {
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
		for(KaptchaEnum enums : KaptchaEnum.values()){
			if (enums.getCode().equals(code)) {
				return enums.getName();
			}
		}
		return code;
	}
}
