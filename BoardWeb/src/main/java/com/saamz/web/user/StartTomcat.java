package com.saamz.web.user;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class StartTomcat {
	private void mian() {
		// TODO Auto-generated method stub
		Tomcat server = new Tomcat();
		try {
			server.start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
