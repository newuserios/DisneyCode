package com.disney.coding;

import java.io.BufferedReader;
import java.io.FileInputStream;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.disney.common.CompareXml;


public class CompareXmlTest {
	private static CompareXml compare;
	
	
	@Parameters({ "xmlFile1","xmlFile2" })
	@Test(priority=8)
	public void compareTwoXml(String xmlFile1,String xmlFile2) throws SAXException, IOException
	{
	 
			FileInputStream fis1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/"+ xmlFile1);
			FileInputStream fis2 = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/" + xmlFile2);

			BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
			BufferedReader target = new BufferedReader(new InputStreamReader(fis2));

			XMLUnit.setIgnoreWhitespace(true);

			List<Difference> differences = compare.compareXML(source, target);

			CompareXml.printDifferences(differences);
 
		
	}
 
	
}