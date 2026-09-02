package genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.baseclass.BaseClass;


public class ListImpClass  implements ITestListener,ISuiteListener{

	public ExtentSparkReporter spark ;
    public   ExtentReports report;
    public static ExtentTest test;
    
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		// add environment information and create test
		 report = new ExtentReports();
		report.attachReporter(spark);            // pass the spark configuration	
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==== ====>" + result.getMethod().getMethodName() + "<====START====");
		 test= report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"=====Started======");
	}

	@Override
	public void onFinish(ISuite suite) {  
		System.out.println("Report backup");
		
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==== ====>" + result.getMethod().getMethodName() + "<====END====");
		test.log(Status.PASS, result.getMethod().getMethodName()+"=====Completed======");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot) BaseClass.sdriver;
		 String filePath = edriver.getScreenshotAs(OutputType.BASE64);
		 
//		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
//		  File srcfile = ts.getScreenshotAs(OutputType.FILE);
		 
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=====Failed======");
		
		
		// store screenshot in local storage
//		try {
//		FileHandler.copy(srcfile, new File("./Screenshot/"+testName+"+"+time+".png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

}
