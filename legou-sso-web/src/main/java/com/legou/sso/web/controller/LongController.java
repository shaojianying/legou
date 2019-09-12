package com.legou.sso.web.controller;
//登陆功能处理器



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.utils.CookieUtils;
import com.legou.common.utils.LegouResult;
import com.legou.sso.service.LoginService;
@Controller
public class LongController {
	
	@Autowired
	LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("page/login")
	public String pagelogin() {//跳转到登陆页面
		return "login";
	}
	@RequestMapping("/user/login")//用户登陆
	@ResponseBody
	public LegouResult userlogin(String username,String password,HttpServletRequest request, HttpServletResponse response) {
		LegouResult legouResult=loginService.selectTbUserByNameAndPassword(username,password);
		if(legouResult.getStatus() == 200) {
			String token = legouResult.getData().toString();
			//如果登录成功需要把token(Redis中用户信息的key)写入cookie
			CookieUtils.setCookie( request,response,TOKEN_KEY, token);
		}
		   return legouResult;
	} 
}
