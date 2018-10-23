/* 
 * 文件名：KaptchaEnum.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.enums;

/**
 * 验证码枚举类
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
public enum KaptchaEnum {
	UUID("10","保存到redis的Key"),
	IMGSTR("20","base64图片字符串");
	
	private String code;
	private String name;
	
	private KaptchaEnum(String code, String name) {
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
