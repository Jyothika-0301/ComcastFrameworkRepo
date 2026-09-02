package Practice.datadriventesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;

public class AddEmployee {




public static void main(String[] args) throws Exception {
	WebDriver driver;
	FileUtility fu = new FileUtility();
	 String browser = fu.getData("browser");
	 String url = fu.getData("url");
	 String un = fu.getData("username");
	 String pwd = fu.getData("password");
	 
	 ExcelUtility eu = new ExcelUtility();
	String name = eu.readDataFromExcel("Sheet2", 1, 0);
	String email = eu.readDataFromExcel("Sheet2", 1, 1);
	String phone = eu.readLongNumber("Sheet2", 1, 2);
	String username = eu.readDataFromExcel("Sheet2", 1, 3);
	String destination = eu.readDataFromExcel("Sheet2", 1, 4);
	String experience = eu.readDataFromExcel("Sheet2", 1, 5);
	String project = eu.readDataFromExcel("Sheet2", 1, 6);
	 
	 
	 if(browser.equals("chrome"))
		 driver = new ChromeDriver();
	 else if(browser.equals("edge"))
	 driver= new EdgeDriver();
	 else 
		 driver= new FirefoxDriver();
	 
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 driver.get(url);
	 
	 driver.findElement(By.id("username")).clear();
	 driver.findElement(By.id("username")).sendKeys(un);
	 driver.findElement(By.id("inputPassword")).clear();
	 driver.findElement(By.id("inputPassword")).sendKeys(pwd);
	 driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();
	 
	 driver.findElement(By.linkText("Employees")).click();
	 Thread.sleep(1000);
	 
	 driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(name);
	
	 driver.findElement(By.xpath("(//input[@type='email'])[2]")).sendKeys(email);
	 driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys(phone);
	 driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys(username);
	 driver.findElement(By.xpath("(//input[@type='text'])[7]")).sendKeys(destination);
	 driver.findElement(By.xpath("(//input[@type='text'])[8]")).sendKeys(experience);
	 

	 
	 
	 Select sel = new Select(driver.findElement(By.xpath("//label[contains(text(),'Project')]/following::select[1]")));
	 sel.selectByVisibleText("Project1");
	 
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 WebElement addBtn = driver.findElement(By.xpath("//input[@type='submit' and@Value='Add']"));
	 
	 js.executeScript("arguments[0].click();",addBtn);

	 
}

}
