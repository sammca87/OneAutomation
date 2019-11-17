package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.Base;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends Base {

	WebDriver driver;

	public Login(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='identifierId']")
	public WebElement loginIDFld;

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	public WebElement passwordFld;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Next')]")
	public WebElement nxtBtn;

	public void typeLoginID(String loginID) {

		try {
			loginIDFld.sendKeys(loginID);

			logger.log(LogStatus.PASS, "Entered '" + loginID + "' on Email or Mobile number field.");
			log.info("Entered '" + loginID + "' on Email or Mobile number field.");
		}catch(Exception e) {
			
			logger.log(LogStatus.FAIL, "Entering value on email or phone# field is failed: " + e.getMessage());
			log.error("Entering value on email or phone# field is failed: " + e.getMessage());
		}
		
	}
	
	public void validateTile() {
		//Login page title validation
	}
	
	public void validateLoginErrorMessage() {
		//Yet to start
		
	}

	public void typePassword(String password) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			passwordFld.sendKeys(password);
			
			logger.log(LogStatus.PASS, "Entered '" + password + "' on password field.");
			log.info("Entered '" + password + "' on password field.");
		}catch(Exception e) {
			
			logger.log(LogStatus.FAIL, "Entering value on password field is failed: " + e.getMessage());
			log.error("Entering value on password field is failed: " + e.getMessage());
		}
		
	}

	public Home clickOnNxtBtn() {

		try {

			wait.until(ExpectedConditions.visibilityOf(nxtBtn)).click();

			log.info("Clicked on Next button.");
			logger.log(LogStatus.PASS, "Clicked on Next button.");
			
			return new Home(driver);

		} catch (Exception e) {
			
			logger.log(LogStatus.FAIL, "Clicking on Next button is failed: " + e.getMessage());
			log.error("Clicking on Next button is failed: " + e.getMessage());
			
		}
		return null;
	}
}
