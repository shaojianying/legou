package com.legou.sso.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.utils.LegouResult;
import com.legou.sso.service.TokenService;


@Controller
//token处理器
public class TokenController {
	
	@Autowired
	TokenService tokenService;
	
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object usertoken(@PathVariable String token,String callback) {
		System.out.println("*/*/*/*"+token);
		//通过传过来的token在Redis中找对应的用户
		LegouResult legouResult=tokenService.getUserByToken(token);
		System.out.println(legouResult.getData()+"*****");
		//响应结果之前，判断是否为jsonp请求
				if (StringUtils.isNotBlank(callback)) {
					//把结果封装成一个js语句响应
					MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(legouResult);
					mappingJacksonValue.setJsonpFunction(callback);
					return mappingJacksonValue;
				}
		return legouResult;
	}
}
