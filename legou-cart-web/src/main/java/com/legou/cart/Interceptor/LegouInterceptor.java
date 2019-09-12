package com.legou.cart.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.legou.common.utils.CookieUtils;
import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbUser;
import com.legou.sso.service.TokenService;


public class LegouInterceptor implements HandlerInterceptor{
	
	@Autowired
	TokenService tokenService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获得浏览器中的cookie值
		String tokenValue=CookieUtils.getCookieValue(request, "token");
		//判断是不是空
		if(StringUtils.isBlank(tokenValue)) {	
		//是空的,让他直接去controller
		return true;
		}
		//不是空的,说明登陆了 有用户信息,通过传过来的token在Redis中找对应的用户
		LegouResult legouResult=tokenService.getUserByToken(tokenValue);
		//判断用户登陆是否异常
		if(legouResult.getStatus()!=200) {
			//说明异常 让他直接去controller 按没登录安排
			return true;
		}
		//登陆信息正常,取出用户信息
		TbUser tbUser=(TbUser) legouResult.getData();
		//将用户信息保存在request中 去controller
		request.setAttribute("legouUser", tbUser);
		return true;
	}

	
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}