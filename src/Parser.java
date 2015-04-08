/*********************************************************
 * 		
 * 		TRIparser
 * 
 * 		- Parser program to read datafiles related to the TRI (Toxics Release Inventory)
 * 		- Created by David Burlinson
 * 			Created March 21, 2015
 * 
 *********************************************************/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Parser {

	public static void main(String[] args) throws IOException {
		
		FacilityList fList = new FacilityList();
		ChemicalList cList = new ChemicalList();
		parseFile(fList, cList);
		printFile(fList, cList);
		fList.clear();
		cList.clear();
	}
	
	/**	Parse the data file **/
	public static void parseFile(FacilityList fList, ChemicalList cList) throws IOException {
			
		BufferedReader br = new BufferedReader(new FileReader("data/TRI_2013_US.txt"));
		DataLine d; 
		
	    try {
	    	String line = br.readLine();
	    	Integer lineNum = 0; 
	    	while((line = br.readLine()) != null) {
	    		lineNum++;
	    		d = new DataLine(line);
	    		fList.find_and_insert(d);
	    		cList.find_and_insert(d);
	    		if(lineNum % 10000 == 0)
	    			System.out.println("Read " + lineNum + " lines. List is " + fList.length() + " facilities long.");
	    		
	    	}
	    	
	    	System.out.println("Finished reading data file. Read " + lineNum + " lines for a total of " + fList.length() + " facilities and " + cList.length() + " chemicals.");
	       	    
		} catch (IOException ex) {
			System.out.println("Error reading from or opening file: " + ex);
	    } finally {
	        try {br.close();} catch (Exception ex) {}
	    }
	}
	
	/**	Print the data file **/
	public static void printFile(FacilityList fList, ChemicalList cList) throws IOException {
		BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/facilityOutput.txt"), "utf-8"));
		BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/chemicalOutput.txt"), "utf-8"));
		BufferedWriter JSONwriter1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/facilities.json"), "utf-8"));
		BufferedWriter JSONwriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/chemicals.json"), "utf-8"));
		BufferedWriter JSONwriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/special.json"), "utf-8"));
		BufferedWriter CSVwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/special.csv"), "utf-8"));
		
		try {
			System.out.println("printing...");
			
		 	Integer i, j, k;
		 	i = j = k = 0;
		 	
	    	fList.moveToStart();
	    	fList.next();
	    	cList.moveToStart();
	    	cList.next();
	    	
	    	
	    	JSONwriter1.write("{ \"facilities\" : [");
	   
	    	while(i < fList.length()) {
	    		//System.out.println(fList.getCurrent().element().getData());
		    	
	    		if(fList.getCurrent().element() == null)
		    		System.out.println("null facility element in list curr");
		    	
	    		writer1.write(fList.getCurrent().element().getData() + "\n");

		    	JSONwriter1.write("{" + fList.getCurrent().element().getJSON() + "}");
		    	
		    	if(i < fList.length() - 1)
		    		JSONwriter1.write(",");
	    		
		    	i++;
		    	fList.next();
	    	}
	    	JSONwriter1.write("] }");
	    	
	    	JSONwriter2.write("{ \"chemicals\" : [");
	    	//JSONwriter3.write("{ \"special\" : [");
	    	CSVwriter.write("chemicalName, chemical ,air,water ,underground,landfill,surface,offsite,other,totalReleases,facilities\n");
			
	    	
	    	while(j < cList.length()) {
	    		//System.out.println(fList.getCurrent().element().getData());
		    	
	    		if(cList.getCurrent().element() == null)
		    		System.out.println("null chemical element in list curr");
	    		
	    		//System.out.println(cList.getCurrent().element().getChemicalName().toString().toLowerCase());
	    		if(cList.getCurrent().element().getChemicalName().toString().toLowerCase().contains("nitrate") || cList.getCurrent().element().getChemicalName().toString().toLowerCase().contains("benzene") || cList.getCurrent().element().getChemicalName().toString().toLowerCase().contains("nickel")) {
	    			//JSONwriter3.write("{" + cList.getCurrent().element().getMoreJSON() + "}");
	    			CSVwriter.write(cList.getCurrent().element().getMoreCSV() + "\n");
//	    			if(k < 19)
//	    				JSONwriter3.write(", ");
    			k++;
	    		}
	    		
	    		writer2.write(cList.getCurrent().element().getData() + "\n");

		    	JSONwriter2.write("{" + cList.getCurrent().element().getJSON() + "}");
		    	
		    	if(j < cList.length() - 1) {
		    		JSONwriter2.write(", ");
		    	}
	    		
		    	j++;
		    	cList.next();
	    	}
	    	JSONwriter2.write("] }");
	    	//JSONwriter3.write("] }");
    	
	    	System.out.println("Finished writing " + i + " facilities, " + j + " chemicals, and " + k + " special to data file!");
	       	    
		} catch (IOException ex) {
			System.out.println("Error creating or writing to file: " + ex);
	    } finally {
	    	try {writer1.close();} catch (Exception ex) {}
	    	try {JSONwriter1.close();} catch (Exception ex) {}
	    	try {writer2.close();} catch (Exception ex) {}
	    	try {JSONwriter2.close();} catch (Exception ex) {}
	    	try {JSONwriter3.close();} catch (Exception ex) {}
	    	try {CSVwriter.close();} catch (Exception ex) {}
	    }
	}
}
