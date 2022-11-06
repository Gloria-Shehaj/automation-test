package base;

public class PriceCheck {
	
	public Double convertToNumber(String price) {
		String fixedPrice = price.substring(1);
		return Double.valueOf(fixedPrice);
	} 

}
