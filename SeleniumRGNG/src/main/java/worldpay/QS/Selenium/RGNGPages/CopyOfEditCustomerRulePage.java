package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;
import worldpay.QS.Selenium.util.Utility;

public class CopyOfEditCustomerRulePage extends WebPage {

	public By webPageId = By.xpath("//div[@id='ContentPlaceHolder1_PanelEditRule']");
	
	public WebItem libraryRuleDDL;
	public WebItemList libraryRuleOptions;
	
	public WebItem submitBtn;
	public WebItem cancelBtn;
	public WebItem dupliBtn;
	
	public WebItemList customerRuleHeadList;
	public WebItem customerRuleBodyPanel;
	
	public WebItem closebtnItem;
	@Override
	public boolean invoke() {

		if(exists(10, webPageId))
		{
			LogWriter.writeToFile("Customer Rule edit popup started.");
			initElements();
			return true;
		}
		else 
		{
			LogWriter.writeToFile("Customer Rule edit popup start failed.");
			return false;
		}

	}
	
	@Override
	public void initElements() {
		// TODO Auto-generated method stub
		libraryRuleDDL = new WebItem(By.xpath("//select[@id='ContentPlaceHolder1_ddLibraryOfRules']"));
		libraryRuleOptions = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_plCaption']//select[@id='ContentPlaceHolder1_ddLibraryOfRules']/optgroup/option"));
		
		customerRuleHeadList = new WebItemList(By.xpath("//div[@id='ContentPlaceHolder1_tcCustomRules_header']/span/span/span/a/span"));
		customerRuleBodyPanel = new WebItem(By.xpath("//div[@id='ContentPlaceHolder1_tcCustomRules_body']"));
		
		submitBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnSubmit']"));
		cancelBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnCancelPopup']"));
		dupliBtn = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnDuplicate']"));
		
		closebtnItem = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_btnClose']"));
	}

	
	public void addLOR(String lorName)
	{
		try {

			initElements();
			
			if(!libraryRuleDDL.isEmpty())
			{
				libraryRuleDDL.select(lorName);
				
				confirmOK();
				
				changeRuleNameAndSubmit();
				addManConditionFieldAndSubmit();
				
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule from LOR name-"+lorName+" failed in popup panel. Library of Rules ddl is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule from LOR popup name-"+lorName+".");
		}
	}
	
	public String addNewRule()
	{
		String newRuleName="";
		try {
			
			initElements();
			
			newRuleName = addNewRuleInfoAction();
			LogWriter.writeToFile("Add new CustomerRule name-"+newRuleName+" basic info&action finished for the selected profile in Risk Profile Page.");
			
			addNewRuleCalculatedCondition();
			LogWriter.writeToFile("Add new CustomerRule name-"+newRuleName+" calculated condition finished for the selected profile in Risk Profile Page.");
			
			addNewRuleCondition();
			LogWriter.writeToFile("Add new CustomerRule name-"+newRuleName+" condition finished for the selected profile in Risk Profile Page.");
			
			addNewRuleScores();
			LogWriter.writeToFile("Add new CustomerRule name-"+newRuleName+" scores finished for the selected profile in Risk Profile Page.");
			
			submitChange();
			LogWriter.writeToFile("Submit finished for the selected profile in Risk Profile Page.");
			
			closePopup();
			LogWriter.writeToFile("Close CustomerRule popup finished for the selected profile in Risk Profile Page.");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule popup.");
		}
		
		return newRuleName;
	}
	
	public void editRule()
	{
		
	}
	

	//add rule related
	public String addNewRuleInfoAction()
	{
		String nameString = "";
		if(!Config.CONFIG.getCustomerRuleNewName().isEmpty()&&!Config.CONFIG.getCustomerRuleNewDesp().isEmpty())
		{
			nameString = addRuleName(Config.CONFIG.getCustomerRuleNewName());
			addRuleDesp(Config.CONFIG.getCustomerRuleNewDesp());
			
			changeRuleActive(Config.CONFIG.isCustomerRuleIsActive());
			changeRuleCopyToAll(Config.CONFIG.isCustomerRuleIsPropAll());
			
			if(Config.CONFIG.isCustomerRuleIsScore())	
			{
				assignRuleScore(Config.CONFIG.getCustomerRuleNewScore(), Config.CONFIG.getCustomerRuleNewDefaultScore(), Config.CONFIG.isCustomerRuleIsOverrideTrxScore());
			}
			
			if(Config.CONFIG.isCustomerRuleIsEmail())	
			{
				assignRuleEmail(Config.CONFIG.getCustomerRuleEmailAddress(), Config.CONFIG.getCustomerRuleEmailText());
			}
			
			if(Config.CONFIG.isCustomerRuleIsSMS())	
			{
				assignRuleSMS(Config.CONFIG.getCustomerRuleSMSNum(), Config.CONFIG.getCustomerRuleSMSMessage());
			}
		}
		
		return nameString;
	}
	
	public void addNewRuleCalculatedCondition()
	{
		if(Config.CONFIG.isCustomerRuleRCCIsOn()&&!Config.CONFIG.getCustomerRuleRCCCondition().isEmpty()
				&&!Config.CONFIG.getCustomerRuleRCCName().isEmpty()&&!Config.CONFIG.getCustomerRuleRCCValue().isEmpty())
		{
			addCalculatedCondition(Config.CONFIG.getCustomerRuleRCCName(), Config.CONFIG.getCustomerRuleRCCCondition(), Config.CONFIG.getCustomerRuleRCCValue());
		}
	}
	
	public void addNewRuleCondition()
	{
		if(Config.CONFIG.isCustomerRuleRCIsOn()&&!Config.CONFIG.getCustomerRuleRCCondition().isEmpty()
				&&!Config.CONFIG.getCustomerRuleRCName().isEmpty()&&!Config.CONFIG.getCustomerRuleRCValue().isEmpty())
		{
			addCondition(Config.CONFIG.getCustomerRuleRCName(), Config.CONFIG.getCustomerRuleRCCondition(), Config.CONFIG.getCustomerRuleRCValue());
		}
	}
	
	public void addNewRuleScores()
	{
		if(Config.CONFIG.isCustomerRuleRSIsOn()&&!Config.CONFIG.getCustomerRuleRSCondition().isEmpty()
				&&!Config.CONFIG.getCustomerRuleRSName().isEmpty()&&Config.CONFIG.getCustomerRuleRSScore()>=0&&Config.CONFIG.getCustomerRuleRSScore()<=100)
		{
			addScores(Config.CONFIG.getCustomerRuleRSName(), Config.CONFIG.getCustomerRuleRSCondition(),Config.CONFIG.getCustomerRuleRSScore());
		}
	}

	public void addCalculatedCondition(String name, String con, String value)
	{
		try {
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem lastListRow;
			
			getCustomerRuleCalculatedCondition();
			
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				if(!name.isEmpty()&&!con.isEmpty()&&!value.isEmpty())
				{
					addConditionBtn=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_btnAddFilter']"), customerRuleBodyPanel);
					
					if(!addConditionBtn.isEmpty())
					{
						addConditionBtn.movetoclick();
						initElements();
						
						conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"), customerRuleBodyPanel);
							
						if(!conditionList.isEmpty())
						{
							lastListRow = conditionList.getListLastElement();
							
							nameDDl = new WebItem(By.xpath("td[1]/select[@class='ruleFields']"),lastListRow);
							conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
							valueTB = new WebItem(By.xpath("td[3]/input"),lastListRow);
							
							if(!nameDDl.isEmpty()&&!conDDL.isEmpty()&&!valueTB.isEmpty())
							{
								nameDDl.select(name);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
								conDDL.select(con);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								valueTB = new WebItem(By.xpath("td[3]/input"),lastListRow);
								valueTB.sendKeys(value);
								
								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter.writeToFile("Add New Customer Rule Calculated Condition. Name-"+name+", Con-"+con+", Value-"+value+".");
								
							}
							else
							{
								LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Name ddl or condition ddl or value tb is NOT found.");
							}
							
						}
						else
						{
							LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition rowlist is NOT found.");
						}
						
						
					}
					else
					{
						LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition btn is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Text values are NOT valid.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Calculated Condition.");
		}
	}
	
	public void addCondition(String name, String con, String value)
	{
		try {
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem lastListRow;
			
			getCustomerRuleCondition();
			
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				if(!name.isEmpty()&&!con.isEmpty()&&!value.isEmpty())
				{
					addConditionBtn=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_btnAddFilter']"), customerRuleBodyPanel);
					
					if(!addConditionBtn.isEmpty())
					{
						addConditionBtn.movetoclick();
						initElements();
						
						conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"), customerRuleBodyPanel);
							
						if(!conditionList.isEmpty())
						{
							lastListRow = conditionList.getListLastElement();
							
							nameDDl = new WebItem(By.xpath("td[1]/select[@class='ruleFields']"),lastListRow);
							conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
							valueTB = new WebItem(By.xpath("td[3]/input"),lastListRow);
							
							if(!nameDDl.isEmpty()&&!conDDL.isEmpty()&&!valueTB.isEmpty())
							{
								nameDDl.select(name);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
								conDDL.select(con);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								valueTB = new WebItem(By.xpath("td[3]/input"),lastListRow);
								valueTB.sendKeys(value);
								
								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter.writeToFile("Add New Customer Rule Condition. Name-"+name+", Con-"+con+", Value-"+value+".");
							}
							else
							{
								LogWriter.writeToFile("Add New Customer Rule Condition failed. Name ddl or condition ddl or value tb is NOT found.");
							}
							
						}
						else
						{
							LogWriter.writeToFile("Add New Customer Rule Condition failed. Add condition rowlist is NOT found.");
						}
						
						
					}
					else
					{
						LogWriter.writeToFile("Add New Customer Rule Condition failed. Add condition btn is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Condition failed. Text values are NOT valid.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add New Customer Rule Condition failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Condition.");
		}
	}
	
	public void addScores(String name, String con, int score)
	{
		try {
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem scoreSlider;
			WebItem scoreSlider_alt;
			WebItemList conditionList;
			WebItem lastListRow;
			
			getCustomerRuleScore();
			
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				if(!name.isEmpty()&&!con.isEmpty()&&score>=0&&score<=100)
				{
					addConditionBtn=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_btnAddFilter']"), customerRuleBodyPanel);
					
					if(!addConditionBtn.isEmpty())
					{
						addConditionBtn.movetoclick();
						initElements();
						
						conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"), customerRuleBodyPanel);
							
						if(!conditionList.isEmpty())
						{
							lastListRow = conditionList.getListLastElement();
							
							nameDDl = new WebItem(By.xpath("td[1]/select[@class='ruleFields']"),lastListRow);
							conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
							
							scoreSlider = new WebItem(By.xpath("td[3]//span[@class='irs-single thumb']"),lastListRow);
							scoreSlider_alt = new WebItem(By.xpath("td[3]//input[@class='slider irs-hidden-input']"),lastListRow);
							
							if(!nameDDl.isEmpty()&&!conDDL.isEmpty()&&!scoreSlider.isEmpty()&&!scoreSlider_alt.isEmpty(false))
							{
								nameDDl.select(name);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),lastListRow);
								conDDL.select(con);
								
								initElements();
								conditionList = new WebItemList(By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"), customerRuleBodyPanel);
								lastListRow = conditionList.getListLastElement();
								scoreSlider = new WebItem(By.xpath("td[3]//span[@class='irs-single thumb']"),lastListRow);
								scoreSlider_alt = new WebItem(By.xpath("td[3]//input[@class='slider irs-hidden-input']"),lastListRow);
								
								scoreSlider.changeinnerHTML(String.valueOf(score));
								scoreSlider_alt.changeattribute("value", String.valueOf(score));
								
								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter.writeToFile("Add New Customer Rule Scores. Name-"+name+", Con-"+con+", Score-"+score+".");
							}
							else
							{
								LogWriter.writeToFile("Add New Customer Rule Scores failed. Name ddl or condition ddl or value tb is NOT found.");
							}
							
						}
						else
						{
							LogWriter.writeToFile("Add New Customer Rule Scores failed. Add condition rowlist is NOT found.");
						}
						
						
					}
					else
					{
						LogWriter.writeToFile("Add New Customer Rule Scores failed. Add condition btn is NOT found.");
					}
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Scores failed. Text and score values are NOT valid.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add New Customer Rule Scores failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Scores.");
		}
	};
	
	public String addRuleName(String ruleName)
	{
		String nameString = "";
		try {
			nameString = ruleName+Utility.getRandomInt(1, 9999);
			WebItem RuleNameTB;
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				RuleNameTB=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRuleName']"), customerRuleBodyPanel);
				
				if(!RuleNameTB.isEmpty())
				{
					RuleNameTB.sendKeys(nameString);
					customerRuleBodyPanel.movetoclick();
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Name-"+nameString+" failed. Rule name tb is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add New Customer Rule Name-"+nameString+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Name.");
		}
		
		return nameString;
	}
	
	public void addRuleDesp(String RuleDesp)
	{
		try {
			WebItem RuleDespTA;
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				RuleDespTA=new WebItem(By.xpath("//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbDescription']"), customerRuleBodyPanel);
				
				if(!RuleDespTA.isEmpty())
				{
					RuleDespTA.sendKeys(RuleDesp);
					customerRuleBodyPanel.movetoclick();
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Name-"+RuleDesp+" failed. Rule Desp textarea is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Add New Customer Rule Name-"+RuleDesp+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Desp.");
		}
	}

	public void changeRuleActive(boolean isActive)
	{
		try {
			WebItem RuleActive;
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				RuleActive=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbActive']"), customerRuleBodyPanel);
				
				if(!RuleActive.isEmpty())
				{
					RuleActive.activeCB(isActive);
					
					customerRuleBodyPanel.movetoclick();
					
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule Active-"+isActive+" failed. Rule Active CB is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Change New Customer Rule Active-"+isActive+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change New Customer Rule Active-"+isActive+".");
		}
	}
	
	public void changeRuleCopyToAll(boolean isCopy)
	{
		try {
			WebItem RuleCopy;
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				RuleCopy=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbCopyToAll']"), customerRuleBodyPanel);
				
				if(!RuleCopy.isEmpty())
				{
					RuleCopy.activeCB(isCopy);
					
					customerRuleBodyPanel.movetoclick();
				}
				else
				{
					LogWriter.writeToFile("Add New Customer Rule CopyToAll-"+isCopy+" failed. Rule CopyToAll CB is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Change New Customer Rule CopyToAll-"+isCopy+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change New Customer Rule CopyToAll-"+isCopy+".");
		}
	}
	
	public void assignRuleScore(int riskScore, int defaultRiskScore, boolean isOverride)
	{
		try {
			WebItem scoreSlider;
			WebItem scoreSlider_alt;
			WebItem defScoreSlider;
			WebItem defScoreSlider_alt;
			
			WebItem assignScoreCB;
			WebItem overrideScoreCB;
			
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				
				assignScoreCB=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbAssignScore']"));
			
				if(!assignScoreCB.isEmpty())
				{
					
					assignScoreCB.activeCB(Config.CONFIG.isCustomerRuleIsScore());
					confirmOK();
					

					if(riskScore>=0&&riskScore<=100)
					{
						
						initElements();
						scoreSlider = new WebItem(By.xpath("//tr[@class='riskSliderPopup bound']//span[@class='irs-single thumb']"),customerRuleBodyPanel);
						scoreSlider_alt = new WebItem(By.xpath("//tr[@class='riskSliderPopup bound']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRisk']"),customerRuleBodyPanel);
						
						if(!scoreSlider.isEmpty()&&!scoreSlider_alt.isEmpty())
						{
							scoreSlider.changeinnerHTML(String.valueOf(riskScore));
							scoreSlider_alt.changeattribute("value", String.valueOf(riskScore));
							confirmOK();
							customerRuleBodyPanel.movetoclick();
							
							LogWriter.writeToFile("Change New Customer Rule Score-"+riskScore+".");
							
	
						}
						else
						{
							LogWriter.writeToFile("Change New Customer Rule Score-"+riskScore+" failed. Slider is NOT found.");
						}
						
						
					}
					else 
					{
						LogWriter.writeToFile("Change New Customer Rule Score-"+riskScore+" failed. Score is NOT valid.");
					}
				
				
					if(defaultRiskScore>=0&&defaultRiskScore<=100)
					{
						defScoreSlider = new WebItem(By.xpath("//tr[@class='defRiskSliderPopup bound']//span[@class='irs-single thumb']"),customerRuleBodyPanel);
						defScoreSlider_alt = new WebItem(By.xpath("//tr[@class='defRiskSliderPopup bound']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbDefaultRisk']"),customerRuleBodyPanel);
						
						if(!defScoreSlider.isEmpty()&&!defScoreSlider_alt.isEmpty())
						{
							defScoreSlider.changeinnerHTML(String.valueOf(defaultRiskScore));
							defScoreSlider_alt.changeattribute("value", String.valueOf(defaultRiskScore));
							confirmOK();
							customerRuleBodyPanel.movetoclick();
							
							LogWriter.writeToFile("Change New Customer Rule Default Score-"+defaultRiskScore+".");
						}
						else
						{
							LogWriter.writeToFile("Change New Customer Rule Default Score-"+defaultRiskScore+" failed. Slider is NOT found.");
						}
					}
					else 
					{
						LogWriter.writeToFile("Change New Customer Rule Default Score-"+defaultRiskScore+" failed. Default Score is NOT valid.");
					}
					
					
					overrideScoreCB = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbAssignTScore']"),customerRuleBodyPanel);
					
					if(!overrideScoreCB.isEmpty())
					{
						overrideScoreCB.activeCB(isOverride);
					}
					else 
					{
						LogWriter.writeToFile("Override Transaction Score in Change New Customer Rule Score-"+riskScore+" and Default Score-"+defaultRiskScore+" failed. Override Score CB is NOT found.");
					}
					
				}
				else
				{
					LogWriter.writeToFile("Change New Customer Rule Score-"+riskScore+" and Default Score-"+defaultRiskScore+" failed. Assign CB is NOT found.");
				}
			}
			else 
			{
				LogWriter.writeToFile("Change New Customer Rule Score-"+riskScore+" and Default Score-"+defaultRiskScore+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change New Customer Rule Score-"+riskScore+" and Default Score-"+defaultRiskScore+".");
		}
	}
	
	public void assignRuleEmail(String emailAddress, String emailText)
	{
		try {
			WebItem addressTB;
			WebItem textTB;
			
			WebItem assignEmailCB;
			
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				assignEmailCB=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbSendEmail']"));

				if(!assignEmailCB.isEmpty())
				{
					
					assignEmailCB.activeCB(Config.CONFIG.isCustomerRuleIsEmail());
					confirmOK();
					customerRuleBodyPanel.movetoclick();
					

					if(!emailAddress.isEmpty()&&!emailText.isEmpty())
					{
						
						addressTB = new WebItem(By.xpath("//tr[@class='pnEmail']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbEmailAddress']"),customerRuleBodyPanel);
						textTB = new WebItem(By.xpath("//tr[@class='pnEmail']//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbEmailText']"),customerRuleBodyPanel);
						
						if(!addressTB.isEmpty()&&!textTB.isEmpty())
						{
							addressTB.sendKeys(emailAddress);
							textTB.sendKeys(emailText);
							
							confirmOK();
							
							LogWriter.writeToFile("Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+".");
							
	
						}
						else
						{
							LogWriter.writeToFile("Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+" failed. TB is NOT valid.");
						}
						
						
					}
					else 
					{
						LogWriter.writeToFile("Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+" failed. Text is NOT valid.");
					}
				}
				else 
				{
					LogWriter.writeToFile("Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+" failed. Email CB is NOT found.");
				}
				
			}
			else 
			{
				LogWriter.writeToFile("Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change New Customer Rule EmailAddr-"+emailAddress+" and EmailText-"+emailText+".");
		}
	}
	
	public void assignRuleSMS(String smsAddress, String smsText)
	{
		try {
			WebItem addressTB;
			WebItem textTB;
			
			WebItem assignSMSCB;
			
			getCustomerRuleBasicInfo();
			initElements();
			
			if(!customerRuleBodyPanel.isEmpty())
			{
				assignSMSCB=new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbSendSMS']"));

				if(!assignSMSCB.isEmpty())
				{
					
					assignSMSCB.activeCB(Config.CONFIG.isCustomerRuleIsSMS());
					confirmOK();
					customerRuleBodyPanel.movetoclick();
					

					if(!smsAddress.isEmpty()&&!smsText.isEmpty())
					{
						
						addressTB = new WebItem(By.xpath("//tr[@class='pnSMS']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbSmsPhoneNumber']"),customerRuleBodyPanel);
						textTB = new WebItem(By.xpath("//tr[@class='pnSMS']//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbSmsMessage']"),customerRuleBodyPanel);
						
						if(!addressTB.isEmpty()&&!textTB.isEmpty())
						{
							addressTB.sendKeys(smsAddress);
							textTB.sendKeys(smsText);
							
							confirmOK();
							
							LogWriter.writeToFile("Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+".");
							
	
						}
						else
						{
							LogWriter.writeToFile("Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+" failed. TB is NOT valid.");
						}
						
						
					}
					else 
					{
						LogWriter.writeToFile("Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+" failed. Text is NOT valid.");
					}
				}
				else 
				{
					LogWriter.writeToFile("Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+" failed. SMS CB is NOT found.");
				}
				
			}
			else 
			{
				LogWriter.writeToFile("Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+" failed. Rule body panel is NOT found.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Change New Customer Rule SMSNumber-"+smsAddress+" and SMSText-"+smsText+".");
		}
	}
	
	public void getCustomerRuleBasicInfo()
	{
		try {
			
			initElements();
			if(!customerRuleHeadList.isEmpty()&&customerRuleHeadList.size()==Config.CONFIG.getCustomerRuleNewTabNum())
			{
				customerRuleHeadList.getListElement("Rule Info and Actions").movetoclick();
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule Basic Info failed. Customer Rule Head List is NOT found or number is NOT valid." );
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Basic Info.");
		}
	}
	
	public void getCustomerRuleCalculatedCondition()
	{
		try {
			
			initElements();
			
			if(!customerRuleHeadList.isEmpty()&&customerRuleHeadList.size()==Config.CONFIG.getCustomerRuleNewTabNum())
			{
				customerRuleHeadList.getListElement("Rule Calculated Conditions").movetoclick();;
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule Calculated Condition failed. Customer Rule Head List is NOT found or number is NOT valid." );
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Calculated Condition.");
		}
	}
	
	public void getCustomerRuleCondition()
	{
		try {
			
			initElements();
			
			if(!customerRuleHeadList.isEmpty()&&customerRuleHeadList.size()==Config.CONFIG.getCustomerRuleNewTabNum())
			{
				customerRuleHeadList.getListElement("Rule Conditions").movetoclick();;
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule Condition failed. Customer Rule Head List is NOT found or number is NOT valid." );
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Condition.");
		}
	}
	
	public void getCustomerRuleScore()
	{
		try {
			
			initElements();
			
			if(!customerRuleHeadList.isEmpty()&&customerRuleHeadList.size()==Config.CONFIG.getCustomerRuleNewTabNum())
			{
				customerRuleHeadList.getListElement("Rule Scores").movetoclick();;
			}
			else
			{
				LogWriter.writeToFile("Add New Customer Rule Score failed. Customer Rule Head List is NOT found or number is NOT valid." );
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in Add New Customer Rule Score.");
		}
	}
	
	public void addManConditionFieldAndSubmit()
	{
		WebItem conditionItem = new WebItem(By.xpath("//input[@class='highlightCss']"));
		
		if(!conditionItem.isEmpty())
		{
			conditionItem.sendKeys(Config.CONFIG.getCustomerRuleLORConditionValue());
			
			if(!customerRuleBodyPanel.isEmpty())
				customerRuleBodyPanel.movetoclick();
			
			LogWriter.writeToFile("Add mandatory field.");
			submitChange();
		}
	}
	
	public void changeRuleNameAndSubmit()
	{
		String ruleNameString = "VeraTest"+Utility.getRandomInt(1, 9999);
		try 
		{
			initElements();
			WebItem ruleNameTB = new WebItem(By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRuleName']"));

			if(!ruleNameTB.isEmpty())
			{
				ruleNameTB.sendKeys(ruleNameString);

				LogWriter.writeToFile("Change rule name for LOR to "+ruleNameString+".");
				submitChange();
			}
			else 
			{
				LogWriter.writeToFile("Change rule name for LOR to "+ruleNameString+" failed. Rule Name textBox is NOT found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in change rule name for LOR to "+ruleNameString+".");
		}
		
	}
	
	public void submitChange()
	{
		try {
			initElements();
			

			if(!submitBtn.isEmpty())
			{
				submitBtn.movetoclick();
				confirmLeave(true);
				confirmOK();
				LogWriter.writeToFile("Submit change.");
			}
			else
			{
				LogWriter.writeToFile("Submit change failed. Submit btn is NOT found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in submit change.");
		}
	}
	
	public void cancelChange()
	{
		try {
			initElements();
			

			if(!cancelBtn.isEmpty())
			{
				cancelBtn.movetoclick();
				confirmLeave(true);
				LogWriter.writeToFile("Submit cancel.");
			}
			else
			{
				LogWriter.writeToFile("Submit cancel failed. Submit btn is NOT found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in submit cancel.");
		}
	}
	
	public void closePopup() 
	{
		if(!closebtnItem.isEmpty())
		{
			closebtnItem.movetoclick();
			confirmLeave(true);
			confirmOK();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
