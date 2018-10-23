/* 
 * 文件名：MainService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月14日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.vo.SysResourceVo;

/**
 * 主界面业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月14日
 */
public interface MainService {

	/**
	 * 加载菜单数据
	 * @param roleId 
	 * @return 集合
	 */
	public List<SysResourceVo> findMenu(String roleId);

}
