package Practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxCommandContext;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.FileUtility;

public class Login {
	public static void main(String[] args) throws Exception {
		WebDriver driver;
		FileUtility fu = new FileUtility();
		 String browser = fu.getData("browser");
		 String url = fu.getData("url");
		 String un = fu.getData("username");
		 String pwd = fu.getData("password");
 
		
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
		 
		 Thread.sleep(2000);
		 driver.quit();
		 
		 
	
	}
	
	
}
