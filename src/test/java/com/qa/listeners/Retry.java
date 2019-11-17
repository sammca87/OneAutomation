package com.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.qa.base.Base;

public class Retry extends Base implements IRetryAnalyzer{
	
	int counter = 0;
	//int retryLimit = 3;
	int retryLimit = config.getRetryLimit();
			
	@Override
	public boolean retry(ITestResult arg0) {
		
		if(counter < retryLimit) {
			
			counter++;
			return true;
		}
		return false;
	}
}
