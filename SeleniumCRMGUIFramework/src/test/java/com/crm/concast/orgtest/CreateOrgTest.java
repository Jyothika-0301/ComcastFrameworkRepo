package com.crm.concast.orgtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;

public class CreateOrgTest extends BaseClass{

	
	@Test
	public void createOrgTest() {
		System.out.println("execute org test and verify");
	}
	@Test
	public void createOrgWithIndustry() {
		System.out.println("execute orh with industry and verify");
	}
	
}
