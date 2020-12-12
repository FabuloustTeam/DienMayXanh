package DataImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class demoDataTestDriven {
	public  void main (String...strings) throws IOException{
		demoDataTestDriven objExcelFile = new demoDataTestDriven();
		String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";
		objExcelFile.readDataFromExcel(filePath, "ViewProductThroughtListData.xlsx", "Sheet1");
	}

	
	public void readDataFromExcel(String filePath, String fileName, String sheetName) throws IOException {
		File file = new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook VPTLWorkbook = null;
		VPTLWorkbook = new XSSFWorkbook(inputStream);
		Sheet VPTLSheet = VPTLWorkbook.getSheet(sheetName);
		int rowCount = VPTLSheet.getLastRowNum()-VPTLSheet.getFirstRowNum();
		
		for(int i =0; i<rowCount+1;i++) {
			Row row = VPTLSheet.getRow(i);
			for(int j=0; j<row.getLastCellNum();j++) {
				System.out.println(row.getCell(j).getStringCellValue()+"|| ");
			}
		}
	}
}
