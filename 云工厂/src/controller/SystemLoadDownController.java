package controller;

import service.*;

public class SystemLoadDownController {
	
	private SystemLoadDown systemLoadDown;

	public SystemLoadDownController() {
		systemLoadDown = new SystemLoadDown();
	}

	public void Init() throws Exception {
		systemLoadDown.Init();
	}

}
