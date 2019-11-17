package com.qa.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.utils.DataTable;
import com.qa.utils.MyConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver = null;
	public static DataTable table = null;
	public static MyConfig config = ConfigFactory.create(MyConfig.class);
	public static WebDriverWait wait = null;
	public String browserName = null;
	public static Logger log = null;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	public void setUp() {

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");
		log = Logger.getLogger("devpinoyLogger");
		
		extent = new ExtentReports(System.getProperty("user.dir") + "\\target\\extentreports\\extent.html", true);
		
		extent
        .addSystemInfo("Host Name", "SoftwareTestingMaterial")
        .addSystemInfo("Environment", "Automation Testing")
        .addSystemInfo("User Name", "Periyasamy");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\runner\\extent-config.xml"));
	}

	@BeforeMethod
	public void setUpBrowser() {

		initBrowser();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

	@AfterSuite
	public void tearDown() {

		extent.flush();
		driver.quit();
	}

	public void initBrowser() {

		browserName = config.getBrowser();
		System.out.println("browser: " + browserName + " retry: " + config.getRetryLimit());

		try {

			if (browserName.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome browser launched!");

			} else if (browserName.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox browser launched!");

			} else if (browserName.equals("ie")) {

				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver(options);

				log.info("IE browser launched!");

			} else {
				log.error("Invalid browser name configured.");
				throw new Exception("Invalid, please provide valid browser name chrome, firefox or ie");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(config.getPageLoadWaitTime(), TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, config.getExplicitWaitTime());

		System.out.println("URL: " + config.getTestSiteURL());

		driver.get(config.getTestSiteURL());

		log.info("Test site URL " + config.getTestSiteURL() + " is loaded.");

		driver.manage().window().maximize();
	}// END of InitBrowser

}
