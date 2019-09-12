package com.legou.cart.service;

import java.util.List;

import com.legou.pojo.TbItem;
import com.legou.pojo.TbUser;

public interface CartService {

	void addcart(long timeid, Integer num, TbUser tbUser);//添加商品到购物车保存在Redis

	void moveCookieToRedis(TbUser tbUser, List<TbItem> list);//用户登陆 合并cookie和Redis中的购物车 

	List<TbItem> getList(TbUser tbUser);

	void updatenum(long itemid, int num, TbUser tbUser);

	void delectCart(long itemid, TbUser tbUser);







}
