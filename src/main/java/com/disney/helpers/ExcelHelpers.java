package com.disney.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelpers {
	
	
    // private static final String FILE_NAME = "Events_Items.xls";
	// private HSSFWorkbook  myExcelWb;
	
	private final String loginInfoSheetName = "Login_Info";
	private final String siteInforSheetName = "Site_Info";
	private String outputFileName;
	private int loginRowCount;
	private int siteRowCount;
	private Workbook wb;
	private Sheet loginSheet, siteInfoSheet;
    
    public ExcelHelpers(String fileName)
    {
    	outputFileName = fileName;
    	loginRowCount = 0;
    	siteRowCount = 0;
    	wb = new HSSFWorkbook();
    	loginSheet = wb.createSheet(loginInfoSheetName);
    	siteInfoSheet = wb.createSheet(siteInforSheetName);
    	
    }
    
    public void writeLoginInfoToExcel(String userName, String password) throws IOException
    {
    	System.out.println("inside write"  + userName + " " +  password);
    	OutputStream fileOut = new FileOutputStream(outputFileName);
		Row row = loginSheet.createRow(loginRowCount++);
		row.createCell(0).setCellValue(userName);
		row.createCell(1).setCellValue(password);
		
		wb.write(fileOut);
  		fileOut.close();
		
    }
    
    public HashMap<String, String> readLoginInfoFromExcel(int index)
    {
    	try 
    	{
    		HSSFWorkbook  myExcelWB = new HSSFWorkbook(new FileInputStream(outputFileName));
    		Sheet sheet = myExcelWB.getSheet(loginInfoSheetName);
    		Row row = sheet.getRow(index);
    		HashMap<String, String> hm = new HashMap<String, String>();
    		
    		String userName =  row.getCell(0).getStringCellValue();
            String password =  row.getCell(1).getStringCellValue();
            hm.put("username", userName);
            hm.put("password", password);
            myExcelWB.close();
        	return hm;
        	
    	}  catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
    
    
    public void writeSiteInfoToExcel(ArrayList<String> siteInfoList) throws IOException
    {
    	OutputStream fileOut = new FileOutputStream(outputFileName);
    	for(String siteInfo:siteInfoList)
    	{
    		Row row = siteInfoSheet.createRow(siteRowCount);
    		row.createCell(0).setCellValue(siteInfo);
    		siteRowCount++;
    	}
    	wb.write(fileOut);
  		fileOut.close();
    }
    
    
    public ArrayList<String> readSiteInfoFromExcel()
    {
    	try 
    	{
    		HSSFWorkbook  myExcelWB = new HSSFWorkbook(new FileInputStream(outputFileName));
    		Sheet sheet = myExcelWB.getSheet(siteInforSheetName);
    		Iterator<Row> iterator = sheet.iterator();
    		ArrayList<String> ValueList = new ArrayList<String>(); 
	            
            while (iterator.hasNext()) {
	        	Row currentRow = iterator.next();
	        	String value = currentRow.getCell(0).getStringCellValue();
	        	System.out.println("Row values from excel " + value);
	   		 	ValueList.add(value);        	 
            }
            myExcelWB.close();
            return ValueList;	
    	}  
    	catch (Exception e) {
            e.printStackTrace();
            return null;
        }
 
    } 
}