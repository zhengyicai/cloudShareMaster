/* 
 * 文件名：HomeService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.lang.reflect.Parameter;
import java.rmi.server.ExportException;
import java.util.List;

import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.*;

/**
 * 首页业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年7月30日
 */
public interface HomeService {

	/**
	 * 查找轮播图
	 * @return
	 */
	public List<String> findBanners();

	/**
	 * 查询最新公告
	 * @return
	 * @throws Exception 
	 */
	public UseNoticeVo findTopNotice() throws Exception;

	/**
	 * 查询最新消息
	 * @return
	 * @throws Exception 
	 */
	public UseMessageVo findTopMsg() throws Exception;

	/**
	 * 查询最新消息总条数
	 * @return
	 * @throws Exception 
	 */
	public long findMsgCount() throws Exception;

	public List<SysParameterVo> paramfindAll() throws Exception;


	/**
	 * 获取用户绑定小区数据
	 */

	public HomeUserCommunityVo findHomeUser() throws Exception;

	/**
	 * 查找楼栋单元树型菜单
	 * @param id
	 * @return
	 */
	public List<TreeVo> findTree(String id);


}
