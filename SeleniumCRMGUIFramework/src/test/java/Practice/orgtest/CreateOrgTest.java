package Practice.orgtest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {
	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/CommanData.properties");
		Properties p = new Properties();
		p.load(fis);

		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");

		Random random = new Random();
		int randomInt = random.nextInt(1000);

		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData/orgTest.xlsx");

		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString() + randomInt;
		wb.close();

		WebDriver driver = null;
		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
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

		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else
			System.out.println(orgName + "==is not created===FAIL");

		// verify header orgname info expected result
		String actOrgName = driver.findElement(By.xpath("//td[@class='dvtCellInfo']")).getText().trim();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else {
			System.out.println(orgName + "==is not created===FAIL");
		}

//		Actions action = new Actions(driver);
//		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();
//		
	}

}
