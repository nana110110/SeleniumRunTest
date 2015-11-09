package worldpay.QS.Selenium.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class CSVFileWriter {

	private String FileNameWithPath;
	private int ColumnCount;
	private FileWriter fileWriter;
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	public static final String OutputFilePath = "TestCaseReport\\";
	
	public CSVFileWriter(String FileName)
	{
		this.FileNameWithPath = OutputFilePath+FileName;
		ColumnCount=0;
	} 
	
	public void addBlankLine(int nColumn)
	{
		
		int nCount = 0;
		try{	
			while(nCount < nColumn)
			{
				if(nCount!=0)
					fileWriter.append(COMMA_DELIMITER);
				
			
				fileWriter.append("");
				nCount++;
			
			}

			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean openFile()
	{

		try {
			fileWriter = new FileWriter(this.FileNameWithPath);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//public int writeColumns(String[] columns)
	public int writeColumns(List<String> columns)
	{
		try 
		{
			for(String s:columns)
			{
				if(ColumnCount!=0)
					
						fileWriter.append(COMMA_DELIMITER);
					
				
				fileWriter.append(s);
				ColumnCount++;
			}
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		return ColumnCount;
	}
	
	public boolean writeToFile(List<String> info)
	{
		int caseCount = 0;
		try 
		{
			for(String s:info)
			{
				if(caseCount!=0)
					
						fileWriter.append(COMMA_DELIMITER);
					
				
				fileWriter.append(s);
				caseCount++;
			}
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void cleanUp()
	{
		try {
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
