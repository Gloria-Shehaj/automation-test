package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	public WebDriver driver;
	
	@FindBy (linkText= "Create an Account")
	WebElement CreateanAccountbt;
	//By CreateanAccountbtn = By.linkText("Create an Account");
	
	@FindBy (id = "firstname")
	WebElement FirstName;
	
	//By FirstName = By.id("firstname");
	
	@FindBy (xpath = "//input[@id='lastname']")
	WebElement LastName;
	
	//By LastName =By.xpath("//input[@id='lastname']");
	
	@FindBy (id = "is_subscribed")
	WebElement SignUpForNewsLetter;
	
	//By SignUpForNewsLetter = By.id("is_subscribed");
	
	@FindBy (xpath = "//input[@id='email_address']")
	WebElement Email;
	//By Email = By.xpath("//input[@id='email_address']");
	
	@FindBy (xpath = "//input[@id='password']")
	WebElement Password;
	
	//By Password = By.xpath("//input[@id='password']");
	
	@FindBy (xpath = "//input[@id='password-confirmation']")
	WebElement ConfirmPassword;

	//By ConfirmPassword = By.xpath("//input[@id='password-confirmation']");
	
	@FindBy (xpath = "//button[@title='Create an Account']")
	WebElement SubmitBtn;
	
	//By SubmitBtnAcc = By.xpath("//button[@title='Create an Account']");
	
	@FindBy (css = "[data-ui-id='message-success']")
	WebElement SuccessMessage;
	
	//By SuccessMessage  = By.cssSelector("[data-ui-id='message-success']");
	
	
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	PageFactory.initElements(driver, this);
	
	
	}
	
	
public void clickCreateAccount() {
     CreateanAccountbt.click();
}

public void setData(String firstname, String lastname, String email,String password,String password1) {
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
