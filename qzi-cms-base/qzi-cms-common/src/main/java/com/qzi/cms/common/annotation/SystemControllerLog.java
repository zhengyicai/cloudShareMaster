/* 
 * 文件名：SystemControllerLog.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月28日
 * 版本号：v1.0
*/
package com.qzi.cms.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志注解，适用于控制器层的方法
 * @author qsy
 * @version v1.0
 * @date 2016年11月28日
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented
public @interface SystemControllerLog {
	String description()  default "";
}
