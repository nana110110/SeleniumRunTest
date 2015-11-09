package worldpay.QS.Selenium.util;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LogListener extends TestListenerAdapter{
	 
    @Override
    public void onTestFailure(ITestResult tr) {
    	LogWriter.writeToFile("");
        LogWriter.writeToFile(tr.getName()+ "--Test method failed.");
        LogWriter.writeToFile("-------------------------------------------------------------------------------------------------\n");
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) {
    	LogWriter.writeToFile("");
    	LogWriter.writeToFile(tr.getName()+ "--Test method skipped.");
    	LogWriter.writeToFile("-------------------------------------------------------------------------------------------------\n");
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr) {
    
    	LogWriter.writeToFile("");
    	LogWriter.writeToFile(tr.getName()+ "--Test method success.");
    	LogWriter.writeToFile("-------------------------------------------------------------------------------------------------\n");
    }
    
    @Override
    public void onTestStart(ITestResult tr)
    {
    	LogWriter.writeToFile("\n-------------------------------------------------------------------------------------------------");
    	LogWriter.writeToFile(tr.getInstanceName()+" - "+tr.getName()+ "--Test method started.");
    	Object[] temp=tr.getParameters();
    	int nParameter = temp.length;
    	
    	for(int ii=0; ii<nParameter; ii++)
    	{
    		LogWriter.writeToFile("Parameter "+(ii+1)+": "+ temp[ii].toString());
    		
    	}
    }
	 


}