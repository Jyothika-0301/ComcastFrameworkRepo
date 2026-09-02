package practicecontacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithOrgTest {
	public static void main(String[] args) throws IOException {
		
		FileInputStream  fis = new FileInputStream("./src/main/resources/CommanData.properties");
		Properties p = new Properties();
		p.load(fis);
		
			String BROWSER = p.getProperty("browser");
			String URL = p.getProperty("url");
			String USERNAME = p.getProperty("username");
			String PASSWORD = p.getProperty("password");
			
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		

		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData/Contact.xlsx");
		
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh =  wb.getSheet("Contact");
			Row row = sh.getRow(7);
			String orgName = row.getCell(2).toString()+randomInt;	
			String contactLastName = row.getCell(3).toString();
			
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
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//verify header msg expected result
			String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(orgName)) {
				System.out.println(orgName+"===is created=== PASS");
			}
			else 
				System.out.println(orgName+"==is not created===FAIL");
			
			//verify header phone number info expected result
			driver.findElement(By.linkText("Contacts")).click();
			
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(contactLastName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			// switch to child window
			 Set<String> set = driver.getWindowHandles();
			 Iterator<String> it= set.iterator();
			 while(it.hasNext()) {
				 String windowID=it.next();
				 driver.switchTo().window(windowID);
				 
				
				String currenturl = driver.getCurrentUrl();
				if(currenturl.contains("module=Accounts")) {
					break;
				}
			 }
			 
			driver.findElement(By.id("search_txt")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			
			//switch to parent window
			 Set<String> set1 = driver.getWindowHandles();
			 Iterator<String> it1= set1.iterator();
			 while(it1.hasNext()) {
				 String windowID=it1.next();
				 driver.switchTo().window(windowID);
				 
				
				String currenturl = driver.getCurrentUrl();
				if(currenturl.contains("Contacts&action")) {
					break;
				}
			 }
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			
			//verify header msg 
			 headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(orgName)) {
				System.out.println(orgName+"===is created=== PASS");
			}
			else 
				System.out.println(orgName+"==is not created===FAIL");
			
			
			
			
			//verify header  info expected result
			String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgName.trim().equals(orgName)) {
				System.out.println(orgName+"===is created=== PASS");
			}
			else 
				System.out.println(orgName+"==is not created===FAIL");
			
			
			
	}

}
