/* 
 * 文件名：ToolUtils.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月26日
 * 版本号：v1.0
*/
package com.qzi.cms.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 系统通用工具操作类
 * @author qsy
 * @version v1.0
 * @date 2016年11月26日
 */
public class ToolUtils {
	public static ObjectMapper objectMapper;
	
	/**
	 * 产生UUID串
	 * @return 32位字符
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
	/**
	 * 随机产生6位数
	 * @return 验证码
	 */
	public static String getCode(){
		return String.valueOf(((int)((Math.random()*9+1)*100000)));
	}
	
	/**
	 * 对象序列化成json字符串
	 * @param obj 目标对象
	 * @return json字符串
	 */
	public static String toJson(Object obj){
		if (objectMapper == null) {  
			objectMapper = new ObjectMapper();  
		}
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
			LogUtils.error("序列化json对象失败",ex);
		}
		return "";
	}
	
	 /** 
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。 
     * (1)转换为普通JavaBean：readValue(json,Student.class) 
     * (2)转换为List,如List<Student>,将第二个参数传递为Student 
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List 
     *  
     * @param jsonStr 
     * @param valueType 
     * @return 
     */  
    public static <T> T toObject(String jsonStr, Class<T> valueType) {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        try {  
            return objectMapper.readValue(jsonStr, valueType);  
        } catch (Exception ex) {  
        	LogUtils.error("字符串转json对象失败",ex);
        }  
  
        return null;  
    }  
      
    /** 
     * json数组转List 
     * @param jsonStr 
     * @param valueTypeRef 
     * @return 
     */  
    public static <T> T toObject(String jsonStr, TypeReference<T> valueTypeRef){  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        try {  
            return objectMapper.readValue(jsonStr, valueTypeRef);  
        } catch (Exception ex) {  
        	LogUtils.error("字符串转json集合对象失败",ex); 
        }  
        return null;  
    }
    
    /**
     * 验证手机号是否正确
     * @param mobile 手机号
     * @return true|false
     */
	public static boolean isMobile(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(16[0-9])|(15[0-9])|(17[0-9])||(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
}
