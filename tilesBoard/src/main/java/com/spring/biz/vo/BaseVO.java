package com.spring.biz.vo;

public class BaseVO {
	private String mainMenu;
	private String subMenu;
	
	public BaseVO() {
		mainMenu = "shopping";
		subMenu = "allCategory";
	}
	
	public String getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}

	public String getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	public String toString() {
		return "BaseVO [mainMenu=" + mainMenu + ", subMenu=" + subMenu + "]";
	}

	
	
	
	

}
