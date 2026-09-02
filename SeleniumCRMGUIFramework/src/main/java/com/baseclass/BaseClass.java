package com.baseclass;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import genericUtility.DataBaseUtility;
import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.UtilityClassObject;
import genericUtility.WebdriverUtility;

public class BaseClass {

	public ExcelUtility eu = new ExcelUtility();
	public JavaUtility ju = new JavaUtility();
	public DataBaseUtility dbu = new DataBaseUtility();
	public FileUtility fu = new FileUtility();
	public WebdriverUtility wu = new WebdriverUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver=null;

    
	@BeforeSuite(groups = "smokeTest")
	public void configBS() throws SQLException {
		System.out.println("==Connect to DB, Report config"); // from dataBaseUtility
		dbu.getDbconnection();
		
	}

//	@Parameters("BROWSER")
//	@BeforeClass(groups = "smokeTest")
//	public void configBC(String browser) throws Exception {
//		System.out.println("==Launch browser");
//		String BROWSER = browser;
//				//String BROWSER=fu.getData("browser");
//
//		if (BROWSER.equals("chrome"))
//			driver = new ChromeDriver();
//		else if (BROWSER.equals("edge"))
//			driver = new EdgeDriver();
//		else if (BROWSER.equals("firefox"))
//			driver = new FirefoxDriver();
//		else
//			driver = new ChromeDriver();
//	}
	
	@BeforeClass(groups = "smokeTest")
	public void configBC() throws Exception {
		System.out.println("==Launch browser");
		String BROWSER = fu.getData("browser");

		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();
		
		
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

	

	@BeforeMethod(groups = "smokeTest")
	public void configBM() throws Exception {
		System.out.println("==Login");
		String URL = fu.getData("url");
		String USERNAME = fu.getData("username");
		String PASSWORD = fu.getData("password");
		LoginPage l = new LoginPage(driver);
		l.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = "smokeTest")
	public void configAM() {
		System.out.println("==Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = "smokeTest")
	public void configAC() {
		System.out.println("==Close browser");
		driver.quit();
	}

	@AfterSuite(groups = "smokeTest")
	public void configAS() throws SQLException {
		System.out.println("==Close DB, Report backup");
		dbu.closeDbconnection();
	}
}
