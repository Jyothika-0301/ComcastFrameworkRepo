package Practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateOrgWithIndustryTest2 {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
FileInputStream  fis = new FileInputStream("./src/main/resources/CommanData.properties");
Properties p = new Properties();
p.load(fis);

	String BROWSER = p.getProperty("browser");
	String URL = p.getProperty("url");
	String USERNAME = p.getProperty("username");
	String PASSWORD = p.getProperty("password");
	
Random random = new Random();
int randomInt = random.nextInt(1000);


FileInputStream fis1 = new FileInputStream("./src/test/resources/testData/orgname.xlsx");

	Workbook wb = WorkbookFactory.create(fis1);
	Sheet sh =  wb.getSheet("Sheet1");
	Row row = sh.getRow(4);
	String orgName = row.getCell(2).toString()+randomInt;	
	String industry = row.getCell(3).toString();
	String type = row.getCell(4).toString();
	wb.close();

	
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
