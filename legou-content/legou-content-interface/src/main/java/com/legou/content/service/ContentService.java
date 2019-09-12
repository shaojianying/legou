package com.legou.content.service;

import java.util.List;

import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbContent;

public interface ContentService {

	LegouResult savecontent(TbContent tbContent);//保存类容

	List<TbContent> getContentByCategoryId(Long categoryId);//通过页面结构位置id找里面的类容

	
	
}
