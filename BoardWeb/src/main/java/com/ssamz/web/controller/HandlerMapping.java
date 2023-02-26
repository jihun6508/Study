package com.ssamz.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssamz.web.controller.user.DeleteBoardController;
import com.ssamz.web.controller.user.GetBoardController;
import com.ssamz.web.controller.user.GetBoardListController;
import com.ssamz.web.controller.user.InsertBoardController;
import com.ssamz.web.controller.user.InsertBoardViewController;
import com.ssamz.web.controller.user.InsertUserViewController;
import com.ssamz.web.controller.user.InsertUserController;
import com.ssamz.web.controller.user.LoginController;
import com.ssamz.web.controller.user.LoginViewController;
import com.ssamz.web.controller.user.LogoutController;
import com.ssamz.web.controller.user.UpdateBoardController;

public class HandlerMapping {

	//Controller�� ������ ��ü���� �����ϴ� Map
	private Map<String, Controller> mapping;
	
	public HandlerMapping() {
		//Key-value ���·� ������ Controller�� ����Ѵ�.
		mapping = new HashMap<String, Controller>();
		mapping.put("/insertUserView.do", new InsertUserViewController());
		mapping.put("/insertUser.do", new InsertUserController());
		mapping.put("/loginView.do", new LoginViewController());
		mapping.put("/login.do", new LoginController());
		mapping.put("/getBoardList.do", new GetBoardListController());
		mapping.put("/getBoard.do", new GetBoardController());
		mapping.put("/insertBoardView.do", new InsertBoardViewController());
		mapping.put("/insertBoard.do", new InsertBoardController());
		mapping.put("/updateBoard.do", new UpdateBoardController());
		mapping.put("/deleteBoard.do", new DeleteBoardController());
		mapping.put("/logout.do", new LogoutController());
		
	}
	
	public Controller getController(String path) {
		//Map�� ��ϵ� Controller�� �߿��� Ư�� ���(path)�� �ش��ϴ� Controller�� �����Ѵ�.
		return mapping.get(path);
	}
}
