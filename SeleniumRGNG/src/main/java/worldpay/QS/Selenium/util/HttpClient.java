package worldpay.QS.Selenium.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HttpClient {

    private static final String RG_PRIFIX = "StringIn=VersionUsed^3~MerchantId^%s~UserName^%s~UserPassword^%s";
    private static final String RG_SURFIX = "~TransactionType^RG~IsTest^1~TimeOut^10000~OrderNumber^RG_2015923_9:48:19_weinaw~AgeVerify^0~ProfileVerify^0~IDVerify^0~PTVerify^0~DataSharing^0~IsExtended^0~IsAnalysis^0~IsSim^0~TypeOfSale^H~StoreID^1~MOP^C~TRXSource^4~AcctName^ondml~AcctNumber^400068000000000001~ExpDate^092017~AcctNumber2^71623~IssueNumber^7~StartDate^031997~CurrencyId^124~Amount^71~IsMember^7~Company^cpdlp~Gender^M~Title^cp~FirstName^cpdlp~MiddleName^c~LastName^cpdlp~AliasFirstName^cpdlp~AliasMiddleName^w~AliasLastName^wqsjq~Suffix^w~Address1^447wqsjq~Address2^wqsjq~Address3^wqsjq~Address4^44~Address5^44~City^wqsjq~StateCode^wq~ZipCode^wqsjq~CountryCode^CA~PhoneNumber^4473866170~PhoneExtension^447~Email^wqsjq@wqsjq.com~ShipToTitle^wq~ShipToCompany^wqsjq~ShipToFirstName^wqsjq~ShipToMiddleName^w~ShipToLastName^oatim~ShipToSuffix^o~ShipToAddress1^212oatim~ShipToAddress2^oatim~ShipToAddress3^oatim~ShipToCity^oatim~ShipToStateCode^oa~ShipToZipCode^oatim~ShipToCountryCode^US~ShipToPhoneNumber^2125170512~ShipToPhoneExtension^212~REMOTE_ADDR^21.21.21.21~HTTP_ACCEPT_LANGUAGE^en, fr-CA~HTTP_ACCEPT_CHARSET^ISO-8859-1, *, utf-8~DOB^21251705~PurchaseDate^20120612~PassengerPhoneNumber^212517051222432627526806256732~PassengerPhoneExtension^2125170512~SecureId^12345678901234567890~CHEnrolled^Y~TXStatus^Y~IsOneWayTrvl^0~IsDirectTravel^0~IsSeatSale^0~IsWithChild^0~IsFreqFlyer^0~IsPackageTrvl^0~IsFlexTicket^0~IsInsPurchased^0~IsChartered^0~XDate^10012009~ClassOfTicket^Pn~ArrivalAirportCode^LHR~DepartureAirportCode^YUL~PassengerName^John Smith~AirlineCode^BA~BookingSystemTrvl^ABC Travel booking systems~FlightCode^313~PurchaseType^1~AgentTrvl^Travel Agency ABC12~SecProgTrvl^The Security Program~xField1^other~xField2^other~xField3^other~xField4^other~xField5^other~xField6^other~xField7^other~xField8^other~xField9^other~xField10^other~xField11^other~xField12^other~xField13^other~xField14^other~xField15^other~xField16^other~xField17^other~xField18^other~xField19^other~xField20^other~xField21^other~xField22^other~xField23^other~xField24^other~xField25^other~nField1^0.0000~nField2^0.0000~nField3^0.0000~nField4^0.0000~nField5^0.0000~nField6^0.0000~nField7^0.0000~nField8^0.0000~nField9^0.0000~nField10^0.0000~nField11^0.0000~nField12^0.0000~nField13^0.0000~nField14^0.0000~nField15^0.0000~nField16^0.0000~nField17^0.0000~nField18^0.0000~nField19^0.0000~nField20^0.0000~nField21^0.0000~nField22^0.0000~nField23^0.0000~nField24^0.0000~nField25^0.0000";
    private static final String RG_MESSAGE_TAG = "MessageCode";
    private static final String RG_ID_TAG = "GttId";
    
    private static URL url;
    
	private String stlinkurlstring = "";
    
	private String MerchantId= "";
    public String getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(String messageCode) {
		MessageCode = messageCode;
	}

	public String getRGID() {
		return RGID;
	}

	public void setRGID(String rGID) {
		RGID = rGID;
	}


	private String TrxUserName = "";
    private String TrxPassword = "";
    
    private String MessageCode="";
    private String RGID="";
    
    public HttpClient(String stlinkurlstring) {
		this.stlinkurlstring = stlinkurlstring;
	}

	public String getStlinkurlstring() {
		return stlinkurlstring;
	}
	
    public String getMerchantId() {
		return MerchantId;
	}

	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}

	public String getTrxUserName() {
		return TrxUserName;
	}

	public void setTrxUserName(String trxUserName) {
		TrxUserName = trxUserName;
	}

	public String getTrxPassword() {
		return TrxPassword;
	}

	public void setTrxPassword(String trxPassword) {
		TrxPassword = trxPassword;
	}

    public HttpClient(String stlinkurlstring, String merchantId,
			String trxUserName, String trxPassword) {
    	
		this.stlinkurlstring = stlinkurlstring;
		this.MerchantId = merchantId;
		this.TrxUserName = trxUserName;
		this.TrxPassword = trxPassword;
	}

	public void sendTransaction() 
	{
		String rgrequest = String.format(RG_PRIFIX, MerchantId, TrxUserName, TrxPassword) + RG_SURFIX;
		String rgresponseString = "";
		rgresponseString = postData(rgrequest);
		
		if(!rgresponseString.isEmpty()&&rgresponseString!=null)
		{
			parseStringOut(rgresponseString);
		}
    }
	
	public void parseStringOut(String responseString)
	{
		MessageCode = parseFields(responseString, RG_MESSAGE_TAG);
		RGID = parseFields(responseString, RG_ID_TAG);
	}
	
	public String parseFields(String stringToParse, String fieldName) 
    {
        String fieldValue = "";
        Matcher m = Pattern.compile("(~" + fieldName + ")\\^(\\d+)",
                     Pattern.CASE_INSENSITIVE).matcher(stringToParse);
        if (m.find()) {
               fieldValue = m.group(2);
        }
        return fieldValue;
    }


	private String postData(String message) {
	    HttpURLConnection connection = null;
	    
	    try 
	    {

            url = new URL(stlinkurlstring);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                                            "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                                            connection.getOutputStream());
            wr.writeBytes(message);
            wr.flush();
            wr.close();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
            }
            rd.close();
            return response.toString();
	    } catch (SocketTimeoutException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	
	    } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	    } finally {
	                    if (connection != null) {
	                                    connection.disconnect();
	                    }
	
	    }
	    return null;

}


}
