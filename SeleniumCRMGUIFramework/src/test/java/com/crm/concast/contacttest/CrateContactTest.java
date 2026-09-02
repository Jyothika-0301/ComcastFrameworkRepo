package com.crm.concast.contacttest;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



public class CrateContactTest extends com.baseclass.BaseClass {

	
	@Test
	public void createContact() {
		System.out.println("==executed contact and verify==");
	}
	@Test
	public void createContactWithDate() {
		System.out.println("==executed contact with date and verify==");
	}

}
