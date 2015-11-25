package worldpay.QS.Selenium.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogWriter{
	private static BufferedWriter bw = null;
	
	public static boolean openLogToWrite(String LogFileName, String LogFilePath)
	{
		
		try {
			
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
			String path = LogFilePath+"\\"+ft.format(dNow);
			boolean success =new File(path).mkdir();
			
			if(success)
				bw = new BufferedWriter(new FileWriter(new File(path+"\\"+LogFileName), true));
			else
				bw = new BufferedWriter(new FileWriter(new File(LogFilePath+"\\"+LogFileName), true));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean closeFile()
	{
		try {
			if(bw!=null)
				bw.close();
		} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	public static void writeToFile(String content)
	{
		try
		{
			if(bw!=null)
			{
			    Date dNow = new Date();
			    SimpleDateFormat ft = new SimpleDateFormat ("[yyyy.MM.dd.hh:mm:ss] ");
				bw.append(ft.format(dNow));
				bw.append(content+'\n');
				bw.flush();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	
	

}
