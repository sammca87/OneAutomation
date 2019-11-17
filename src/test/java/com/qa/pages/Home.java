package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.Base;
import com.qa.common.CommonLibrary;
import com.relevantcodes.extentreports.LogStatus;

public class Home extends Base {

	WebDriver driver;

	public Home(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@class='gb_za gbii']")
	public WebElement profileIcon;

	@FindBy(how = How.XPATH, using = "//a[@id='gb_71']")
	public WebElement logOutBtn;
	
	public String getTitle() {

		String title = "";
		
		if(!CommonLibrary.isElementPresent(profileIcon))
			return "";
		
		try {
			
			title = driver.getTitle();
			
			log.info("Navigated to Home page, the title is '" + title + "'");
			logger.log(LogStatus.PASS, "Navigated to Home page, the title is '" + title + "'");
			
		}catch(Exception e) {
			
			log.error("Error on getting title of the home page: " + e.getMessage());
			logger.log(LogStatus.ERROR, "Error on getting title of the home page: " + e.getMessage());
		}
		return title;
		
	}

	public void logOut() {

		try {
			
			wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();

			wait.until(ExpectedConditions.elementToBeClickable(logOutBtn)).click();

			log.info("Clicked on logout out button.");
			logger.log(LogStatus.PASS, "Clicked on logout out button.");

		} catch (Exception e) {
			
			log.error("Clicking on logout button is failed: " + e.getMessage());
			logger.log(LogStatus.FAIL, "Clicking on logout button is failed: " + e.getMessage());
			
		}
	}
}
