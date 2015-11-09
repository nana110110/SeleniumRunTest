package auto_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebPage {
	
	
	
	public boolean exists(int waitTime, By elementId){
		WebDriver dr = Browser.getInstance().driver;
		this.initElements();
		WebElement element = (new WebDriverWait(dr, waitTime)).until(ExpectedConditions.presenceOfElementLocated(elementId));
		if (element!=null){
			return true;
		}
		return false;
	}
	public abstract void invoke();
	public abstract void initElements();

}
