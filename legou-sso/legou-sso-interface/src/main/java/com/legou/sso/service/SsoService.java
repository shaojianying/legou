package com.legou.sso.service;

import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbUser;

public interface SsoService {

LegouResult addUser(TbUser tbUser);//添加用户到数据库

LegouResult selectUser(String name, Integer num);
	
}
