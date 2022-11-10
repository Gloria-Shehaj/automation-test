package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	public WebDriver driver;

	@FindBy(css = ".items.list.product-items.products > li:nth-of-type(1)")
	WebElement firstProduct;

	@FindBy(css = ".swatch-opt-1252 > .size.swatch-attribute > div[role='listbox'] > div:nth-of-type(3)")
	WebElement sizeMedium;

	@FindBy(css = "li:nth-of-type(1) > .product-item-info form[method='post'] > button[title='Add to Cart']")
	WebElement firstProductAddtoCard;

	@FindBy(css = ".items.list.product-items.products > li:nth-of-type(1) > .product-item-info  .price-box.price-final_price .price-wrapper")
	WebElement priceOfFirtsElement;

	@FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[3]/ol/li[2]")
	WebElement secondProduct;

	@FindBy(css = ".swatch-opt-1236 >.size.swatch-attribute>div[role='listbox']>div:nth-of-type(4)")
	WebElement sizeLarge;

	@FindBy(css = "li:nth-of-type(2) >.product-item-info form[method='post']>button[title='Add to Cart']")
	WebElement secondProductAddtoCard;

	@FindBy(css = ".counter-number")
	WebElement itemsNumberOnCard;

	@FindBy(css = ".message.message-success.success  a")
	WebElement shoppingCartLink;

	@FindBy(css = "tbody:nth-of-type(1) > .item-info > .col.price > .price-excluding-tax  .price")
	WebElement firstProductPrice;

	@FindBy(css = "tbody:nth-of-type(2) > .item-info > .col.price > .price-excluding-tax  .price")
	WebElement secondProductPrice;

	@FindBy(css = ".grand.totals > .amount  .price")
	WebElement totalAmount;

	@FindBy(css = "tbody:nth-of-type(1) a[title='Remove item']")
	WebElement deleteFirstProduct;

	@FindBy(css = ".cart.item a[title='Remove item']")
	WebElement deleteSecondProduct;

	@FindBy(css = "div[role='alert']")
	WebElement successMessageDisplayed;

	@FindBy(css = "#shopping-cart-table .item-info .col.subtotal .cart-price .price")
	public List<WebElement> cartElementPrices;

	@FindBy(css = ".grand.totals > .amount")
	WebElement totalPrice;

	@FindBy(css = ".action.action-delete")
	List<WebElement> deleteButtons;

	@FindBy(css = ".cart-empty > p:nth-of-type(1)")
	WebElement noItemMessageDisplayed;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void hoverElementClickSizeAndAddToShop() {
		Actions hover = new Actions(driver);
		hover.moveToElement(firstProduct).moveToElement(sizeMedium).click().moveToElement(firstProductAddtoCard).click()
				.moveToElement(secondProduct).moveToElement(sizeLarge).click().moveToElement(secondProductAddtoCard)
				.click().build().perform();

	}

	public String getCardItemsNumber() {
		return itemsNumberOnCard.getText();
	}

	public void clikcShoppingCartLink() {
		shoppingCartLink.click();

	}

	public Boolean successMsgDisplayed() {
		Boolean status = successMessageDisplayed.isDisplayed();
		return status;
	}

	public List<WebElement> getcartElementPrices() {
		return cartElementPrices;

	}

	public String getTotalPrice() {
		return totalPrice.getText();
	}

	public List<WebElement> getDeleteButtons() {
		return deleteButtons;
	}

	public int getDeleteButtonsNumber() {
		return deleteButtons.size();
	}

	public String getMessageNoItemDisplayed() {
		return noItemMessageDisplayed.getText();
	}
}
