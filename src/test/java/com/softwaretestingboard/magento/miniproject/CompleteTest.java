package com.softwaretestingboard.magento.miniproject;

import base.BasePage;
import base.DataGeneration;
import base.PriceCheck;
import base.RDGpage;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.codearte.jfairy.producer.person.PersonProperties.PersonProperty;
import io.qameta.allure.Description;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(resources.Listeners.class)

@Test
public class CompleteTest extends BasePage {
	SignIn signIn;
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
		 * fakedata = new RDGpage(); Fairy fairy = Fairy.create(); Person person
		 * =fairy.person();
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
		// driver.close();
		driver.quit();
	}

	@Test(priority = 1)
	@Description("Testing all registration steps, checking urls, checking showing success messages")
	public void RegistrationTest() throws InterruptedException {
		driver.manage().window().maximize();

		registrationPage = new RegistrationPage(driver);
		registrationPage.clickCreateAccount();
		registrationPage.setData("Gloria", "Shehaj", email, "Test12345", "Test12345");
		registrationPage.clickNewsLetter();
		registrationPage.clickSubmit();

		String pageTitle = driver.getTitle();
		assertEquals(pageTitle,
				"My Account Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites");
		System.out.println(pageTitle);

		assertTrue(registrationPage.messageDisplayed());

		WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", (element));

		signIn = new SignIn(driver);
		signIn.clickSignOut();
	}

	@Test(priority = 2)
	@Description("Testing all LogIn steps,checking if username is displayed correctly")
	public void LogInTest() throws InterruptedException {
		signIn = new SignIn(driver);
		signIn.clickSignInBtn();
		signIn.enterEmailAndPassword(email, "Test12345");
		signIn.clickSingInSubmit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		signIn.clickUserName();
		String expectedRes = signIn.userNameCheck();
		String actualRes = "Welcome, Gloria Shehaj!";
		assertEquals(expectedRes, actualRes);
		System.out.println(expectedRes);
		signIn.dropDown();
		signIn.clickSignOut();
		signIn.clickSignInBtn();
		signIn.enterEmailAndPassword(email, "Test12345");
		signIn.clickSingInSubmit();

	}

	@Test(priority = 3)
	@Description("Testing color and price filters, checking if items are displayed as expected")
	public void CheckPageFilters() throws InterruptedException {
		filtersPage = new FiltersPage(driver);

		WebElement mainMenu = driver.findElement(By.linkText("Women"));

		Actions actions = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		actions.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]")))
				.moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-11\"]"))).click().build().perform();

		filtersPage.clickColorMenu();
		filtersPage.clickBlueColor();

		System.out.println(filtersPage.getSizeOfProductsDisplayed());

		int actualBlueElements = 0;
		for (WebElement item : filtersPage.getListOfSellingProducts()) {
			WebElement blueDiv = item
					.findElement(By.xpath("//div[contains(@class,'selected') and @option-label='Blue']"));
			if (blueDiv != null)
				actualBlueElements++;

		}
		assertEquals(filtersPage.getSizeOfProductsDisplayed(), actualBlueElements);

		filtersPage.openPriceFilters();
		filtersPage.selectSecondPrice();
		System.out.println(filtersPage.getSizeOfProductsDisplayed());
		int expectedResult = 2;
		assertEquals(filtersPage.getSizeOfProductsDisplayed(), expectedResult);

		int elementWithSelectedPrice = 0;
		for (WebElement item : filtersPage.getListOfSellingProducts()) {

			String priceStr = item.findElement(By.cssSelector(".price-wrapper > .price")).getText();
			Double fixPrice = PriceCheck.convertToNumber(priceStr);
			System.out.print(fixPrice);
			if (fixPrice > 50 & fixPrice < 59.99)
				elementWithSelectedPrice++;

		}
	}

	@Test(priority = 4)
	@Description("Testing wish list options and the items number displayed")
	public void wishListControlTest() throws InterruptedException {

		filtersPage = new FiltersPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		filtersPage.removeSelectedPrice();

		int actualnumber = 2;
		if (filtersPage.getSizeOfProductsDisplayed() > actualnumber)
			System.out.println("Success,the number is increased");
		else
			System.out.println("Error");

		wishListPage = new WishListPage(driver);

		

		wishListPage.hoverFistElementclickWishList();
		assertTrue(wishListPage.successMsgDisplayed());

		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wishListPage.hoverSecondElementclickWishList();
		assertTrue(wishListPage.successMsgDisplayed());

		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", (element));

		System.out.println(wishListPage.getItemsNumberInformation());
		String expectedItems = "2 items";
		wait.until(ExpectedConditions.textToBe(
				By.cssSelector(
						".header.panel > .header.links  .customer-menu > .header.links > .link.wishlist > a > span"),
				("2 items")));
		assertEquals(wishListPage.getItemsNumberInformation(), expectedItems);

	}

	// }
	@Test(priority = 5)
	@Description("Testing Add to Cart, checking success msg, verifying navigation to the correct page, verifyinf total price, deleting items")
	public void ShopCartTests() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		filtersPage = new FiltersPage(driver);
		WebElement mainMenu = driver.findElement(By.linkText("Women"));

		Actions actions = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		actions.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]")))
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
		assertEquals(
				"Shopping Cart Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites",
				shoppingCartPageTitle);

		shoppingCartPage = new ShoppingCartPage(driver);

		double TotalSum = 0;

		for (WebElement itemOnCart : shoppingCartPage.getcartElementPrices()) {

			double fixPriceOfItems = PriceCheck.convertToNumber(itemOnCart.getText());
			TotalSum += fixPriceOfItems;

		}

		System.out.println(shoppingCartPage.getTotalPrice());
		double TotalPrice = PriceCheck.convertToNumber(shoppingCartPage.getTotalPrice());
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
