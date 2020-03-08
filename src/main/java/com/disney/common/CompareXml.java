package com.disney.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

public class CompareXml {
	static HashMap<String, String> hm;



	public static List<Difference> compareXML(Reader source, Reader target) throws SAXException, IOException {
		Diff xmlDiff = new Diff(source, target);
		DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
		return detailXmlDiff.getAllDifferences();
	}

	public static void printDifferences(List<Difference> differences) throws IOException {
		int totalDifferences = differences.size();

		System.out.println("===============================");
		System.out.println("Total differences : " + totalDifferences);
		System.out.println("================================");
		Workbook wb = new HSSFWorkbook();
		OutputStream fileOut = new FileOutputStream("CompareXml.xls");
		Sheet sheet = wb.createSheet("Differences");
		int rowCount = 0;
		Row row = sheet.createRow(rowCount);
		row.createCell(0).setCellValue("Xml path");
		row.createCell(1).setCellValue("XMLCompareFile1.xml");
		row.createCell(2).setCellValue("XMLCompareFile2.xml");
		for (Difference difference : differences) {
			row = sheet.createRow(++rowCount);
			writeBook(difference, row);
		}
		wb.write(fileOut);
		fileOut.close();
	}

	public static void writeBook(Difference difference, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(((Difference) difference).getControlNodeDetail().getXpathLocation());

		cell = row.createCell(1);
		cell.setCellValue(((Difference) difference).getControlNodeDetail().getValue());

		cell = row.createCell(2);
		cell.setCellValue(((Difference) difference).getTestNodeDetail().getValue());
	}
}
