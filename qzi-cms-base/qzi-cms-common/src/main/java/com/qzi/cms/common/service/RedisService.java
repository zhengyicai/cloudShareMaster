/* 
 * 文件名：RedisServer.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月23日
 * 版本号：v1.0
*/
package com.qzi.cms.common.service;

/**
 * Redis数据库操作接口
 * @author qsy
 * @version v1.0
 * @date 2016年11月23日
 */
public interface RedisService {
	/**
	 * 存储key-value数据
	 * @param key 键
	 * @param value 值
	 * @param seconds 过期时间 单位：秒
	 * @return 成功返回OK 失败和异常返回null String
	 */
	public String putString(String key, String value, int seconds);

	/**
	 * 获取值
	 * @param key 键
	 * @return 值
	 */
	public String getString(String key);
	
	/**
	 * 保存对象
	 * @param key 键
	 * @param value 值
	 * @param seconds 过期时间 单位：秒
	 * @return 是否成功
	 */
	public String putObj(String key, Object value, int seconds);

	/**
	 * 获取对象
	 * @param key 键
	 * @return 值
	 */
	public Object getObj(String key);

	/**
	 * 更新token时间
	 * @param token id
	 * @param sessionTimeout 时间
	 * @return 
	 */
	public Long expire(String key, int seconds);
	
	/**
	 * 删除key的值
	 * @param keys key数组
	 * @return 删除成功个数
	 */
	public Long del(String ... keys);
}
