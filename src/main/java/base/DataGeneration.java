package base;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;



public class DataGeneration {
	
	
		
		public static String generateRandomEmail(int length) {
		    
		    String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-";
		    String email = "";
		    String temp = RandomStringUtils.random(length, allowedChars);
		    email = temp.substring(0, temp.length() - 9) + "@testdata.com";
		    return email;
		}


	}


