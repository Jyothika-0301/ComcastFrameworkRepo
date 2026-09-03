package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalendarPage {
	
	@FindBy(xpath = "//a[@href='index.php?action=ListView&module=Calendar&parenttab=My Home Page']")
	private WebElement allEventsLink;
	
	

}
