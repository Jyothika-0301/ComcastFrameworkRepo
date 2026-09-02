package Practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {
	public static void main(String[] args) throws IOException, ParseException {
		
FileInputStream  fis = new FileInputStream("src/main/resources/CommanData.properties");
		
		
//		Properties pObj = new Properties();
//		pObj.load(fis);
//		


JSONParser parser = new JSONParser();
Object obj= parser.parse(new FileReader("C:\\Users\\jyothika\\eclipse-workspace\\SeleniumCRMGUIFramework\\src\\test\\resources\\testData\\appCommonData.json"));

JSONObject map = (JSONObject)obj;
		String BROWSER =  (String) map.get("browser").toString();                          //pObj.getProperty("browser");
		String URL = 	 (String) map.get("url").toString();    								//pObj.getProperty("url");
		String USERNAME = 	(String) map.get("username").toString();    							//pObj.getProperty("username");
		String PASSWORD = 			(String) map.get("password").toString();    						//pObj.getProperty("password");
		
FileInputStream fis1 = new FileInputStream("./src/test/resources/testData/orgname.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh =  wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString();		
		wb.close();
		
//		
//		Scanner s = new Scanner(System.in);
//		System.out.println("Enter the Browser");
//		String browser=s.next();
//		// we was using this browser in the if stmt
//		
		
		WebDriver driver =null;
		if(BROWSER.equals("chrome"))
			 driver = new ChromeDriver();
		 else if(BROWSER.equals("edge"))
		 driver= new EdgeDriver();
		 else if(BROWSER.equals("firefox"))
			 driver= new FirefoxDriver();
		 else
			 driver = new ChromeDriver();
//		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
	}

}
