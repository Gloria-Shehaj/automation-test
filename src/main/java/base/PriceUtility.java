package base;

public class PriceUtility {
	
	public static Double convertToNumber(String price) {
		String fixedPrice = price.substring(1);
		return Double.parseDouble(fixedPrice);
		
	
	} 	

}
