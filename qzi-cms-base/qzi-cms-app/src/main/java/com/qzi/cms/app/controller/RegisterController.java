package com.qzi.cms.app.controller;

/**
 * Created by Administrator on 2018/9/11.
 */


import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.*;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.LogUtils;

import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.mapper.SysCityMapper;
import com.qzi.cms.server.mapper.UseCommunityResidentMapper;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.service.app.LoginService;
import com.qzi.cms.server.service.app.RegisterService;
import com.qzi.cms.server.service.app.UseCommunityResidentService;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.NewResidentService;
import com.qzi.cms.server.service.web.ResidentService;
import com.qzi.cms.server.service.web.UnitService;
import com.qzi.cms.server.service.web.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注册控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;


    @Resource(name="appLogin")
    private LoginService loginService;

    @Resource
	private ResidentService residentService;


    @Resource
	private UnitService unitService;


    @Resource
	private UseCommunityResidentService useCommunityResidentService;


    @Resource
	private NewResidentService newResidentService;

	@Resource
	private UseCommunityResidentMapper communityResidentMapper;


	@Resource
	private SysCityMapper cityMapper;

	@Resource
	private CommonService commonService;

	@Resource
	private UseResidentMapper useResidentMapper;


	@GetMapping("/getLevel")
	@SystemControllerLog(description="获取楼栋数据")
	public RespBody getLevel(java.lang.String level) {
		// 创建返回对象
		RespBody respBody = new RespBody();
		try {




			respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取楼栋成功",cityMapper.findLevel(level));

		}catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "用户登录失败");
			LogUtils.error("用户登录失败！",ex);
		}
		return respBody;
	}
    @PostMapping("/getCommunity")
    @SystemControllerLog(description="获取小区")
    public RespBody getCommunity(@RequestBody UseCommunityPo po) {
        // 创建返回对象
        RespBody respBody = new RespBody();
        try {
            respBody.add(RespCodeEnum.SUCCESS.getCode(),"添加小区成功", registerService.regfindAll(po));
        }catch (Exception ex) {
            respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
            LogUtils.error("添加小区失败！",ex);
        }
        return respBody;
    }


    @GetMapping("/getBuilding")
    @SystemControllerLog(description="获取楼栋数据")
    public RespBody getBuilding(java.lang.String communityId) {
       // 创建返回对象
       RespBody respBody = new RespBody();
       try {

          List<UseBuildingPo> list =   registerService.findBuilding(communityId);
          List<UseBuildingVo>  listVo =  new ArrayList<UseBuildingVo>();
          if(list.size()>0){

			  for(UseBuildingPo po:list){
				  UseBuildingVo useBuildingVo = YBBeanUtils.copyProperties(po, UseBuildingVo.class);
				  useBuildingVo.setUnitName("");
				  SysUnitPo unitPo = new SysUnitPo();
				  unitPo.setBuildingId(useBuildingVo.getId());
				  useBuildingVo.setUnits(unitService.findAll(unitPo));
				  if("10".equals(po.getState())){
					  listVo.add(useBuildingVo);
				  }

			   }

		  }


           respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取楼栋成功",listVo);

       }catch (Exception ex) {
           respBody.add(RespCodeEnum.ERROR.getCode(), "用户登录失败");
           LogUtils.error("用户登录失败！",ex);
       }
       return respBody;
    }


	@PostMapping("/addCommunity")
	@SystemControllerLog(description="添加小区")
	public RespBody addCommunity(@RequestBody UseResidentVo residentVo) {

		// 创建返回对象
		RespBody respBody = new RespBody();
		try {


			UseResidentVo userVo = commonService.findResident();

			if(userVo !=null){
				 residentVo.setId(userVo.getId());
				 registerService.addCommunity(residentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"添加小区成功");
			}

		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("修改昵称失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改昵称失败");
			LogUtils.error("修改昵称失败！",ex);
		}
		return respBody;

	}

	@PostMapping("/updateCommunityisTrue")
	@SystemControllerLog(description="修复默认小区")
	public RespBody updateCommunityisTrue(@RequestBody UseResidentRoomVo useResidentRoomVo) {

		// 创建返回对象
		RespBody respBody = new RespBody();
		try {



				registerService.updateCommunityisTrue(useResidentRoomVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"添加默认小区成功");


		}catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "修改默认小区失败");
			LogUtils.error("修改默认小区失败！",ex);
		}
		return respBody;

	}


	@GetMapping("/communityList")
	@SystemControllerLog(description="小区列表")
	public RespBody communityList() {

		// 创建返回对象
		RespBody respBody = new RespBody();
		try {

			UseResidentVo userVo = commonService.findResident();
			if(userVo !=null){
				respBody.add(RespCodeEnum.SUCCESS.getCode(),"获取小区列表成功",useResidentMapper.authListDetail(userVo.getId()));
			}

		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
			LogUtils.error("获取小区列表失败！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取小区列表失败");
			LogUtils.error("获取小区列表失败！",ex);
		}
		return respBody;
	}

	@PostMapping("/communityDelete")
	@SystemControllerLog(description="删除小区")
	public RespBody communityDelete(@RequestBody UseResidentRoomVo useResidentRoomVo){
		RespBody respBody = new RespBody();
		try {
			useResidentMapper.delAuth(useResidentRoomVo.getId());
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "小区删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "小区删除失败");
			LogUtils.error("小区删除失败！",ex);
		}
		return respBody;
	}



    @GetMapping("/delMobile")
	@SystemControllerLog(description="删除云之讯")
	public RespBody delMobile(String mobile) {
		RespBody respBody = new RespBody();
		try {
			newResidentService.delMobile(mobile);
		} catch (Exception e) {
			e.printStackTrace();
			respBody.add("9999","删除失败");
			return respBody;
		}
		respBody.add("0000","删除成功");
		return respBody;
	}


	@GetMapping("/delMobileStr")
	@SystemControllerLog(description="删除云之讯")
	public RespBody delMobileStr(String mobile) {
		RespBody respBody = new RespBody();
		try {
			String str[] = mobile.split(",");
			for(int i = 0 ;i<str.length;i++){
				System.out.print(str[i]);
				newResidentService.delMobile(str[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
			respBody.add("9999","删除失败");
			return respBody;
		}
		respBody.add("0000","删除成功");
		return respBody;
	}

	@PostMapping("/appRegister")
	@SystemControllerLog(description="用户注册")
	public RespBody appRegister(@RequestBody UseResidentVo residentVo){
		// 创建返回对象
		RespBody respBody = new RespBody();
		if(hasErrors(residentVo,respBody)){
			UseResidentPo po =  registerService.findMobile(residentVo.getMobile());

			if(po != null){
				respBody.add("2000","该用户已存在");

			}else{
				try {
					loginService.register(residentVo);
				} catch (Exception e) {

					respBody.add(RespCodeEnum.ERROR.getCode(), e.getMessage());
					LogUtils.error("用户注册失败！",e);
				}
			}
		}

		return respBody;
	}


    @PostMapping("/register")
    	@SystemControllerLog(description="用户登录")
    	public RespBody register(@RequestBody UseResidentVo residentVo) {
    		// 创建返回对象
    		RespBody respBody = new RespBody();
    		try {
    			//验证FormBean
    			if(hasErrors(residentVo,respBody)){
					if(registerService.existsMobile(residentVo.getMobile())){
						UseResidentPo po =  registerService.findMobile(residentVo.getMobile());
						if(useCommunityResidentService.existsLoginCR(po.getId())){
							if((new Date().getTime()-po.getCreateTime().getTime())/1000<(60*60*24*7)){
								respBody.add("1000","申请尚在审核中");
								return respBody;
							}else{
								 //7天以上的， 修改用户信息注册时间
								residentVo.setCreateTime(new Date());
								residentService.updateCreateTime(po.getId());
								respBody.add("2000","注册成功，等待管理员审核");
								return respBody;
							}


						}else{




							//communityResidentMapper.existsCR(residentVo.getId(),residentVo.getCommunityId())

							//respBody.add("3000","该用户已存在，请不要重复注册");
							UseResidentPo po1  =   registerService.findMobile(residentVo.getMobile());
							po1.setName(residentVo.getName());
							String salt = ToolUtils.getUUID();
							po1.setSalt(salt);
							String loginPw = CryptUtils.hmacSHA1Encrypt(residentVo.getPassword(), salt);
							po1.setPassword(loginPw);
							registerService.updateRegister(po1);

							 UseCommunityResidentPo upo =  communityResidentMapper.existsCRResident(po1.getId());
							 if(upo!=null){
								 if("20".equals( upo.getState())){
									  respBody.add("3000","该用户被禁用，请联系管理员");
									return   respBody;
								}
							 }




							residentVo.setId(po1.getId());
							loginService.registerUpdate(residentVo);
							respBody.add("3000","注册成功，等待管理员审核");


							return respBody;

						}

					}


    				loginService.register(residentVo);
					
    				respBody.add(RespCodeEnum.SUCCESS.getCode(),"用户注册成功,等待管理员审核");
    			}
    		} catch (CommException ex) {
    			respBody.add(RespCodeEnum.ERROR.getCode(), ex.getMessage());
    			LogUtils.error("用户注册失败！",ex);
    		} catch (Exception ex) {
    			respBody.add(RespCodeEnum.ERROR.getCode(), "用户注册失败");
    			LogUtils.error("用户注册失败！",ex);
    		}
    		return respBody;
    	}

    /**
     *      2018-09-11
    	 *  验证用户注册信息
    	 * @param residentVo 新用户
    	 * @param respBody
    	 * @return
    	 */
    	private boolean hasErrors(UseResidentVo residentVo, RespBody respBody) {
    		if(StringUtils.isEmpty(residentVo.getName())){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "姓名不能为空");
    			return false;
    		}
    		if(residentVo.getName().length()<2  || residentVo.getName().length()>6){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "姓名必须输入2～6个字符");
    			return false;
    		}
    		if(StringUtils.isEmpty(residentVo.getMobile())){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号不能为空");
    			return false;
    		}
    	/*	if(!ToolUtils.isMobile(residentVo.getMobile())){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "手机号输入有误");
    			return false;
    		}*/
    		if(StringUtils.isEmpty(residentVo.getSmsCode())){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码不能为空");
    			return false;
    		}
    		if(residentVo.getSmsCode().length()!=6){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "手机验证码必须输入6位数字");
    			return false;
    		}
    		if(StringUtils.isEmpty(residentVo.getPassword())){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "密码不能为空");
    			return false;
    		}
    		if(residentVo.getPassword().length()<6  || residentVo.getPassword().length()>20){
    			respBody.add(RespCodeEnum.ERROR.getCode(), "密码必须输入6～20个字符或数字");
    			return false;
    		}
    		return true;
    	}

}
