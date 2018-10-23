/* 
 * 文件名：ConfUtils.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月27日
 * 版本号：v1.0
*/
package com.qzi.cms.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取资源文件
 * @author qsy
 * @version v1.0
 * @date 2016年11月27日
 */
@Component
public class ConfUtils {
	/**
	 * 会话超时，单位秒
	 */
	@Value("${user.sessionTimeout}")
	private int sessionTimeout;

	/**
	 * @return the sessionTimeout
	 */
	public int getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * @param sessionTimeout the sessionTimeout to set
	 */
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
	
}
