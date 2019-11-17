package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable {

	public static FileInputStream in = null;
	public static Workbook workbook = null;
	public static FileOutputStream out = null;
	private String fileName = null;

	public DataTable(String fileName) {

		this.fileName = fileName;

		File file = new File(fileName);

		String fileExtension = fileName.substring(fileName.indexOf("."));

		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (fileExtension.equals(".xlsx")) {

				workbook = new XSSFWorkbook(in);
			} else {

				workbook = new HSSFWorkbook(in);
			}

		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getRowCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1)
			return 0;

		Sheet sheet = workbook.getSheetAt(index);
		return sheet.getLastRowNum() + 1;
	}
	
	public int getRowCount(String sheetName, String colName, String cellData) {

		int count = 0;
		
		int index = workbook.getSheetIndex(sheetName);

		if (index == -1)
			return 0;

		Sheet sheet = workbook.getSheetAt(index);
		
		System.out.println("last rowNum: " + sheet.getLastRowNum());
		
		for(int row = 0; row <= sheet.getLastRowNum(); row++) {
			//System.out.println(getCellData(sheetName, row, colName) + " " + cellData + " " + count);
			if(getCellData(sheetName, row + 1, colName).equalsIgnoreCase(cellData))
				count++;
		}
		
		return count;
	}


	public int getColumnCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1)
			return 0;

		Sheet sheet = workbook.getSheetAt(index);
		Row row = sheet.getRow(0);

		if (row == null) {

			return 0;

		} else {

			return row.getLastCellNum();
		}
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1 || rowNum <= 0)
			return "";

		Sheet sheet = workbook.getSheetAt(index);

		return getData(sheet, rowNum, colNum);

	}

	public String getCellData(String sheetName, int rowNum, String colName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1 || rowNum <= 0)
			return "";

		Sheet sheet = workbook.getSheetAt(index);

		int colNum = -1;

		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {

			if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
				colNum = i + 1;
				break;
			}
		}

		return getData(sheet, rowNum, colNum);

	}

	private String getData(Sheet sheet, int rowNum, int colNum) {

		try {

			Row row = sheet.getRow(rowNum - 1);

			if (row == null)
				return "";

			Cell cell = row.getCell(colNum - 1);

			if (cell == null)
				return "";

			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

				if (DateUtil.isCellDateFormatted(cell)) {

					double date = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();

					cal.setTime(DateUtil.getJavaDate(date));

					return String.valueOf(cal.get(Calendar.MONTH) + 1) + "/"
							+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.valueOf(cal.get(Calendar.YEAR));

				} else {
					return String.valueOf(cell.getNumericCellValue());
				}
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public boolean setCellData(String sheetName, int rowNum, int colNum, String value) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1 || rowNum <= 0)
			return false;

		Sheet sheet = workbook.getSheetAt(index);

		return setData(sheet, rowNum, colNum, value);

	}

	public boolean setCellData(String sheetName, int rowNum, String colName, String value) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1 || rowNum <= 0)
			return false;

		Sheet sheet = workbook.getSheetAt(index);

		int colNum = -1;

		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {

			if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
				colNum = i + 1;
				break;
			}
		}

		return setData(sheet, rowNum, colNum, value);

	}

	private boolean setData(Sheet sheet, int rowNum, int colNum, String value) {

		try {

			Row row = sheet.getRow(rowNum - 1);

			if (row == null)
				row = sheet.createRow(rowNum - 1);

			Cell cell = row.getCell(colNum - 1);

			if (cell == null)
				cell = row.createCell(colNum - 1);

			cell.setCellValue(value);

			out = new FileOutputStream(fileName);

			workbook.write(out);

			out.close();

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			return false;
		}

	}

}
