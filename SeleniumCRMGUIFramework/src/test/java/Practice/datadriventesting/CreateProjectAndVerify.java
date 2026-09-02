package Practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerify {
	public static void main(String[] args) throws SQLException, InterruptedException {
		
		//create project in GUI using selenium
		String projectName ="Instagram_10";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://49.249.29.4:8091/");
		Thread.sleep(2000);
	
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Projects")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("jyothika");

		Select sel = new Select(
				driver.findElement(By.xpath("//label[contains(text(),'Project')]/following::select[2]")));
		sel.selectByVisibleText("Created");

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//verify the project in DB using JDBC
		String expectedProjectName ="FB_01";
		boolean flag = false;

// 1. load / register the database driver
Driver driverRef = new Driver();               // Driver is class tht is the implimentation of Driver interface
DriverManager.registerDriver(driverRef);

// 2. connect to database
Connection con = DriverManager.getConnection("jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
System.out.println("==========Done============");

// 3. create Sql statement
 Statement stat= con.createStatement();
 
 
// 4. execute select query and get result
  ResultSet relset=  stat.executeQuery("select * from project");
  while(relset.next()) {
	  String actProjectNameString=relset.getString(4);
	  if(expectedProjectName.equals(actProjectNameString)) {
		  flag=true;
	  System.out.println(expectedProjectName+"is available==Pass");
  }
  }
  
  if(flag==false) {
	  System.out.println(expectedProjectName+"is not available==FAIL");
  }
// 5. close the connection
  con.close();
		
	}

}
