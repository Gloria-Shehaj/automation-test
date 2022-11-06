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
	

    public ShoppingCartPage(WebDriver driver) {
	   this.driver = driver;
	PageFactory.initElements(driver, this);
	 
    }
	
    public void hoverFistElementclickSizeMedium(){
		Actions hover = new Actions (driver);
		hover.moveToElement(firstProduct).moveToElement(sizeMedium).click().build().perform();
		
		
		}
	
	 public void hoverFirstElementclickAddToCart() {
		 Actions hover = new Actions(driver);
		 hover.moveToElement(firstProduct).moveToElement(firstProductAddtoCard);
		 
	 }
	  
	 
	 
	 
    }







