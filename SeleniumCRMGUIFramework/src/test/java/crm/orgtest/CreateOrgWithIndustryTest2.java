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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;

public class CreateOrgWithIndustryTest2 {
public static void main(String[] args) throws Exception {
	
	FileUtility fu = new FileUtility();
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();

	String BROWSER = fu.getData("browser");
	String URL = fu.getData("url");
	String USERNAME = fu.getData("username");
	String PASSWORD = fu.getData("password");
		



	String orgName = eu.readDataFromExcel("org", 4, 2)+ju.generateRandomNumber();	
	String industry = eu.readDataFromExcel("org", 4 ,3);
	String type = eu.readDataFromExcel("org", 4, 4);
	

	
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
	
	
	 WebElement industryDropdown=  driver.findElement(By.name("industry"));
	Select sel = new Select(industryDropdown);
	sel.selectByVisibleText("Energy");
	
	 WebElement typeDropdown=  driver.findElement(By.name("accounttype"));
	Select sel1 = new Select(typeDropdown);
	sel1.selectByVisibleText("Press");
	
	
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//verify header msg expected result
	 String actIndustries = driver.findElement(By.name("industry")).getText();
	 if(actIndustries.equals(industry)) {
		 System.out.println(industry+"====is verified==PASS");
		 
	 }else
		 System.out.println(industry+"====is not verified==FAIL");
	
	
	
	//verify header orgname info expected result
	 String actType = driver.findElement(By.name("accounttype")).getText();
	 if(actType.equals(type)) {
		 System.out.println(type+"====is verified==PASS");
		 
	 }else
		 System.out.println(type+"====is not verified==FAIL");
	
	
}
}
