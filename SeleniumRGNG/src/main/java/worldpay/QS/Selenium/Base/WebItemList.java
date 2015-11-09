package worldpay.QS.Selenium.Base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import worldpay.QS.Selenium.util.Utility;

public class WebItemList {
	
	public List<WebElement> items;
	
	public String locator;
	
	public WebItemList(By by)
	{
		locator = by.toString().substring(by.toString().indexOf(":")+1, by.toString().length()).trim();

		
		if(Utility.isElementPresent(Browser.getInstance().driver, by))
		{
			items = Browser.getInstance().driver.findElements(by);
		}
		else 
		{
			//LogWriter.writeToFile("item cannot be found:"+locator);
		}

	}
	
	public int size()
	{
		if(items!=null)
			return items.size();
		else {
			return 0;
		}
	}
	
	public WebItemList(By by, WebItem element)
	{
		
		locator = by.toString().substring(by.toString().indexOf(":")+1, by.toString().length()).trim();

		
		if(Utility.isElementPresent(element.item, by))
		{
			items = element.item.findElements(by);
		}
		else 
		{
			//LogWriter.writeToFile("item cannot be found:"+locator);
		}

	}
	
	
	public WebItem getListFirstElement()
	{
		if(items!=null&&items.size()>0)
		{
			WebItem webeleItem = new WebItem(items.get(0));
			return webeleItem;
		}
		else
		{
			return null;
		}
	}
	
	public WebItem getListLastElement()
	{
		if(items!=null&&items.size()>0)
		{
			WebItem webeleItem = new WebItem(items.get(items.size()-1));
			return webeleItem;
		}
		else
		{
			return null;
		}
	}
	
	public WebItem getListElement(int index)
	{
		if(items!=null&&items.size()>index)
		{
			WebItem webeleItem = new WebItem(items.get(index));
			return webeleItem;
		}
		else
		{
			return null;
		}
	}
	
	public WebItem getListElement(String text)
	{
		if(items!=null)
		{
			for(int i=0; i< items.size(); i++)
			{
				if(items.get(i).getText().contains(text))
				{
					WebItem webeleItem = new WebItem(items.get(i));
					return webeleItem;
				}
			}
		}

		return null;
	}
	
	public ArrayList<String> getTextList()
	{
		ArrayList<String> textlist = new ArrayList<String>();
		
		for(WebElement item:items)
		{
			textlist.add(item.getText());
		}
		
		return textlist;
	}
	
	public String getText(int index)
	{
		if(!items.isEmpty()&&items.size()>index)
		{
			WebElement item = items.get(index);
			return item.getText();
		}
		else
		{
			return "";
		}		
	}
	
	public boolean isEmpty()
	{
		return (items==null);
	}
	
	
	
}
