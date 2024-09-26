package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

import org.openqa.selenium.WebDriver;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initiliazeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterAnAccountWithMandatoryFields() {
		
		registerPage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerPage.enterLastNameField(dataProp.getProperty("lastName"));
		registerPage.enterEmailField(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneField(dataProp.getProperty("telephone"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicyCheckbox();
		accountSuccessPage = registerPage.clickContinueButton();
		
		String actualSuccessHeading = accountSuccessPage.retreiveAccountSuccessPageHeadingText();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountCreatedMessage"), "Account is not created");
	
	}
	
	@Test(priority = 2)
	public void verifyRegisterAnAccountWithAllFields() {
		
		registerPage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerPage.enterLastNameField(dataProp.getProperty("lastName"));
		registerPage.enterEmailField(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneField(dataProp.getProperty("telephone"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterCheckbox();
		registerPage.selectPrivacyPolicyCheckbox();
		accountSuccessPage = registerPage.clickContinueButton();
		
		
		String actualSuccessHeading = accountSuccessPage.retreiveAccountSuccessPageHeadingText();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountCreatedMessage"), "Account is not created");
		
	}
	
	@Test(priority = 3)
	public void verifyRegisterAnAccountWithExistingEmailAddress() {
		
		registerPage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerPage.enterLastNameField(dataProp.getProperty("lastName"));
		registerPage.enterEmailField(prop.getProperty("validEmail"));
		registerPage.enterTelephoneField(dataProp.getProperty("telephone"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterCheckbox();
		registerPage.selectPrivacyPolicyCheckbox();
		registerPage.clickContinueButton();
		
		String actualErrorMessage = registerPage.retreiveAccountExistsWarningMessage();
		Assert.assertEquals(actualErrorMessage, dataProp.getProperty("accountsExistsErrorMessage"), "Warning not displayed");
		
	}
	
	@Test(priority = 4)
	public void verifyRegisterAnAccountWithoutAnyDetails() {
		
		registerPage.clickContinueButton();
		
		String actualPrivacyPolicyWarning = registerPage.retreivePrivacyPolicyWarningMessage();
		Assert.assertEquals(actualPrivacyPolicyWarning, dataProp.getProperty("privacyPolicyErrorMessage"), "Warning not displayed");
		
		String actualFirstNameWarning = registerPage.retreiveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameErrorMessage"), "Warning not macthing");
		
		String actualLastNameWarning = registerPage.retreiveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameErrorMessage"), "Warning not macthing");
		
		String actualEmailWarning = registerPage.retreiveEmailWarningMessage();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailErrorMessage"), "Email not macthing");
		
		String actualTelephoneWarning = registerPage.retreiveTelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneErrorMessage"), "Telephone message not macthing");
		
		String actualPasswordWarning = registerPage.retreivePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordErrorMessage"), "Password message not macthing");
		
	}
	
	

}
