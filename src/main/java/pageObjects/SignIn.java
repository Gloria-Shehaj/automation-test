package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

	WebDriver driver;

	@FindBy(linkText = "Sign In")
	WebElement SignInbtn;

	@FindBy(css = "input#email")
	WebElement Email;

	@FindBy(css = "fieldset #pass")
	WebElement Password;

	@FindBy(id = "send2")
	WebElement SignInsubmit;

	@FindBy(xpath = "//span[@class=\"logged-in\"]")
	WebElement UserName;

	@FindBy(xpath = "//span[@class=\"logged-in\"]")
	WebElement GetUserName;

	@FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")
	WebElement UserProfile;

	@FindBy(css = ".panel li:nth-of-type(2) [data-post]")
	WebElement SignOutbtn;

	@FindBy(css = ".panel [tabindex='-1']")
	WebElement dropDownProfileBtn;

	public SignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickSignInBtn() {
		SignInbtn.click();
	}

	public void enterEmailAndPassword(String email, String password) {
		this.Email.sendKeys(email);
		this.Password.sendKeys(password);
	}

	public void clickSingInSubmit() {
		SignInsubmit.click();
	}

	public void clickUserProfile() {
		UserProfile.click();
	}

	public void clickSignOut() {
		SignOutbtn.click();
	}

	public void clickUserName() {
		UserName.click();
	}

	public String userNameCheck() {
		return GetUserName.getAttribute("innerHTML");

	}

	public void dropDown() {
		dropDownProfileBtn.click();

	}

}	


