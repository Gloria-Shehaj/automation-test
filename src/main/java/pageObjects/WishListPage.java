package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {

	public WebDriver driver;

	@FindBy(css = ".product-items .product-item:nth-of-type(1)")
	WebElement firstElementontheList;

	@FindBy(css = ".product-items .product-item:nth-of-type(2)")
	WebElement secondElementontheListe;

	@FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[3]/ol/li[1]/div/div/div[3]/div/div[2]/a[1]")
	WebElement fistElementWishButton;

	@FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[3]/ol/li[2]/div/div/div[4]/div/div[2]/a[1]")
	WebElement secondElementWishButton;

	@FindBy(css = ".product-addto-links>.towishlist>span")
	WebElement wishButtonFirstElement;

	@FindBy(css = "div[role='alert'] > .message.message-success.success")
	WebElement successMsgWishList;

	@FindBy(css = ".panel .counter")
	WebElement wishListItemsNumber;

	public WishListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void hoverFistElementclickWishList() {
		Actions hover = new Actions(driver);
		hover.moveToElement(firstElementontheList).moveToElement(fistElementWishButton).click().build().perform();

	}

	public void clickFirstElement() {
		firstElementontheList.click();
	}

	public void clickSecondElement() {
		secondElementontheListe.click();
	}

	public void clickAddToWishList() {
		wishButtonFirstElement.click();

	}

	public void clickWishElement() {
		fistElementWishButton.click();
	}

	public void hoverSecondElementclickWishList() {

		Actions hover = new Actions(driver);
		hover.moveToElement(secondElementontheListe).moveToElement(secondElementWishButton).click().build().perform();
	}

	public Boolean successMsgDisplayed() {
		Boolean status = successMsgWishList.isDisplayed();
		return status;
	}

	public String getItemsNumberInformation() {
		return wishListItemsNumber.getText();
	}

}
	   