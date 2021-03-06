package app;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Country;

public class WriteListToExcelFile {

	public static void writeCountryListToFile(String fileName, List<Country> countryList) throws Exception {
		Workbook workbook = null;

		if (fileName.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (fileName.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new Exception("invalid file name, should be xls or xlsx");
		}
		
		Sheet sheet = workbook.createSheet("Countries");
		Iterator<Country> iterator = countryList.iterator();
		
		int rowIndex = 0;
		while(iterator.hasNext()){
			Country country = iterator.next();
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(country.getName());
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(country.getShortCode());
		}
		
		
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		
		fos.close();
		System.out.println(fileName + " written successfully");
	}
	
	public static void main(String args[]) throws Exception{
		
		List<Country> list = new ArrayList<Country>();
		
		list.add(0, (new Country("S�o Paulo", "SP")));
		
		
		WriteListToExcelFile.writeCountryListToFile("C:/teste.xls", list);
	}
		
	}


