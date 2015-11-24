package worldpay.QS.Selenium.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import worldpay.QS.Selenium.util.Utility;

public class WebItem {
	
	public WebElement item;
	
	public String locator;
	
	public WebItem(WebElement webele)
	{
		item = webele;
	}
	
	public WebItem(By by)
	{
		locator = by.toString().substring(by.toString().indexOf(":")+1, by.toString().length()).trim();
		
		if(Utility.isElementPresent(Browser.getInstance().driver, by))
		{
			item = Browser.getInstance().driver.findElement(by);
		}
		else 
		{
			//LogWriter.writeToFile("item cannot be found:"+locator);
		}

	}
	
	public WebItem(By by, WebItem element)
	{
		locator = by.toString().substring(by.toString().indexOf(":")+1, by.toString().length()).trim();

		if(Utility.isElementPresent(element.item, by))
		{
			item = element.item.findElement(by);
		}
		else 
		{
			//LogWriter.writeToFile("item cannot be found:"+locator);
		}

	}	

	public boolean isEmpty()
	{
		boolean isEmpty =false;
		
		if(item==null)
		{
			isEmpty =  true;
		}
		else if(!item.isDisplayed())
		{
			isEmpty =  true;
		}
		
		return isEmpty;
	}
	
	public boolean isEmpty(Boolean isDisplay)
	{
		boolean isEmpty =false;
		
		if(item==null)
		{
			isEmpty =  true;
		}
		else if(!item.isDisplayed()&&isDisplay)
		{
			isEmpty =  true;
		}
		
		return isEmpty;
	}
	
	public void sendKeys(String keys){
		item.sendKeys(keys);
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void click(){
		item.click();
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void movetoclick(){
		Actions action = new Actions(Browser.getInstance().driver);
		action.moveToElement(item).click();
		action.perform();
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void select(String visibleText){
		Select userTypeDropdown = new Select(item);
		userTypeDropdown.selectByVisibleText(visibleText);
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void select(int index){
		Select userTypeDropdown = new Select(item);
		userTypeDropdown.selectByIndex(index);
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clear(){
		item.clear();
	}
	
	
	public void changeattribute(String attrName, String value)
	{
		//String itemid = item.getAttribute("id");
		
		JavascriptExecutor js = (JavascriptExecutor) Browser.getInstance().driver;
		//js.executeScript("document.getElementById('"+itemid+"').setAttribute('"+attrName+"', '"+value+"')");
		js.executeScript("arguments[0].setAttribute('"+attrName+"', '"+value+"')", item);
	}
	
	public void changeinnerHTML(String value)
	{
		//String itemid = item.getAttribute("id");
		
		JavascriptExecutor js = (JavascriptExecutor) Browser.getInstance().driver;
		//js.executeScript("document.getElementById('"+itemid+"').setAttribute('"+attrName+"', '"+value+"')");
		js.executeScript("arguments[0].innerHTML ='"+value+"'", item);
	}
	
	public boolean isDisplay()
	{
		return item.isDisplayed();
	}
	
	public void activeCB(boolean isActive)
	{
		if(item!=null)
		{
			if((item.isSelected()&&!isActive)||(!item.isSelected()&&isActive))
			{
				Actions action = new Actions(Browser.getInstance().driver);
				action.moveToElement(item).click();
				action.perform();
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String getText()
	{
		if(item!=null)
		{
			return item.getText();
		}
		else
		{
			return "";
		}
	}
}
