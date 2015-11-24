package worldpay.QS.Selenium.util;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Utility {
	
	public static int MaxChartNumber = 5;
	
	public final static String username = "nana110119120@163.com";
	public final static String password = "zheshixiaohao110";

	public static boolean isElementPresent(WebElement element, By by)
	{
	    try {
	        element.findElement(by);
	        return true;
	    } catch(org.openqa.selenium.NoSuchElementException e){
	        return false;
	    }
	}
	
	public static boolean isElementPresent(WebDriver driver, By by)
	{
	    try {
	    	driver.findElement(by);
	        return true;
	    } catch(org.openqa.selenium.NoSuchElementException e){
	        return false;
	    }
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static int getRandomInt(int low, int high)
	{
		Random R = new Random();
		return R.nextInt(high-low) + low;
	}
	
	public static boolean isOdd(int value)
	{
		if(value%2==1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
