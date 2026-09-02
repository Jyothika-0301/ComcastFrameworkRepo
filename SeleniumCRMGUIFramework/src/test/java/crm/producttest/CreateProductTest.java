package crm.producttest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.baseclass.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.ProductPage;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebdriverUtility;

public class CreateProductTest  extends BaseClass{
	public void createProductTest() throws Exception {
		
		
		
		String productName = eu.readDataFromExcel("Sheet1", 1, 2)+ju.generateRandomNumber();
		
		
			LoginPage lp= new LoginPage(driver);            // it will take care of initialization
					lp.loginToApp("admin", "admin", productName);
			
				// navigate to product module
					HomePage hp = new HomePage(driver);
					hp.getProductLink().click();
		
					
					 // click on create product button
					
					ProductPage pp = new ProductPage(driver);
					pp.getCreateProdBtn().click();
					
				// enter all the details and create new organization
					
					CreateNewProductPage cpp = new CreateNewProductPage(driver);
					cpp.createProduct(productName);
					
					// verify Header msg expected result
					
					
					
//					OrganizationInformationPage oip = new OrganizationInformationPage(driver);
//					 String actOrgName = oip.getHeaderMsg().getText();
//					 actOrgName.contains(actOrgName)
					 
					 
//					if(actOrgName.contains(productName)) {
//						System.out.println(productName+"name is verified==pass");
//					}else
//						System.out.println(productName+"name is not verified==fail");
//					
					
			
	}

}
