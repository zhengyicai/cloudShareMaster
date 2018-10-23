/* 
 * 文件名：LogInterceptor.java  
 * 版权：Copyright 2016-2016 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2016年11月28日
 * 版本号：v1.0
*/
package com.qzi.cms.server.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.service.RedisService;
import com.qzi.cms.common.util.ConfUtils;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.server.service.common.SysLogsService;
import com.qzi.cms.server.service.common.SysUrlRecordService;

/**
 * 请求拦截器，校验用户是否登录
 * @author qsy
 * @version v1.0
 * @date 2016年11月28日
 */
public class LoginAuthInterceptor extends HandlerInterceptorAdapter{
	@Resource
	private SysLogsService logService;
	@Resource
	private SysUrlRecordService recordService;
	@Resource
	private RedisService redisService;
	@Resource
	private ConfUtils confUtils;

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		String requestUri = request.getRequestURI();
		//获取请求URL
		String url = requestUri.substring(contextPath.length());
		if (url.lastIndexOf("?") > 0) {
			url = url.substring(0, url.lastIndexOf("?"));
		}
		//查找不被拦截URL
		List<String> urls = recordService.findUrl();
		if(urls.contains(url)){
			return true;
		}
		
		//获取请求token
		String token = request.getHeader("token");
		if (null == token) {
			token = request.getParameter("token");
		}
		LogUtils.info("请求Token:" + token);
		
		//token为空
		if(token == null || token.trim().length()==0 || token.equals("null")){
			RespBody respBody = new RespBody();
			respBody.add(RespCodeEnum.NOLOGIN.getCode(),"非法访问，系统自动退出");
			errorOut(response,respBody);
			return false;
		}
		
		//redis是否存在
		Object obj = redisService.getObj(token);
		if(obj == null || !(obj instanceof SysUserVo || obj instanceof UseResidentPo)){
			RespBody respBody = new RespBody();
			respBody.add(RespCodeEnum.NOLOGIN.getCode(),"过长时间没有操作，页面过期，请重新登录");
			errorOut(response,respBody);
			return false;
		}
		//更新时间
		redisService.expire(token, confUtils.getSessionTimeout());
		return true;
	}
	
	private void errorOut(HttpServletResponse response, RespBody res) {
		response.setStatus(608);
		response.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(ToolUtils.toJson(res));
		} catch (IOException e) {
			LogUtils.error("响应异常", e);
		}finally{
			if(out!= null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}

}
