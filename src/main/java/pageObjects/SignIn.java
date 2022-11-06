package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {
	
	 WebDriver driver;
	
	
	By SignInbtn = By.linkText("Sign In");
	
	By Email = By.cssSelector("input#email");
	
	By Password = By.cssSelector("fieldset #pass");
	
	By SignInsubmit = By.xpath("/html//form[@id='login-form']/fieldset[@class='fieldset login']//button[@name='send']");
	
	By UserName = By.xpath("//span[@class=\"logged-in\"]");
    
	By UserProfile = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button");
	
	By SignOutbtn = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]");
	
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		Actions actions = new Actions(driver);
	}
	
	
	public void clickSignInBtn() {
		driver.findElement(SignInbtn).click();
   }
	
	public void enterEmailAndPassword(String email,String password) {
		driver.findElement(Email).sendKeys(email);
		driver.findElement(Password).sendKeys(password);
	}
	
	public void clickSingInSubmit() {
		driver.findElement(SignInsubmit).click();
	}
	
	public void usernameCheck() {	
		driver.findElement(UserName).getAttribute("innerHTML");
	}
	
	public void clickUserProfile() {
		driver.findElement(UserProfile);
	}
	
	public void clickSignOut() {
		driver.findElement(SignOutbtn).click();
	}
	
	public void clickUserName() {
		driver.findElement(UserName).click();
	}
}
	
	
	
	
	
	


