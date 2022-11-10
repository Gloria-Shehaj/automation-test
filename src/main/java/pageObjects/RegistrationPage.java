package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	public WebDriver driver;

	@FindBy(linkText = "Create an Account")
	WebElement CreateanAccountbt;

	@FindBy(id = "firstname")
	WebElement FirstName;

	@FindBy(xpath = "//input[@id='lastname']")
	WebElement LastName;

	@FindBy(id = "is_subscribed")
	WebElement SignUpForNewsLetter;

	@FindBy(xpath = "//input[@id='email_address']")
	WebElement Email;

	@FindBy(xpath = "//input[@id='password']")
	WebElement Password;

	@FindBy(xpath = "//input[@id='password-confirmation']")
	WebElement ConfirmPassword;

	@FindBy(xpath = "//button[@title='Create an Account']")
	WebElement SubmitBtn;

	@FindBy(css = "[data-ui-id='message-success']")
	WebElement SuccessMessage;

	@FindBy(xpath=".panel.header .customer-welcome .customer-name>.action.switch")
	public WebElement ProfileDropDown;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickCreateAccount() {
		CreateanAccountbt.click();
	}

	public void setData(String firstname, String lastname, String email, String password, String password1) {
		this.FirstName.sendKeys(firstname);
		this.LastName.sendKeys(lastname);
		this.Email.sendKeys(email);
		this.Password.sendKeys(password1);
		this.ConfirmPassword.sendKeys(password1);
	}

	public void clickNewsLetter() {
		SignUpForNewsLetter.click();
	}

	public void clickSubmit() {
		SubmitBtn.click();
	}

	public Boolean messageDisplayed() {
		Boolean status = SuccessMessage.isDisplayed();
		return status;
	}
}
