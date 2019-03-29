package com.qzi.cms.app.controller;

/**
 * Created by Administrator on 2019/3/7.
 */


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.qzi.cms.common.util.HttpClientManager;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;


/**
 * 注册控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @RequestMapping("loginInit.do")
    public void loginInit(HttpServletRequest request, HttpServletResponse response)  throws  Exception {
        //回调地址,要跟下面的地址能调通(getWechatGZAccessToken.do)
        String backUrl="http://fitp7y.natappfree.cc/app/wx/getWechatGZAccessToken.do";


        String appid = "wxd818db4621242582";
        String appsecret = "a64a1a585ed96cd4999769ac8df16c8b";
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        //AuthUtil.APPID微信公众号的appId   scope是否需要授权用户信息   snsapi_base  只获取openId ，snsapi_userinfo：获取用户其他信息
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +appid+
                "&redirect_uri=" + URLEncoder.encode(backUrl,"UTF-8")+
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        response.sendRedirect(url);
       // return "redirect:/"+url;
    }



    @RequestMapping("getWechatGZAccessToken.do")
    public String getWechatGZAccessToken(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //微信公众号的APPID和APPSECRET
        String code=request.getParameter("code");
        String appid = "wxd818db4621242582";
        String appsecret = "a64a1a585ed96cd4999769ac8df16c8b";
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid+
                "&secret=" +appsecret+
                "&code=" +code+
                "&grant_type=authorization_code";
        String result = HttpClientManager.getUrlData(url);
//        JSONObject jsStr = JSONObject.parseObject(result);
//
//        return jsStr.toString();
        //JSONObject.parseObject(result)
        Map<String,Object> data = JSONObject.parseObject(result);
        String openid=data.get("openid").toString();
        String token=data.get("access_token").toString();
        //获取信息
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        String infoResult = HttpClientManager.getUrlData(infoUrl);

        return  infoResult;

    }

}
