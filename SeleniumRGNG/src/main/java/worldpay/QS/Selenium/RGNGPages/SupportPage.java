package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class SupportPage extends WebPage {

	public By webPageId = By.xpath("//h2[contains(text(), 'Support')]");
	
	public WebItemList subNavList;
	public WebItem supportContentPanel;
	@Override
	public boolean invoke() {
		MainPage mainpage = new MainPage();
		
		if(mainpage.invoke())
		{
			mainpage.initElements();

			mainpage.support.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Support page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Support page start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean invoke(String merchantid, String username, String password, String url){
		
		MainPage mainpage = new MainPage();

		if(mainpage.invoke(merchantid,username, password, url))
		{
			mainpage.initElements();
			mainpage.support.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Support page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Support page start failed.");
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
		subNavList = new WebItemList(By.xpath("//ul[@id='Categories']/li/a"));
		supportContentPanel = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_panelSearch']"));
	}
	
	public String getSection(int index)
	{
		String sectionName = "";
		initElements();
		
		WebItem sectionItem;

		if(!subNavList.isEmpty())
		{
			sectionItem = subNavList.getListElement(index);
			
			
			if(sectionItem!=null)
			{
				sectionName = sectionItem.item.getText();
				sectionItem.movetoclick();
				LogWriter.writeToFile("Select section index-"+index+" name-"+sectionName+".");
				
				confirmBack();
				
				return sectionName;
			}
			else 
			{
				LogWriter.writeToFile("Select section index-"+index+" failed. Get section item failed.");
				return "";
			}
				
		}
		else
		{
			LogWriter.writeToFile("Select section index-"+index+" failed. Section list is empty.");
			return "";
		}
	}
	
	public String getSection(String sectionName)
	{

		initElements();
		
		WebItem sectionItem;
		
		if(!subNavList.isEmpty())
		{
			sectionItem = subNavList.getListElement(sectionName);
			
			if(sectionItem!=null)
			{
				sectionName = sectionItem.item.getText();
				sectionItem.movetoclick();
				LogWriter.writeToFile("Select section name-"+sectionName+".");
				confirmBack();
				return sectionName;
			}
			else 
			{
				LogWriter.writeToFile("Select section name-"+sectionName+" failed. Get section item failed.");
				return "";
			}
				
		}
		else
		{
			LogWriter.writeToFile("Select section name-"+sectionName+" failed. Section list is empty.");
			return "";
		}
	}
	
	public void iterateSection()
	{
		initElements();
		for(int ii=0; ii<subNavList.size(); ii++)
		{
			getSection(ii);
			
			try {
				Thread.sleep(2000);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}
		}
	}

	public void iterateReferece()
	{
		getSection("Reference");
		initElements();
		
		if(!supportContentPanel.isEmpty())
		{
			WebItemList referenceList = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_header']/span"));
			
			if(!referenceList.isEmpty())
			{
				iterateRefSection(referenceList);
			}
			else 
			{
				LogWriter.writeToFile("Check Reference Section failed. Reference list is NOT found.");
			}
		}
		else 
		{
			LogWriter.writeToFile("Check Reference Section failed. Support content is NOT found.");
		}
	}
	
	public void searchReferece(String searchValue)
	{
		WebItem searchTxt;
		initElements();
		
		searchTxt=new WebItem(By.id("ContentPlaceHolder1_tbSearch"));

		if(!searchTxt.isEmpty())
		{
			searchTxt.sendKeys(searchValue);
		}
		else
		{
			LogWriter.writeToFile("Search Reference failed. Search Textbox is NOT found.");
		}

	}
	
	public void searchRefereceDirect(String tabName, String searchValue)
	{
		WebItem searchTxt;
		getSection("Reference");
		initElements();
		
		if(!supportContentPanel.isEmpty())
		{
			WebItemList referenceList = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_header']/span"));
			
			if(!referenceList.isEmpty())
			{
				getRefSection(tabName, referenceList);
				
				searchTxt=new WebItem(By.id("ContentPlaceHolder1_tbSearch"));
				
				if(!searchTxt.isEmpty())
				{
					switch (tabName) 
					{
					case "Message Codes":
						searchReferece(Config.CONFIG.getRefSearchMC());
						break;
					case "State/Region Codes":
						searchReferece(Config.CONFIG.getRefSearchSRC());
						break;
					case "Country Codes":
						searchReferece(Config.CONFIG.getRefSearchCC());
						break;
					case "Currency Codes":
						searchReferece(Config.CONFIG.getRefSearchCRC());
						break;
					default:
						break;
					}
				}
				else
				{
					LogWriter.writeToFile("Search Reference failed. Search Textbox is NOT found.");
				}
					
			}
			else 
			{
				LogWriter.writeToFile("Search Reference failed. Reference list is NOT found.");
			}
		}
		else 
		{
			LogWriter.writeToFile("Search Reference failed. Support content is NOT found.");
		}

	}
	
	public String getRefSection(int index, WebItemList referencelist)
	{
		String refSectionName = "";
		initElements();
		
		WebItem sectionItem;
		
		if(!referencelist.isEmpty())
		{
			sectionItem = referencelist.getListElement(index);

			if(sectionItem!=null)
			{
				refSectionName = sectionItem.item.getText();
				sectionItem.movetoclick();
				LogWriter.writeToFile("Select reference section index-"+index+" name-"+refSectionName+".");
				
				return refSectionName;
			}
			else 
			{
				LogWriter.writeToFile("Select reference section index-"+index+" failed. Get section item failed.");
				return "";
			}	
		}
		else
		{
			LogWriter.writeToFile("Select reference section index-"+index+" failed. Section list is empty.");
			return "";
		}
	}
	
	public String getRefSection(String sectionName,  WebItemList referencelist)
	{

		initElements();
		
		WebItem sectionItem;
		
		if(!referencelist.isEmpty())
		{
			sectionItem = referencelist.getListElement(sectionName);
			
			if(sectionItem!=null)
			{
				sectionName = sectionItem.item.getText();
				sectionItem.movetoclick();
				LogWriter.writeToFile("Select reference section name-"+sectionName+".");
				
				return sectionName;
			}
			else 
			{
				LogWriter.writeToFile("Select reference section name-"+sectionName+" failed. Get section item failed.");
				return "";
			}
				
		}
		else
		{
			LogWriter.writeToFile("Select reference section name-"+sectionName+" failed. Section list is empty.");
			return "";
		}
	}
	
	public void iterateRefSection(WebItemList referencelist)
	{
		String tabNameString;
		initElements();
		for(int ii=0; ii<referencelist.size(); ii++)
		{
			tabNameString = getRefSection(ii, referencelist);
			
			if(tabNameString!=null&&tabNameString!="")
			{
				switch (tabNameString) {
				case "Message Codes":
					searchReferece(Config.CONFIG.getRefSearchMC());
					break;
				case "State/Region Codes":
					searchReferece(Config.CONFIG.getRefSearchSRC());
					break;
				case "Country Codes":
					searchReferece(Config.CONFIG.getRefSearchCC());
					break;
				case "Currency Codes":
					searchReferece(Config.CONFIG.getRefSearchCRC());
					break;
				default:
					break;
				}
			}			
			
			try {
				Thread.sleep(2000);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}
		}
	}
	
	
	public void checkAuditLog() 
	{
		getSection("Audit Log");
		initElements();
		
		if(!supportContentPanel.isEmpty())
		{
			addFilterAuditLog("RG Profile");
			deletelastFilterAuditLog() ;
			
		}
		else 
		{
			LogWriter.writeToFile("Check Reference Section failed. Support content is NOT found.");
		}

	}
	
	public void addFilterAuditLog(String filterType) 
	{
		WebItem addfilterBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnAddFilter']"));
		WebItem searchBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnValidateTrigger']")); 
		WebItemList filterList; 
		WebItem filterItem;
		
		WebItem filterDDLType;
		WebItem filterDDLValue;
		WebItemList filterDDLValueOptions;
		
		initElements();
		if(!addfilterBtn.isEmpty()&&!searchBtn.isEmpty())
		{
			addfilterBtn.movetoclick();
			
			filterList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_gvFilters']//tr"));
			if(!filterList.isEmpty())
			{
				filterItem = filterList.getListLastElement();
				
				filterDDLType = new WebItem(By.xpath("//td[1]/select[@class='searchFields']"), filterItem);
				//filterDDLValue = new WebItem(By.xpath("//td[3]/select"), filterItem);
				
				if(!filterDDLType.isEmpty())
				{
					filterDDLType.select(filterType);
					
					filterList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_gvFilters']"));
					
					filterItem = filterList.getListLastElement();
					filterDDLValue = new WebItem(By.xpath("//td[3]/select"), filterItem);
					filterDDLValueOptions = new WebItemList(By.xpath("//td[3]/select/option"), filterItem);
					
					if(!filterDDLValue.isEmpty()&&!filterDDLValueOptions.isEmpty())
					{
						filterDDLValue.select(0);
						searchBtn= new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnValidateTrigger']"));
						searchBtn.movetoclick();

						confirmBack();
					}
					else
					{
						LogWriter.writeToFile("Check Audit Log Section failed. Date filter value ddl is NOT found.");
					}
					
				}
				else
				{
					LogWriter.writeToFile("Check Audit Log Section failed. Date filter type ddl is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Check Audit Log Section failed. Filter list is NOT found.");
			}
			
		}
		else
		{
			LogWriter.writeToFile("Check Audit Log Section failed. Filter btn or search btn is NOT found.");
		}

	}
	
	public void deletefirstFilterAuditLog() 
	{
		WebItemList filterList; 
		WebItem filterItem;
		WebItem filterDeleteItem;
		
		initElements();
		filterList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_gvFilters']"));
		if(!filterList.isEmpty())
		{
			filterItem = filterList.getListFirstElement();
			
			filterDeleteItem = new WebItem(By.xpath("//td/a[@class='btnRemoveFilter']"), filterItem);
			
			if(!filterDeleteItem.isEmpty())
			{
				filterDeleteItem.movetoclick();
				LogWriter.writeToFile("Delete Audit Log first filter.");
			}
			else
			{
				LogWriter.writeToFile("Delete Audit Log first filter failed. Delete btn is NOT found.");
			}
		}
		else
		{
			LogWriter.writeToFile("Delete Audit Log first filter failed. Filter list is NOT found.");
		}
	}
	
	public void deletelastFilterAuditLog() 
	{
		WebItemList filterList; 
		WebItem filterItem;
		WebItem filterDeleteItem;
		
		initElements();
		filterList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_gvFilters']"));
		if(!filterList.isEmpty())
		{
			filterItem = filterList.getListLastElement();
			
			filterDeleteItem = new WebItem(By.xpath("//td/a[@class='btnRemoveFilter']"), filterItem);
			
			if(!filterDeleteItem.isEmpty())
			{
				filterDeleteItem.movetoclick();
				LogWriter.writeToFile("Delete Audit Log last filter.");
			}
			else
			{
				LogWriter.writeToFile("Delete Audit Log last filter failed. Delete btn is NOT found.");
			}
		}
		else
		{
			LogWriter.writeToFile("Delete Audit Log last filter failed. Filter list is NOT found.");
		}
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		iterateSection();
		LogWriter.writeToFile("'Iterate all Support sections' finished in Support Page.");
		
		iterateReferece();
		LogWriter.writeToFile("'Iterate all Support-Reference Sections' finished in Support Page.");
		
		checkAuditLog();
		LogWriter.writeToFile("'Check Audit Log section' finished in Support Page.");
		
	}

}
