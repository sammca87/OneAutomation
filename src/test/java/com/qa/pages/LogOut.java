package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogOut {
	
	WebDriver driver;
	
	public LogOut(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void validateLogOut() {
		//yet to start
	}

}
