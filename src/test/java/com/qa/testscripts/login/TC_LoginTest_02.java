package com.qa.testscripts.login;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.listeners.Retry;
import com.qa.pages.Home;
import com.qa.pages.Login;
import com.qa.utils.DataReader;

public class TC_LoginTest_02 extends Base{
	
	Login login;
	Home home;
	
	@Test(dataProviderClass = DataReader.class, dataProvider = "dp", retryAnalyzer = Retry.class)
	public void loginTest(Hashtable<String, String> data) {
		
		login = new Login(driver);
		
		login.typeLoginID(data.get("username"));
		login.clickOnNxtBtn();
		
		login.typePassword(data.get("password"));
		home = login.clickOnNxtBtn();
		
		Assert.assertTrue(home.getTitle().contains("Inbox"));
		
		home.logOut();
		
		//added few lines in the bob myrepo

	}

}
