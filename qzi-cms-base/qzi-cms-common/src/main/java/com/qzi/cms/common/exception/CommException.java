/* 
 * 文件名：CommException.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月26日
 * 版本号：v1.0
*/
package com.qzi.cms.common.exception;

/**
 * 自定义异常
 * @author qsy
 * @version v1.0
 * @date 2017年7月26日
 */
public class CommException extends Exception {
	private static final long serialVersionUID = -1242639751003917776L;
	
	/**
	 * 构造
	 */
	public CommException(String msg) {
		super(msg);
	}
	
}
