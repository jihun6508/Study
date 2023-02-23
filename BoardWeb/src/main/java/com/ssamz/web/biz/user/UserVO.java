package com.ssamz.web.biz.user;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.Data;

@Data //모든 멤버변수 초기ghk 하는 생성자를 추가(AllArgsConstructor), getter,setter,toString, equals, hashcode(EqualsAndHashCode)
public class UserVO implements HttpSessionBindingListener {
	private String id, password, name, role;
	
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("--->UserVO 객체가 세션에 등록됨");
	}
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("--->UserVO 객체가 세션에서 제거됨");
	}
}
