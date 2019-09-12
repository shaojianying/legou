package com.legou.sso.imp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.legou.common.redis.JedisClientPool;
import com.legou.common.utils.JsonUtils;
import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbUser;
import com.legou.sso.service.TokenService;
@Service
public class TokenServiceImp implements TokenService {
	@Autowired
	JedisClientPool JedisClient;
	@Override
	public LegouResult getUserByToken(String token) {
		//Redis中通过key找值
		String jsonString=JedisClient.get("SESSION:" + token);
		//判断是否有值 
		if(StringUtils.isBlank(jsonString)) {
			return LegouResult.build(400, "用户登陆过期");
		}
		System.out.println(jsonString);
		//从新修改Redis中Token过期时间
		JedisClient.expire("SESSION:" + token, 1800);
		//将Redis中取到的json值转成pojo
		TbUser user=JsonUtils.jsonToPojo(jsonString, TbUser.class);
		return LegouResult.ok(user);
	}

}
