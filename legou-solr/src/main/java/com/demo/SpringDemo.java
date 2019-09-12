package com.demo;

import org.springframework.stereotype.Service;

@Service
public class SpringDemo {
	private SpringDemo2 springDemo2;

	public SpringDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpringDemo(SpringDemo2 springDemo2) {
		super();
		this.springDemo2 = springDemo2;
	}

	public SpringDemo2 getSpringDemo2() {
		return springDemo2;
	}

	public void setSpringDemo2(SpringDemo2 springDemo2) {
		this.springDemo2 = springDemo2;
	}
	public void demo() {
		System.out.println("****");
	}
	
}
