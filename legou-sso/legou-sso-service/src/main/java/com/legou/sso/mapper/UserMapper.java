package com.legou.sso.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbUser;

public interface UserMapper {
	LegouResult addUser(TbUser tbuser);//添加用户到数据库
    LegouResult selectUser(String name, Integer num);
   List <TbUser> selectUserByName(String name);
   List <TbUser> selectUserByPhone(String phone);

}
