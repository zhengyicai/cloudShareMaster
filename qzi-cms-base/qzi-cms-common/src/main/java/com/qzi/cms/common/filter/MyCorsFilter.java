/* 
 * 文件名：MyCorsFilter.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月29日
 * 版本号：v1.0
*/
package com.qzi.cms.common.filter;

import javax.servlet.annotation.WebFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决浏览器跨域问题
 * 
 * @author qsy
 * @version v1.0
 * @date 2016年11月29日
 */
@WebFilter("/*")
public class MyCorsFilter extends CorsFilter {

	
	
	public MyCorsFilter() {
		super(configurationSource());
	}

	private static UrlBasedCorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
