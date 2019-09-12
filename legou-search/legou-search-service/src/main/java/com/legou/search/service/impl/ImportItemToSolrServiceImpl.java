package com.legou.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legou.common.pojo.SearchItem;
import com.legou.common.utils.LegouResult;
import com.legou.search.mapper.ItemMapper;
import com.legou.search.service.ImportItemToSolrService;
//数据导入solr数据库实现
@Service
public class ImportItemToSolrServiceImpl implements ImportItemToSolrService {
	@Autowired
	SolrServer solrServer;
	@Autowired
	ItemMapper itemMapper;
	@Override
	public LegouResult indexItemToSolr() {//数据库数据导入solr索引库
		
			try {
				//首先需要将mysql中的数据查出来
				List<SearchItem> list=itemMapper.getItemList();
				//通过循环将查询到的值一条一条放入solr数据中并保存
				for (SearchItem searchItem : list) {
					//创建一条空的solr数据对象
					SolrInputDocument document=new SolrInputDocument();
					//将数据库中的一条数据放入document中
					document.addField("item_title", searchItem.getTitle());
					document.addField("item_sell_point", searchItem.getSell_point());
					document.addField("item_price", searchItem.getPrice());
					document.addField("item_image", searchItem.getImage());
					document.addField("item_category_name", searchItem.getCategory_name());
					document.addField("id", searchItem.getId());
					//创建一个solr索引库对象 并把数据添加进去
					solrServer.add(document);
			    }//提交事务 
				 solrServer.commit();
				 //返回status=200 表明成功
				 return LegouResult.ok();
			}catch (SolrServerException | IOException e) {
			    	System.out.println("导入异常");
				    e.printStackTrace();
			}
	     	    return null;
		}	
	}
