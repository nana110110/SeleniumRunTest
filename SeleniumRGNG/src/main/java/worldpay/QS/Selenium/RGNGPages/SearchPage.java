package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;

public class SearchPage extends WebPage {

	public By webPageId = By.xpath("//h2[contains(text(), 'Search')]");
	
	public WebItem searchBtn;
	public WebItem addfilterBtn;
	public WebItem searchHeadPanel;
	public WebItemList searchHeadList;
	public WebItem searchBodyPanel;
	public WebItemList filterList;
	
	
	
	@Override
	public boolean invoke() {
		MainPage mainpage = new MainPage();
		
		if(mainpage.invoke())
		{
			mainpage.initElements();
			mainpage.search.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Search page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Search page start failed.");
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
			mainpage.search.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Search page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Search page start failed.");
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
		searchBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnValidateTrigger']"));
		addfilterBtn = new WebItem(By.xpath("//input[@class='btnAddFilter']"));
		searchHeadPanel = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_header']"));
		searchHeadList = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_header']/span"));
		searchBodyPanel = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_body']"));
		filterList = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_tcSearch_body']//table[@class='searchTransaction']/tbody/tr"));
	}

	public void startSearch()
	{
		initElements();
		
		if(!searchBtn.isEmpty())
		{
			searchBtn.movetoclick();
		}
		else
		{
			LogWriter.writeToFile("Search failed. Search btn is NOT found.");
		}
	}
	
	public void addFilterTB(String filterName, int ConIndex, String filterValue) //This is for textbox filter. filtertype ddl=dropdownlist, tx=textbox, cld = calendar
	{

		WebItem filterListRow;
		
		WebItem filterNameItem;
		WebItem filterConItem;
		WebItem filterValueItem;

		initElements();
		
		if(!addfilterBtn.isEmpty())
		{
			addfilterBtn.movetoclick();
			
			initElements();

			if(!filterList.isEmpty())
			{
				filterListRow = filterList.getListLastElement();
				filterNameItem = new WebItem(By.xpath("td[1]/select[@class='searchFields']"), filterListRow);
			
				if(!filterNameItem.isEmpty())
				{
					filterNameItem.select(filterName);
					
					initElements();
					
					filterListRow = filterList.getListLastElement();
					filterConItem = new WebItem(By.xpath("td[2]/select"), filterListRow);
					
					if(!filterConItem.isEmpty())
					{
						filterConItem.select(ConIndex);
						
						initElements();
						
						filterListRow = filterList.getListLastElement();
						filterValueItem = new WebItem(By.xpath("td[3]/input"), filterListRow);
						
						if(!filterValueItem.isEmpty())
						{
							filterValueItem.sendKeys(filterValue);
							searchHeadPanel.movetoclick();
						}
						else 
						{
							LogWriter.writeToFile("Add filter failed. Filter Value Textbox is NOT found.");
						}
					}
					else
					{
						LogWriter.writeToFile("Add filter failed. Filter Con ddl is NOT found.");
					}
					
				}
				else
				{
					LogWriter.writeToFile("Add filter failed. Filter Name ddl is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add filter failed. Filter list is Not found.");
			}
			
		}
		else
		{
			LogWriter.writeToFile("Add filter failed. Add filter btn is Not found.");
		}

	}

	
	public void deleteFilter(int index)
	{
		WebItem filterListRow;
		WebItem deleteBtn;
		initElements();
		
		if(index < filterList.size())
		{
			filterListRow = filterList.getListElement(index);
			
			if(!filterListRow.isEmpty())
			{
				deleteBtn=new WebItem(By.xpath("td/a[@class='btnRemoveFilter']"),filterListRow);
				
				if(!deleteBtn.isEmpty())
				{
					deleteBtn.movetoclick();
					initElements();
					searchHeadPanel.movetoclick();
					LogWriter.writeToFile("Delete filter index-"+index+".");
				}
				else 
				{
					LogWriter.writeToFile("Delete filter index-"+index+" failed. Delete btn is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Delete filter index-"+index+" failed. Row is NOT found.");
			}
			
		}
		else 
		{
			LogWriter.writeToFile("Delete filter index-"+index+" failed. Row index to find is TOO BIG.");
		}
	}//This is for textbox filter. filtertype ddl=dropdownlist, tx=textbox, cld = calendar
	
	public void deleteLastFilter()
	{
		WebItem filterListRow;
		WebItem deleteBtn;
		initElements();
		
		if(filterList.size()>0)
		{
			filterListRow = filterList.getListLastElement();
			
			if(!filterListRow.isEmpty())
			{
				deleteBtn=new WebItem(By.xpath("td/a[@class='btnRemoveFilter']"),filterListRow);
				
				if(!deleteBtn.isEmpty())
				{
					deleteBtn.movetoclick();
					initElements();
					searchHeadPanel.movetoclick();
					LogWriter.writeToFile("Delete last filter.");
				}
				else 
				{
					LogWriter.writeToFile("Delete last filter failed. Delete btn is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Delete last filter failed. Last Row is NOT found.");
			}
			
		}
		else 
		{
			LogWriter.writeToFile("Delete last filter failed. filterList is empty.");
		}
	}//This is for text
	
	public void deleteFirstFilter()
	{
		WebItem filterListRow;
		WebItem deleteBtn;
		
		initElements();
		
		if(filterList.size()>0)
		{
			filterListRow = filterList.getListFirstElement();
			
			if(!filterListRow.isEmpty())
			{
				deleteBtn=new WebItem(By.xpath("td/a[@class='btnRemoveFilter']"),filterListRow);
				
				if(!deleteBtn.isEmpty())
				{
					deleteBtn.movetoclick();
					initElements();
					searchHeadPanel.movetoclick();
					LogWriter.writeToFile("Delete first last filter.");
				}
				else 
				{
					LogWriter.writeToFile("Delete first filter failed. Delete btn is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Delete first filter failed. Last Row is NOT found.");
			}
			
		}
		else 
		{
			LogWriter.writeToFile("Delete first filter failed. filterList is empty.");
		}
	}//This is for tex
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		startSearch();
		LogWriter.writeToFile("Search started in Search Page.");
		
		addFilterTB("RG Score",2, "90");
		addFilterTB("RG Score",2, "80");
		addFilterTB("RG Score",2, "70");
		
		LogWriter.writeToFile("Add filters in Search Page.");
		
		deleteFirstFilter();
		LogWriter.writeToFile("Delete first filter in Search Page.");
		
		deleteLastFilter();
		LogWriter.writeToFile("Delete last filter in Search Page.");
		startSearch();
		LogWriter.writeToFile("Search started in Search Page.");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
