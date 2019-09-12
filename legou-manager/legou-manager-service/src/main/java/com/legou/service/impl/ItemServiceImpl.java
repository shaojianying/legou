package com.legou.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.legou.common.pojo.EasyUIDataGridResult;
import com.legou.common.redis.JedisClientPool;
import com.legou.common.utils.IDUtils;
import com.legou.common.utils.JsonUtils;
import com.legou.common.utils.LegouResult;
import com.legou.mapper.TbItemDescMapper;
import com.legou.mapper.TbItemMapper;
import com.legou.pojo.TbContent;
import com.legou.pojo.TbItem;
import com.legou.pojo.TbItemDesc;
import com.legou.pojo.TbItemExample;
import com.legou.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired//以类型注入
	private JmsTemplate jmsTemplate;
	
	@Resource //默认以名字注入  我们使用的Destination(消息目的地有两个孙子 一个topics 一个queue 以类型注入会混淆)
	private Destination activeMQTopic;
	
	
	//redis
	@Autowired
	JedisClientPool jedisClientPool;
	
	@Override
	public TbItem getItem(Long itemId) {//通过itemid查询商品信息
		//查询Redis中key叫itemId+"qwe"的值
		String cashItems= jedisClientPool.get(itemId+"qwe");
		//判断是否为空就知道Redis中有没有
		if(!(cashItems==null)) {
		System.out.println("Redis中取得TbItem");
		//有值就从Redis中拿
		//Redis中查到的是json字符串 给它转成TbItem类型
		return JsonUtils.jsonToPojo(cashItems, TbItem.class);
		}
	     //数据库查到的数据
		TbItem tbItem=	tbItemMapper.selectByPrimaryKey(itemId);
		//给查到的数据转成json字符串格式保存Redis
		 jedisClientPool.set(itemId+"qwe",JsonUtils.objectToJson(tbItem));
		 System.out.println("数据库中拿TbItem");
		 return tbItem;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		//配置分页助手                  从哪一页开始显示,一页显示多少行(由传进来的参数决定)
		PageHelper.startPage(page, rows);
		//调用mapper查询数据
		TbItemExample example=new TbItemExample();
		//获得所有信息
		List<TbItem>selectByExampleList=tbItemMapper.selectByExample(example);
		//获得的数据想使用分页插件管理就要转成pageInfo 构建一个,并把数据装换成pageinfo
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(selectByExampleList);
		//方法最后返回的必须是 EasyUIDataGridResult类型 ,我们还要把pageinfo转成EasyUIDataGridResult
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult();
		//把查到的数据放到easyUIDataGridResult的row属性中
		easyUIDataGridResult.setRows(selectByExampleList);
		//在获得分页的全部行数
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		
		return easyUIDataGridResult;
	}

	@Override
	public LegouResult saveItemAndDesc(TbItem tbItem, String desc) {//将商品信息保存到商品信息表
		//将商品信息保存到商品信息表.有的字段还没有值 要赋值一下
		//调用id工具类 生成一个不重复的id
		long itemId = IDUtils.genItemId();
		//赋值给tbitem
		tbItem.setId(itemId);
		//设置他的属性值
		tbItem.setStatus((byte) 1);
		//设置创建时间
		tbItem.setCreated(new Date());
		//设置修改时间
		tbItem.setUpdated(new Date());
		//存入数据库
		tbItemMapper.insert(tbItem);
		
		//保存商品描述信息到商品描述表
		//需要一个商品信息的实体类
		TbItemDesc tbItemDesc2=new TbItemDesc();
		//设置他的商品信息id(表明这个商品描述信息是谁的)
		tbItemDesc2.setItemId(itemId);
		//设置他的商品描述字段(传过来的)
		tbItemDesc2.setItemDesc(desc);
		//设置他的创建时间
		tbItemDesc2.setCreated(new Date());
		//设置修改时间
		tbItemDesc2.setUpdated(new Date());
		//保存到数据库
		tbItemDescMapper.insert(tbItemDesc2);
		
		//数据保存到数据库后 再往MQ中间件中发送一条消息
		//发送消息需要两个对象:一个模板类 JmsTemplate(相当于消息生产者)一个消息目的地(Destination),往哪个队列里发
		//在springxml文件中配置了这些Ben 所以直接@Autowired从容器中取就可以了
		jmsTemplate.send(activeMQTopic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(Long.toString(itemId));//将新增商品 的id传到消息中间件
			}
		});
		//保存完成 return一个方法  方法放回的值就是	 this.status = 200;  this.msg = "OK";this.data = data
		return LegouResult.ok();
	}

	@Override
	public TbItemDesc getItemDescById(Long itemid) {//获得单个商品的描述表信息
		//查询Redis中,key叫itemid+"desc"的集合值
				String cashItemDescList= jedisClientPool.get(itemid+"desc");
				System.out.println(cashItemDescList);
				//判断是否为空就知道Redis中有没有
			if(!(cashItemDescList==null)) {
				System.out.println("Redis中取得TbItemDesc");
				//有值就从Redis中拿
				//Redis中查到的是json字符串 给它转成TbItemDesc类型
				TbItemDesc jsonToPojo = JsonUtils.jsonToPojo(cashItemDescList, TbItemDesc.class);
				System.out.println(jsonToPojo.getItemId()+"qweqw");
				return jsonToPojo;
				}
		      TbItemDesc tbItemDesc= tbItemDescMapper.getItemDescById(itemid);
		  //给查到的数据转成json字符串格式保存Redis
		 jedisClientPool.set(itemid+"desc", JsonUtils.objectToJson(tbItemDesc));
		 System.out.println("数据库中拿TbItemDesc");
		 return tbItemDesc;
	}

}
