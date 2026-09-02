package practicecontacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {
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
			
			
			Date date = new Date();
			
			SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
			
			String startDate = sim.format(date);
			
			
			Calendar cal=sim.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH,30);        // static variable day of month
			
			
			String endDate= sim.format(cal.getTime());   // it will return the required date
		
			
			
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			
			
			driver.findElement(By.name("support_start_date")).clear();
			driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			
			driver.findElement(By.name("support_end_date")).clear();
			driver.findElement(By.name("support_end_date")).sendKeys(endDate);
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
