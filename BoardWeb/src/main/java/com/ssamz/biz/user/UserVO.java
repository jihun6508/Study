package com.ssamz.biz.user;

import lombok.Data;

@Data
public class UserVO {
	private String userId;
	private String password;
	private String name;
	private String role;
}
