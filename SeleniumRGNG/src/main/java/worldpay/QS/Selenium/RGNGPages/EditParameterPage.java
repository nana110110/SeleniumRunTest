package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;
import worldpay.QS.Selenium.util.Utility;

public class EditParameterPage extends WebPage {
	
	public By webPageId = By.xpath("//div[@id='ContentPlaceHolder1_pnPopModify']");
	
	public WebItem searchTextbox;
	public WebItem searchBtn;
	public WebItem factorDetailTable;
	public WebItem factorInsertRow;
	public WebItem factorInsertDesp;
	public WebItem factorInsertValue;
	public WebItem factorInsertScore;
	public WebItem factorInsertScore_alt;
	public WebItem factorInsertAddbtn;
	public WebItem closebtnItem;
	
	
	public WebItemList factorDetailList;
	
	@Override
 	public boolean invoke() {

		if(exists(10, webPageId))
		{
			LogWriter.writeToFile("Parameter edit popup started.");
			initElements();
			return true;
		}
		else 
		{
			LogWriter.writeToFile("Parameter edit popup start failed.");
			return false;
		}

	}
	
	public boolean invoke(String sectionName, String paraName) {
		RiskProfilePage rskprofilepage = new RiskProfilePage();
		
		if(rskprofilepage.invoke())
		{
			rskprofilepage.initElements();

			rskprofilepage.editParameter(sectionName, paraName);
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Parameter edit popup started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Parameter edit popup start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean invoke(String merchantid, String username, String password, String url, String sectionName, String paraName) {
		RiskProfilePage rskprofilepage = new RiskProfilePage();

		if(rskprofilepage.invoke(merchantid,username, password, url))
		{
			rskprofilepage.initElements();

			rskprofilepage.editParameter(sectionName, paraName);
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Parameter edit popup started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Parameter edit popup start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	

	@Override
	public void initElements() {
		// TODO Auto-generated method stub
		searchTextbox = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tbSearch']"));
		searchBtn = new WebItem(By.xpath("//a[@class='searchButton']"));
		factorDetailTable = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_pnlFactorDetails']/table"));
		
		factorInsertRow = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']"));
		
		factorInsertDesp = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']/td[1]/input"));
		factorInsertValue  = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']/td[2]/input"));
		factorInsertScore  = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']/td[3]//input[@class='slider irs-hidden-input']"));
		factorInsertScore_alt = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']/td[3]//span[@class='irs-single thumb']"));
		factorInsertAddbtn  = new WebItem(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider insertRow bound']/td[4]/a[@class='btnAddFilter']"));
		
		factorDetailList = new WebItemList(By.xpath("//table[@class='gridview factorDetails']/tbody/tr[@class='flex-container popupSlider bound']"));
		
		closebtnItem = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnClose']"));		
	}
	
	//search
	public void searchParameter(String searchValue)
	{
		try{
			initElements();
	
			if(!searchTextbox.isEmpty()&&!searchBtn.isEmpty())
			{
				searchTextbox.sendKeys(searchValue);
				searchBtn.movetoclick();
				
				LogWriter.writeToFile("Search "+searchValue+" in edit popup.");
			}
			else
			{
				LogWriter.writeToFile("Search "+searchValue+" in edit popup failed. Search TextBox or btn is NOT found.");
			}
			
			Thread.sleep(2000);	
			
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Search Parameter-"+searchValue+" in edit popup.");
		}
	}
	
	
	public void addNewFactor(String desp, String value, int score) 
	{
		try{
			String surfixString = String.valueOf(Utility.getRandomInt(1, 9999));
			
			String despString="";
			String valueString="";
			
			despString = desp+surfixString;
			valueString = despString+value;
					
			initElements();
	
			if(!factorInsertDesp.isEmpty()&&!factorInsertValue.isEmpty()&&!factorInsertScore.isEmpty()&&!factorInsertScore_alt.isEmpty())
			{
				factorInsertDesp.sendKeys(despString);
				factorInsertValue.sendKeys(valueString);
				factorInsertScore.changeattribute("value", String.valueOf(score));
				factorInsertScore_alt.changeinnerHTML(String.valueOf(score));
				
				if(!factorInsertAddbtn.isEmpty())
				{
					factorInsertAddbtn.movetoclick();
					
					confirmOK();
					LogWriter.writeToFile("Add New Factor desp-"+despString+", value-"+valueString+", score-"+score+" in edit popup.");

					
				}
				else
				{
					LogWriter.writeToFile("Add New Factor in edit popup failed. Add btn is NOT found");
				}
			}
			else
			{
				LogWriter.writeToFile("Add New Factor in edit popup failed. Description tb or Value tb or Score input is NOT found");
			}
			Thread.sleep(2000);	
			
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Add New Factor in edit popup.");
		}
	}
	
	public void changeFactorScore(int index, int score) 
	{
		try{
			
			WebItem rowItem;
			WebItem scoreItem;
			WebItem scoreItem_alt;
			WebItem saveBtnItem;
			WebItem saveDivItem;
			initElements();

			if(!factorDetailList.isEmpty())
			{
				
				rowItem = factorDetailList.getListElement(index);
				
				if(!rowItem.isEmpty())
				{
					saveDivItem = new WebItem(By.xpath("td[4]/div[@class='divSave']"), rowItem);
					scoreItem = new WebItem(By.xpath("td[3]//input[@class='slider irs-hidden-input']"), rowItem);
					scoreItem_alt = new WebItem(By.xpath("td[3]//span[@class='irs-single thumb']"), rowItem);
					saveBtnItem = new WebItem(By.xpath("td[4]//a[@class='btnSave']"), rowItem);
					
					if(!scoreItem.isEmpty()&&!scoreItem_alt.isEmpty()&&!saveDivItem.isEmpty(false))
					{
						saveDivItem.changeattribute("style","display: block;");
						scoreItem_alt.movetoclick();
						
						scoreItem.changeattribute("value", String.valueOf(score));
						scoreItem_alt.changeinnerHTML(String.valueOf(score));
						
						saveBtnItem.movetoclick();
						
						confirmOK();
						
						LogWriter.writeToFile("Change Factor Score in edit popup index-"+index+", score-"+score+".");
					}
					else
					{
						LogWriter.writeToFile("Change Factor Score in edit popup index-"+index+", score-"+score+" failed. Factor List row score input or save btn is NOT found");
					}
				}
				else 
				{
					LogWriter.writeToFile("Change Factor Score in edit popup index-"+index+", score-"+score+" failed. Factor List row is NOT found");
				}
			}
			else
			{
				LogWriter.writeToFile("Change Factor Score in edit popup index-"+index+", score-"+score+" failed. Factor List is NOT found.");
			}
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change Factor Score in edit popup index-"+index+", score-"+score+".");
		}
	}
	
	public void propagateFactor(int index) 
	{
		try{
			
			WebItem rowItem;
			WebItem propBtnItem;

			initElements();

			if(!factorDetailList.isEmpty())
			{
				
				rowItem = factorDetailList.getListElement(index);
				
				if(!rowItem.isEmpty())
				{
					propBtnItem = new WebItem(By.xpath("td[4]/a[@class='btnCopy']"), rowItem);
					
					if(!propBtnItem.isEmpty())
					{
						propBtnItem.movetoclick();
						LogWriter.writeToFile("Propagate Factor index-"+index+" in edit popup.");
						confirmPosp(true);
						
					}
					else
					{
						LogWriter.writeToFile("Propagate Factor index-"+index+" in edit popup failed. Propagate btn is NOT found.");
					}
				}
				else 
				{
					LogWriter.writeToFile("Propagate Factor index-"+index+" in edit popup failed. Factor List row is NOT found");
				}
			}
			else
			{
				LogWriter.writeToFile("Propagate Factor index-"+index+" in edit popup failed. Factor List is NOT found.");
			}
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Propagate Factor in edit popup.");
		}
	}

	public void confirmPosp(Boolean isSub)
	{
		// TODO Auto-generated method stub
		WebItem confirmPanelItem = new WebItem(By.xpath("//div[@class='ModalPopupConfirmation confirmCopy']"));
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
					LogWriter.writeToFile("Confirmed pospagation.");
					
					confirmPanelItem = new WebItem(By.xpath("//input[@id='btnCancelCopyToAll']"));
					
					if(!confirmPanelItem.isEmpty())
						confirmPanelItem.click();
				}
			}
			else
			{
				cancelBtnItem = new WebItem(By.xpath("//input[@id='btnCancelCopyToAll']"),confirmPanelItem);
				if(!cancelBtnItem.isEmpty())
				{
					cancelBtnItem.movetoclick();
					LogWriter.writeToFile("Canceled pospagation.");
				}
			}
		}

	}
	

	public void closePopup() {
		if(!closebtnItem.isEmpty())
		{
			closebtnItem.movetoclick();
			confirmLeave(true);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		searchParameter(Config.CONFIG.getEditPopupSearchValue());
		LogWriter.writeToFile("Search parameter factor -"+Config.CONFIG.getEditPopupSearchValue()
				+" finished for Risk Profile Page Edit popup.");
		
		
		addNewFactor(Config.CONFIG.getEditPopupAddDesp(), Config.CONFIG.getEditPopupAddValue(), Config.CONFIG.getEditPopupAddScore());
		LogWriter.writeToFile("Add new parameter factor Desp-"+Config.CONFIG.getEditPopupAddDesp()
				+", Value-"+Config.CONFIG.getEditPopupAddValue()
				+", Score-"+Config.CONFIG.getEditPopupAddScore()
				+" finished for Risk Profile Page Edit popup.");
		
		changeFactorScore(Config.CONFIG.getEditFactorIndex(), Config.CONFIG.getEditFactorScore());
		
		LogWriter.writeToFile("Change parameter factor Score-"+Config.CONFIG.getEditFactorScore()
				+" for factor index-"+Config.CONFIG.getEditFactorIndex()
				+" finished for Risk Profile Page Edit popup.");
		
		propagateFactor(Config.CONFIG.getEditPopupPropIndex());
		
		LogWriter.writeToFile("Propagate parameter factor index-"+Config.CONFIG.getEditFactorIndex()
				+" finished for Risk Profile Page Edit popup.");
		
		closePopup();
		
		LogWriter.writeToFile("Close Edit popup finished for Risk Profile Page Edit popup.");
	}

}
