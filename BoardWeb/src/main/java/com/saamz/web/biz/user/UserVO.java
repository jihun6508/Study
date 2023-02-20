package com.saamz.web.biz.user;

import lombok.Data;

@Data //모든 멤버변수 초기ghk 하는 생성자를 추가(AllArgsConstructor), getter,setter,toString, equals, hashcode(EqualsAndHashCode)
public class UserVO {
	private String id, password, name, role;
}
