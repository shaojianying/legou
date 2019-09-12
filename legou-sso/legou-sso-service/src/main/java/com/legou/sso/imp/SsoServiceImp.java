package com.legou.sso.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.legou.pojo.TbUserExample.Criteria;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.legou.common.utils.LegouResult;
import com.legou.mapper.TbUserMapper;
import com.legou.pojo.TbUser;
import com.legou.pojo.TbUserExample;
import com.legou.sso.mapper.UserMapper;
import com.legou.sso.service.SsoService;
@Service
public class SsoServiceImp implements SsoService{
	@Autowired
	private TbUserMapper userMapper;
	@Override
	public LegouResult addUser(TbUser tbuser) {
		//检查一下准备保存的用户属性值是否正常
		if (StringUtils.isBlank(tbuser.getUsername())
				||StringUtils.isBlank(tbuser.getPassword())
				||StringUtils.isBlank(tbuser.getPhone())) {
			return LegouResult.build(400, "数据不完整");
		}
		//把时间信息保存一下
		tbuser.setCreated(new Date());
		tbuser.setUpdated(new Date());
		//保存用户
		userMapper.insert(tbuser);
		return LegouResult.ok();
	}

	@Override
	public LegouResult selectUser(String name, Integer num) {
		//根据不同的num生成不同的查询条件
		TbUserExample example = new TbUserExample();
		//创建空的条件
		Criteria criteria = example.createCriteria();
		//1：用户名 2：手机号
		if (num == 1) {
			//根据传过来的不同num值(1或者2)给条件赋值
			criteria.andUsernameEqualTo(name);
		} else if (num == 2) {
			criteria.andPhoneEqualTo(name);
		}  else {
			return LegouResult.build(400, "数据类型错误");
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		//判断结果中是否包含数据
		if (list != null && list.size()>0) {
			//如果有数据返回false
			return LegouResult.ok(false);
		}
		//如果没有数据返回true说明数据库没有 可以注册
		return LegouResult.ok(true);
		
	}
	
	
	
	//@Autowired
	//UserMapper uuserMapper;
	//@Override
	//public LegouResult addUser(TbUser tbuser) {//添加用户到数据库
		//return userMapper.addUser(tbuser);
	//}

	/*@Override
	public LegouResult selectUser(String name, Integer num) {
		List list =new ArrayList<TbUser>();
		if(num==1) {//用户名
		list=userMapper.selectUserByName(name);
		}else if(num ==2){//手机号
		list=userMapper.selectUserByPhone(name);
		}else{
		return LegouResult.build(400, "数据类型错误!");
		}
		if(list.size()>0 && list !=null) {
			return LegouResult.ok(false);
		}
		return LegouResult.ok(true);
	}*/
	
}
