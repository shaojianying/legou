package com.legou.search.Mq;
import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.legou.common.pojo.SearchItem;
import com.legou.search.mapper.ItemMapper;
//监听器 监听MQ消息中间件中消息队列的变化 中间件中新新增了消息(新增商品id)我们就要通过这个id把数据从数据库找出 在加入solr索引库
public class LegouActiveMQListener implements MessageListener{//实现jms包中的监听器接口
	@Autowired
	ItemMapper itemMapper;//用于数据库
	@Autowired
	SolrServer solrServer;//用于solr索引库
	
	@Override
	public void onMessage(Message message) {
		//获得中间件指定队列的消息
		//因为知道消息生产者那边发送的消息类型是text,所以我们也用text来接收
		TextMessage textMessage = (TextMessage) message;
		try {
			String itemid = textMessage.getText();
			//中间件获得的值是String 转为long
			long itemidLong = Long.parseLong(itemid);
			
			// 通过itemid找到新增的商品

			SearchItem searchItem = itemMapper.getItemById(itemidLong);
			//获得solr库中一条空数据
			SolrInputDocument document = new SolrInputDocument();
			//赋值给solr表 表字段solr库已经定义
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			try {
				//存入solr库
				solrServer.add(document);
				//提交事务
				solrServer.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

		
	}
	
	

