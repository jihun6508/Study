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

	//Controller를 구현한 객체들으 저장하는 Map
	private Map<String, Controller> mapping;
	
	public HandlerMapping() {
		//Key-value 형태로 수많은 Controller를 등록한다.
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
		//Map에 드록된 Controller들 중에서 특정 경로(path)에 해당하는 Controller를 리턴한다.
		return mapping.get(path);
	}
}
