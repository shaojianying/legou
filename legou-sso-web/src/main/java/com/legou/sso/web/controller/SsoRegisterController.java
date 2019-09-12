package com.legou.sso.web.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbUser;
import com.legou.sso.service.SsoService;

//用户注册 处理器
@Controller
public class SsoRegisterController {
	@Autowired
	SsoService ssoService;
	@RequestMapping("/page/register")
	public String touserRegister() {//去注册页面
		return "register";
	}
	//路径传过来的值
	@RequestMapping("/user/check/{name}/{num}")//检查注册用户用户名 手机号是否重复
	@ResponseBody
	public LegouResult userRegister(@PathVariable String name,@PathVariable Integer num) throws UnsupportedEncodingException {//注册用户 往数据库保存
		LegouResult legouResult=ssoService.selectUser(name,num);
		return legouResult; 	
	}
	@RequestMapping("/user/register")
	@ResponseBody
	public LegouResult Userregister(TbUser tbUser) {
		LegouResult legouResult=ssoService.addUser(tbUser);
		return legouResult ; 
	}
}
