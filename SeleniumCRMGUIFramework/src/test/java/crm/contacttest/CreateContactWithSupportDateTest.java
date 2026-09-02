package crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Exception {

		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();

		String BROWSER = fu.getData("browser");
		String URL = fu.getData("url");
		String USERNAME = fu.getData("username");
		String PASSWORD = fu.getData("password");

		String lastName = eu.readDataFromExcel("contact", 4, 2) + ju.generateRandomNumber();

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

		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		String startDate = ju.getSystemDateYYYYDDMM();
		String endDate = ju.getRequiredDateYYYYDDMM(30);

		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header info expected result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "===is created=== PASS");
		} else
			System.out.println(lastName + "==is not created===FAIL");

	}

}
