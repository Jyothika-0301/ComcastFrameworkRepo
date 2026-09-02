package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public FileInputStream fis;

	public int totalNumberOfRows(String sheetName) throws EncryptedDocumentException, IOException {  //getrowcount
		fis = new FileInputStream("./src/test/resources/testData/orgTest.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();          // getLastNum
	}

	public int totalNumberOfColumns(String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/testData/orgTest.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}

	public String readDataFromExcel(String sheetName, int row, int cell)   //getdatafromexcel
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/testData/orgTest.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getRow(row).getCell(cell).toString();
	}

	
	public void setDataFromExcel(String sheetName, int row, int cell) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/testData/orgTest.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(sheetName);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/testData/orgTest.xlsx");
		wb.write(fos);
		wb.close();

	}

	public String readLongNumber(String sheetName, int row, int cell) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		DataFormatter data = new DataFormatter();
		return wb.getSheet(sheetName).getRow(row).getCell(cell).toString();
	}

}
