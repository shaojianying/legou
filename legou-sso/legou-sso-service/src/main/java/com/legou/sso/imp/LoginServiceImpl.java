package com.legou.sso.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.legou.common.redis.JedisClient;
import com.legou.common.redis.JedisClientPool;
import com.legou.common.utils.JsonUtils;
import com.legou.common.utils.LegouResult;
import com.legou.mapper.TbUserMapper;
import com.legou.pojo.TbUser;
import com.legou.pojo.TbUserExample;
import com.legou.pojo.TbUserExample.Criteria;
import com.legou.sso.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	TbUserMapper tbUserMapper;
	@Autowired
	JedisClientPool JedisClient;
	
	//@Value("${SESSION_EXPIRE}")
	//private Integer SESSION_EXPIRE;
	
	@Override
	public LegouResult selectTbUserByNameAndPassword(String username, String password) {
		TbUserExample tbUserExample=new TbUserExample();
		//创建一个空的条件
		Criteria criteria=tbUserExample.createCriteria();
		//给name赋值给条件
		criteria.andUsernameEqualTo(username);
		//通过username数据库查找用户
		List<TbUser> list=tbUserMapper.selectByExample(tbUserExample);
		System.out.println("list***"+list.size());
		if(list.size()==0 || list==null) {
			//没值说明没查到 
			return LegouResult.build(400,"用户名或密码有误oo");
		}
		TbUser tbUser=list.get(0);
		/*if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
			return LegouResult.build(400,"用户名或密码有误oo");
		}*/
		String token = UUID.randomUUID().toString();
		// 4、把用户信息写入redis，key：token value：用户信息
		//tbUser.setPassword(null);
		//从容器中取出的Redis对象 将数据放入Redis
		JedisClient.set("SESSION:" + token, JsonUtils.objectToJson(tbUser));
		// 5、设置Session的过期时间
		JedisClient.expire("SESSION:" + token, 1800);
		return LegouResult.ok(token);
	
	}
	
}
