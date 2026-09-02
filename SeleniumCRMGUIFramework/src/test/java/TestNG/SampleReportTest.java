package TestNG;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.baseclass.BaseClass;

import genericUtility.ListImpClass;

public class SampleReportTest extends ListImpClass {
	
	@Test
	public void createContactTest() {
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.29.4:8888/");
		
//		
//		TakesScreenshot edriver = (TakesScreenshot) driver;
//		 String filePath = edriver.getScreenshotAs(OutputType.BASE64);
		
		
	// test= report.createTest("createContactTest");
	
	
		test.log(Status.INFO,"Login to App");
		test.log(Status.INFO,"navigate to contact page");
		test.log(Status.INFO,"Create contact");
		if("HDFC".equals("HDFhC")) {
			test.log(Status.PASS,"contact is created");
	//	}else
		//	test.addScreenCaptureFromBase64String(filePath, "ErrorFile");
	}
	

	
//	@Test
//	public void createContactWithOrgTest() {
//		
//		
//	ExtentTest test= report.createTest("createContactWithOrgTest");
//	
//	
//		test.log(Status.INFO,"Login to App");
//		test.log(Status.INFO,"navigate to contact page");
//		test.log(Status.INFO,"Create contact");
//		if("HDFC".equals("HDFC")) {
//			test.log(Status.PASS,"contact is created");
//		}else
//			test.log(Status.FAIL,"contact is not created");
//		
//		
//	}
//
//	@Test
//	public void createContactWithPhoneNumberTest() {
//		
//		
//	ExtentTest test= report.createTest("createContactWithPhoneNumberTest");
//	
//	
//		test.log(Status.INFO,"Login to App");
//		test.log(Status.INFO,"navigate to contact page");
//		test.log(Status.INFO,"Create contact");
//		if("HDFC".equals("HDFC")) {
//			test.log(Status.PASS,"contact is created");
//		}else
//			test.log(Status.FAIL,"contact is not created");
//		
//
//	}

}
}