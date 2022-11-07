package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	public WebDriver driver;
	
	@FindBy(css=".items.list.product-items.products > li:nth-of-type(1)")
	WebElement firstProduct;
	
	
	@FindBy(css=".swatch-opt-1252 > .size.swatch-attribute > div[role='listbox'] > div:nth-of-type(3)")
	WebElement sizeMedium;
	
	
	@FindBy(css="li:nth-of-type(1) > .product-item-info form[method='post'] > button[title='Add to Cart']")
	WebElement firstProductAddtoCard;
	
	
	
	@FindBy(css=".items.list.product-items.products > li:nth-of-type(1) > .product-item-info  .price-box.price-final_price .price-wrapper")
	WebElement priceOfFirtsElement;
	
	
	@FindBy(xpath="/html/body/div[2]/main/div[3]/div[1]/div[3]/ol/li[2]")
	WebElement secondProduct;
	
	
	@FindBy(css=".swatch-opt-1236 >.size.swatch-attribute>div[role='listbox']>div:nth-of-type(4)")
	WebElement sizeLarge;
	
	
	@FindBy(css="li:nth-of-type(2) >.product-item-info form[method='post']>button[title='Add to Cart']")
	WebElement secondProductAddtoCard;
	 
	
	@FindBy(css=".counter-number")
	WebElement itemsNumberOnCard;
	
	
	@FindBy(css=".message.message-success.success  a")
	WebElement shoppingCartLink;
	
	@FindBy(css="tbody:nth-of-type(1) > .item-info > .col.price > .price-excluding-tax  .price")
	WebElement firstProductPrice;
	
	
	@FindBy(css="tbody:nth-of-type(2) > .item-info > .col.price > .price-excluding-tax  .price")
	WebElement secondProductPrice;
	
	
	@FindBy(css=".grand.totals > .amount  .price")
	WebElement totalAmount;
	
	
	@FindBy(css="tbody:nth-of-type(1) a[title='Remove item']")
	WebElement deleteFirstProduct;
	
	@FindBy(css=".cart.item a[title='Remove item']")
	WebElement deleteSecondProduct;
	
	
	
	
	
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void hoverFistElementClickSizeAndAddToShop() {
		Actions hover = new Actions(driver);
		hover.moveToElement(firstProduct).moveToElement(sizeMedium).click().moveToElement(firstProductAddtoCard).click()
				.moveToElement(secondProduct).moveToElement(sizeLarge).click().moveToElement(secondProductAddtoCard)
				.click().build().perform();

	}

//	
	public String getCardItemsNumber() {
		return itemsNumberOnCard.getText();
	}
	
	public void clikshoppingCartLink() {
		shoppingCartLink.click();
		
	}
}
	 
	 
	 
    







