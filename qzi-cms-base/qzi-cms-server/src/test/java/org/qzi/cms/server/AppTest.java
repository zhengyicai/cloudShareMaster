package org.qzi.cms.server;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.DateUtils;
import com.qzi.cms.common.util.HttpUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.common.vo.ClientVo;
import com.qzi.cms.common.vo.SmsVo;

public class AppTest {
	
	@Test
	public void test(){
//		String salt = UUID.randomUUID().toString().replaceAll("-","");
//		String loginPw = CryptUtils.hmacSHA1Encrypt("1q2w3e", salt);
//		System.out.println(salt);
//		System.out.println(loginPw+"="+loginPw.length());
//		System.out.println(String.format("%012d单元",2000001));
//		System.out.println(ToolUtils.isMobile("13578978955"));
//		String  temp = "000003080101";
//		System.out.println(temp.substring(0, 10)+String.format("%04d单元",101));
		String a = "eyJBbGciOiJIUzI1NiIsIkFjY2lkIjoiNGU3MjA0OWI2M2Y5YTE5ZmU3OWIxNTg1YTI4Y2FmODciLCJBcHBpZCI6Ijk5NzUxN2UxMjI5MTQzYzU5NzJjZTEyYzYyZDRiYzkxIiwiVXNlcmlkIjoiMTM2MjcyNDUwNTUifQ==.3XwgMuypaYE+dfU9SnuLCBUp9Qf5DBudwdxNa1AuJF8=";
		System.out.println(a.length());
				
		for(int i=1;i<20;i++){
			System.out.println(ToolUtils.getUUID());
		}
	}
	
	@Test
	public void testJson(){
		AdminVo avo = new AdminVo();
		avo.setCommunityId("====");
		List<String> lis = new ArrayList<>();
		lis.add("aaa");
		lis.add("bbb");
		lis.add("ccc");
		lis.add("eee");
		lis.add("ddd");
		lis.add("fff");
		avo.setUserIds(lis);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			System.out.println(mapper.writeValueAsString(avo));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient() throws Exception{
		String strAccountId = "4e72049b63f9a19fe79b1585a28caf87";
		String strAutoTaken = "7490ae2b21235f58d850fbab00a9d1aa";
		String strAppId = "997517e1229143c5972ce12c62d4bc91";
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String url = "https://api.ucpaas.com";
		url = url.concat("/2015-06-30")
		.concat("/Accounts/")
		.concat(strAccountId)
		.concat("/Clients")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(strAccountId, strAutoTaken, timestamp));
		
		ClientVo clientVo = new ClientVo();
		clientVo.setAppId(strAppId);
		clientVo.setUserId("13527245055");
		String param = "{\"client\":"+ToolUtils.toJson(clientVo)+"}";
		System.out.println(param);
		String auth= CryptUtils.base64Encoder(strAccountId+":"+timestamp);
		String res = HttpUtils.sendPostJson(url,param,auth);
		System.out.println(res);
	}
	
	
	@Test
	public void testDropClient() throws Exception{
		String strAccountId = "4e72049b63f9a19fe79b1585a28caf87";
		String strAutoTaken = "7490ae2b21235f58d850fbab00a9d1aa";
		String strAppId = "997517e1229143c5972ce12c62d4bc91";
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String url = "https://api.ucpaas.com";
		url = url.concat("/2015-06-30")
		.concat("/Accounts/")
		.concat(strAccountId)
		.concat("/dropClient")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(strAccountId, strAutoTaken, timestamp));
		
		ClientVo clientVo = new ClientVo();
		clientVo.setAppId(strAppId);
		clientVo.setUserId("13800138000");
		String param = "{\"client\":"+ToolUtils.toJson(clientVo)+"}";
		System.out.println(param);
		String auth= CryptUtils.base64Encoder(strAccountId+":"+timestamp);
		String res = HttpUtils.sendPostJson(url,param,auth);
		System.out.println(res);
	}
	
	@Test
	public void testSms() throws Exception{
		String strAccountId = "4e72049b63f9a19fe79b1585a28caf87";
		String strAutoTaken = "7490ae2b21235f58d850fbab00a9d1aa";
		String strAppId = "997517e1229143c5972ce12c62d4bc91";
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String url = "https://api.ucpaas.com";
		url = url.concat("/2014-06-30")
		.concat("/Accounts/")
		.concat(strAccountId)
		.concat("/Messages/templateSMS")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(strAccountId, strAutoTaken, timestamp));
		
		SmsVo smsVo = new SmsVo();
		smsVo.setAppId(strAppId);
		smsVo.setParam("115566");
		smsVo.setTemplateId("108030");
		smsVo.setTo("18676487058");
		String param = "{\"templateSMS\":"+ToolUtils.toJson(smsVo)+"}";
		System.out.println(param);
		String auth= CryptUtils.base64Encoder(strAccountId+":"+timestamp);
		String res = HttpUtils.sendPostJson(url,param,auth);
		System.out.println(res);
	}
	
	@Test
	public void strToImg(){
		String img = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCACCAV4DAREAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAr/xAAUEAEAAAAAAAAAAAAAAAAAAAAA/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/ALeAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAf/9k=";
		img = img.substring(img.indexOf(";base64,")+8);
		try   
        {
            //Base64解码  
            byte[] b = Base64.getDecoder().decode(img);
            //生成jpeg图片
            String imgFilePath = "d://222.jpg";//新生成的图片 
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();
        }  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
