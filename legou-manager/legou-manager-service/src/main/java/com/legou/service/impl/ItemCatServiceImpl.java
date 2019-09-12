package com.legou.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legou.common.pojo.EasyUITreeNode;
import com.legou.mapper.TbItemCatMapper;
import com.legou.pojo.TbItemCat;
import com.legou.pojo.TbItemCatExample;
import com.legou.pojo.TbItemCatExample.Criteria;
import com.legou.service.ItemCatService;
//类目实现类
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(long id) {
		TbItemCatExample example= new TbItemCatExample();
		//需要把传过来的long id的值放入example中 让他带进下面的执行方法中
		//创建一个example的空条件
		Criteria createCriteria = example.createCriteria();
		//把 id 放入这个条件
		createCriteria.andParentIdEqualTo(id);
		//查询所有类目 方法需要一个example  建一个
		List<TbItemCat> selectByExampleList=tbItemCatMapper.selectByExample(example);
		//查询出来的类型是放Tbitemcat类型的集合 要把它转换成List<EasyUITreeNode>的类型
		//建一个新的集合
		List<EasyUITreeNode>nodesList=new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : selectByExampleList) {
			EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
			easyUITreeNode.setId(tbItemCat.getId());
			easyUITreeNode.setText(tbItemCat.getName());
			easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			nodesList.add(easyUITreeNode);
		}
		return nodesList;
	}

}
