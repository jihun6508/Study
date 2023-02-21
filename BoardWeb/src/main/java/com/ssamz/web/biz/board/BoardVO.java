package com.ssamz.web.biz.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BoardVO {
	//boardVO(Value Object Ŭ����)
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	
	//�˻� ���� ����
	private String searchCondition;
	private String searchKeyword;
}
