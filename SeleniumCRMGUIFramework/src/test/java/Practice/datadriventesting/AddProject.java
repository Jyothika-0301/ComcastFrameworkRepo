package Practice.datadriventesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;

public class AddProject {
	public static void main() throws Exception {
		WebDriver driver;
		FileUtility fu = new FileUtility();
		String browser = fu.getData("browser");
		String url = fu.getData("url");
		String un = fu.getData("username");
		String pwd = fu.getData("password");

		ExcelUtility eu = new ExcelUtility();
		String projectname = eu.readDataFromExcel("Sheet1", 1, 0);
		String projectManager = eu.readDataFromExcel("Sheet1", 1, 1);
		String projectStatus = eu.readLongNumber("Sheet1", 1, 2);

		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else
			driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(un);
		driver.findElement(By.id("inputPassword")).clear();
		driver.findElement(By.id("inputPassword")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();

		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectname);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys(projectManager);

		Select sel = new Select(
				driver.findElement(By.xpath("//label[contains(text(),'Project')]/following::select[2]")));
		sel.selectByVisibleText("Created");

		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

}
