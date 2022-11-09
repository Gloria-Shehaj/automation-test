package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PriceCheck {
	
	public static Double convertToNumber(String price) {
		String fixedPrice = price.substring(1);
		return Double.parseDouble(fixedPrice);
		
	
	} 	

}
