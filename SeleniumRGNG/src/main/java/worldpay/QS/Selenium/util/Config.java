package worldpay.QS.Selenium.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;


public class Config {
	
	
	public  static Config CONFIG;
	
    static 
    {
        
         Gson gson = new Gson();
		
		 try {
			BufferedReader brObj = new BufferedReader(new FileReader("config.json"));
			
			CONFIG = gson.fromJson(brObj, Config.class);
			
			
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

    }

	private String FESMerchantId;
	private String FESUserName;
	private String FESPassWord;
	private String FESURL;

	private String LogFilePath;
	private String LogFileName;
		
	private int TimeZoneDDLCount;
	private int LangDDLCount;
	
	private int ChartDespDDLCount;
	private int ProfileNameDDLCount;
	private int PeriodCoveredCount;
	private int ChartTypeCount;
	
	private String RefSearchMC;
	private String RefSearchSRC;
	private String RefSearchCC;
	private String RefSearchCRC;

	private String AuditLogSearchDate;
	
	private int ProfileIndex;
	private String ProfileName;
	
	private String BulkProcessSectionName;
	private int BulkProcessScore;
	
	private String SingleProcessSectionName;
	private int SingleProcessParameterIndex;
	private int SingleProcessParameterScore;
	
	private int CustomerRuleScore;
	private String CustomerRuleSearchValue;
	private int CustomerRuleLORIndex;
	
	private String CustomerRuleLORName;
	private String CustomerRuleLORConditionValue;

	private String SearchSectionName;
	private String SearchValue;
	
	private String EditParameterSectionName;
	private String EditParameterName;
	private String EditPopupSearchValue;
	private int EditPopupPropIndex;
	private String EditPopupAddDesp;
	private String EditPopupAddValue;
	private int EditPopupAddScore;
	private int EditFactorIndex;
	private int EditFactorScore;
	
	
	private int CustomerRuleNewTabNum;
	private String CustomerRuleNewName;
	private String CustomerRuleNewDesp;
	private boolean CustomerRuleIsActive;
	private boolean CustomerRuleIsPropAll;
	private boolean CustomerRuleIsScore;
	private boolean CustomerRuleIsOverrideTrxScore;
	private int CustomerRuleNewScore;
	private int CustomerRuleNewDefaultScore;
	private boolean CustomerRuleIsEmail;
	private String CustomerRuleEmailAddress;
	private String CustomerRuleEmailText;
	private boolean CustomerRuleIsSMS;
	private String CustomerRuleSMSNum;
	private String CustomerRuleSMSMessage;
	
	private boolean CustomerRuleRCCIsOn;
	private String CustomerRuleRCCName;
	private String CustomerRuleRCCCondition;
	private String CustomerRuleRCCValue;
	
	private boolean CustomerRuleRCIsOn;
	private String CustomerRuleRCName;
	private String CustomerRuleRCCondition;
	private String CustomerRuleRCValue;
	
	private boolean CustomerRuleRSIsOn;
	private String CustomerRuleRSName;
	private String CustomerRuleRSCondition;
	private int CustomerRuleRSScore;
	
	
	private String CustomerRuleEditName;
	private String CustomerRuleEditDesp;
	private boolean CustomerRuleEditIsActive;
	private boolean CustomerRuleEditIsPropAll;
	
	private boolean CustomerRuleEditIsScore;
	private boolean CustomerRuleEditIsOverrideTrxScore;
	private int CustomerRuleEditScore;
	private int CustomerRuleEditDefaultScore;
	
	private boolean CustomerRuleEditIsEmail;
	private String CustomerRuleEditEmailAddress;
	private String CustomerRuleEditEmailText;
	private boolean CustomerRuleEditIsSMS;
	private String CustomerRuleEditSMSNum;
	private String CustomerRuleEditSMSMessage;
	
	private boolean CustomerRuleEditRCCIsOn;
	private String CustomerRuleEditRCCName;
	
	
	public String getCustomerRuleEditName() {
		return CustomerRuleEditName;
	}
	public void setCustomerRuleEditName(String customerRuleEditName) {
		CustomerRuleEditName = customerRuleEditName;
	}
	public String getCustomerRuleEditDesp() {
		return CustomerRuleEditDesp;
	}
	public void setCustomerRuleEditDesp(String customerRuleEditDesp) {
		CustomerRuleEditDesp = customerRuleEditDesp;
	}
	public boolean isCustomerRuleEditIsActive() {
		return CustomerRuleEditIsActive;
	}
	public void setCustomerRuleEditIsActive(boolean customerRuleEditIsActive) {
		CustomerRuleEditIsActive = customerRuleEditIsActive;
	}
	public boolean isCustomerRuleEditIsPropAll() {
		return CustomerRuleEditIsPropAll;
	}
	public void setCustomerRuleEditIsPropAll(boolean customerRuleEditIsPropAll) {
		CustomerRuleEditIsPropAll = customerRuleEditIsPropAll;
	}
	public boolean isCustomerRuleEditIsScore() {
		return CustomerRuleEditIsScore;
	}
	public void setCustomerRuleEditIsScore(boolean customerRuleEditIsScore) {
		CustomerRuleEditIsScore = customerRuleEditIsScore;
	}
	public boolean isCustomerRuleEditIsOverrideTrxScore() {
		return CustomerRuleEditIsOverrideTrxScore;
	}
	public void setCustomerRuleEditIsOverrideTrxScore(
			boolean customerRuleEditIsOverrideTrxScore) {
		CustomerRuleEditIsOverrideTrxScore = customerRuleEditIsOverrideTrxScore;
	}
	
	public int getCustomerRuleEditScore() {
		return CustomerRuleEditScore;
	}
	public void setCustomerRuleEditScore(int customerRuleEditScore) {
		CustomerRuleEditScore = customerRuleEditScore;
	}
	public int getCustomerRuleEditDefaultScore() {
		return CustomerRuleEditDefaultScore;
	}
	public void setCustomerRuleEditDefaultScore(int customerRuleEditDefaultScore) {
		CustomerRuleEditDefaultScore = customerRuleEditDefaultScore;
	}
	public boolean isCustomerRuleEditIsEmail() {
		return CustomerRuleEditIsEmail;
	}
	public void setCustomerRuleEditIsEmail(boolean customerRuleEditIsEmail) {
		CustomerRuleEditIsEmail = customerRuleEditIsEmail;
	}
	public String getCustomerRuleEditEmailAddress() {
		return CustomerRuleEditEmailAddress;
	}
	public void setCustomerRuleEditEmailAddress(String customerRuleEditEmailAddress) {
		CustomerRuleEditEmailAddress = customerRuleEditEmailAddress;
	}
	public String getCustomerRuleEditEmailText() {
		return CustomerRuleEditEmailText;
	}
	public void setCustomerRuleEditEmailText(String customerRuleEditEmailText) {
		CustomerRuleEditEmailText = customerRuleEditEmailText;
	}
	public boolean isCustomerRuleEditIsSMS() {
		return CustomerRuleEditIsSMS;
	}
	public void setCustomerRuleEditIsSMS(boolean customerRuleEditIsSMS) {
		CustomerRuleEditIsSMS = customerRuleEditIsSMS;
	}
	public String getCustomerRuleEditSMSNum() {
		return CustomerRuleEditSMSNum;
	}
	public void setCustomerRuleEditSMSNum(String customerRuleEditSMSNum) {
		CustomerRuleEditSMSNum = customerRuleEditSMSNum;
	}
	public String getCustomerRuleEditSMSMessage() {
		return CustomerRuleEditSMSMessage;
	}
	public void setCustomerRuleEditSMSMessage(String customerRuleEditSMSMessage) {
		CustomerRuleEditSMSMessage = customerRuleEditSMSMessage;
	}
	public boolean isCustomerRuleEditRCCIsOn() {
		return CustomerRuleEditRCCIsOn;
	}
	public void setCustomerRuleEditRCCIsOn(boolean customerRuleEditRCCIsOn) {
		CustomerRuleEditRCCIsOn = customerRuleEditRCCIsOn;
	}
	public String getCustomerRuleEditRCCName() {
		return CustomerRuleEditRCCName;
	}
	public void setCustomerRuleEditRCCName(String customerRuleEditRCCName) {
		CustomerRuleEditRCCName = customerRuleEditRCCName;
	}
	public String getCustomerRuleEditRCCCondition() {
		return CustomerRuleEditRCCCondition;
	}
	public void setCustomerRuleEditRCCCondition(String customerRuleEditRCCCondition) {
		CustomerRuleEditRCCCondition = customerRuleEditRCCCondition;
	}
	public String getCustomerRuleEditRCCValue() {
		return CustomerRuleEditRCCValue;
	}
	public void setCustomerRuleEditRCCValue(String customerRuleEditRCCValue) {
		CustomerRuleEditRCCValue = customerRuleEditRCCValue;
	}
	public boolean isCustomerRuleEditRCIsOn() {
		return CustomerRuleEditRCIsOn;
	}
	public void setCustomerRuleEditRCIsOn(boolean customerRuleEditRCIsOn) {
		CustomerRuleEditRCIsOn = customerRuleEditRCIsOn;
	}
	public String getCustomerRuleEditRCName() {
		return CustomerRuleEditRCName;
	}
	public void setCustomerRuleEditRCName(String customerRuleEditRCName) {
		CustomerRuleEditRCName = customerRuleEditRCName;
	}
	public String getCustomerRuleEditRCCondition() {
		return CustomerRuleEditRCCondition;
	}
	public void setCustomerRuleEditRCCondition(String customerRuleEditRCCondition) {
		CustomerRuleEditRCCondition = customerRuleEditRCCondition;
	}
	public String getCustomerRuleEditRCValue() {
		return CustomerRuleEditRCValue;
	}
	public void setCustomerRuleEditRCValue(String customerRuleEditRCValue) {
		CustomerRuleEditRCValue = customerRuleEditRCValue;
	}
	public boolean isCustomerRuleEditRSIsOn() {
		return CustomerRuleEditRSIsOn;
	}
	public void setCustomerRuleEditRSIsOn(boolean customerRuleEditRSIsOn) {
		CustomerRuleEditRSIsOn = customerRuleEditRSIsOn;
	}
	public String getCustomerRuleEditRSName() {
		return CustomerRuleEditRSName;
	}
	public void setCustomerRuleEditRSName(String customerRuleEditRSName) {
		CustomerRuleEditRSName = customerRuleEditRSName;
	}
	public String getCustomerRuleEditRSCondition() {
		return CustomerRuleEditRSCondition;
	}
	public void setCustomerRuleEditRSCondition(String customerRuleEditRSCondition) {
		CustomerRuleEditRSCondition = customerRuleEditRSCondition;
	}
	public int getCustomerRuleEditRSScore() {
		return CustomerRuleEditRSScore;
	}
	public void setCustomerRuleEditRSScore(int customerRuleEditRSScore) {
		CustomerRuleEditRSScore = customerRuleEditRSScore;
	}
	private String CustomerRuleEditRCCCondition;
	private String CustomerRuleEditRCCValue;
	
	private boolean CustomerRuleEditRCIsOn;
	private String CustomerRuleEditRCName;
	private String CustomerRuleEditRCCondition;
	private String CustomerRuleEditRCValue;
	
	private boolean CustomerRuleEditRSIsOn;
	private String CustomerRuleEditRSName;
	private String CustomerRuleEditRSCondition;
	private int CustomerRuleEditRSScore;
	
	public boolean isCustomerRuleRCCIsOn() {
		return CustomerRuleRCCIsOn;
	}
	public void setCustomerRuleRCCIsOn(boolean customerRuleRCCIsOn) {
		CustomerRuleRCCIsOn = customerRuleRCCIsOn;
	}
	public boolean isCustomerRuleRCIsOn() {
		return CustomerRuleRCIsOn;
	}
	public void setCustomerRuleRCIsOn(boolean customerRuleRCIsOn) {
		CustomerRuleRCIsOn = customerRuleRCIsOn;
	}
	public boolean isCustomerRuleRSIsOn() {
		return CustomerRuleRSIsOn;
	}
	public void setCustomerRuleRSIsOn(boolean customerRuleRSIsOn) {
		CustomerRuleRSIsOn = customerRuleRSIsOn;
	}
	public boolean isCustomerRuleIsOverrideTrxScore() {
		return CustomerRuleIsOverrideTrxScore;
	}
	public void setCustomerRuleIsOverrideTrxScore(
			boolean customerRuleIsOverrideTrxScore) {
		CustomerRuleIsOverrideTrxScore = customerRuleIsOverrideTrxScore;
	}
	public int getCustomerRuleNewTabNum() {
		return CustomerRuleNewTabNum;
	}
	public void setCustomerRuleNewTabNum(int customerRuleNewTabNum) {
		CustomerRuleNewTabNum = customerRuleNewTabNum;
	}
	public String getCustomerRuleNewName() {
		return CustomerRuleNewName;
	}
	public void setCustomerRuleNewName(String customerRuleNewName) {
		CustomerRuleNewName = customerRuleNewName;
	}
	public String getCustomerRuleNewDesp() {
		return CustomerRuleNewDesp;
	}
	public void setCustomerRuleNewDesp(String customerRuleNewDesp) {
		CustomerRuleNewDesp = customerRuleNewDesp;
	}
	public boolean isCustomerRuleIsActive() {
		return CustomerRuleIsActive;
	}
	public void setCustomerRuleIsActive(boolean customerRuleIsActive) {
		CustomerRuleIsActive = customerRuleIsActive;
	}
	public boolean isCustomerRuleIsPropAll() {
		return CustomerRuleIsPropAll;
	}
	public void setCustomerRuleIsPropAll(boolean customerRuleIsPropAll) {
		CustomerRuleIsPropAll = customerRuleIsPropAll;
	}
	public boolean isCustomerRuleIsScore() {
		return CustomerRuleIsScore;
	}
	public void setCustomerRuleIsScore(boolean customerRuleIsScore) {
		CustomerRuleIsScore = customerRuleIsScore;
	}
	public int getCustomerRuleNewScore() {
		return CustomerRuleNewScore;
	}
	public void setCustomerRuleNewScore(int customerRuleNewScore) {
		CustomerRuleNewScore = customerRuleNewScore;
	}
	public int getCustomerRuleNewDefaultScore() {
		return CustomerRuleNewDefaultScore;
	}
	public void setCustomerRuleNewDefaultScore(int customerRuleNewDefaultScore) {
		CustomerRuleNewDefaultScore = customerRuleNewDefaultScore;
	}
	public boolean isCustomerRuleIsEmail() {
		return CustomerRuleIsEmail;
	}
	public void setCustomerRuleIsEmail(boolean customerRuleIsEmail) {
		CustomerRuleIsEmail = customerRuleIsEmail;
	}
	public String getCustomerRuleEmailAddress() {
		return CustomerRuleEmailAddress;
	}
	public void setCustomerRuleEmailAddress(String customerRuleEmailAddress) {
		CustomerRuleEmailAddress = customerRuleEmailAddress;
	}
	public String getCustomerRuleEmailText() {
		return CustomerRuleEmailText;
	}
	public void setCustomerRuleEmailText(String customerRuleEmailText) {
		CustomerRuleEmailText = customerRuleEmailText;
	}
	public boolean isCustomerRuleIsSMS() {
		return CustomerRuleIsSMS;
	}
	public void setCustomerRuleIsSMS(boolean customerRuleIsSMS) {
		CustomerRuleIsSMS = customerRuleIsSMS;
	}
	public String getCustomerRuleSMSNum() {
		return CustomerRuleSMSNum;
	}
	public void setCustomerRuleSMSNum(String customerRuleSMSNum) {
		CustomerRuleSMSNum = customerRuleSMSNum;
	}
	public String getCustomerRuleSMSMessage() {
		return CustomerRuleSMSMessage;
	}
	public void setCustomerRuleSMSMessage(String customerRuleSMSMessage) {
		CustomerRuleSMSMessage = customerRuleSMSMessage;
	}
	public String getCustomerRuleRCCName() {
		return CustomerRuleRCCName;
	}
	public void setCustomerRuleRCCName(String customerRuleRCCName) {
		CustomerRuleRCCName = customerRuleRCCName;
	}
	public String getCustomerRuleRCCCondition() {
		return CustomerRuleRCCCondition;
	}
	public void setCustomerRuleRCCCondition(String customerRuleRCCCondition) {
		CustomerRuleRCCCondition = customerRuleRCCCondition;
	}
	public String getCustomerRuleRCCValue() {
		return CustomerRuleRCCValue;
	}
	public void setCustomerRuleRCCValue(String customerRuleRCCValue) {
		CustomerRuleRCCValue = customerRuleRCCValue;
	}
	public String getCustomerRuleRCName() {
		return CustomerRuleRCName;
	}
	public void setCustomerRuleRCName(String customerRuleRCName) {
		CustomerRuleRCName = customerRuleRCName;
	}
	public String getCustomerRuleRCCondition() {
		return CustomerRuleRCCondition;
	}
	public void setCustomerRuleRCCondition(String customerRuleRCCondition) {
		CustomerRuleRCCondition = customerRuleRCCondition;
	}
	public String getCustomerRuleRCValue() {
		return CustomerRuleRCValue;
	}
	public void setCustomerRuleRCValue(String customerRuleRCValue) {
		CustomerRuleRCValue = customerRuleRCValue;
	}
	public String getCustomerRuleRSName() {
		return CustomerRuleRSName;
	}
	public void setCustomerRuleRSName(String customerRuleRSName) {
		CustomerRuleRSName = customerRuleRSName;
	}
	public String getCustomerRuleRSCondition() {
		return CustomerRuleRSCondition;
	}
	public void setCustomerRuleRSCondition(String customerRuleRSCondition) {
		CustomerRuleRSCondition = customerRuleRSCondition;
	}
	public int getCustomerRuleRSScore() {
		return CustomerRuleRSScore;
	}
	public void setCustomerRuleRSScore(int customerRuleRSScore) {
		CustomerRuleRSScore = customerRuleRSScore;
	}
	public String getCustomerRuleLORConditionValue() {
		return CustomerRuleLORConditionValue;
	}
	public void setCustomerRuleLORConditionValue(
			String customerRuleLORConditionValue) {
		CustomerRuleLORConditionValue = customerRuleLORConditionValue;
	}

	public String getCustomerRuleLORName() {
		return CustomerRuleLORName;
	}
	public void setCustomerRuleLORName(String customerRuleLORName) {
		CustomerRuleLORName = customerRuleLORName;
	}
	public int getCustomerRuleLORIndex() {
		return CustomerRuleLORIndex;
	}
	public void setCustomerRuleLORIndex(int customerRuleLORIndex) {
		CustomerRuleLORIndex = customerRuleLORIndex;
	}
	public int getEditFactorIndex() {
		return EditFactorIndex;
	}
	public void setEditFactorIndex(int editFactorIndex) {
		EditFactorIndex = editFactorIndex;
	}
	public int getEditFactorScore() {
		return EditFactorScore;
	}
	public void setEditFactorScore(int editFactorScore) {
		EditFactorScore = editFactorScore;
	}
	public int getEditPopupPropIndex() {
		return EditPopupPropIndex;
	}
	public void setEditPopupPropIndex(int editPopupPropIndex) {
		EditPopupPropIndex = editPopupPropIndex;
	}
	public String getCustomerRuleSearchValue() {
		return CustomerRuleSearchValue;
	}
	public void setCustomerRuleSearchValue(String customerRuleSearchValue) {
		CustomerRuleSearchValue = customerRuleSearchValue;
	}
	public int getCustomerRuleScore() {
		return CustomerRuleScore;
	}
	public void setCustomerRuleScore(int customerRuleScore) {
		CustomerRuleScore = customerRuleScore;
	}
	public String getSingleProcessSectionName() {
		return SingleProcessSectionName;
	}
	public void setSingleProcessSectionName(String singleProcessSectionName) {
		SingleProcessSectionName = singleProcessSectionName;
	}
	public int getSingleProcessParameterIndex() {
		return SingleProcessParameterIndex;
	}
	public void setSingleProcessParameterIndex(int singleProcessParameterIndex) {
		SingleProcessParameterIndex = singleProcessParameterIndex;
	}
	public int getSingleProcessParameterScore() {
		return SingleProcessParameterScore;
	}
	public void setSingleProcessParameterScore(int singleProcessParameterScore) {
		SingleProcessParameterScore = singleProcessParameterScore;
	}
	public int getBulkProcessScore() {
		return BulkProcessScore;
	}
	public void setBulkProcessScore(int bulkProcessScore) {
		BulkProcessScore = bulkProcessScore;
	}
	public String getBulkProcessSectionName() {
		return BulkProcessSectionName;
	}
	public void setBulkProcessSectionName(String bulkProcessSectionName) {
		BulkProcessSectionName = bulkProcessSectionName;
	}
	public int getProfileIndex() {
		return ProfileIndex;
	}
	public void setProfileIndex(int profileIndex) {
		ProfileIndex = profileIndex;
	}
	public String getProfileName() {
		return ProfileName;
	}
	public void setProfileName(String profileName) {
		ProfileName = profileName;
	}
	public int getEditPopupAddScore() {
		return EditPopupAddScore;
	}
	public void setEditPopupAddScore(int editPopupAddScore) {
		EditPopupAddScore = editPopupAddScore;
	}
	public String getEditPopupAddDesp() {
		return EditPopupAddDesp;
	}
	public void setEditPopupAddDesp(String editPopupAddDesp) {
		EditPopupAddDesp = editPopupAddDesp;
	}
	public String getEditPopupAddValue() {
		return EditPopupAddValue;
	}
	public void setEditPopupAddValue(String editPopupAddValue) {
		EditPopupAddValue = editPopupAddValue;
	}
	
	public String getSearchSectionName() {
		return SearchSectionName;
	}
	public void setSearchSectionName(String searchSectionName) {
		SearchSectionName = searchSectionName;
	}
	public String getEditParameterSectionName() {
		return EditParameterSectionName;
	}
	public void setEditParameterSectionName(String editParameterSectionName) {
		EditParameterSectionName = editParameterSectionName;
	}
	public String getEditParameterName() {
		return EditParameterName;
	}
	public void setEditParameterName(String editParameterName) {
		EditParameterName = editParameterName;
	}
	public String getSearchValue() {
		return SearchValue;
	}
	public void setSearchValue(String searchValue) {
		SearchValue = searchValue;
	}
	public String getEditPopupSearchValue() {
		return EditPopupSearchValue;
	}
	public void setEditPopupSearchValue(String editPopupSearchValue) {
		EditPopupSearchValue = editPopupSearchValue;
	}
	public String getAuditLogSearchDate() {
		return AuditLogSearchDate;
	}
	public void setAuditLogSearchDate(String auditLogSearchDate) {
		AuditLogSearchDate = auditLogSearchDate;
	}
	public String getRefSearchMC() {
		return RefSearchMC;
	}
	public void setRefSearchMC(String refSearchMC) {
		RefSearchMC = refSearchMC;
	}
	public String getRefSearchSRC() {
		return RefSearchSRC;
	}
	public void setRefSearchSRC(String refSearchSRC) {
		RefSearchSRC = refSearchSRC;
	}
	public String getRefSearchCC() {
		return RefSearchCC;
	}
	public void setRefSearchCC(String refSearchCC) {
		RefSearchCC = refSearchCC;
	}
	public String getRefSearchCRC() {
		return RefSearchCRC;
	}
	public void setRefSearchCRC(String refSearchCRC) {
		RefSearchCRC = refSearchCRC;
	}
	public static Config getCONFIG() {
		return CONFIG;
	}
	public static void setCONFIG(Config cONFIG) {
		CONFIG = cONFIG;
	}
	public int getTimeZoneDDLCount() {
		return TimeZoneDDLCount;
	}
	public void setTimeZoneDDLCount(int timeZoneDDLCount) {
		TimeZoneDDLCount = timeZoneDDLCount;
	}
	public int getLangDDLCount() {
		return LangDDLCount;
	}
	public void setLangDDLCount(int langDDLCount) {
		LangDDLCount = langDDLCount;
	}
	public int getChartDespDDLCount() {
		return ChartDespDDLCount;
	}
	public void setChartDespDDLCount(int chartDespDDLCount) {
		ChartDespDDLCount = chartDespDDLCount;
	}
	public int getProfileNameDDLCount() {
		return ProfileNameDDLCount;
	}
	public void setProfileNameDDLCount(int profileNameDDLCount) {
		ProfileNameDDLCount = profileNameDDLCount;
	}
	public int getPeriodCoveredCount() {
		return PeriodCoveredCount;
	}
	public void setPeriodCoveredCount(int periodCoveredCount) {
		PeriodCoveredCount = periodCoveredCount;
	}
	public int getChartTypeCount() {
		return ChartTypeCount;
	}
	public void setChartTypeCount(int chartTypeCount) {
		ChartTypeCount = chartTypeCount;
	}
	public String getFESMerchantId() {
		return FESMerchantId;
	}
	public void setFESMerchantId(String fESMerchantId) {
		FESMerchantId = fESMerchantId;
	}
	public String getFESUserName() {
		return FESUserName;
	}
	public void setFESUserName(String fESUserName) {
		FESUserName = fESUserName;
	}
	public String getFESPassWord() {
		return FESPassWord;
	}
	public void setFESPassWord(String fESPassWord) {
		FESPassWord = fESPassWord;
	}
	public String getFESURL() {
		return FESURL;
	}
	public void setFESURL(String fESURL) {
		FESURL = fESURL;
	}
	public String getLogFilePath() {
		return LogFilePath;
	}
	public void setLogFilePath(String logFilePath) {
		LogFilePath = logFilePath;
	}
	public String getLogFileName() {
		return LogFileName;
	}
	public void setLogFileName(String logFileName) {
		LogFileName = logFileName;
	}

}
