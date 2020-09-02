package com.demo.abstraction.codechallenge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandling {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D://TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int noofRows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i <= noofRows - 1; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j <= row.getPhysicalNumberOfCells() - 1; j++) {
				Cell cell = row.getCell(j);
				System.out.println(cell.getStringCellValue());
			}
		}
	}

}
