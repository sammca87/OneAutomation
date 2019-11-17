package com.qa.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src\\test\\resources\\properties\\config.properties"})
public interface MyConfig extends Config{
	
	@Key("testSiteURL")
	public String getTestSiteURL();
	
	@Key("browser")
	@DefaultValue("chrome")
	public String getBrowser();
	
	@Key("implicitWaitTime")
	@DefaultValue("20")
	public Long getImplicitWaitTime();
	
	@Key("explicitWaitTime")
	@DefaultValue("20")
	public Long getExplicitWaitTime();
	
	@Key("pageLoadWaitTime")
	@DefaultValue("20")
	public Long getPageLoadWaitTime();
	
	@Key("retryLimit")
	public int getRetryLimit();

}
