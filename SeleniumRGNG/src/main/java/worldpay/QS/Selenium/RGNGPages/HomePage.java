package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class HomePage extends WebPage{


	public By webPageId = By.xpath("//a[contains(text(), 'Add New Chart')]");
	
	public WebItem addNewChart_btn;
	public WebItemList chartList;
	
	
	public WebItem TimeZone;
	public WebItem Language;
	
	public WebItem TimeZoneDDL;
	public WebItem LanguageDDL;
	

	@Override
	public void initElements() {
		addNewChart_btn = new WebItem(By.xpath("//a[contains(text(), 'Add New Chart')]"));
		chartList = new WebItemList(By.xpath("//div[@class='charts']/div/span/span"));
		
		TimeZone = 	    new WebItem(By.xpath("//div[@class='merchantUISettings']/a[@id='btnTimeZone']"));
		Language = 		new WebItem(By.xpath("//div[@class='merchantUISettings']/a[@id='btnLang']"));
		
		TimeZoneDDL =   new WebItem(By.xpath("//div[@class='merchantUISettings']/select[@id='ddTimeZone']"));
		LanguageDDL = 	new WebItem(By.xpath("//div[@class='merchantUISettings']/select[@id='ddLang']"));
	}
	
	@Override
	public boolean invoke()
	{
		MainPage mainpage = new MainPage();
		
		if(mainpage.invoke())
		{
			mainpage.initElements();
			mainpage.home.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Home page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Home page start failed.");
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
			mainpage.home.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Home page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Home page start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public WebItem getFirstChart()
	{
		initElements();
		if(!chartList.isEmpty() && chartList.size()>0)
		{
			WebItem firstChart =chartList.getListFirstElement(); 
			
			if(firstChart==null)
			{
				LogWriter.writeToFile("Home page get 1st chart in the chart list failed");
			}
			
			return firstChart;
		}
		else
		{
			LogWriter.writeToFile("Home page chartList is empty.");
			return null;
		}
	}
	
	public WebItem getLastChart()
	{
		initElements();
		if(!chartList.isEmpty() && chartList.size()>0)
		{
			WebItem LastChart =chartList.getListLastElement(); 
			
			if(LastChart==null)
			{
				LogWriter.writeToFile("Home page get last chart in the chart list failed");
			}
	
			return LastChart;
		}
		else
		{
			LogWriter.writeToFile("Home page chartList is empty.");
			return null;
		}
	}
	
	public void addChart()
	{
		initElements();
		if(addNewChart_btn!=null)
		{
			addNewChart_btn.click();
			LogWriter.writeToFile("Home page add chart button found and clicked.");
		}
		else 
		{
			LogWriter.writeToFile("Home page add chart button NOT found.");
		}
	}
	
	public void deleteChart(WebItem chartElement)
	{
		initElements();
		WebItem deleteButton = new WebItem(By.xpath("//div[@class='chartsBar']//a[@class='btnRemoveChart']"), chartElement);
		
		if(!deleteButton.isEmpty())
		{
			deleteButton.movetoclick();
			LogWriter.writeToFile("Delete button is found and clicked to delete the chart.");
		}
		else
		{
			LogWriter.writeToFile("Delete button is NOT found, selected chart CANNOT be deleted.");
		}
	}
	
	public void iterateChartDescription(WebItem chartElement)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Chart')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Chart')]/following-sibling::select/option"), chartElement);
		
		//ArrayList<String> textlistArrayList = ChartDescriptionOptions.getTextList();
		
		for(int i=0; i<ChartDescriptionOptions.items.size();i++)
		{
			ChartDescription.select(i);
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	
	public void iterateProfileName(WebItem chartElement)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Profile Name')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Profile Name')]/following-sibling::select/option"), chartElement);
		
		//ArrayList<String> textlistArrayList = ChartDescriptionOptions.getTextList();
		
		for(int i=0; i<ChartDescriptionOptions.items.size();i++)
		{
			ChartDescription.select(i);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void iteratePeriodCoverd(WebItem chartElement)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Period Covered')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Period Covered')]/following-sibling::select/option"), chartElement);
		
		//ArrayList<String> textlistArrayList = ChartDescriptionOptions.getTextList();
		
		for(int i=0; i<ChartDescriptionOptions.items.size();i++)
		{
			ChartDescription.select(i);
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void iterateChartType(WebItem chartElement)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Type')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Type')]/following-sibling::select/option"), chartElement);
		
		//ArrayList<String> textlistArrayList = ChartDescriptionOptions.getTextList();
		
		for(int i=0; i<ChartDescriptionOptions.items.size();i++)
		{
			ChartDescription.select(i);
					
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getChartDescriptionElement(WebItem chartElement, int index)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Chart')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Chart')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty()&&!ChartDescription.isEmpty())
		{

			ChartDescription.select(index);
			LogWriter.writeToFile("Select ChartDescription ddl index-"+index+".");
			return ChartDescriptionOptions.getText(index);
		}
		else 
		{
			LogWriter.writeToFile("Select ChartDescription ddl index-"+index+" failed. Get dropdownlist failed.");
			return "";
		}

	}
	
	public String getProfileNameElement(WebItem chartElement, int index)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Profile Name')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Profile Name')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty()&&!ChartDescription.isEmpty())
		{
			ChartDescription.select(index);
			LogWriter.writeToFile("Select ProfileName ddl index-"+index+".");
			return ChartDescriptionOptions.getText(index);
		}
		else 
		{
			LogWriter.writeToFile("Select ProfileName ddl index-"+index+" failed. Get dropdownlist failed.");
			return "";
		}
	}
	
	public String getPeriodCoverdElement(WebItem chartElement, int index)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Period Covered')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Period Covered')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty()&&!ChartDescription.isEmpty())
		{
			ChartDescription.select(index);
			LogWriter.writeToFile("Select PeriodCoverd ddl index-"+index+".");
			return ChartDescriptionOptions.getText(index);
		}
		else 
		{
			LogWriter.writeToFile("Select PeriodCoverd ddl index-"+index+" failed. Get dropdownlist failed.");
			return "";
		}
		
	}
	
	public String getChartTypeElement(WebItem chartElement, int index)
	{
		initElements();
		WebItem ChartDescription = new WebItem(By.xpath("//span[contains(text(),'Type')]/following-sibling::select"), chartElement);
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Type')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty()&&!ChartDescription.isEmpty())
		{
			ChartDescription.select(index);
			LogWriter.writeToFile("Select ChartType ddl index-"+index+".");
			return ChartDescriptionOptions.getText(index);
		}
		else 
		{
			LogWriter.writeToFile("Select ChartType ddl index-"+index+" failed. Get dropdownlist failed.");
			return "";
		}
		
	}
	
	public int getChartDescriptionCount(WebItem chartElement)
	{
		initElements();
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Chart')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty())
		{
			LogWriter.writeToFile("Select ChartDescription ddl option number "+ChartDescriptionOptions.items.size()+".");
			return ChartDescriptionOptions.items.size();
			
		}
		else
		{
			LogWriter.writeToFile("Select ChartDescription ddl option number failed.");
			return 0;
		}

	}
	
	public int getProfileNameElementCount(WebItem chartElement)
	{
		initElements();
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Profile Name')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty())
		{
			LogWriter.writeToFile("Select ProfileName ddl option number "+ChartDescriptionOptions.items.size()+".");
			return ChartDescriptionOptions.items.size();
			
		}
		else
		{
			LogWriter.writeToFile("Select ProfileName ddl option number failed.");
			return 0;
		}
	}
	
	public int getPeriodCoverdCount(WebItem chartElement)
	{
		initElements();
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Period Covered')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty())
		{
			LogWriter.writeToFile("Select PeriodCoverd ddl option number "+ChartDescriptionOptions.items.size()+".");
			return ChartDescriptionOptions.items.size();
			
		}
		else
		{
			LogWriter.writeToFile("Select PeriodCoverd ddl option number failed.");
			return 0;
		}
	}
	
	
	public int getChartTypeCount(WebItem chartElement)
	{
		initElements();
		WebItemList ChartDescriptionOptions = new WebItemList(By.xpath("//span[contains(text(),'Type')]/following-sibling::select/option"), chartElement);
		
		if(!ChartDescriptionOptions.isEmpty())
		{
			LogWriter.writeToFile("Select ChartType ddl option number "+ChartDescriptionOptions.items.size()+".");
			return ChartDescriptionOptions.items.size();
			
		}
		else
		{
			LogWriter.writeToFile("Select ChartType ddl option number failed.");
			return 0;
		}
	}
	
	
	public void iterateChart(WebItem chartElement)
	{
		initElements();
		String ChartDescription="";
		String ProfileName="";
		String PeriodCoverd="";
		String ChartType="";
		
		int nChartDesp 		= Math.min(Config.CONFIG.getChartDespDDLCount(), getChartDescriptionCount(chartElement));
		int nProfileName 	= Math.min(Config.CONFIG.getProfileNameDDLCount(), getProfileNameElementCount(chartElement));
		int nPeriodCoverd 	= Math.min(Config.CONFIG.getPeriodCoveredCount(), getPeriodCoverdCount(chartElement));
		int nChartType 		= Math.min(Config.CONFIG.getChartTypeCount(), getChartTypeCount(chartElement));
		
		for(int ii=0; ii<nChartDesp;ii++)
		{
			ChartDescription = getChartDescriptionElement(chartElement, ii);
			
			for(int jj=0; jj<nProfileName;jj++)
			{
				ProfileName= getProfileNameElement(chartElement, jj);
				
				for(int mm=0; mm<nPeriodCoverd; mm++)
				{
					PeriodCoverd = getPeriodCoverdElement(chartElement, mm);
					
					for(int nn=0; nn<nChartType ; nn++)
					{
						ChartType = getChartTypeElement(chartElement, nn);
						
						LogWriter.writeToFile("Chart option: "+ChartDescription+": "+ProfileName+": "+PeriodCoverd+": "+ChartType+".");
						
						try {
							Thread.sleep(2000);
						} 
						catch (Exception e) 
						{
							// TODO: handle exception
						}
					}
				}
			}
		}
	}

	public void checkTimeZoneDDL()
	{
		initElements();
		WebItemList timezoneOptions;
		int nCount = Config.CONFIG.getTimeZoneDDLCount();
		
		if(!TimeZone.isEmpty())
		{
			TimeZone.movetoclick();
			timezoneOptions = new WebItemList(By.xpath("//select[@id='ddTimeZone']/option"));
			
			if(!timezoneOptions.isEmpty())
			{
				if(timezoneOptions.size()<nCount)
					nCount = timezoneOptions.size();
			
				for(int ii = 0; ii<nCount; ii++)
				{
					initElements();
					TimeZoneDDL.select(ii);
					LogWriter.writeToFile("Home page select timezone index-"+ii+".");
					
					
					initElements();
					Language.movetoclick();
					initElements();
					
					if(!TimeZone.isEmpty())
						TimeZone.movetoclick();
					else if(!TimeZoneDDL.isEmpty())
						TimeZoneDDL.movetoclick();
					
				}
			}
			else
			{
				LogWriter.writeToFile("Home page timezone ddl NOT found");
			}
			
		}
		else
		{
			LogWriter.writeToFile("Home page timezone button NOT found");
		}
	}
	
	public void checkLangDDL()
	{
		initElements();
		WebItemList langOptions;
		int nCount = Config.CONFIG.getLangDDLCount();
		
		if(!Language.isEmpty())
		{
			Language.movetoclick();
			langOptions = new WebItemList(By.xpath("//select[@id='ddLang']/option"));
			
			if(!langOptions.isEmpty())
			{
				if(langOptions.size()<nCount)
					nCount = langOptions.size();
			
				for(int ii = nCount-1; ii>=0; ii--)
				{
					initElements();
					LanguageDDL.select(ii);
					LogWriter.writeToFile("Home page select language index-"+ii+".");
					
					initElements();
					TimeZone.movetoclick();
					initElements();
					
					if(!Language.isEmpty())
						Language.movetoclick();
					else if(!LanguageDDL.isEmpty())
						LanguageDDL.movetoclick();
					//Language.movetoclick();
					
				}
			}
			else
			{
				LogWriter.writeToFile("Home page language ddl NOT found");
			}
			
		}
		else
		{
			LogWriter.writeToFile("Home page language button NOT found");
		}
	}	
	
	public void run()
	{
		try {
			
			checkTimeZoneDDL();
			LogWriter.writeToFile("'Iterate timezone' finished in Home Page.");
			invoke();
			
			checkLangDDL();
			LogWriter.writeToFile("'Iterate language' finished in Home Page.");
			invoke();
		
			if(getFirstChart()!=null)
			{
				iterateChart(getFirstChart());
			}
			LogWriter.writeToFile("'iterate all options in first chart' finished in Home Page.");
			
			addChart();
			LogWriter.writeToFile("'Add new chart' finished in Home Page.");
			
			deleteChart(getLastChart());
			LogWriter.writeToFile("'Delete last chart' finished in Home Page.");
			
		} catch (Exception e) {
			LogWriter.writeToFile("Exception in Home Page run.");
		}
		
	}
	
	
}
