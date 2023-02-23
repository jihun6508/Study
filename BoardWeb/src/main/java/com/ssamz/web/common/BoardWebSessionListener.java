package com.ssamz.web.common;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class BoardWebSessionListener implements HttpSessionAttributeListener {
	public BoardWebSessionListener() {
		System.out.println("===> BoardWebSessionListener 생성");
		
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("---> 세션에 정보를 등록함");
	}
	
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("---> 세션에 등록된 정보를 덮어 씀");
	}
	
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("---> 세션에 등록된 정보가 삭제됨");
	}
}
