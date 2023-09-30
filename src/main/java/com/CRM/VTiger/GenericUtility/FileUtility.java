package com.CRM.VTiger.GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVWriter;

public class FileUtility {
	/**
	 * This method helps us to fetch the data from property file
	 * @Sriyanka
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream(IPathConstant.proportyFilePath)	;
		Properties prop=new Properties();
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}
	public String getDataFromExcelSheet(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IPathConstant.excelFilePath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheetName);
		DataFormatter format=new DataFormatter();
		String value = format.formatCellValue(sh.getRow(rowNum).getCell(cellNum));
		return value;	
				
	}
	public void updateExcelData(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IPathConstant.excelFilePath);
		Workbook book=WorkbookFactory.create(fis);
		book.createSheet(sheetName).createRow(rowNum).createCell(cellNum);
		FileOutputStream fos=new FileOutputStream(IPathConstant.excelFilePath);
		book.write(fos);
	}
	public void writeDataInCSVFile(int cellNum,String value) throws IOException {
		FileWriter fw=new FileWriter(IPathConstant.csvFilePath);
		CSVWriter cw=new CSVWriter(fw);
		String cell[]=new String[cellNum];
		cell[cellNum-1]=value;
		cw.writeNext(cell);
	}
	@DataProvider
	public Object[][] multipleDataFromExcel() throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstant.excelFilePath);
		Sheet sh = WorkbookFactory.create(fis).getSheet("DataProvider");
		int lastRow = sh.getLastRowNum()+1;
		int lastCell=sh.getRow(0).getLastCellNum();
		Object[][] dt=new Object[lastRow][lastCell];
		DataFormatter dft=new DataFormatter();
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				dt[i][j]=dft.formatCellValue(sh.getRow(i).getCell(j));
			}
		}
		return dt;
	}

}
