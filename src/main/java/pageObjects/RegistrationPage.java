package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	public WebDriver driver;
	
	By CreateanAccountbtn = By.linkText("Create an Account");
	
	By FirstName = By.id("firstname");
	
	By LastName =By.xpath("//input[@id='lastname']");
	
	By SignUpForNewsLetter = By.id("is_subscribed");
	
	By Email = By.xpath("//input[@id='email_address']");
	
	By Password = By.xpath("//input[@id='password']");
	
	By ConfirmPassword = By.xpath("//input[@id='password-confirmation']");
	
	By SubmitBtnAcc = By.xpath("//button[@title='Create an Account']");
     
	
	Actions action;
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	PageFactory.initElements(driver, this);
	action = new Actions(driver);
	
	}
	
	
public void clickCreateAccount() {
	driver.findElement(CreateanAccountbtn).click();
}

public void setData(String firstname, String lastname, String email,String password,String password1) {
	driver.findElement(FirstName).sendKeys(firstname);
	driver.findElement(LastName).sendKeys(lastname);
	driver.findElement(Email).sendKeys(email);
	driver.findElement(Password).sendKeys(password1);
	driver.findElement(ConfirmPassword).sendKeys(password1);
}

public void clickNewsLetter() {
	driver.findElement(SignUpForNewsLetter).click();
}


public void clickSubmit() {
	driver.findElement(SubmitBtnAcc).click();
}
}
