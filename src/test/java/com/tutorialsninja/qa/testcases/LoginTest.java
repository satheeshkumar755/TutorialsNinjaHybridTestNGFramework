package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

import org.openqa.selenium.WebDriver;

public class LoginTest extends Base{
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initiliazeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {
	
		loginPage.enterEmailAddressField(email);
		loginPage.enterPasswordField(password);
		accountPage = loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Edit you account information, option not displayed");
		
	}
	
	@DataProvider
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterEmailAddressField(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordField(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retreiveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message not displayed");
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterEmailAddressField(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordField(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retreiveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message not displayed");
	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterEmailAddressField(prop.getProperty("validEmail"));
		loginPage.enterPasswordField(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retreiveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message not displayed");
	
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retreiveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message not displayed");
	
	}

}
