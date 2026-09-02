package Practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ReadFromExcel {
	public void readData() throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh =  wb.getSheet("Sheet1");
		
		Row row = sh.getRow(1);
		
		Cell cell = row.getCell(0);
		
		System.out.println(cell.toString());
		
		wb.close();
		
	}
	
	public void writeData() throws EncryptedDocumentException, IOException {
FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh =  wb.getSheet("Sheet1");
		
		Row row = sh.createRow(4);
		row.createCell(0).setCellValue("IBM");
		row.createCell(1).setCellValue("bnmcv");
		row.createCell(2).setCellValue("qwerty");
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestScriptdata.xlsx");
		wb.write(fos);
		fos.close();
		wb.close();
	}
		
		public static void main(String[] args) throws EncryptedDocumentException, IOException {
			ReadFromExcel r = new ReadFromExcel();
			r.readData();
			r.writeData();
		}
		

		
			
			
	}


