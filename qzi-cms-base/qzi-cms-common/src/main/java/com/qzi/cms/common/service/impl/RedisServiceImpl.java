/* 
 * 文件名：RedisServerImpl.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月23日
 * 版本号：v1.0
*/
package com.qzi.cms.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 数据库操作类
 * @author qsy
 * @version v1.0
 * @date 2016年11月23日
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Resource
	private JedisPool jedisPool;

	@Override
	public String putString(String key, String value, int seconds) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis();// 获得jedis实例
			return jedis.setex(key, seconds, value);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			close(jedis);
		}
		return res;
	}
	
	@Override
	public String getString(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getJedis();// 获得jedis实例
			value = jedis.get(key);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			close(jedis);
		}
		return value;
	}

	@Override
	public String putObj(String key, Object value, int seconds) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis();// 获得jedis实例
			res = jedis.set(key.getBytes(), SerializeUtil.serialize(value));
			jedis.expire(key.getBytes(), seconds);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			close(jedis);
		}
		return res;
	}

	@Override
	public Object getObj(String key) {
		Jedis jedis = null;
		byte[] byteValue = null;
		try {
			jedis = getJedis();// 获得jedis实例
			byteValue = jedis.get(key.getBytes());
			if (null == byteValue || byteValue.length < 1) {
				return null;
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			close(jedis);
		}
		return SerializeUtil.unserialize(byteValue);
	}
	
	/**
	 * 释放连接
	 * @param jedis
	 */
	private void close(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}
	
	/**
	 * 获取Redis实例
	 * @return redis实例
	 */
	private Jedis getJedis() {
		if (jedisPool != null) {
			return jedisPool.getResource();
		}else{
			return null;
		}
	}

	/**
	 * 更新时间
	 */
	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = getJedis();// 获得jedis实例
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			close(jedis);
		}
		return res;
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getJedis();// 获得jedis实例
			return jedis.del(keys);
		} catch (Exception ex) {
			LogUtils.error(ex.getMessage(), ex);
		}finally{
			close(jedis);
		}
		return 0L;
	}

}
