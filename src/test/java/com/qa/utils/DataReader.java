package com.qa.utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataReader {
	
	@DataProvider(name = "dp")
	public static Object[] getData(Method m) {
		
		String sheetName = "testdata";
		String fileName = m.getDeclaringClass().getPackage().getName().split("\\.")[3] + ".xlsx";
		String testName = m.getDeclaringClass().getSimpleName();
		
		DataTable table = new DataTable(System.getProperty("user.dir") + "\\src\\test\\resources\\datatable\\" + fileName);
		
		int tcCount = table.getRowCount(sheetName, "testcaseID", testName);
		int rowCount = table.getRowCount(sheetName);
		int colCount = table.getColumnCount(sheetName);
		
		//System.out.println("row: " + rowCount + "col: " + colCount + "itrcount: " + tcCount);
		
		Object[] data = new Object[tcCount];
		 
		Hashtable<String, String> hashTable = null;
		
		for(int rowNum = 2; rowNum <= rowCount; rowNum++) {
			
			if(!table.getCellData(sheetName, rowNum, "testcaseID").equalsIgnoreCase(testName))
				continue;
			
			hashTable = new Hashtable<String, String>();
			
			for(int colNum = 1; colNum <= colCount; colNum++) {
				
				hashTable.put(table.getCellData(sheetName, 1, colNum), table.getCellData(sheetName, rowNum, colNum));
			}
			
			data[rowNum - 2] = hashTable;
		}
		
		return data;
		
	}

}
