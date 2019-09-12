package com.legou.service;

import com.legou.common.pojo.EasyUIDataGridResult;
import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbItem;
import com.legou.pojo.TbItemDesc;

public interface ItemService {
	
	TbItem getItem(Long  itemId);
	EasyUIDataGridResult getItemList(Integer page,Integer rows);//获得所有信息
	LegouResult saveItemAndDesc(TbItem tbItem, String desc);//保存商品到数据库 并保存商品描述带商品描述表
	TbItemDesc getItemDescById(Long itemid);//获得商品描述
}
