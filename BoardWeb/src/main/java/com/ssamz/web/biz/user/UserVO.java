package com.ssamz.web.biz.user;

import lombok.Data;

@Data //��� ������� �ʱ�ghk �ϴ� �����ڸ� �߰�(AllArgsConstructor), getter,setter,toString, equals, hashcode(EqualsAndHashCode)
public class UserVO {
	private String id, password, name, role;
}
