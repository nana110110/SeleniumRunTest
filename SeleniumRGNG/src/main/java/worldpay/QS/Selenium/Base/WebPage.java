package worldpay.QS.Selenium.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import worldpay.QS.Selenium.util.LogWriter;

public abstract class WebPage {
	
	public WebPage(){
		this.invoke();
		}
	
	public boolean exists(int waitTime, By elementId){
		WebDriver dr = Browser.getInstance().driver;
		this.initElements();
		WebElement element = (new WebDriverWait(dr, waitTime)).until(ExpectedConditions.presenceOfElementLocated(elementId));
		if (element!=null){
			return true;
		}
		return false;
	}
	
	public void confirmLeave(Boolean isSub)
	{
		// TODO Auto-generated method stub
		WebItem confirmPanelItem = new WebItem(By.xpath("//div[@id='pnlConfirmPopup']"));
		WebItem confirmBtnItem;
		WebItem cancelBtnItem;
		
		if(!confirmPanelItem.isEmpty())
		{
			if(isSub)
			{
				confirmBtnItem = new WebItem(By.xpath("//input[@id='btnYes']"),confirmPanelItem);
				if(!confirmBtnItem.isEmpty())
				{
					confirmBtnItem.movetoclick();
					LogWriter.writeToFile("Confirmed leave.");
				}
			}
			else
			{
				cancelBtnItem = new WebItem(By.xpath("//input[@id='SubmitButtonConfirm']"),confirmPanelItem);
				if(!cancelBtnItem.isEmpty())
				{
					cancelBtnItem.movetoclick();
					LogWriter.writeToFile("Canceled leave.");
				}
			}
		}

	}
	
	public void confirmCopy(Boolean isSub)
	{
		// TODO Auto-generated method stub
		WebItem confirmPanelItem = new WebItem(By.xpath("//div[@id='Panel1']"));
		WebItem confirmBtnItem;
		WebItem cancelBtnItem;
		
		if(!confirmPanelItem.isEmpty())
		{
			if(isSub)
			{
				confirmBtnItem = new WebItem(By.xpath("//input[@id='btnSubmitCopyToAll']"),confirmPanelItem);
				if(!confirmBtnItem.isEmpty())
				{
					confirmBtnItem.movetoclick();
					LogWriter.writeToFile("Confirmed copy.");
				}
			}
			else
			{
				cancelBtnItem = new WebItem(By.xpath("//input[@id='btnCancelCopyToAll']"),confirmPanelItem);
				if(!cancelBtnItem.isEmpty())
				{
					cancelBtnItem.movetoclick();
					LogWriter.writeToFile("Canceled copy.");
				}
			}
		}

	}
	
	public void confirmOK()
	{
		WebItem oKButtonItem;
		
		oKButtonItem = new WebItem(By.id("OKButton1"));
		
		if(!oKButtonItem.isEmpty())
		{
			oKButtonItem.movetoclick();
		}
	}
	
	public void confirmError()
	{
		WebItem errorButtonItem;
		errorButtonItem = new WebItem(By.id("btnCancelCopyToAll"));
		if(!errorButtonItem.isEmpty())
		{
			errorButtonItem.movetoclick();
		}
	}
	
	public void confirmBack() 
	{
		// TODO Auto-generated method stub
		WebItem backBtnItem;
		backBtnItem = new WebItem(By.xpath("//a[@id='btnBack']"));
		
		if(!backBtnItem.isEmpty())
		{
			backBtnItem.movetoclick();
			invoke();
		}
	}
	
	public abstract boolean invoke();
	public abstract void initElements();
	public abstract void run();

}
