package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public WebDriver driver;
	SearchPage searchPage;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initiliazeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterTextSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickSearchButton();
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct());
		
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterTextSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickSearchButton();
		
		String actualSearchMessage = searchPage.retreiveNoProductTextMessage();
		Assert.assertEquals(actualSearchMessage, "abcd", "No product message");
		//Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextMessage"), "No product message");
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickSearchButton();
		
		String actualSearchMessage = searchPage.retreiveNoProductTextMessage();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextMessage"), "No product message");
	}

}
