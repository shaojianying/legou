package com.demo;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SpringDemo2 {
	private String name;
	private List customList;
	private HashMap customMap;
	public SpringDemo2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpringDemo2(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void demo2() {
		System.out.println("22222");
	}
	
	public void customInit() {
		System.out.println("我出生了");
	}
	public void customDestroy() {
		System.out.println("我要挂了");
	}

	public List getCustomList() {
		return customList;
	}

	public void setCustomList(List customList) {
		this.customList = customList;
	}

	public SpringDemo2(String name, List customList) {
		super();
		this.name = name;
		this.customList = customList;
	}

	public SpringDemo2(String name, List customList, HashMap customMap) {
		super();
		this.name = name;
		this.customList = customList;
		this.customMap = customMap;
	}

	public HashMap getCustomMap() {
		return customMap;
	}

	public void setCustomMap(HashMap customMap) {
		this.customMap = customMap;
	}
	
	
}
