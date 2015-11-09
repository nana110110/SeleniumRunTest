package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;
import worldpay.QS.Selenium.util.Utility;

public class RiskProfilePage extends WebPage{

	public By webPageId = By.xpath("//h2[contains(text(), 'Risk Profile')]");
	//public WebItem Risk Profile
	
	public WebItem profileDrop;
	public WebItemList subNavList;
	public WebItem paraPanelItem;
	public WebItem updateBtn;
	public WebItem searchTextbox;
	public WebItem searchBtn;
	
	public WebItem cr_Content;
	public WebItem cr_ScoreSlider;
	public WebItem cr_ScoreSlider_Alt;
	public WebItem cr_ActiveCB;
	public WebItem cr_UpdateBtn;
	public WebItem cr_SearchTb;
	public WebItem cr_SearchBtn;
	public WebItem cr_ActiveRuleDDL;
	public WebItem cr_AddLORBtn;
	public WebItem cr_AddBtn;
	public WebItemList cr_List;

	
	@Override
	public void initElements() {
		// TODO Auto-generated method stub
		profileDrop = new WebItem(By.id("ContentPlaceHolder1_subMenuRiskProfile_ddProfiles"));
		subNavList = new WebItemList(By.xpath("//ul[@id='Categories']/li/a"));
		paraPanelItem = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_panelFields']/div[@id='ContentPlaceHolder1_udpRiskFactors']"));
		updateBtn = new WebItem(By.xpath("//input[@id='btnSubmit']"));
		searchTextbox = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tbSearchFactors']"));
		searchBtn = new WebItem(By.xpath("//a[@class='searchButton']"));
	}
	
	@Override
	public boolean invoke()
	{
		MainPage mainpage = new MainPage();
		
		if(mainpage.invoke())
		{
			mainpage.initElements();

			mainpage.riskprofile.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Risk Profile page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Risk Profile page start failed.");
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
			mainpage.riskprofile.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("Risk Profile page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("Risk Profile page start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public String getProfile(int index)
	{
		initElements();
		
		if(!profileDrop.isEmpty())
		{
			
			profileDrop.select(index);
			
			confirmLeave(true);

			WebItemList profileDropOptions = new WebItemList(By.xpath("//select[@id='ContentPlaceHolder1_subMenuRiskProfile_ddProfiles']/option"));
			
			if(!profileDropOptions.isEmpty())
			{
				LogWriter.writeToFile("Select profile index-"+index+".");
				return profileDropOptions.getText(index);
			}
			else
			{
				LogWriter.writeToFile("Select profile index-"+index+" failed. Profile ddl is empty.");
				return "";
			}
		}
		else
		{
			LogWriter.writeToFile("Select profile index-"+index+" failed. Profile ddl is empty.");
			return "";
		}
	}
	
	public String getProfile(String profileName)
	{
		try{

			initElements();
			
			if(!profileDrop.isEmpty())
			{
				profileDrop.select(profileName);
				confirmLeave(true);
				
				WebItemList profileDropOptions = new WebItemList(By.xpath("//select[@id='ContentPlaceHolder1_subMenuRiskProfile_ddProfiles']/option"));
				
				if(!profileDropOptions.isEmpty())
				{
					LogWriter.writeToFile("Select profile name-"+profileName);
					return profileName;
				}
				else
				{
					LogWriter.writeToFile("Select profile name-"+profileName+" failed. Profile ddl is empty.");
					return "";
				}
			}
			else
			{
				LogWriter.writeToFile("Select profile name-"+profileName+" failed. Profile ddl is empty.");
				return "";
			}
		
		}
		catch(Exception e)
		{
			LogWriter.writeToFile("Exception happens in Select profile name-"+profileName+".");
			return "";
		}
	}
	
	public void iterateProfile()
	{
		WebItemList profileDropOptions = new WebItemList(By.xpath("//select[@id='ContentPlaceHolder1_subMenuRiskProfile_ddProfiles']/option"));
		
		for(int ii=0; ii<profileDropOptions.size(); ii++)
		{
			getProfile(ii);
			
			try {
				Thread.sleep(2000);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}
		}
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
				confirmLeave(true);
				LogWriter.writeToFile("Select section index-"+index+" name-"+sectionName+".");
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
				confirmLeave(true);
				LogWriter.writeToFile("Select section  name-"+sectionName+".");
				
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

	
	//bulk process of section elements
	public void allParaDeactive(String sectionName)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			WebItemList paraListItem;
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{
				paraListItem = new WebItemList(By.xpath("//table[@class='riskFactors']/tbody/tr[@class='flex-container bound']"), paraPanelItem);
				
				if(!paraListItem.isEmpty())
				{
					for(int ii=0; ii<paraListItem.size();ii++)
					{
						rowItem = getParameter(ii);
						
						changeParameterActive(rowItem, false);
						getParameterHelp(rowItem);
						
						LogWriter.writeToFile("Deactiveate Parameter in Section-"+sectionName+", index-"+ii);
					}
					
					LogWriter.writeToFile("Deactiveate all Parameter in Section-"+sectionName+".");
					
					saveChange();
				}
				else
				{
					LogWriter.writeToFile("Deactiveate all Parameter in Section-"+sectionName+". Parameter list table is NOT found.");
				}
					
			}
			else
			{
				LogWriter.writeToFile("Deactiveate all Parameter in Section-"+sectionName+". Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in 'Deactiveate all Parameter' in Section-"+sectionName+".");
		}
	}
	
	public void setAllParameterScore(String sectionName, int value)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			WebItemList paraListItem;
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{
				paraListItem = new WebItemList(By.xpath("//table[@class='riskFactors']/tbody/tr[@class='flex-container bound']"), paraPanelItem);
				
				if(!paraListItem.isEmpty())
				{
					for(int ii=0; ii<paraListItem.size();ii++)
					{
						//initElements();
						rowItem = getParameter(ii);
						changeParameterActive(rowItem, true);
						changeParameterScore(rowItem, value);
						
						LogWriter.writeToFile("Set Parameter to "+value+" in Section-"+sectionName+", index-"+ii+".");
						
						getParameterHelp(rowItem);

					}
					
					
					LogWriter.writeToFile("Set all Parameter to "+value+" in Section-"+sectionName+".");
					saveChange();
					
				}
				else
				{
					LogWriter.writeToFile("Set all Parameter to"+value+" in Section-"+sectionName+" failed. Parameter list table is NOT found.");
				}
					
			}
			else
			{
				LogWriter.writeToFile("Set all Parameter to"+value+" in Section-"+sectionName+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Set all Parameter to"+value+" in Section-"+sectionName+".");
		}
	}
	
	public void setAllParameterScore(String sectionName)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			WebItemList paraListItem;
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{
				paraListItem = new WebItemList(By.xpath("//table[@class='riskFactors']/tbody/tr[@class='flex-container bound']"), paraPanelItem);
				
				if(!paraListItem.isEmpty())
				{
					for(int ii=0; ii<paraListItem.size();ii++)
					{
						//initElements();
						rowItem = getParameter(ii);

						changeParameterActive(rowItem, true);
						changeParameterScore(rowItem);
						LogWriter.writeToFile("Set Parameter to random value in Section-"+sectionName+", index-"+ii+".");
						getParameterHelp(rowItem);
					}
					
					LogWriter.writeToFile("Set all Parameter to random value in Section-"+sectionName+".");
					
					saveChange();
					
				}
				else
				{
					LogWriter.writeToFile("Set all Parameter to random value in Section-"+sectionName+" failed. Parameter list table is NOT found.");
				}
					
			}
			else
			{
				LogWriter.writeToFile("Set all Parameter to random value in Section-"+sectionName+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Set all Parameter to random value in Section-"+sectionName+".");
		}
	}
	
	//search
	public void searchParameter(String sectionName, String searchValue)
	{
		try{
			initElements();
			getSection(sectionName);
			
			initElements();
			
			if(!searchTextbox.isEmpty()&&!searchBtn.isEmpty())
			{
				searchTextbox.sendKeys(searchValue);
				searchBtn.movetoclick();
				
				LogWriter.writeToFile("Search Parameter-"+searchValue+" in section-"+sectionName+".");
			}
			else
			{
				LogWriter.writeToFile("Search Parameter-"+searchValue+" in section-"+sectionName+" failed. Search TextBox or btn is NOT found.");
			}
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Search Parameter-"+searchValue+" in section-"+sectionName+".");
		}
	}
	
	//change score for parameter:
	public void changeParameterScore(WebItem rowItem, int value)
	{	
		try {
			WebItem paraScore;
			paraScore = new WebItem(By.xpath("td[2]//input[@class='slider irs-hidden-input']"), rowItem);
			
			if(value<=100&&value>=0)
			{
				paraScore.changeattribute("value", String.valueOf(value));
				LogWriter.writeToFile("Change Parameter Score to "+value+".");
			}
			else 
			{
				LogWriter.writeToFile("Change Parameter Score failed. Score value is NOT valid.");
			}
	
			Thread.sleep(2000);	
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter score-"+value+".");
		}
	}
	
	public void changeParameterScore(WebItem rowItem)
	{		
		try{
			WebItem paraScore;
			int value = Utility.getRandomInt(1, 100);
			
			paraScore = new WebItem(By.xpath("td[2]//input[@class='slider irs-hidden-input']"), rowItem);
			
			if(value<=100&&value>=0)
			{
				paraScore.changeattribute("value", String.valueOf(value));
				LogWriter.writeToFile("Change Parameter Score to "+value+".");
			}
			else 
			{
				LogWriter.writeToFile("Change Parameter Score failed. Score value is NOT valid.");
			}
	
			Thread.sleep(2000);	
		}
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter score.");
		}
	}
	
	public void changeParameterScore(String sectionName, String parametername, int value)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(parametername);
				
				if(!rowItem.isEmpty())
				{
					changeParameterActive(rowItem, false);
					getParameterHelp(rowItem);
					saveChange();
					initElements();
					
					changeParameterActive(rowItem, true);
					changeParameterScore(rowItem, value);
					getParameterHelp(rowItem);
	
					
					saveChange();
					LogWriter.writeToFile("Change parameter score: "+sectionName+"-"+parametername+"-"+value+".");
				}
				else 
				{
					LogWriter.writeToFile("Change parameter score: "+sectionName+"-"+parametername+"-"+value+" failed. CANNOT find row item.");
				}

			}
			else
			{
				LogWriter.writeToFile("Change parameter score: "+sectionName+"-"+parametername+"-"+value+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter score: "+sectionName+"-"+parametername+"-"+value+".");
		}
	}
	
	public void changeParameterScore(String sectionName, int index, int value)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(index);
				
				if(!rowItem.isEmpty())
				{
					changeParameterActive(rowItem, false);
					getParameterHelp(rowItem);
					saveChange();
					initElements();
					
					changeParameterActive(rowItem, true);
					changeParameterScore(rowItem, value);
					getParameterHelp(rowItem);
	
					
					saveChange();
					LogWriter.writeToFile("Set parameter value: "+sectionName+"-"+index+"-"+value+".");
				}
				else 
				{
					LogWriter.writeToFile("Set parameter value: "+sectionName+"-"+index+"-"+value+" failed. CANNOT find row item.");
				}

			}
			else
			{
				LogWriter.writeToFile("Set parameter value: "+sectionName+"-"+index+"-"+value+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Set parameter value: "+sectionName+"-"+index+"-"+value+".");
		}
	}

	//change active for parameter:
	public void changeParameterActive(String sectionName, String parametername, Boolean isActive)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(parametername);
				
				if(!rowItem.isEmpty())
				{
					changeParameterActive(rowItem, isActive);
					getParameterHelp(rowItem);
					saveChange();

					LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+parametername+"-"+isActive+".");
				}
				else 
				{
					LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+parametername+"-"+isActive+" failed. CANNOT find row item.");
				}

			}
			else
			{
				LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+parametername+"-"+isActive+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter active: "+sectionName+"-"+parametername+"-"+isActive+".");
		}
	}
	
	public void changeParameterActive(String sectionName, int index, Boolean isActive)
	{
		try {

			initElements();
			getSection(sectionName);
			
			initElements();
			
			WebItem rowItem;
			
			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(index);
				
				if(!rowItem.isEmpty())
				{
					changeParameterActive(rowItem, isActive);
					getParameterHelp(rowItem);
					saveChange();

					LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+index+"-"+isActive+".");
				}
				else 
				{
					LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+index+"-"+isActive+" failed. CANNOT find row item.");
				}

			}
			else
			{
				LogWriter.writeToFile("Change parameter active: "+sectionName+"-"+index+"-"+isActive+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
			}
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter active: "+sectionName+"-"+index+"-"+isActive+".");
		}
	}
	
	public void changeParameterActive(WebItem rowItem, boolean isActive)
	{
		try {

			WebItem paraActive;
			
			paraActive = new WebItem(By.xpath("td[3]/span/input"), rowItem);
			
			if(!paraActive.isEmpty())
			{
				
				if((paraActive.item.isSelected()&&!isActive)||(!paraActive.item.isSelected()&&isActive))
				{
					paraActive.movetoclick();
					confirmOK();
	
				}
				else 
				{
					paraActive.movetoclick();
					
					confirmOK();
					
					paraActive.movetoclick();
					
					confirmOK();
				}
				
				LogWriter.writeToFile("Change Parameter Active to "+isActive+".");
			}
			else
			{
				LogWriter.writeToFile("Change Parameter Active to "+isActive+ " failed. Active checkbox is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter active-"+isActive+".");
		}

	}
	
	public void changeParameterActive(WebItem rowItem)
	{
		try {

			WebItem paraActive;
		
			paraActive = new WebItem(By.xpath("td[3]/span/input"), rowItem);
			
			if(!paraActive.isEmpty())
			{
				paraActive.movetoclick();
				
				confirmOK();
				
				LogWriter.writeToFile("Change Parameter Active.");
			}
			else
			{
				LogWriter.writeToFile("Change Parameter Active failed. Active checkbox is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Change parameter active.");
		}
	}
	
	//get help text for parameter:
	public void getParameterHelp(WebItem rowItem)
	{
		try {

			WebItem paraName;
			paraName = new WebItem(By.xpath("td[1]"), rowItem);
			//System.out.println(paraName.item.getText());
			if(!paraName.isEmpty())
			{
				paraName.movetoclick();
				LogWriter.writeToFile("Get Parameter-"+paraName.item.getText()+" Help.");
			}
			else 
			{
				LogWriter.writeToFile("Get Parameter Help failed. Parameter name is NOT found.");
			}
			
			Thread.sleep(2000);
		} 		
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Get Parameter Help.");
		}
	}
		
	public void getParameterHelp(String sectionName, int index)
	{
		try 
		{
			WebItem rowItem;
			WebItem paraName;
			initElements();
			getSection(sectionName);
			
			initElements();

			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(index);
		
				paraName = new WebItem(By.xpath("td[1]"), rowItem);
				//System.out.println(paraName.item.getText());
				if(!paraName.isEmpty())
				{
					paraName.movetoclick();
					confirmLeave(true);
					LogWriter.writeToFile("Get Parameter-"+index+" Help.");
				}
				else 
				{
					LogWriter.writeToFile("Get Parameter-"+index+" Help failed. Parameter name is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Get Parameter-"+index+" Help failed. Parameter list panel is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Get Parameter-"+index+" Help.");
		}
		
		
	}

	public void getParameterHelp(String sectionName, String parametername)
	{
		try 
		{
			WebItem rowItem;
			WebItem paraName;
			initElements();
			getSection(sectionName);
			
			initElements();

			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(parametername);
		
				paraName = new WebItem(By.xpath("td[1]"), rowItem);
				//System.out.println(paraName.item.getText());
				if(!paraName.isEmpty())
				{
					paraName.movetoclick();
					confirmLeave(true);
					LogWriter.writeToFile("Get Parameter-"+parametername+" Help.");
				}
				else 
				{
					LogWriter.writeToFile("Get Parameter-"+parametername+" Help failed. Parameter name is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Get Parameter-"+parametername+" Help failed. Parameter list panel is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Get Parameter-"+parametername+" Help.");
		}
		
		
	}

	
	//edit parameter:
	public void editParameter(String sectionName, String parametername)
	{
		try 
		{
			WebItem rowItem;
			WebItem paraEdit;
			initElements();
			getSection(sectionName);
			
			initElements();

			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(parametername);
		
				paraEdit = new WebItem(By.xpath("td[4]/a"), rowItem);
				//System.out.println(paraName.item.getText());
				if(!paraEdit.isEmpty())
				{
					paraEdit.movetoclick();
					confirmLeave(true);
					LogWriter.writeToFile("Edit Parameter-"+parametername+".");
					
					editParameterPopup();
				}
				else 
				{
					LogWriter.writeToFile("Edit Parameter-"+parametername+" failed. Parameter name is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Edit Parameter-"+parametername+" failed. Parameter list panel is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Edit Parameter-"+parametername+".");
		}
		

	}
	
	public void editParameter(String sectionName, int index)
	{
		try 
		{
			WebItem rowItem;
			WebItem paraEdit;
			initElements();
			getSection(sectionName);
			
			initElements();

			if(!paraPanelItem.isEmpty())		
			{

				rowItem = getParameter(index);
		
				paraEdit = new WebItem(By.xpath("td[4]/a"), rowItem);
				//System.out.println(paraName.item.getText());
				if(!paraEdit.isEmpty())
				{
					paraEdit.movetoclick();
					confirmLeave(true);
					LogWriter.writeToFile("Edit Parameter-"+index+".");
					
					editParameterPopup();
				}
				else 
				{
					LogWriter.writeToFile("Edit Parameter-"+index+" failed. Parameter name is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Edit Parameter-"+index+" failed. Parameter list panel is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Edit Parameter-"+index+".");
		}
		

	}

	public void editParameter(WebItem rowItem)
	{
		try 
		{
			WebItem paraEdit;
			
			initElements();

			paraEdit = new WebItem(By.xpath("td[4]/a"), rowItem);
			//System.out.println(paraName.item.getText());
			if(!paraEdit.isEmpty())
			{
				paraEdit.movetoclick();
				confirmLeave(true);
				LogWriter.writeToFile("Edit Parameter.");
				
				editParameterPopup();
			}
			else 
			{
				LogWriter.writeToFile("Edit Parameter failed. Parameter name is NOT found.");
			}
			
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			LogWriter.writeToFile("Exception happens in Edit Parameter.");
		}
	}
	
	
	//edit parameter popup panel:
	public void editParameterPopup()
	{
		EditParameterPage editParameterPage = new EditParameterPage();
		
		if(editParameterPage.invoke())
		{
			//editParameterPage.searchParameter(searchValue);
			editParameterPage.run();
		}

	}

	
	//get Parameter row item
	public WebItem getParameter(int index)// not for Profile specific or Custom Rules
	{
		try 
		{
			initElements();
			
			WebItemList paraListItem;
			WebItem paraItem;
			
	
			if(!paraPanelItem.isEmpty())
			{
				
				paraListItem = new WebItemList(By.xpath("//table[@class='riskFactors']/tbody/tr[@class='flex-container bound']"), paraPanelItem);
	
				if(!paraListItem.isEmpty())
				{
					
					paraItem = paraListItem.getListElement(index);
					
					if(!paraItem.isEmpty())
					{
						return paraItem;
					}
					else
					{
						LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter index row are Not found.");
						return null;
					}
				}
				else
				{
					LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter list table is NOT found.");
					return null;
				}
			}
			else
			{
				LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
				return null;
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Select parameter index-"+index+".");
			return null;
		}
	}
	
	public WebItem getParameter(String parameterName)// not for Profile specific or Custom Rules
	{
		try {

			WebItem paraPanelItem;
			WebItemList paraListItem;
			WebItem paraItem;
			initElements();
			
			paraPanelItem = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_panelFields']/div[@id='ContentPlaceHolder1_udpRiskFactors']"));
			if(!paraPanelItem.isEmpty())
			{
				
				paraListItem = new WebItemList(By.xpath("table[@class='riskFactors']/tbody/tr/td[1]"), paraPanelItem);
	
				if(!paraListItem.isEmpty())
				{
					
					paraItem = paraListItem.getListElement(parameterName);
	
					if(!paraItem.isEmpty())
					{
						paraItem=new WebItem( By.xpath(".."), paraItem);
						return paraItem;
					}
					else
					{
						LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter name row are Not found.");
						return null;
					}
				}
				else
				{
					LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter list table is NOT found.");
					return null;
				}
			}
			else
			{
				LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
				return null;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Select parameter name-"+parameterName+".");
			return null;
		}
	}
	
	public WebItem getParameter(String sectionname, int index)// not for Profile specific or Custom Rules
	{
		try {
			initElements();
			
			WebItemList paraListItem;
			WebItem paraItem;
			
			getSection(sectionname);
			initElements();
	
			if(!paraPanelItem.isEmpty())
			{
				
				paraListItem = new WebItemList(By.xpath("//table[@class='riskFactors']/tbody/tr[@class='flex-container bound']"), paraPanelItem);
	
				if(!paraListItem.isEmpty())
				{
					
					paraItem = paraListItem.getListElement(index);
					
					if(!paraItem.isEmpty())
					{
						return paraItem;
					}
					else
					{
						LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter index row are Not found.");
						return null;
					}
				}
				else
				{
					LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter list table is NOT found.");
					return null;
				}
			}
			else
			{
				LogWriter.writeToFile("Select parameter index-"+index+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
				return null;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Select parameter index-"+index+".");
			return null;
		}
	}
	
	public WebItem getParameter(String sectionname, String parameterName) throws Exception// not for Profile specific or Custom Rules
	{
		try {
			
			WebItem paraPanelItem;
			WebItemList paraListItem;
			WebItem paraItem;
			initElements();
			
			getSection(sectionname);
			initElements();
			
			paraPanelItem = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_panelFields']/div[@id='ContentPlaceHolder1_udpRiskFactors']"));
			if(!paraPanelItem.isEmpty())
			{
				
				paraListItem = new WebItemList(By.xpath("table[@class='riskFactors']/tbody/tr/td[1]"), paraPanelItem);
	
				if(!paraListItem.isEmpty())
				{
					paraItem = paraListItem.getListElement(parameterName);
	
					if(!paraItem.isEmpty())
					{
						paraItem=new WebItem( By.xpath(".."), paraItem);
						return paraItem;
					}
					else
					{
						LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter name row are Not found.");
						return null;
					}
				}
				else
				{
					LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter list table is NOT found.");
					return null;
				}
			}
			else
			{
				LogWriter.writeToFile("Select parameter name-"+parameterName+" failed. Parameter list panel is NOT found or current section does not have parameter list panel.");
				return null;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Select parameter name-"+parameterName+".");
			return null;
		}
	}

	
	public void saveChange()
	{
		initElements();
		
		if(!updateBtn.isEmpty())
		{
			updateBtn.movetoclick();
			confirmLeave(true);
			LogWriter.writeToFile("Update section.");
		}
		else
		{
			LogWriter.writeToFile("Update section failed. Update btn is NOT found.");
		}
	}

	public void initCustomerRuleElements() 
	{
		cr_Content = new WebItem(By.xpath("//div[@class='rpPageContent customRule']"));
		cr_ScoreSlider = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//input[@id='ContentPlaceHolder1_tbCustomRuleRisk']"));
		cr_ScoreSlider_Alt = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//span[@class='irs-single thumb']"));
		cr_ActiveCB = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//input[@id='ContentPlaceHolder1_cbCustomRuleActive']"));
		cr_UpdateBtn = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//input[@id='ContentPlaceHolder1_btnUpdate']"));;
		cr_SearchTb = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//div[@class='searchBox']/input[@class='searchTextBox']"));
		cr_SearchBtn = new WebItem(By.xpath("//div[@class='rpPageContent customRule']//div[@class='searchBox']/a[@class='searchButton']"));
		
		cr_List = new WebItemList(By.xpath("//div[@class='customRuleList']//table[@id='ContentPlaceHolder1_gvCustomRuleList']//tr/td[1]"));
		cr_ActiveRuleDDL = new WebItem(By.xpath("//div[@class='customRuleList']//select[@id='ContentPlaceHolder1_ddCustomRuleStatus']"));
		cr_AddLORBtn = new WebItem(By.xpath("//div[@class='customRuleList']//input[@id='ContentPlaceHolder1_btnAddNewLibraryRule']"));
		cr_AddBtn  = new WebItem(By.xpath("//div[@class='customRuleList']//input[@id='ContentPlaceHolder1_btnAddNewCustomRule']"));
	}
	
	public String addCustomerRule()
	{
		String ruleName="";
		
		try {

			getSection("Custom Rules");
			
			initCustomerRuleElements();
			
			if(!cr_AddBtn.isEmpty())
			{
				cr_AddBtn.movetoclick();
				
				EditCustomerRulePage editCustomerRuleLOR= new EditCustomerRulePage();
				
				if(editCustomerRuleLOR.invoke())
				{
					//editParameterPage.searchParameter(searchValue);
					ruleName = editCustomerRuleLOR.addNewRule();
					LogWriter.writeToFile("Add New Customer Rule.");
				}
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule failed. Add btn is NOT found.");
			}
			

			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule.");
		}
		
		return ruleName;
		
	}
	
	
	public String editCustomerRule(String ruleName)
	{
		
		try {
			
			WebItem rowItem;

			getSection("Custom Rules");
			
			initCustomerRuleElements();
			
			searchCustomerRule(ruleName);
			
			initCustomerRuleElements();
			if(!cr_List.isEmpty()&&cr_List.size()>0)
			{
				rowItem = cr_List.getListElement(ruleName);
				
				if(!rowItem.isEmpty())
				{
					rowItem.click();
					
					EditCustomerRulePage editCustomerRule= new EditCustomerRulePage();
					
					if(editCustomerRule.invoke())
					{
						
						editCustomerRule.editRule();
					}
				}
				else
				{
					LogWriter.writeToFile("Edit Customer Rule name-"+ruleName+" failed. CANNOT find the rule");
				}
					
				
			}
			else
			{
				LogWriter.writeToFile("Edit Customer Rule name-"+ruleName+" failed. CANNOT search out the rule");
			}
			

			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Edit Customer Rule.");
		}
		
		return ruleName;
		
	}
	
	
	public void searchTrxCustomerRule(String ruleName)
	{
		try {
			WebItem rowItem;
			WebItem searchBtn;
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			if(!cr_ActiveRuleDDL.isEmpty())
			{
				cr_ActiveRuleDDL.select("Both");
				initCustomerRuleElements();
				
				if(!cr_List.isEmpty())
				{
					rowItem = cr_List.getListElement(ruleName);
					
					if(!rowItem.isEmpty())
					{
						searchBtn = new WebItem(By.xpath("../td[6]/a[@class='btTransactions']"),rowItem);
						if(!searchBtn.isEmpty())
						{
							searchBtn.movetoclick();
							confirmCopy(true);
							LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+".");
							
							invoke();
							getProfile(Config.CONFIG.getProfileName());
							getSection("Custom Rules");
							

						}
						else
						{
							LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Copy btn is NOT found.");
						}
					}
					else
					{
						LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Row item is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Rule list is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Rule Active ddl is NOT found.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Propagate Customer Rule Name-"+ruleName+".");
		}
	}
	
	public void propagateCustomerRule(String ruleName)
	{
		try {
			WebItem rowItem;
			WebItem copyBtn;
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			if(!cr_ActiveRuleDDL.isEmpty())
			{
				cr_ActiveRuleDDL.select("Both");
				initCustomerRuleElements();
				
				if(!cr_List.isEmpty())
				{
					rowItem = cr_List.getListElement(ruleName);
					
					if(!rowItem.isEmpty())
					{
						copyBtn = new WebItem(By.xpath("../td[6]/a[@class='btCopy']"),rowItem);
						if(!copyBtn.isEmpty())
						{
							copyBtn.movetoclick();
							confirmCopy(true);
							confirmError();
							LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+".");
						}
						else
						{
							LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Copy btn is NOT found.");
						}
					}
					else
					{
						LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Row item is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Rule list is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Propagate Customer Rule Name-"+ruleName+" failed. Rule Active ddl is NOT found.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Propagate Customer Rule Name-"+ruleName+".");
		}
	}
	
	public void deactivateCustomerRule(String ruleName)
	{
		try {
			WebItem rowItem;
			WebItem activeCB;
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			if(!cr_ActiveRuleDDL.isEmpty())
			{
				cr_ActiveRuleDDL.select("Active Rules");
				initCustomerRuleElements();
				
				if(!cr_List.isEmpty())
				{
					rowItem = cr_List.getListElement(ruleName);
					
					if(!rowItem.isEmpty())
					{
						activeCB = new WebItem(By.xpath("../td[6]/input[@class='cbCustomRules']"),rowItem);
						if(!activeCB.isEmpty())
						{
							activeCB.activeCB(false);
							confirmOK();
							
							LogWriter.writeToFile("Deactivate Customer Rule Name-"+ruleName+".");
						}
						else
						{
							LogWriter.writeToFile("Deactivate Customer Rule Name-"+ruleName+" failed. Active cb is NOT found.");
						}
					}
					else
					{
						LogWriter.writeToFile("Deactivate Customer Rule Name-"+ruleName+" failed. Row item is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Deactivate Customer Rule Name-"+ruleName+" failed. Rule list is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Deactivate Customer Rule Name-"+ruleName+" failed. Rule Active ddl is NOT found.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Deactivate Customer Rule Name-"+ruleName+".");
		}
	}
	
	public void activateCustomerRule(String ruleName)
	{
		try {
			WebItem rowItem;
			WebItem activeCB;
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			if(!cr_ActiveRuleDDL.isEmpty())
			{
				cr_ActiveRuleDDL.select("Inactive Rules");
				initCustomerRuleElements();
				
				if(!cr_List.isEmpty())
				{
					rowItem = cr_List.getListElement(ruleName);
					
					if(!rowItem.isEmpty())
					{
						activeCB = new WebItem(By.xpath("../td[6]/input[@class='cbCustomRules']"),rowItem);
						if(!activeCB.isEmpty())
						{
							activeCB.activeCB(true);
							confirmOK();
							LogWriter.writeToFile("Activate Customer Rule Name-"+ruleName+".");
						}
						else
						{
							LogWriter.writeToFile("Activate Customer Rule Name-"+ruleName+" failed. Active cb is NOT found.");
						}
					}
					else
					{
						LogWriter.writeToFile("Activate Customer Rule Name-"+ruleName+" failed. Row item is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Activate Customer Rule Name-"+ruleName+" failed. Rule list is NOT found.");
				}
			}
			else
			{
				LogWriter.writeToFile("Activate Customer Rule Name-"+ruleName+" failed. Rule Active ddl is NOT found.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Activate Customer Rule Name-"+ruleName+".");
		}
	}
	
	public void addCustomerRuleFromLOR(String rulename)
	{
		try {
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			
			if(!cr_AddLORBtn.isEmpty())
			{
				cr_AddLORBtn.movetoclick();
				
				EditCustomerRulePage editCustomerRuleLOR= new EditCustomerRulePage();
				
				if(editCustomerRuleLOR.invoke())
				{
					//editParameterPage.searchParameter(searchValue);
					editCustomerRuleLOR.addLOR(rulename);
					LogWriter.writeToFile("Add New Customer Rule from LOR name-"+rulename+".");
				}
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule from LOR name-"+rulename+" failed. Add LOR btn is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule from LOR name-"+rulename+".");
		}
	}
	
	public void searchCustomerRule(String searchValue)
	{
		try {
			getSection("Custom Rules");
			
			initCustomerRuleElements();
			
			if(!cr_SearchTb.isEmpty()&&!cr_SearchBtn.isEmpty())
			{
				cr_SearchTb.sendKeys(searchValue);
				cr_SearchBtn.movetoclick();
				
				LogWriter.writeToFile("Search Customer Rule Value-"+searchValue+".");
			}
			else
			{
				LogWriter.writeToFile("Search Customer Rule Value-"+searchValue+" failed. Search tb or btn is NOT found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Search Customer Rule Value-"+searchValue+".");
		}
	}
	
	
	// not working, why?
	public void changeCustomerRuleDefaultScore(int score)
	{
		try {
			getSection("Custom Rules");
			initCustomerRuleElements();
			
			changeCustomerRuleDefaultActive(false);
			
			initCustomerRuleElements();
			if(!cr_Content.isEmpty())
			{
				if(!cr_ScoreSlider.isEmpty()&&!cr_ScoreSlider_Alt.isEmpty()&&!cr_ActiveCB.isEmpty())
				{

					cr_ActiveCB.click();
					initCustomerRuleElements();
					cr_ScoreSlider_Alt.movetoclick();
					cr_ScoreSlider_Alt.changeinnerHTML(String.valueOf(score));

					initCustomerRuleElements();
					cr_ScoreSlider.changeattribute("value", String.valueOf(score));
					cr_ScoreSlider.changeinnerHTML(String.valueOf(score));

					
					if(!cr_UpdateBtn.isEmpty())
					{
						cr_UpdateBtn.movetoclick();
						LogWriter.writeToFile("Change Customer Rule Default Score score-"+score+".");
						confirmLeave(true);
					}
					else
					{
						LogWriter.writeToFile("Change Customer Rule Default Score score-"+score+" failed. Update btn is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Change Customer Rule Default Score score-"+score+" failed. Score input or Active CB is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Change Customer Rule Default Score score-"+score+" failed. Customer Rule info content panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change Customer Rule Default Score score-"+score+".");
		}
	}
	
	
	public void changeCustomerRuleDefaultActive(Boolean isActive)
	{
		try {
			getSection("Custom Rules");
			initCustomerRuleElements();
			
			if(!cr_Content.isEmpty())
			{
				if(!cr_ActiveCB.isEmpty())
				{
					if((cr_ActiveCB.item.isSelected()&&!isActive)||(!cr_ActiveCB.item.isSelected()&&isActive))
					{
						cr_ActiveCB.click();
						confirmOK();
						
						if(!cr_UpdateBtn.isEmpty())
						{
							cr_UpdateBtn.movetoclick();
							LogWriter.writeToFile("Change Customer Rule Default Active-"+isActive+".");
						}
						else
						{
							LogWriter.writeToFile("Change Customer Rule Default Active-"+isActive+" failed. Update btn is NOT found.");
						}
					}
				}
				else
				{
					LogWriter.writeToFile("Change Customer Rule Default Active-"+isActive+" failed. Active CB is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Change Customer Rule Default Active-"+isActive+" failed. Customer Rule info content panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change Customer Rule Default Active-"+isActive+".");
		}
	}
	
	
	
	public void run()
	{

		try {
			
		
			iterateProfile();
			LogWriter.writeToFile("'iterate all Profiles' finished in Risk Profile Page.");
			//Thread.sleep(2000);
			
			getProfile(Config.CONFIG.getProfileIndex());
			LogWriter.writeToFile("Get to profile-"+Config.CONFIG.getProfileIndex()+" in Risk Profile Page.");
			//Thread.sleep(2000);
			
			iterateSection();
			LogWriter.writeToFile("'iterate all Parameter Sections' finished for the selected profile in Risk Profile Page.");

			
			allParaDeactive(Config.CONFIG.getBulkProcessSectionName());
			LogWriter.writeToFile("Deactivate all parameters in section-"+Config.CONFIG.getBulkProcessSectionName()+" finished for the selected profile in Risk Profile Page.");
			
			setAllParameterScore(Config.CONFIG.getBulkProcessSectionName());
			LogWriter.writeToFile("Set all parameters to random scores in section-"+Config.CONFIG.getBulkProcessSectionName()+" finished for the selected profile in Risk Profile Page.");
			
			
		 	//setAllParameterScore(Config.CONFIG.getBulkProcessSectionName(), Config.CONFIG.getBulkProcessScore());
			//LogWriter.writeToFile("Set all parameters score-"+Config.CONFIG.getBulkProcessScore()+" in section-"+Config.CONFIG.getBulkProcessSectionName()+" finished for the selected profile in Risk Profile Page.");
			
			changeParameterScore(Config.CONFIG.getSingleProcessSectionName(), Config.CONFIG.getSingleProcessParameterIndex(), Config.CONFIG.getSingleProcessParameterScore());
			LogWriter.writeToFile("Change single parameters score-"+Config.CONFIG.getSingleProcessParameterScore()+" index-"+ Config.CONFIG.getSingleProcessParameterIndex()
					+" in section-"+Config.CONFIG.getSingleProcessSectionName()+" finished for the selected profile in Risk Profile Page.");
			
			searchParameter(Config.CONFIG.getSearchSectionName(), Config.CONFIG.getSearchValue());
			LogWriter.writeToFile("Search  parameter value-"+Config.CONFIG.getSearchValue()
					+" in section-"+Config.CONFIG.getSearchSectionName()+" finished for the selected profile in Risk Profile Page.");
		
			editParameter(Config.CONFIG.getEditParameterSectionName(), Config.CONFIG.getEditParameterName());
			LogWriter.writeToFile("Search  parameter -"+Config.CONFIG.getEditParameterName()
					+" in section-"+Config.CONFIG.getSearchSectionName()+" finished for the selected profile in Risk Profile Page.");
			
			
			changeCustomerRuleDefaultScore(Config.CONFIG.getCustomerRuleScore());
			LogWriter.writeToFile("Change CustomerRule Default Score-"+Config.CONFIG.getCustomerRuleScore()+" finished for the selected profile in Risk Profile Page.");
			
			searchCustomerRule(Config.CONFIG.getCustomerRuleSearchValue());
			LogWriter.writeToFile("Search CustomerRule value-"+Config.CONFIG.getCustomerRuleSearchValue()+" finished for the selected profile in Risk Profile Page.");
			
			addCustomerRuleFromLOR(Config.CONFIG.getCustomerRuleLORName());
			LogWriter.writeToFile("Add CustomerRule from LOR finished for the selected profile in Risk Profile Page.");
				
			String rulenameString =addCustomerRule();
			LogWriter.writeToFile("Add new CustomerRule name-"+rulenameString+" finished for the selected profile in Risk Profile Page.");
		
			//String rulenameString = "QSVeraTest8186";
			if(!rulenameString.isEmpty())
			{
				propagateCustomerRule(rulenameString);
				LogWriter.writeToFile("Propagate newly added CustomerRule name-"+rulenameString+" finished for the selected profile in Risk Profile Page.");
				
				searchTrxCustomerRule(rulenameString);
				LogWriter.writeToFile("Search transactions for newly added CustomerRule name-"+rulenameString+" finished for the selected profile in Risk Profile Page.");
				
				deactivateCustomerRule(rulenameString);
				LogWriter.writeToFile("Deactivate newly added CustomerRule name-"+rulenameString+" finished for the selected profile in Risk Profile Page.");
				
				activateCustomerRule(rulenameString);
				LogWriter.writeToFile("Activate newly added CustomerRule name-"+rulenameString+" finished for the selected profile in Risk Profile Page.");
			}
			
		
		} catch (Exception e) {
			LogWriter.writeToFile("Exception in Risk Profile Page run.");
		}
	}
}
