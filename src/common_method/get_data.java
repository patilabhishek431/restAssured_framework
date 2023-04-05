package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class get_data {
	
	public static String TestDataPath;
	
	public static ArrayList<String> getdataexcel(String testsheetname,String testcasename) throws IOException 
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		
		// step 1 - to locate excel data file by creating object of file input stream
		TestDataPath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(TestDataPath+"/Test_data.xlsx");
		
		//create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//step 3 - access the desired sheet
		//step 3.1 - fetch the count of sheet available in the excel file
		int countofSheet = workbook.getNumberOfSheets();

		//step 3.2 - fetch the name of sheet and compare against desired sheet name
		for(int i=0; i<countofSheet; i++) {
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testsheetname))
			{
				// step 4 - Access the sheet iterate through rows to fetch the column in which test case name column is found
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				// Step 4.1 - create iterator for rows
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				
				//Step 4.2 - create iterator for cells
				Iterator<Cell> cells = firstrow.cellIterator();
				
				int j=0;
				int tc_column=0;
				
				//Step 4.3 - Read the ce values of row number 1 to compare against the test case name
				while(cells.hasNext())
				{
					Cell cellvalue = cells.next();
					if(cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
					{
						tc_column = j;
					}
					j++;
				}
				//Step 5 - fetch the data for designated test case
				while(rows.hasNext()) {
					Row datarow = rows.next();
					if(datarow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testcasename)) 
					{
						Iterator<Cell> datacellvalue = datarow.cellIterator();
						while(datacellvalue.hasNext()) 
						{
							Cell dataofCell = datacellvalue.next();
							
							//Extract cell value of Excel file by using below methods 
							
							//method 1 - BY using "TRY" and "Catch"
//							try
//							{
//								String testdata = dataofCell.getStringCellValue();
//								arrayData.add(testdata);
//							}
//							catch (IllegalStateException e) 
//							{
//								int inttestdata = (int) dataofCell.getNumericCellValue();
//								String stringtestdata = Integer.toString(inttestdata);
//								arrayData.add(stringtestdata);
//							}
							
							
							//method 2 - BY using IF and Else
//							CellType datatype = dataofCell.getCellType();
//							
//							if (datatype.toString() == "NUMERIC")
//							{
//								int inttestdata = (int) dataofCell.getNumericCellValue();
//								String stringtestdata = Integer.toString(inttestdata);
//							    arrayData.add(stringtestdata);
//							}
//							else if (datatype.toString() == "STRING")
//							{
//								String testdata = dataofCell.getStringCellValue();
//								arrayData.add(testdata);
//							}
							
							
							//method 3 - by using data formatter
							DataFormatter formate = new DataFormatter();
							String testData = formate.formatCellValue(dataofCell);
							arrayData.add(testData);
							
							
							//method 4 - extract the data by converting into string 
//							String testdata = dataofCell.toString().replaceAll("\\.\\d+$", "");
//							arrayData.add(testdata);
						}
					}
				}
			}
		}
		return arrayData;
	}
}




