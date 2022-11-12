package com.softwaretestingboard.magento.miniproject;

import base.BasePage;
import base.DataGeneration;
import base.PriceUtility;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(resources.Listeners.class)

@Test
public class CompleteTest extends BasePage {
	SignInPage signInPage;
	RegistrationPage registrationPage;
	FiltersPage filtersPage;
	String email;
	WishListPage wishListPage;
	ShoppingCartPage shoppingCartPage;
	

	// RDGpage fakedata;

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

		/*
		 * fakedata = new RDGpage(); 
		 * Fairy fairy = Fairy.create(); 
		 * Person person=fairy.person();
		 * 
		 * this.fakedata.setFirstName(person.getFirstName());
		 * this.fakedata.setLastName(person.getLastName());
		 * this.fakedata.setEmail(person.getEmail());
		 * this.fakedata.setPassword(person.getPassword());
		 * 
		 * System.out.println(fakedata.getFirstName());
		 */
	}

	@AfterTest
	public void end() {
		driver.close();
		driver.quit();
	}

	@Test(priority=1)
	@Description("Testing all registration steps, checking urls, checking showing success messages")
	public void RegistrationTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   
		registrationPage = new RegistrationPage(driver);
		registrationPage.clickCreateAccount();
		registrationPage.setData("Gloria", "Shehaj", email, "Test12345", "Test12345");
		registrationPage.clickNewsLetter();
		registrationPage.clickSubmit();

		String pageTitle = driver.getTitle();
		assertEquals(pageTitle,"My Account Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites");
		System.out.println(pageTitle);

		assertTrue(registrationPage.messageDisplayed());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.cssSelector(".header.panel > .header.links  span[role='button'] > .action.switch"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", (element));

		signInPage = new SignInPage(driver);
		signInPage.clickSignOut();
	}

	@Test(priority=2)
	@Description("Testing all LogIn steps,checking if username is displayed correctly")
	public void LogInTest() throws InterruptedException {
		signInPage = new SignInPage(driver);
		signInPage.clickSignInBtn();
		signInPage.enterEmailAndPassword(email, "Test12345");
		signInPage.clickSingInSubmit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBe(By.cssSelector(".panel.wrapper>.panel.header>.header.links>.greet.welcome>.logged-in"),"Welcome, Gloria Shehaj!" ));
		String actualRes = "Welcome, Gloria Shehaj!";
		String expectedRes =signInPage.userNameCheck();
		assertEquals(expectedRes, actualRes);
		System.out.println(expectedRes);
		signInPage.dropDown();
		signInPage.clickSignOut();
		signInPage.clickSignInBtn();
		signInPage.enterEmailAndPassword(email, "Test12345");
		signInPage.clickSingInSubmit();
		

	}

	@Test(priority=3)
	@Description("Testing color and price filters, checking if items are displayed as expected")
	public void CheckPageFilters() throws InterruptedException {
		filtersPage = new FiltersPage(driver);

		

		Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions.moveToElement(driver.findElement(By.linkText("Women"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]")))
		.moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-11\"]"))).click().build().perform();

		filtersPage.clickColorMenu();
		filtersPage.clickBlueColor();

		System.out.println(filtersPage.getProducts().size());

		int actualBlueElements = 0;
		for (WebElement item : filtersPage.getProducts()) {
			WebElement blueDiv = item
					.findElement(By.xpath("//div[contains(@class,'selected') and @option-label='Blue']"));
			if (blueDiv != null)
				actualBlueElements++;

		}
		assertEquals(filtersPage.getProducts().size(), actualBlueElements);

		filtersPage.openPriceFilters();
		filtersPage.selectSecondPrice();
		System.out.println(filtersPage.getProducts().size());
		int expectedResult = 2;
		assertEquals(filtersPage.getProducts().size(), expectedResult);

		int elementWithSelectedPrice = 0;
		for (WebElement item : filtersPage.getProducts()) {

			String priceStr = item.findElement(By.cssSelector(".price-wrapper > .price")).getText();
			Double fixPrice = PriceUtility.convertToNumber(priceStr);
			System.out.print(fixPrice);
			if (fixPrice > 50 & fixPrice < 59.99)
				elementWithSelectedPrice++;

		}
	}

	@Test(priority=4)
	@Description("Testing wish list options and the items number displayed")
	public void wishListControlTest() throws InterruptedException {

		filtersPage = new FiltersPage(driver);
		filtersPage.removeSelectedPrice();

		int actualNumber = 2;
		assertTrue(filtersPage.getProducts().size()>actualNumber);
		

		wishListPage = new WishListPage(driver);
		wishListPage.hoverFistElementclickWishList();
		assertTrue(wishListPage.successMsgDisplayed());

		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wishListPage.hoverSecondElementclickWishList();
		assertTrue(wishListPage.successMsgDisplayed());

		wishListPage.clickUpdateWishListButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement element = driver.findElement(By.cssSelector(".header.panel > .header.links  span[role='button'] > .action.switch"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", (element));

		System.out.println(wishListPage.getItemsNumberInformation());
		String expectedItems = "2 items";
		//wait.until(ExpectedConditions.textToBe(
		//		By.cssSelector(
		//				".header.panel > .header.links  .customer-menu > .header.links > .link.wishlist > a > span"),
		//		("2 items")));
		assertEquals(wishListPage.getItemsNumberInformation(), expectedItems);

	}

	// }
	@Test(priority=5)
	@Description("Testing Add to Cart, checking success msg, verifying navigation to the correct page, verifyinf total price, deleting items")
	public void ShopCartTests() throws InterruptedException {

		
		filtersPage = new FiltersPage(driver);

		Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.moveToElement(driver.findElement(By.linkText("Women"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]")))
		.moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-11\"]"))).click().build().perform();

		filtersPage.clickColorMenu();
		filtersPage.clickBlueColor();
		filtersPage.openPriceFilters();
		filtersPage.selectSecondPrice();

		shoppingCartPage = new ShoppingCartPage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		shoppingCartPage.hoverElementClickSizeAndAddToShop();
		assertTrue(shoppingCartPage.successMsgDisplayed());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		shoppingCartPage.clikcShoppingCartLink();
		String shoppingCartPageTitle = driver.getTitle();
		assertEquals("Shopping Cart Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites",shoppingCartPageTitle);

		shoppingCartPage = new ShoppingCartPage(driver);

		double TotalSum = 0;

		for (WebElement itemOnCart : shoppingCartPage.getcartElementPrices()) {

			double fixPriceOfItems = PriceUtility.convertToNumber(itemOnCart.getText());
			TotalSum += fixPriceOfItems;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

		System.out.println(shoppingCartPage.getTotalPrice());
		double TotalPrice = PriceUtility.convertToNumber(shoppingCartPage.getTotalPrice());
		assertEquals(TotalSum, TotalPrice);

		shoppingCartPage.getDeleteButtonsNumber();
		System.out.println("printo Numrin e butonave" + shoppingCartPage.getDeleteButtonsNumber());

		while (shoppingCartPage.getDeleteButtonsNumber() > 0) {

			List<WebElement> deleteButton = shoppingCartPage.getDeleteButtons();
			deleteButton.get(0).click();
			

		}
		String expectedMsg = shoppingCartPage.getMessageNoItemDisplayed();
		String actualMsg = "You have no items in your shopping cart.";
		assertEquals(expectedMsg, actualMsg);

	}
}
