package com.legou.content.service.impl;

import java.util.Date;
import java.util.List;

import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.legou.common.redis.JedisClientPool;
import com.legou.common.utils.JsonUtils;
import com.legou.common.utils.LegouResult;
import com.legou.content.service.ContentService;
import com.legou.mapper.TbContentMapper;
import com.legou.pojo.TbContent;

import com.legou.pojo.TbContentExample;
import com.legou.pojo.TbContentExample.Criteria;


@Service
public class ContentServiceImp implements ContentService {
	@Autowired
	TbContentMapper tbContentMapper;
	@Autowired
	JedisClientPool jedisClientPool;
	@Override
	public LegouResult savecontent(TbContent tbContent) {
		//页面传过来的TbContent对象还差几个属性值
		//设置创建时间
		tbContent.setCreated(new Date());
		//设置修改时间
		tbContent.setUpdated(new Date());
		//添加到数据库
		tbContentMapper.insert(tbContent);
		//数据库新增了商品后 缓存中的数据就和数据库不一致了  要给他清空
		jedisClientPool.hdel("Redislist", tbContent.getCategoryId().toString());
		//返回LegouResult执行ok方法  返回属性status等于200		
		return LegouResult.ok();
	}
	@Override
	public List<TbContent> getContentByCategoryId(Long categoryId) {//通过页面结构位置id查找里面所有的内容
		//查询数据库前应该先查询Redis缓存中是否有值
		//查询Redis中名字叫Redislist的,key叫categoryId的集合值
		String cashContentList= jedisClientPool.hget("Redislist", categoryId+"");
		//判断是否为空就知道Redis中有没有
		if(!StringUtils.isEmpty(cashContentList)) {
			System.out.println("Redis中取得");
			//有值就从Redis中拿
			//Redis中查到的是json字符串 给它转成集合
			return JsonUtils.jsonToList(cashContentList, TbContent.class);
		}
		System.out.println("数据库中拿");
		TbContentExample example=new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list=tbContentMapper.selectByExample(example);
		//第一次查询 Redis中肯定是没有的 所以我们需要把从数据库查出来的值加到Redis中
		//给查到的数据转成json字符串格式保存Redis
		 jedisClientPool.hset("Redislist", categoryId+"", JsonUtils.objectToJson(list));
		return list;
	}
	
}
