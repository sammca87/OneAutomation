package com.qa.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.Base;

public class CommonLibrary extends Base{
	
	public static boolean isElementPresent(WebElement element) {
		
		try {
			
			wait.until(ExpectedConditions.visibilityOf(element));
			
			return true;
			
		}catch(Exception e) {
			
			return false;
		}
	}
	
}
