package com.legou.cart.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legou.cart.service.CartService;
import com.legou.common.redis.JedisClient;
import com.legou.common.utils.CookieUtils;
import com.legou.common.utils.JsonUtils;
import com.legou.mapper.TbItemMapper;
import com.legou.pojo.TbItem;
import com.legou.pojo.TbUser;
@Service
public class CartServiceImp implements CartService {
	@Autowired
	TbItemMapper tbItemMapper;
	@Autowired
	JedisClient jedisClient;
	@Override
	public void addcart(long timeid, Integer num, TbUser tbUser) {
		//判断Redis中有没有有这个商品 如果有 修改商品数量就可以
		Boolean sexBoolean=	jedisClient.hexists("legou"+tbUser.getId(), timeid+"");
		if(sexBoolean) {//表示Redis中有值
			//找到这个值
			String tbitemString=jedisClient.hget("legou"+tbUser.getId(), timeid+"");
			TbItem tbItem=JsonUtils.jsonToPojo(tbitemString, TbItem.class);
			//修改数量
			tbItem.setNum(tbItem.getNum()+num);
			//保存到Redis
			jedisClient.hset("legou"+tbUser.getId(),timeid+"",JsonUtils.objectToJson(tbItem) );
		}
		
		
		//第一次添加这个商品 需要从数据库中找到这个商品,调用item模块服务
		TbItem tbItem=tbItemMapper.selectByPrimaryKey(timeid);
		//设置这个商品的数量
		tbItem.setNum(num);
		//需要一张商品图片
		String imString=tbItem.getImage();
		String[] imagStrings=imString.split(",");
		tbItem.setImage(imagStrings[0]);
		//保存到Redis
		jedisClient.hset("legou"+tbUser.getId(),timeid+"",JsonUtils.objectToJson(tbItem) );
	}
	@Override
	public void moveCookieToRedis(TbUser tbUser, List<TbItem> list) {
		//合并cookie 就是将cookie中的list购物车列表添加到Redis中 所以只要调用上面添加的方法
		
		for (TbItem tbItem : list) {
			//每循环一次cookie购物车列表中的商品出来 都调用一次添加方法 加入Redis
			addcart(tbItem.getId(),tbItem.getNum(),tbUser);
		}
		
	}
	@Override
	public List<TbItem> getList(TbUser tbUser) {
		List<TbItem> list =new ArrayList<TbItem>();
		List<String> listjsonList=jedisClient.hvals("legou"+tbUser.getId());
		for (String jsonString : listjsonList) {
			TbItem tbItem=JsonUtils.jsonToPojo(jsonString, TbItem.class);
			list.add(tbItem);
		}
		return list;
	}
	@Override
	public void updatenum(long itemid, int num, TbUser tbUser) {
		//获得Redis内容
		String jsonString=jedisClient.hget("legou"+tbUser.getId(), itemid+"");
		TbItem tbItem=JsonUtils.jsonToPojo(jsonString, TbItem.class);
		//修改Redis中的商品信息数量后再保存到Redis中
		tbItem.setNum(num);
		jedisClient.hset("legou"+tbUser.getId(), itemid+"", JsonUtils.objectToJson(tbItem));
	}
	@Override
	public void delectCart(long itemid, TbUser tbUser) {
		jedisClient.hdel("legou"+tbUser.getId(),itemid+"");
	}


}
