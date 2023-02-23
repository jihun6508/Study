package com.ssamz.web.biz.user;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.Data;

@Data //��� ������� �ʱ�ghk �ϴ� �����ڸ� �߰�(AllArgsConstructor), getter,setter,toString, equals, hashcode(EqualsAndHashCode)
public class UserVO implements HttpSessionBindingListener {
	private String id, password, name, role;
	
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("--->UserVO ��ü�� ���ǿ� ��ϵ�");
	}
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("--->UserVO ��ü�� ���ǿ��� ���ŵ�");
	}
}
