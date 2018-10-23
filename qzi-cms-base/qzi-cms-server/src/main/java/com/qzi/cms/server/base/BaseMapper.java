/* 
 * 文件名：BaseMapper.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月26日
 * 版本号：v1.0
*/
package com.qzi.cms.server.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用dao基础接口，其他dao继承该接口即可
 * @author qsy
 * @version v1.0
 * @date 2016年11月26日
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
