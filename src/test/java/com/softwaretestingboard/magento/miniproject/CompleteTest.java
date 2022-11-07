package com.softwaretestingboard.magento.miniproject;

import base.BasePage;
import base.DataGeneration;
import base.RDGpage;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(resources.Listeners.class)

@Test
public class CompleteTest extends BasePage  {
	SignIn signIn;
	RegistrationPage registrationPage;
	FiltersPage filtersPage;
	String email;
	WishListPage wishListPage;
	ShoppingCartPage shoppingCart;
	//RDGpage fakedata;

	public CompleteTest() throws IOException {
		super();
	}

	@BeforeTest
	public void setup() throws IOException {

		driver = getDriver();
		driver.manage().window().fullscreen();
		driver.get(getUrl());
		
	}

	@BeforeClass
	public void generateData() {
		email = DataGeneration.generateRandomEmail(20);
		
		/* Fairy fairy = Fairy.create();
		Person person =fairy.person();
		
		this.fakedata.setFirstName(person.getFirstName());
		this.fakedata.setLastName(person.getLastName());
		this.fakedata.setEmail(person.getEmail());
		this.fakedata.setPassword(person.getPassword());*/
	}

	@AfterTest
	public void end() {
		//driver.close();
		driver.quit();
	}
    @Test(priority= 1)
	public void RegistrationTest() throws InterruptedException {
		driver.manage().window().maximize();

		registrationPage = new RegistrationPage(driver);
		registrationPage.clickCreateAccount();
		registrationPage.setData("Gloria","Shehaj",email,"Test12345","Test12345");
		registrationPage.clickNewsLetter();
		registrationPage.clickSubmit();

		
		String pageTitle = driver.getTitle();
		assertEquals(pageTitle,"My Account Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites");
		System.out.println(pageTitle);
		assertTrue(registrationPage.messageDisplayed());
	
		//System.out.println(successMsg);

		WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", (element));

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 40); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]")));
		signIn = new SignIn (driver);
		signIn.clickSignOut();    		
	} 
	@Test(priority =2)
	public void LogInTest() throws InterruptedException {
		signIn = new SignIn(driver); 
		signIn.clickSignInBtn();
		signIn.enterEmailAndPassword(email,"Test12345");
		signIn.clickSingInSubmit();
		Thread.sleep(1000);
		signIn.clickUserName();
		String expectedRes =driver.findElement(By.xpath("//span[@class=\"logged-in\"]")).getAttribute("innerHTML");
		String actualRes="Welcome, Gloria Shehaj!";
		assertEquals(expectedRes, actualRes);
		System.out.println(expectedRes); 
		WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", (element));
		WebDriverWait wait = new WebDriverWait(driver, 40); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]")));
		signIn.clickSignOut();
		signIn.clickSignInBtn();
		signIn.enterEmailAndPassword(email,"Test12345");
		signIn.clickSingInSubmit();
		Thread.sleep(1000);
		signIn.clickUserName();

	}
	@Test (priority =3)
	public void CheckPageFilters() throws InterruptedException {
		filtersPage = new FiltersPage(driver);


		WebElement mainMenu = driver.findElement(By.linkText("Women"));

		Actions actions= new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		actions.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-11\"]"))).click().build().perform();

		filtersPage.clickColorMenu();
		filtersPage.clickBlueColor();
		System.out.println(filtersPage.getSizeOfProductsDisplayed());
		filtersPage.openPriceFilters();
		filtersPage.selectSecondPrice();
		System.out.println(filtersPage.getSizeOfProductsDisplayed());
		int expectedResult= 2;
		assertEquals(expectedResult,filtersPage.getSizeOfProductsDisplayed());
	}
    @Test(priority = 4)
	public void wishListControl() throws InterruptedException {

		filtersPage = new FiltersPage(driver);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		filtersPage.removeSelectedPrice();



		int actualnumber= 2;
		if (filtersPage.getSizeOfProductsDisplayed()>actualnumber)
			System.out.println("Success,the number is increased");
		else  System.out.println("Error");


		wishListPage = new WishListPage(driver);

		

		wishListPage.hoverFistElementclickWishList();
		boolean SuccessMsgFE =driver.findElement(By.cssSelector("div[role='alert'] > .message.message-success.success")).isDisplayed();
		System.out.println(SuccessMsgFE);

		

		//WebDriverWait wait = new WebDriverWait(driver,10);
		driver.get("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html");

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wishListPage.hoverSecondElementclickWishList();
		boolean SuccessMsgSE =driver.findElement(By.cssSelector("div[role='alert'] > .message.message-success.success")).isDisplayed();
		System.out.println(SuccessMsgSE);

		//registrationPage= new RegistrationPage(driver);

		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", (element));
	    //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.navigate().refresh();
		System.out.println(wishListPage.getItemsNumberInformation());
		//String expectedItems = "2 items";
		//WebDriverWait wait = new WebDriverWait(driver, 20); 
		//wait.until(ExpectedConditions.textToBe(By.className(".header.panel > .header.links  .customer-menu > .header.links > .link.wishlist  .counter.qty"), ("2 items")));
		assertEquals(wishListPage.getItemsNumberInformation(), 2);


	}
    @Test (priority = 5)
	public void ShopCardTests() {

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		filtersPage =new FiltersPage(driver);
		WebElement mainMenu = driver.findElement(By.linkText("Women"));

		Actions actions= new Actions(driver);


		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		actions.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-11\"]"))).click().build().perform();


		filtersPage.clickColorMenu();
		filtersPage.clickBlueColor();
		filtersPage.openPriceFilters();
		filtersPage.selectSecondPrice();

		shoppingCart = new ShoppingCartPage(driver);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		shoppingCart.hoverFistElementClickSizeAndAddToShop();
		boolean successMsgFe =driver.findElement(By.cssSelector("div[role='alert']")).isDisplayed();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        shoppingCart.clikshoppingCartLink();
        String shoppingCartPageTitle=driver.getTitle();
		assertEquals("Shopping Cart Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites",shoppingCartPageTitle);

        
			
	}

}


	/*public void PageTitle() {
		
		String pageTitle=driver.getTitle();

		Assert.assertEquals("Shopping Cart Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites",pageTitle);

*/
	
	
	
		
	
	


			
	    
		//WebDriverWait wait = new WebDriverWait(driver, 40); 
		//wait.until(ExpectedConditions.attributeContains(By.xpath("\"//span[@class=\\\"logged-in\\\"]\""),.getAttribute("innerHTML"), "Welcome,Gloria Shehaj!"));
		

	
		
	   
	
