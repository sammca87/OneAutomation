package com.qa.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.Base;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends Base implements ITestListener, ISuiteListener{

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
		logger.log(LogStatus.FAIL, arg0.getName() + " is failed.");
		extent.endTest(logger);
		
		log.info(arg0.getName() + " is failed with " + arg0.getThrowable().toString());
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		
		logger.log(LogStatus.SKIP, arg0.getName() + " is skipped.");
		extent.endTest(logger);
		
		log.info(arg0.getName() + " is skipped");
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
		logger = extent.startTest(arg0.getName(), "Execution started!");
		log.info(arg0.getName() + " is started");
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		
		logger.log(LogStatus.PASS, arg0.getName() + " is executed succesfully");
		extent.endTest(logger);
		log.info(arg0.getName() + " is pass");
	}

}
