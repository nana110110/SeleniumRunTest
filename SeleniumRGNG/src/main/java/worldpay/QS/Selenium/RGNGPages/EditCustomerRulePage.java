package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebItemList;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;
import worldpay.QS.Selenium.util.Utility;

public class EditCustomerRulePage extends WebPage {

	public By webPageId = By
			.xpath("//div[@id='ContentPlaceHolder1_PanelEditRule']");

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

		if (exists(10, webPageId)) {
			LogWriter.writeToFile("Customer Rule edit popup started.");
			initElements();
			return true;
		} else {
			LogWriter.writeToFile("Customer Rule edit popup start failed.");
			return false;
		}

	}

	@Override
	public void initElements() {
		// TODO Auto-generated method stub
		libraryRuleDDL = new WebItem(
				By.xpath("//select[@id='ContentPlaceHolder1_ddLibraryOfRules']"));
		libraryRuleOptions = new WebItemList(
				By.xpath("//div[@id='ContentPlaceHolder1_plCaption']//select[@id='ContentPlaceHolder1_ddLibraryOfRules']/optgroup/option"));

		customerRuleHeadList = new WebItemList(
				By.xpath("//div[@id='ContentPlaceHolder1_tcCustomRules_header']/span/span/span/a/span"));
		customerRuleBodyPanel = new WebItem(
				By.xpath("//div[@id='ContentPlaceHolder1_tcCustomRules_body']"));

		submitBtn = new WebItem(
				By.xpath("//input[@id='ContentPlaceHolder1_btnSubmit']"));
		cancelBtn = new WebItem(
				By.xpath("//input[@id='ContentPlaceHolder1_btnCancelPopup']"));
		dupliBtn = new WebItem(
				By.xpath("//input[@id='ContentPlaceHolder1_btnDuplicate']"));

		closebtnItem = new WebItem(
				By.xpath("//input[@id='ContentPlaceHolder1_btnClose']"));
	}

	public void addLOR(String lorName) {
		try {

			initElements();

			if (!libraryRuleDDL.isEmpty()) {
				libraryRuleDDL.select(lorName);

				confirmOK();

				changeRuleNameAndSubmit();
				addManConditionFieldAndSubmit();

			} else {
				LogWriter
						.writeToFile("Add New Customer Rule from LOR name-"
								+ lorName
								+ " failed in popup panel. Library of Rules ddl is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule from LOR popup name-"
							+ lorName + ".");
		}
	}

	public String addNewRule() {
		String newRuleName = "";
		try {

			initElements();

			newRuleName = addNewRuleInfoAction();
			LogWriter
					.writeToFile("Add new CustomerRule name-"
							+ newRuleName
							+ " basic info&action finished for the selected profile in Risk Profile Page.");

			addNewRuleCalculatedCondition();
			LogWriter
					.writeToFile("Add new CustomerRule name-"
							+ newRuleName
							+ " calculated condition finished for the selected profile in Risk Profile Page.");

			addNewRuleCondition();
			LogWriter
					.writeToFile("Add new CustomerRule name-"
							+ newRuleName
							+ " condition finished for the selected profile in Risk Profile Page.");

			addNewRuleScores();
			LogWriter
					.writeToFile("Add new CustomerRule name-"
							+ newRuleName
							+ " scores finished for the selected profile in Risk Profile Page.");

			submitChange();
			LogWriter
					.writeToFile("Submit finished for the selected profile in Risk Profile Page.");

			closePopup();
			LogWriter
					.writeToFile("Close CustomerRule popup finished for the selected profile in Risk Profile Page.");

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule popup.");
		}

		return newRuleName;
	}

	public void editRule() {
		try {

			initElements();

			// newRuleName = addNewRuleInfoAction();
			String newRuleName = editNewRuleInfoAction();
			LogWriter
					.writeToFile("Edit CustomerRule name-"
							+ newRuleName
							+ " basic info&action finished for the selected profile in Risk Profile Page.");

			editNewRuleCalculatedCondition();
			LogWriter
					.writeToFile("Edit new CustomerRule name-"
							+ newRuleName
							+ " calculated condition finished for the selected profile in Risk Profile Page.");

			editNewRuleCondition();
			LogWriter
					.writeToFile("Edit new CustomerRule name-"
							+ newRuleName
							+ " condition finished for the selected profile in Risk Profile Page.");

			editNewRuleScores();
			LogWriter
					.writeToFile("Edit new CustomerRule name-"
							+ newRuleName
							+ " scores finished for the selected profile in Risk Profile Page.");

			submitChange();
			LogWriter
					.writeToFile("Submit finished for the selected profile in Risk Profile Page.");

			closePopup();
			LogWriter
					.writeToFile("Close CustomerRule popup finished for the selected profile in Risk Profile Page.");

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule popup.");
		}
	}

	// add rule related
	public String addNewRuleInfoAction() {
		String nameString = "";
		if (!Config.CONFIG.getCustomerRuleNewName().isEmpty()
				&& !Config.CONFIG.getCustomerRuleNewDesp().isEmpty()) {
			nameString = addRuleName(Config.CONFIG.getCustomerRuleNewName());
			addRuleDesp(Config.CONFIG.getCustomerRuleNewDesp());

			changeRuleActive(Config.CONFIG.isCustomerRuleIsActive());
			changeRuleCopyToAll(Config.CONFIG.isCustomerRuleIsPropAll());

			if (Config.CONFIG.isCustomerRuleIsScore()) {
				assignRuleScore(Config.CONFIG.getCustomerRuleNewScore(),
						Config.CONFIG.getCustomerRuleNewDefaultScore(),
						Config.CONFIG.isCustomerRuleIsOverrideTrxScore());
			}

			if (Config.CONFIG.isCustomerRuleIsEmail()) {
				assignRuleEmail(Config.CONFIG.getCustomerRuleEmailAddress(),
						Config.CONFIG.getCustomerRuleEmailText());
			}

			if (Config.CONFIG.isCustomerRuleIsSMS()) {
				assignRuleSMS(Config.CONFIG.getCustomerRuleSMSNum(),
						Config.CONFIG.getCustomerRuleSMSMessage());
			}
		}

		return nameString;
	}

	public void addNewRuleCalculatedCondition() {
		if (Config.CONFIG.isCustomerRuleRCCIsOn()
				&& !Config.CONFIG.getCustomerRuleRCCCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleRCCName().isEmpty()
				&& !Config.CONFIG.getCustomerRuleRCCValue().isEmpty()) {
			addCalculatedCondition(Config.CONFIG.getCustomerRuleRCCName(),
					Config.CONFIG.getCustomerRuleRCCCondition(),
					Config.CONFIG.getCustomerRuleRCCValue());
		}
	}

	public void addNewRuleCondition() {
		if (Config.CONFIG.isCustomerRuleRCIsOn()
				&& !Config.CONFIG.getCustomerRuleRCCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleRCName().isEmpty()
				&& !Config.CONFIG.getCustomerRuleRCValue().isEmpty()) {
			addCondition(Config.CONFIG.getCustomerRuleRCName(),
					Config.CONFIG.getCustomerRuleRCCondition(),
					Config.CONFIG.getCustomerRuleRCValue());
		}
	}

	public void addNewRuleScores() {
		if (Config.CONFIG.isCustomerRuleRSIsOn()
				&& !Config.CONFIG.getCustomerRuleRSCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleRSName().isEmpty()
				&& Config.CONFIG.getCustomerRuleRSScore() >= 0
				&& Config.CONFIG.getCustomerRuleRSScore() <= 100) {
			addScores(Config.CONFIG.getCustomerRuleRSName(),
					Config.CONFIG.getCustomerRuleRSCondition(),
					Config.CONFIG.getCustomerRuleRSScore());
		}
	}

	// edit rule related
	public String editNewRuleInfoAction() {
		String nameString = "";
		if (!Config.CONFIG.getCustomerRuleEditName().isEmpty())
			nameString = addRuleName(Config.CONFIG.getCustomerRuleEditName());

		if (!Config.CONFIG.getCustomerRuleEditName().isEmpty())
			addRuleDesp(Config.CONFIG.getCustomerRuleEditDesp());

		changeRuleActive(Config.CONFIG.isCustomerRuleEditIsActive());
		changeRuleCopyToAll(Config.CONFIG.isCustomerRuleEditIsPropAll());

		assignRuleScoreActive(Config.CONFIG.isCustomerRuleEditIsScore());
		if (Config.CONFIG.isCustomerRuleEditIsScore()) {
			assignRuleScore(Config.CONFIG.getCustomerRuleEditScore(),
					Config.CONFIG.getCustomerRuleEditDefaultScore(),
					Config.CONFIG.isCustomerRuleEditIsOverrideTrxScore());
		}

		assignRuleEmailActive(Config.CONFIG.isCustomerRuleEditIsEmail());
		if (Config.CONFIG.isCustomerRuleEditIsEmail()) {
			assignRuleEmail(Config.CONFIG.getCustomerRuleEditEmailAddress(),
					Config.CONFIG.getCustomerRuleEditEmailText());
		}

		assignRuleSMSActive(Config.CONFIG.isCustomerRuleEditIsSMS());
		if (Config.CONFIG.isCustomerRuleEditIsSMS()) {
			assignRuleSMS(Config.CONFIG.getCustomerRuleEditSMSNum(),
					Config.CONFIG.getCustomerRuleEditSMSMessage());
		}

		return nameString;
	}

	public void editNewRuleCalculatedCondition() {
		if (Config.CONFIG.isCustomerRuleEditRCCIsOn()
				&& !Config.CONFIG.getCustomerRuleEditRCCCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleEditRCCName().isEmpty()
				&& !Config.CONFIG.getCustomerRuleEditRCCValue().isEmpty()) {
			editCalculatedCondition(Config.CONFIG.getCustomerRuleEditRCCName(),
					Config.CONFIG.getCustomerRuleEditRCCCondition(),
					Config.CONFIG.getCustomerRuleEditRCCValue());
		}
	}

	public void editNewRuleCondition() {
		if (Config.CONFIG.isCustomerRuleEditRCIsOn()
				&& !Config.CONFIG.getCustomerRuleEditRCCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleEditRCName().isEmpty()
				&& !Config.CONFIG.getCustomerRuleEditRCValue().isEmpty()) {
			editCondition(Config.CONFIG.getCustomerRuleEditRCName(),
					Config.CONFIG.getCustomerRuleEditRCCondition(),
					Config.CONFIG.getCustomerRuleEditRCValue());
		}
	}

	public void editNewRuleScores() {
		if (Config.CONFIG.isCustomerRuleEditRSIsOn()
				&& !Config.CONFIG.getCustomerRuleEditRSCondition().isEmpty()
				&& !Config.CONFIG.getCustomerRuleEditRSName().isEmpty()
				&& Config.CONFIG.getCustomerRuleEditRSScore() >= 0
				&& Config.CONFIG.getCustomerRuleEditRSScore() <= 100) {
			editScores(Config.CONFIG.getCustomerRuleEditRSName(),
					Config.CONFIG.getCustomerRuleEditRSCondition(),
					Config.CONFIG.getCustomerRuleEditRSScore());
		}
	}

	
	
	// core basic functions

	public void deleteCondition(int index)
	{
		try
		{
			WebItem conditionListTable; 
			WebItemList conditionList; 
			WebItem ListRow;
			WebItem deleteBtn;
			initElements();
			
			
			if (!customerRuleBodyPanel.isEmpty()) {
				conditionListTable = new WebItem(By.xpath("//div[@style='visibility: visible;']//table[@class='ruleCondition']"),customerRuleBodyPanel);
				if(!conditionListTable.isEmpty())
				{
					conditionList = new WebItemList(
							By.xpath("tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
							conditionListTable);
					
					if(!conditionList.isEmpty()&&conditionList.size()>index)
					{
						ListRow = conditionList.getListElement(index);
						
						if (ListRow != null) {
							deleteBtn = new WebItem(By.xpath("../../../../td[4]/a[@class='btnRemoveFilter']"),ListRow);
							
						}
						else
						{
							LogWriter
							.writeToFile("Delete Condition index-"+index+" failed. List row is NOT found.");
						}
						
						
						
					}
					else 
					{
						LogWriter
						.writeToFile("Delete Condition index-"+index+" failed. Condition list is NOT valid.");
					}
				}
				else
				{
					LogWriter
					.writeToFile("Delete Condition index-"+index+" failed. Condition list table is NOT found.");
				}
			}else {
				LogWriter
				.writeToFile("Delete Condition index-"+index+" failed. Rule body panel is NOT found.");
			}
		}
		catch(Exception e)
		{
			LogWriter.writeToFile("Exception happens in delete Condition index-"+index+".");
		}
	}
	
	public void deleteCondition(String name)
	{
		try
		{
			initElements();
		}
		catch(Exception e)
		{
			LogWriter.writeToFile("Exception happens in delete Condition name-"+name+".");
		}
	}
	
	public void deleteLastCondition()
	{
		try
		{
			initElements();
		}
		catch(Exception e)
		{
			LogWriter.writeToFile("Exception happens in delete last Condition.");
		}
	}
	
	public void deleteFirstCondition()
	{
		try
		{
			initElements();
		}
		catch(Exception e)
		{
			LogWriter.writeToFile("Exception happens in delete first Condition.");
		}
	}
	
	// if ConditionName exits change con and value, otherwise add a new one with
	// the info
	public void editCalculatedCondition(String name, String con, String value) {
		try {
			boolean isconfitionFound = false;
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem ListRow;

			getCustomerRuleCalculatedCondition();

			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && !value.isEmpty()) {

					conditionList = new WebItemList(
							By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']/tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
							customerRuleBodyPanel);

					if (!conditionList.isEmpty() && conditionList.size() > 0) {
						ListRow = conditionList.getListElement(name);

						if (ListRow != null) {
							isconfitionFound = true;

							conDDL = new WebItem(
									By.xpath("../../../../td[2]/select"),
									ListRow);
							conDDL.select(con);

							initElements();
							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
									customerRuleBodyPanel);
							ListRow = conditionList.getListElement(name);

							valueTB = new WebItem(
									By.xpath("../../../../td[3]/input"),
									ListRow);

							valueTB.clear();
							customerRuleBodyPanel.movetoclick();
							valueTB.sendKeys(value);
							confirmOK();
							customerRuleBodyPanel.movetoclick();
							LogWriter
									.writeToFile("Edit Customer Rule Calculated Condition. Name-"
											+ name
											+ ", Con-"
											+ con
											+ ", Value-" + value + ".");
						}
					}

					if (!isconfitionFound) {
						addConditionBtn = new WebItem(
								By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_btnAddFilter']"),
								customerRuleBodyPanel);

						if (!addConditionBtn.isEmpty()) {
							addConditionBtn.movetoclick();
							initElements();

							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
									customerRuleBodyPanel);

							if (!conditionList.isEmpty()) {
								ListRow = conditionList.getListLastElement();

								nameDDl = new WebItem(
										By.xpath("td[1]/select[@class='ruleFields']"),
										ListRow);

								if (!nameDDl.isEmpty()) {
									nameDDl.select(name);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									conDDL = new WebItem(
											By.xpath("td[2]/select"), ListRow);

									if (!conDDL.isEmpty())
										conDDL.select(con);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									valueTB = new WebItem(
											By.xpath("td[3]/input"), ListRow);

									if (!valueTB.isEmpty())
										valueTB.sendKeys(value);

									confirmOK();
									customerRuleBodyPanel.movetoclick();
									LogWriter
											.writeToFile("Add New Customer Rule Calculated Condition. Name-"
													+ name
													+ ", Con-"
													+ con
													+ ", Value-" + value + ".");

								} else {
									LogWriter
											.writeToFile("Add New Customer Rule Calculated Condition failed. Name ddl or condition ddl or value tb is NOT found.");
								}

							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition rowlist is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition btn is NOT found.");
						}
					}

				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Calculated Condition failed. Text values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Calculated Condition failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Calculated Condition.");
		}
	}

	// if ConditionName exits change con and value, otherwise add a new one with
	// the info
	public void editCondition(String name, String con, String value) {
		try {
			boolean isconfitionFound = false;
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem ListRow;

			getCustomerRuleCondition();

			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && !value.isEmpty()) {

					conditionList = new WebItemList(
							By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']/tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
							customerRuleBodyPanel);

					if (!conditionList.isEmpty() && conditionList.size() > 0) {
						ListRow = conditionList.getListElement(name);

						if (ListRow != null) {
							isconfitionFound = true;

							conDDL = new WebItem(
									By.xpath("../../../../td[2]/select"),
									ListRow);
							conDDL.select(con);

							initElements();
							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
									customerRuleBodyPanel);
							ListRow = conditionList.getListElement(name);

							valueTB = new WebItem(
									By.xpath("../../../../td[3]/input"),
									ListRow);

							valueTB.clear();
							customerRuleBodyPanel.movetoclick();
							valueTB.sendKeys(value);
							confirmOK();
							customerRuleBodyPanel.movetoclick();
							LogWriter
									.writeToFile("Edit Customer Rule Condition. Name-"
											+ name
											+ ", Con-"
											+ con
											+ ", Value-" + value + ".");
						}
					}

					if (!isconfitionFound) {

						addConditionBtn = new WebItem(
								By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_btnAddFilter']"),
								customerRuleBodyPanel);

						if (!addConditionBtn.isEmpty()) {
							addConditionBtn.movetoclick();
							initElements();

							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
									customerRuleBodyPanel);

							if (!conditionList.isEmpty()) {
								ListRow = conditionList.getListLastElement();

								nameDDl = new WebItem(
										By.xpath("td[1]/select[@class='ruleFields']"),
										ListRow);

								if (!nameDDl.isEmpty()) {
									nameDDl.select(name);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									conDDL = new WebItem(
											By.xpath("td[2]/select"), ListRow);

									if (!conDDL.isEmpty())
										conDDL.select(con);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									valueTB = new WebItem(
											By.xpath("td[3]/input"), ListRow);

									if (!valueTB.isEmpty())
										valueTB.sendKeys(value);

									confirmOK();
									customerRuleBodyPanel.movetoclick();
									LogWriter
											.writeToFile("Add New Customer Rule Condition. Name-"
													+ name
													+ ", Con-"
													+ con
													+ ", Value-" + value + ".");
								} else {
									LogWriter
											.writeToFile("Add New Customer Rule Condition failed. Name ddl or condition ddl or value tb is NOT found.");
								}

							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Condition failed. Add condition rowlist is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Condition failed. Add condition btn is NOT found.");
						}
					}
				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Condition failed. Text values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Condition failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Condition.");
		}
	}

	// if ConditionName exits change con and value, otherwise add a new one with
	// the info
	public void editScores(String name, String con, int score) {
		try {
			boolean isconfitionFound = false;
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem scoreSlider;
			WebItem scoreSlider_alt;
			WebItemList conditionList;
			WebItem ListRow;

			getCustomerRuleScore();

			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && score >= 0
						&& score <= 100) {

					conditionList = new WebItemList(
							By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
							customerRuleBodyPanel);

					if (!conditionList.isEmpty() && conditionList.size() > 0) {
						ListRow = conditionList.getListElement(name);

						if (ListRow != null) {
							isconfitionFound = true;

							conDDL = new WebItem(
									By.xpath("../../../../td[2]/select"),
									ListRow);
							conDDL.select(con);

							initElements();
							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tbody/tr/td[1]/select/optgroup/option[@selected='selected']"),
									customerRuleBodyPanel);
							ListRow = conditionList.getListElement(name);

							scoreSlider = new WebItem(
									By.xpath("../../../../td[3]//span[@class='irs-single thumb']"),
									ListRow);
							scoreSlider_alt = new WebItem(
									By.xpath("../../../../td[3]//input[@class='slider irs-hidden-input']"),
									ListRow);

							scoreSlider.changeinnerHTML(String.valueOf(score));
							scoreSlider_alt.changeattribute("value",
									String.valueOf(score));
							confirmOK();
							customerRuleBodyPanel.movetoclick();

							LogWriter
									.writeToFile("Edit Customer Rule Score. Name-"
											+ name
											+ ", Con-"
											+ con
											+ ", Score-" + score + ".");
						}
					}

					if (!isconfitionFound) {

						addConditionBtn = new WebItem(
								By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_btnAddFilter']"),
								customerRuleBodyPanel);

						if (!addConditionBtn.isEmpty()) {
							addConditionBtn.movetoclick();
							initElements();

							conditionList = new WebItemList(
									By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
									customerRuleBodyPanel);

							if (!conditionList.isEmpty()) {
								ListRow = conditionList.getListLastElement();

								nameDDl = new WebItem(
										By.xpath("td[1]/select[@class='ruleFields']"),
										ListRow);

								if (!nameDDl.isEmpty()) {
									nameDDl.select(name);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									conDDL = new WebItem(
											By.xpath("td[2]/select"), ListRow);

									if (!conDDL.isEmpty())
										conDDL.select(con);

									initElements();
									conditionList = new WebItemList(
											By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
											customerRuleBodyPanel);
									ListRow = conditionList
											.getListLastElement();
									scoreSlider = new WebItem(
											By.xpath("td[3]//span[@class='irs-single thumb']"),
											ListRow);
									scoreSlider_alt = new WebItem(
											By.xpath("td[3]//input[@class='slider irs-hidden-input']"),
											ListRow);

									if (!scoreSlider.isEmpty()
											&& !scoreSlider_alt.isEmpty()) {
										scoreSlider.changeinnerHTML(String
												.valueOf(score));
										scoreSlider_alt.changeattribute(
												"value", String.valueOf(score));
									}

									confirmOK();
									customerRuleBodyPanel.movetoclick();
									LogWriter
											.writeToFile("Add New Customer Rule Scores. Name-"
													+ name
													+ ", Con-"
													+ con
													+ ", Score-" + score + ".");
								} else {
									LogWriter
											.writeToFile("Add New Customer Rule Scores failed. Name ddl or condition ddl or value tb is NOT found.");
								}

							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Scores failed. Add condition rowlist is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Scores failed. Add condition btn is NOT found.");
						}
					}
				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Scores failed. Text and score values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Scores failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Scores.");
		}
	};

	public void addCalculatedCondition(String name, String con, String value) {
		try {
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem lastListRow;

			getCustomerRuleCalculatedCondition();

			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && !value.isEmpty()) {
					addConditionBtn = new WebItem(
							By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_btnAddFilter']"),
							customerRuleBodyPanel);

					if (!addConditionBtn.isEmpty()) {
						addConditionBtn.movetoclick();
						initElements();

						conditionList = new WebItemList(
								By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
								customerRuleBodyPanel);

						if (!conditionList.isEmpty()) {
							lastListRow = conditionList.getListLastElement();

							nameDDl = new WebItem(
									By.xpath("td[1]/select[@class='ruleFields']"),
									lastListRow);

							if (!nameDDl.isEmpty()) {
								nameDDl.select(name);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),
										lastListRow);

								if (!conDDL.isEmpty())
									conDDL.select(con);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleCalculatedConditions_ucCalculatedConditions_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								valueTB = new WebItem(By.xpath("td[3]/input"),
										lastListRow);

								if (!valueTB.isEmpty())
									valueTB.sendKeys(value);

								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter
										.writeToFile("Add New Customer Rule Calculated Condition. Name-"
												+ name
												+ ", Con-"
												+ con
												+ ", Value-" + value + ".");

							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Calculated Condition failed. Name ddl or condition ddl or value tb is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition rowlist is NOT found.");
						}

					} else {
						LogWriter
								.writeToFile("Add New Customer Rule Calculated Condition failed. Add condition btn is NOT found.");
					}
				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Calculated Condition failed. Text values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Calculated Condition failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Calculated Condition.");
		}
	}

	public void addCondition(String name, String con, String value) {
		try {
			WebItem addConditionBtn;
			WebItem nameDDl;
			WebItem conDDL;
			WebItem valueTB;
			WebItemList conditionList;
			WebItem lastListRow;

			getCustomerRuleCondition();

			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && !value.isEmpty()) {
					addConditionBtn = new WebItem(
							By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_btnAddFilter']"),
							customerRuleBodyPanel);

					if (!addConditionBtn.isEmpty()) {
						addConditionBtn.movetoclick();
						initElements();

						conditionList = new WebItemList(
								By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
								customerRuleBodyPanel);

						if (!conditionList.isEmpty()) {
							lastListRow = conditionList.getListLastElement();

							nameDDl = new WebItem(
									By.xpath("td[1]/select[@class='ruleFields']"),
									lastListRow);

							if (!nameDDl.isEmpty()) {
								nameDDl.select(name);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),
										lastListRow);

								if (!conDDL.isEmpty())
									conDDL.select(con);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleConditions_ucRuleConditions_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								valueTB = new WebItem(By.xpath("td[3]/input"),
										lastListRow);

								if (!valueTB.isEmpty())
									valueTB.sendKeys(value);

								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter
										.writeToFile("Add New Customer Rule Condition. Name-"
												+ name
												+ ", Con-"
												+ con
												+ ", Value-" + value + ".");
							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Condition failed. Name ddl or condition ddl or value tb is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Condition failed. Add condition rowlist is NOT found.");
						}

					} else {
						LogWriter
								.writeToFile("Add New Customer Rule Condition failed. Add condition btn is NOT found.");
					}
				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Condition failed. Text values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Condition failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Condition.");
		}
	}

	public void addScores(String name, String con, int score) {
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

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!name.isEmpty() && !con.isEmpty() && score >= 0
						&& score <= 100) {
					addConditionBtn = new WebItem(
							By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_btnAddFilter']"),
							customerRuleBodyPanel);

					if (!addConditionBtn.isEmpty()) {
						addConditionBtn.movetoclick();
						initElements();

						conditionList = new WebItemList(
								By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
								customerRuleBodyPanel);

						if (!conditionList.isEmpty()) {
							lastListRow = conditionList.getListLastElement();

							nameDDl = new WebItem(
									By.xpath("td[1]/select[@class='ruleFields']"),
									lastListRow);

							if (!nameDDl.isEmpty()) {
								nameDDl.select(name);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								conDDL = new WebItem(By.xpath("td[2]/select"),
										lastListRow);

								if (!conDDL.isEmpty())
									conDDL.select(con);

								initElements();
								conditionList = new WebItemList(
										By.xpath("//table[@id='ContentPlaceHolder1_tcCustomRules_tabRuleScores_ucRuleScores_gvConditions']//tr"),
										customerRuleBodyPanel);
								lastListRow = conditionList
										.getListLastElement();
								scoreSlider = new WebItem(
										By.xpath("td[3]//span[@class='irs-single thumb']"),
										lastListRow);
								scoreSlider_alt = new WebItem(
										By.xpath("td[3]//input[@class='slider irs-hidden-input']"),
										lastListRow);

								if (!scoreSlider.isEmpty()
										&& !scoreSlider_alt.isEmpty(false)) {
									scoreSlider.changeinnerHTML(String
											.valueOf(score));
									scoreSlider_alt.changeattribute("value",
											String.valueOf(score));
								}

								confirmOK();
								customerRuleBodyPanel.movetoclick();
								LogWriter
										.writeToFile("Add New Customer Rule Scores. Name-"
												+ name
												+ ", Con-"
												+ con
												+ ", Score-" + score + ".");
							} else {
								LogWriter
										.writeToFile("Add New Customer Rule Scores failed. Name ddl or condition ddl or value tb is NOT found.");
							}

						} else {
							LogWriter
									.writeToFile("Add New Customer Rule Scores failed. Add condition rowlist is NOT found.");
						}

					} else {
						LogWriter
								.writeToFile("Add New Customer Rule Scores failed. Add condition btn is NOT found.");
					}
				} else {
					LogWriter
							.writeToFile("Add New Customer Rule Scores failed. Text and score values are NOT valid.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Scores failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Scores.");
		}
	};

	// for both add and edit
	public String addRuleName(String ruleName) {
		String nameString = "";
		try {
			nameString = ruleName + Utility.getRandomInt(1, 9999);
			WebItem RuleNameTB;
			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				RuleNameTB = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRuleName']"),
						customerRuleBodyPanel);

				if (!RuleNameTB.isEmpty()) {
					RuleNameTB.clear();
					customerRuleBodyPanel.movetoclick();
					RuleNameTB.sendKeys(nameString);
					customerRuleBodyPanel.movetoclick();
				} else {
					LogWriter.writeToFile("Add New Customer Rule Name-"
							+ nameString
							+ " failed. Rule name tb is NOT found.");
				}
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Name-" + nameString
								+ " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Name.");
		}

		return nameString;
	}

	// for both add and edit
	public void addRuleDesp(String RuleDesp) {
		try {
			WebItem RuleDespTA;
			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				RuleDespTA = new WebItem(
						By.xpath("//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbDescription']"),
						customerRuleBodyPanel);

				if (!RuleDespTA.isEmpty()) {
					RuleDespTA.clear();
					customerRuleBodyPanel.movetoclick();
					RuleDespTA.sendKeys(RuleDesp);
					customerRuleBodyPanel.movetoclick();
				} else {
					LogWriter.writeToFile("Add New Customer Rule Name-"
							+ RuleDesp
							+ " failed. Rule Desp textarea is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Add New Customer Rule Name-" + RuleDesp
						+ " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Desp.");
		}
	}

	public void changeRuleActive(boolean isActive) {
		try {
			WebItem RuleActive;
			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				RuleActive = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbActive']"),
						customerRuleBodyPanel);

				if (!RuleActive.isEmpty()) {
					RuleActive.activeCB(isActive);

					customerRuleBodyPanel.movetoclick();

				} else {
					LogWriter.writeToFile("Add New Customer Rule Active-"
							+ isActive
							+ " failed. Rule Active CB is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Change New Customer Rule Active-"
						+ isActive + " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change New Customer Rule Active-"
							+ isActive + ".");
		}
	}

	public void changeRuleCopyToAll(boolean isCopy) {
		try {
			WebItem RuleCopy;
			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				RuleCopy = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbCopyToAll']"),
						customerRuleBodyPanel);

				if (!RuleCopy.isEmpty()) {
					RuleCopy.activeCB(isCopy);

					customerRuleBodyPanel.movetoclick();
				} else {
					LogWriter.writeToFile("Add New Customer Rule CopyToAll-"
							+ isCopy
							+ " failed. Rule CopyToAll CB is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Change New Customer Rule CopyToAll-"
						+ isCopy + " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change New Customer Rule CopyToAll-"
							+ isCopy + ".");
		}
	}

	public void assignRuleScoreActive(boolean isActive) {
		try {
			WebItem assignScoreCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {

				assignScoreCB = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbAssignScore']"));

				if (!assignScoreCB.isEmpty()) {

					assignScoreCB.activeCB(isActive);
					confirmOK();
				} else {
					LogWriter.writeToFile("Change Customer Rule score active-"
							+ isActive + " failed. Assign CB is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Change Customer Rule score active-"
						+ isActive + " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change Customer Rule score active-"
							+ isActive + ".");
		}

	}

	public void assignRuleEmailActive(boolean isActive) {
		try {
			WebItem assignScoreCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {

				assignScoreCB = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbSendEmail']"));

				if (!assignScoreCB.isEmpty()) {

					assignScoreCB.activeCB(isActive);
					confirmOK();
				} else {
					LogWriter.writeToFile("Change Customer Rule email active-"
							+ isActive + " failed. Send CB is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Change Customer Rule email active-"
						+ isActive + " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change Customer Rule email active-"
							+ isActive + ".");
		}
	}

	public void assignRuleSMSActive(boolean isActive) {
		try {
			WebItem assignScoreCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {

				assignScoreCB = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbSendSMS']"));

				if (!assignScoreCB.isEmpty()) {

					assignScoreCB.activeCB(isActive);
					confirmOK();
				} else {
					LogWriter.writeToFile("Change Customer Rule SMS active-"
							+ isActive + " failed. Send CB is NOT found.");
				}
			} else {
				LogWriter.writeToFile("Change Customer Rule SMS active-"
						+ isActive + " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change Customer Rule SMS active-"
							+ isActive + ".");
		}
	}

	public void assignRuleScore(int riskScore, int defaultRiskScore,
			boolean isOverride) {
		try {
			WebItem scoreSlider;
			WebItem scoreSlider_alt;
			WebItem defScoreSlider;
			WebItem defScoreSlider_alt;

			WebItem assignScoreCB;
			WebItem overrideScoreCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (riskScore >= 0 && riskScore <= 100) {
					scoreSlider = new WebItem(
							By.xpath("//tr[@class='riskSliderPopup bound']//span[@class='irs-single thumb']"),
							customerRuleBodyPanel);
					scoreSlider_alt = new WebItem(
							By.xpath("//tr[@class='riskSliderPopup bound']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRisk']"),
							customerRuleBodyPanel);

					if (!scoreSlider.isEmpty() && !scoreSlider_alt.isEmpty()) {
						scoreSlider.changeinnerHTML(String.valueOf(riskScore));
						scoreSlider_alt.changeattribute("value",
								String.valueOf(riskScore));
						confirmOK();
						customerRuleBodyPanel.movetoclick();

						LogWriter.writeToFile("Change New Customer Rule Score-"
								+ riskScore + ".");

					} else {
						LogWriter.writeToFile("Change New Customer Rule Score-"
								+ riskScore + " failed. Slider is NOT found.");
					}

				} else {
					LogWriter.writeToFile("Change New Customer Rule Score-"
							+ riskScore + " failed. Score is NOT valid.");
				}

				if (defaultRiskScore >= 0 && defaultRiskScore <= 100) {
					defScoreSlider = new WebItem(
							By.xpath("//tr[@class='defRiskSliderPopup bound']//span[@class='irs-single thumb']"),
							customerRuleBodyPanel);
					defScoreSlider_alt = new WebItem(
							By.xpath("//tr[@class='defRiskSliderPopup bound']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbDefaultRisk']"),
							customerRuleBodyPanel);

					if (!defScoreSlider.isEmpty()
							&& !defScoreSlider_alt.isEmpty()) {
						defScoreSlider.changeinnerHTML(String
								.valueOf(defaultRiskScore));
						defScoreSlider_alt.changeattribute("value",
								String.valueOf(defaultRiskScore));
						confirmOK();
						customerRuleBodyPanel.movetoclick();

						LogWriter
								.writeToFile("Change New Customer Rule Default Score-"
										+ defaultRiskScore + ".");
					} else {
						LogWriter
								.writeToFile("Change New Customer Rule Default Score-"
										+ defaultRiskScore
										+ " failed. Slider is NOT found.");
					}
				} else {
					LogWriter
							.writeToFile("Change New Customer Rule Default Score-"
									+ defaultRiskScore
									+ " failed. Default Score is NOT valid.");
				}

				overrideScoreCB = new WebItem(
						By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_cbAssignTScore']"),
						customerRuleBodyPanel);

				if (!overrideScoreCB.isEmpty()) {
					overrideScoreCB.activeCB(isOverride);
				} else {
					LogWriter
							.writeToFile("Override Transaction Score in Change New Customer Rule Score-"
									+ riskScore
									+ " and Default Score-"
									+ defaultRiskScore
									+ " failed. Override Score CB is NOT found.");
				}

			} else {
				LogWriter.writeToFile("Change New Customer Rule Score-"
						+ riskScore + " and Default Score-" + defaultRiskScore
						+ " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change New Customer Rule Score-"
							+ riskScore
							+ " and Default Score-"
							+ defaultRiskScore + ".");
		}
	}

	public void assignRuleEmail(String emailAddress, String emailText) {
		try {
			WebItem addressTB;
			WebItem textTB;

			WebItem assignEmailCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {
				if (!emailAddress.isEmpty() && !emailText.isEmpty()) {

					addressTB = new WebItem(
							By.xpath("//tr[@class='pnEmail']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbEmailAddress']"),
							customerRuleBodyPanel);
					textTB = new WebItem(
							By.xpath("//tr[@class='pnEmail']//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbEmailText']"),
							customerRuleBodyPanel);

					if (!addressTB.isEmpty() && !textTB.isEmpty()) {

						addressTB.clear();
						textTB.clear();

						customerRuleBodyPanel.movetoclick();

						addressTB.sendKeys(emailAddress);
						textTB.sendKeys(emailText);
						confirmOK();
						customerRuleBodyPanel.movetoclick();

						LogWriter
								.writeToFile("Change New Customer Rule EmailAddr-"
										+ emailAddress
										+ " and EmailText-"
										+ emailText + ".");

					} else {
						LogWriter
								.writeToFile("Change New Customer Rule EmailAddr-"
										+ emailAddress
										+ " and EmailText-"
										+ emailText
										+ " failed. TB is NOT valid.");
					}

				} else {
					LogWriter.writeToFile("Change New Customer Rule EmailAddr-"
							+ emailAddress + " and EmailText-" + emailText
							+ " failed. Text is NOT valid.");
				}
			} else {
				LogWriter.writeToFile("Change New Customer Rule EmailAddr-"
						+ emailAddress + " and EmailText-" + emailText
						+ " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change New Customer Rule EmailAddr-"
							+ emailAddress
							+ " and EmailText-"
							+ emailText
							+ ".");
		}
	}

	public void assignRuleSMS(String smsAddress, String smsText) {
		try {
			WebItem addressTB;
			WebItem textTB;

			WebItem assignSMSCB;

			getCustomerRuleBasicInfo();
			initElements();

			if (!customerRuleBodyPanel.isEmpty()) {

				if (!smsAddress.isEmpty() && !smsText.isEmpty()) {

					addressTB = new WebItem(
							By.xpath("//tr[@class='pnSMS']//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbSmsPhoneNumber']"),
							customerRuleBodyPanel);
					textTB = new WebItem(
							By.xpath("//tr[@class='pnSMS']//textarea[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbSmsMessage']"),
							customerRuleBodyPanel);

					if (!addressTB.isEmpty() && !textTB.isEmpty()) {
						addressTB.clear();
						textTB.clear();

						customerRuleBodyPanel.movetoclick();

						addressTB.sendKeys(smsAddress);
						textTB.sendKeys(smsText);

						confirmOK();

						customerRuleBodyPanel.movetoclick();

						LogWriter
								.writeToFile("Change New Customer Rule SMSNumber-"
										+ smsAddress
										+ " and SMSText-"
										+ smsText + ".");

					} else {
						LogWriter
								.writeToFile("Change New Customer Rule SMSNumber-"
										+ smsAddress
										+ " and SMSText-"
										+ smsText + " failed. TB is NOT valid.");
					}

				} else {
					LogWriter.writeToFile("Change New Customer Rule SMSNumber-"
							+ smsAddress + " and SMSText-" + smsText
							+ " failed. Text is NOT valid.");
				}

			} else {
				LogWriter.writeToFile("Change New Customer Rule SMSNumber-"
						+ smsAddress + " and SMSText-" + smsText
						+ " failed. Rule body panel is NOT found.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Change New Customer Rule SMSNumber-"
							+ smsAddress + " and SMSText-" + smsText + ".");
		}
	}

	public void getCustomerRuleBasicInfo() {
		try {

			initElements();
			if (!customerRuleHeadList.isEmpty()
					&& customerRuleHeadList.size() == Config.CONFIG
							.getCustomerRuleNewTabNum()) {
				customerRuleHeadList.getListElement("Rule Info and Actions")
						.movetoclick();
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Basic Info failed. Customer Rule Head List is NOT found or number is NOT valid.");

			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Basic Info.");
		}
	}

	public void getCustomerRuleCalculatedCondition() {
		try {

			initElements();

			if (!customerRuleHeadList.isEmpty()
					&& customerRuleHeadList.size() == Config.CONFIG
							.getCustomerRuleNewTabNum()) {
				customerRuleHeadList.getListElement(
						"Rule Calculated Conditions").movetoclick();
				;
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Calculated Condition failed. Customer Rule Head List is NOT found or number is NOT valid.");

			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Calculated Condition.");
		}
	}

	public void getCustomerRuleCondition() {
		try {

			initElements();

			if (!customerRuleHeadList.isEmpty()
					&& customerRuleHeadList.size() == Config.CONFIG
							.getCustomerRuleNewTabNum()) {
				customerRuleHeadList.getListElement("Rule Conditions")
						.movetoclick();
				;
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Condition failed. Customer Rule Head List is NOT found or number is NOT valid.");

			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Condition.");
		}
	}

	public void getCustomerRuleScore() {
		try {

			initElements();

			if (!customerRuleHeadList.isEmpty()
					&& customerRuleHeadList.size() == Config.CONFIG
							.getCustomerRuleNewTabNum()) {
				customerRuleHeadList.getListElement("Rule Scores")
						.movetoclick();
				;
			} else {
				LogWriter
						.writeToFile("Add New Customer Rule Score failed. Customer Rule Head List is NOT found or number is NOT valid.");

			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in Add New Customer Rule Score.");
		}
	}

	public void addManConditionFieldAndSubmit() {
		WebItem conditionItem = new WebItem(
				By.xpath("//input[@class='highlightCss']"));

		if (!conditionItem.isEmpty()) {
			conditionItem.sendKeys(Config.CONFIG
					.getCustomerRuleLORConditionValue());

			if (!customerRuleBodyPanel.isEmpty())
				customerRuleBodyPanel.movetoclick();

			LogWriter.writeToFile("Add mandatory field.");
			submitChange();
		}
	}

	public void changeRuleNameAndSubmit() {
		String ruleNameString = "VeraTest" + Utility.getRandomInt(1, 9999);
		try {
			initElements();
			WebItem ruleNameTB = new WebItem(
					By.xpath("//input[@id='ContentPlaceHolder1_tcCustomRules_tabRuleInfoActions_tbRuleName']"));

			if (!ruleNameTB.isEmpty()) {
				ruleNameTB.sendKeys(ruleNameString);

				LogWriter.writeToFile("Change rule name for LOR to "
						+ ruleNameString + ".");
				submitChange();
			} else {
				LogWriter.writeToFile("Change rule name for LOR to "
						+ ruleNameString
						+ " failed. Rule Name textBox is NOT found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter
					.writeToFile("Exception happens in change rule name for LOR to "
							+ ruleNameString + ".");
		}

	}

	public void submitChange() {
		try {
			initElements();

			if (!submitBtn.isEmpty()) {
				submitBtn.movetoclick();
				confirmLeave(true);
				confirmOK();
				LogWriter.writeToFile("Submit change.");
			} else {
				LogWriter
						.writeToFile("Submit change failed. Submit btn is NOT found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in submit change.");
		}
	}

	public void cancelChange() {
		try {
			initElements();

			if (!cancelBtn.isEmpty()) {
				cancelBtn.movetoclick();
				confirmLeave(true);
				LogWriter.writeToFile("Submit cancel.");
			} else {
				LogWriter
						.writeToFile("Submit cancel failed. Submit btn is NOT found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogWriter.writeToFile("Exception happens in submit cancel.");
		}
	}

	public void closePopup() {
		if (!closebtnItem.isEmpty()) {
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
