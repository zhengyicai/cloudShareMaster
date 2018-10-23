/* 
 * 文件名：YBBeanUtils.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月25日
 * 版本号：v1.0
*/
package com.qzi.cms.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * TODO
 * @author qsy
 * @version v1.0
 * @date 2016年11月25日
 */
public class YBBeanUtils {
	
	/**
	 * 对象复制
	 * @param src 源对象
	 * @param cls 目标类型
	 * @return 目标对象
	 * @throws Exception
	 */
	public static <T> T copyProperties(Object src,Class<T> cls) throws Exception{
		T target = cls.newInstance();
		BeanUtils.copyProperties(src,target);
		return target;
	}
	
	/**
	 * list集合复制
	 * @param srcs 源集合
	 * @param cls 目标集合类型
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> copyList(List<?> srcs,Class<T> cls) throws Exception{
		List<T> target = null;
		if(srcs != null){
			for(Object o:srcs){
				if(target == null){
					target = new ArrayList<T>();
				}
				target.add(copyProperties(o,cls));
			}
		}
		return target;
	}
}
