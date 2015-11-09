package auto_test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebItem {
	
	public WebElement item;
	public List<WebElement> items;
	public String locator;
	
	public WebItem(By by, boolean list){
		locator = by.toString().substring(by.toString().indexOf(":")+1, by.toString().length()).trim();
		if (list){
			items = Browser.getInstance().driver.findElements(by);
			
		}else{
			item = Browser.getInstance().driver.findElement(by);
			
		}
		
	}
	
	public void sendKeys(String keys){
		item.sendKeys(keys);
	}
	
	public void click(){
		item.click();
	}
	
	public void select(String visibleText){
		Select userTypeDropdown = new Select(item);
		userTypeDropdown.selectByVisibleText(visibleText);
	}

}
