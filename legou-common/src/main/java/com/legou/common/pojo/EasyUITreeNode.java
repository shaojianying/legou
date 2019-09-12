package com.legou.common.pojo;

import java.io.Serializable;

//类目 载体实体类 数据通过这个传给easyUI里的tree
public class EasyUITreeNode implements Serializable {
	//tree需要这三种属性,才能读到这个里面的值
	private long id;
	private String text;//类目名称
	private String state;//类目状态 打开还是关闭
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
