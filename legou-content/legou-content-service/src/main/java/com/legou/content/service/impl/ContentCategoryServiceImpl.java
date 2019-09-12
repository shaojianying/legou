package com.legou.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legou.common.pojo.EasyUITreeNode;
import com.legou.common.utils.LegouResult;
import com.legou.content.service.ContentCategoryService;
import com.legou.mapper.TbContentCategoryMapper;
import com.legou.pojo.TbContentCategory;
import com.legou.pojo.TbContentCategoryExample;
import com.legou.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getConteCategoryList(long id) {
		//创建一个example
		TbContentCategoryExample example= new TbContentCategoryExample();
		//创建一个他的空的条件
		Criteria createCriteria = example.createCriteria();
		//给id当条件加入example
		createCriteria.andParentIdEqualTo(id);
		//查询数据库,但是反向工程生成的方法需要一个example的实体类载体  上面创建,获得存放TbContentCategory(页面结构)的集合
		List<TbContentCategory>  categoryList= tbContentCategoryMapper.selectByExample(example);
		//需要返回的是符合页面要求的List<EasyUITreeNode>类型集合 所以需要转换一下
		//创建一个符合条件的新集合
		List<EasyUITreeNode> nodelisTreeNodes= new ArrayList<EasyUITreeNode>();
		//循环遍历将数据库值得集合赋值给新集合
		for(TbContentCategory category :categoryList) {
			
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			
			easyUITreeNode.setId(category.getId());
			easyUITreeNode.setText(category.getName());
			//判断是否是父结构,是的话 tree关闭状态 不是的话就是打开状态
			easyUITreeNode.setState(category.getIsParent()?"closed":"open");
			nodelisTreeNodes.add(easyUITreeNode);
			
		}
		//返回符合条件的集合
		return nodelisTreeNodes;
	}

	@Override
	public LegouResult createcontent(Long parentId, String name) {
		//创建一个页面结构的实体类
		TbContentCategory tbContentCategory = new TbContentCategory();
		//将还没有的属性赋值给他
		//赋值传过来的父类结构id
		tbContentCategory.setParentId(parentId);
		//赋值结构的名称
		tbContentCategory.setName(name);
		//赋值结构 的状态 
		tbContentCategory.setStatus(1);
		//赋值结构的排序
		tbContentCategory.setSortOrder(1);
		//赋值结构是否有子类结构(新增的结构肯定没有子类)
		tbContentCategory.setIsParent(false);
		//设置创建时间和修改时间
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		//将赋值完成的结构类存入数据库
		tbContentCategoryMapper.insert(tbContentCategory);
		//保存完后  在其结构上创建了新的结构  那那个结构肯定就成了父结构  所以我们要判断修改下他的isparent属性(是否父类结构)
		//获得创建了新结构的父结构
		TbContentCategory tbContentCategory2=tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!tbContentCategory2.getIsParent()) {
			tbContentCategory2.setIsParent(true);
			//将修改后的 传过去让他让数据库修改
			tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory2);
		}
		//前台页面需要保存后的数据的id
		return LegouResult.ok(tbContentCategory);
	}

	@Override
	public void deleteById(long id) {//删除页面结构
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateContentById(String name, long id) {//重命名
		TbContentCategory tbContentCategory= tbContentCategoryMapper.selectByPrimaryKey(id);
		tbContentCategory.setName(name);
		tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		
	}


}
