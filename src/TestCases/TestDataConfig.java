package TestCases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestDataConfig
	{
	XSSFWorkbook wb;
	XSSFSheet Sheet1;
	
	public TestDataConfig(String excelFilePath) throws FileNotFoundException
	{
	//String excelFilePath = "D:\\Selenium\\Test Data\\TestData.xlsx";
	File scr= new File(excelFilePath);
	FileInputStream inputStream = new FileInputStream(scr);
	try {
		wb=new XSSFWorkbook(inputStream);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public String ReadExcel(int Sheet, int Row, int Column){
		Sheet1=wb.getSheetAt(Sheet);
		String data=Sheet1.getRow(Row).getCell(Column).getStringCellValue();
		System.out.println(data);
		return data;
	}
	public int RowCount()
	{
		int TotalRows= wb.getSheetAt(0).getLastRowNum();
		TotalRows=TotalRows+1;
		return TotalRows;
		
	}
}