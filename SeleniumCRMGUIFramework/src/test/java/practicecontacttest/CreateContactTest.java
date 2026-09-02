package practicecontacttest;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateContactTest {
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
			

			FileInputStream fis1 = new FileInputStream("./src/test/resources/testData/contact.xlsx");
			
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh =  wb.getSheet("Sheet1");
				Row row = sh.getRow(1);
				String lastName = row.getCell(2).toString()+randomInt;		
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
				
				driver.findElement(By.linkText("Contacts")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				
				
				//verify header  info expected result
				String actLastName = driver.findElement(By.id("mouseArea_Last Name")).getText();
				if(actLastName.equals(lastName)) {
					System.out.println(lastName+"===is created=== PASS");
				}
				else 
					System.out.println(lastName+"==is not created===FAIL");
				
				
	}

}
