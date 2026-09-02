package crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;

public class CreateOrgWithPhoneNumberTest {
	public static void main(String[] args) throws Exception {
		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();

		String BROWSER = fu.getData("browser");
		String URL = fu.getData("url");
		String USERNAME = fu.getData("username");
		String PASSWORD = fu.getData("password");
			
			String orgName = eu.readDataFromExcel("org", 7, 2)+ju.generateRandomNumber();	
			String phoneNumber = eu.readDataFromExcel("org", 7, 3);
			
			
			WebDriver driver =null;
			if(BROWSER.equals("chrome"))
				 driver = new ChromeDriver();
			 else if(BROWSER.equals("edge"))
			 driver= new EdgeDriver();
			 else if(BROWSER.equals("firefox"))
				 driver= new FirefoxDriver();
			 else
				 driver = new ChromeDriver();
		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.linkText("Organizations")).click();
			
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(orgName);
			
			
			
			driver.findElement(By.id("phone")).sendKeys(phoneNumber);			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//verify header msg expected result
			String actPhoneNumber = driver.findElement(By.id("phone")).getText();
			if(actPhoneNumber.contains(phoneNumber)) {
				System.out.println(phoneNumber+"===is created=== PASS");
			}
			else 
				System.out.println(phoneNumber+"==is not created===FAIL");
			
			//verify header orgname info expected result
			String actOrgName = driver.findElement(By.xpath("//td[@class='dvtCellInfo']")).getText();
			if(actOrgName.equals(orgName)) {
				System.out.println(orgName+"===is created=== PASS");
			}
			else 
				System.out.println(orgName+"==is not created===FAIL");
			
			
	}

}
