package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FiltersPage {
	
	public WebDriver driver;
	
	//WebElement womenMenu = driver.findElement(By.linkText("Women"));
	//WebElement topsMenu = driver.findElement( By.xpath("//*[@id=\"ui-id-9\"]"));
	//WebElement jacketsMenu = driver.findElement( By.xpath("//*[@id=\\\"ui-id-11\\\"]"));
     
	@FindBy(linkText = "Women")
	WebElement womenMenu;

	@FindBy(xpath = "//*[@id=\"ui-id-9\"]")
	WebElement topsMenu;

	@FindBy(xpath = "//*[@id=\\\"ui-id-11\\\"]")
	WebElement jacketsMenu;

	@FindBy(css = "[role='presentation']:nth-of-type(4) [role='tab']")
	WebElement colorMenu;

	@FindBy(css = "[role='presentation']:nth-of-type(4) .swatch-option-link-layered:nth-of-type(2) [tabindex]")
	WebElement blueColor;

	@FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[3]/ol/li")
	List<WebElement> sellingProducts;

	@FindBy(css = "[title='Remove Color Blue']")
	WebElement removeSelectedColor;

	@FindBy(css = "div:nth-of-type(3) > div[role='tab']")
	WebElement priceFilter;

	@FindBy(xpath = "//*[@id=\"narrow-by-list\"]/div[3]/div[2]/ol/li[2]")
	WebElement secondPrice;

	@FindBy(xpath = "//*[@id=\"layered-filter-block\"]/div[2]/div[1]/ol/li[1]/a")
	WebElement removePriceBtn;

	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]")));
	// assertEquals(6, notebooksPage.getSizeOfProductsDisplayed());



	public FiltersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void hoverJacketsMenu() {

		// Actions hover = new Actions(driver);

		// hover.moveToElement(womenMenu).moveToElement(topsMenu).moveToElement(jacketsMenu).click().build().perform();
	}

	public void clickColorMenu() {
		colorMenu.click();
	}

	public void clickBlueColor() {
		blueColor.click();

	}

	public int getSizeOfProductsDisplayed() {
		return sellingProducts.size();
	}

	public List<WebElement> getListOfSellingProducts() {
		return sellingProducts;
	}

	public void openPriceFilters() {
		priceFilter.click();
	}

	public void selectSecondPrice() {
		secondPrice.click();
	}

	public void removeSelectedPrice() {
		removePriceBtn.click();
	}

}
//}
