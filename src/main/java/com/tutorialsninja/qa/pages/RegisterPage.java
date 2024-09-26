package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField; 
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField; 
	
	@FindBy(id="input-email")
	private WebElement emailField; 
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField; 
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@value=\"Continue\"]")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name=\"newsletter\"][@value=1]")
	private WebElement newsLetterCheckbox;
	
	@FindBy(xpath="//div[contains(@class, \"alert-dismissible\")]")
	private WebElement accountExistsWarningMessage;
	
	@FindBy(xpath="//div[contains(@class, \"alert-dismissible\")]")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath="//input[@id=\"input-firstname\"]/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath="//input[@id=\"input-lastname\"]/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath="//input[@id=\"input-email\"]/following-sibling::div")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath="//input[@id=\"input-password\"]/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstNameField(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastNameField(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailField(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephoneField(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPasswordField(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPasswordField(String passwordConfirmText) {
		passwordConfirmField.sendKeys(passwordConfirmText);
	}
	
	public void selectPrivacyPolicyCheckbox() {
		privacyPolicyCheckbox.click();
	}
	
	public AccountSuccessPage clickContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectNewsLetterCheckbox() {
		newsLetterCheckbox.click();
	}
	
	public String retreiveAccountExistsWarningMessage() {
		String warningText = accountExistsWarningMessage.getText();
		return warningText;
	}
	
	public String retreivePrivacyPolicyWarningMessage() {
		String warningText = privacyPolicyWarningMessage.getText();
		return warningText;
	}
	
	public String retreiveFirstNameWarningMessage() {
		String warningText = firstNameWarningMessage.getText();
		return warningText;
	}
	
	public String retreiveLastNameWarningMessage() {
		String warningText = lastNameWarningMessage.getText();
		return warningText;
	}
	
	public String retreiveEmailWarningMessage() {
		String warningText = emailWarningMessage.getText();
		return warningText;
	}
	
	public String retreiveTelephoneWarningMessage() {
		String warningText = telephoneWarningMessage.getText();
		return warningText;
	}
	
	public String retreivePasswordWarningMessage() {
		String warningText = passwordWarningMessage.getText();
		return warningText;
	}
}
