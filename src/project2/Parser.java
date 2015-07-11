package project2;
/*********************************************************
 * 		
 * 		TRIparser
 * 
 * 		- Parser program to read datafiles related to the TRI (Toxics Release Inventory)
 * 		- Created by David Burlinson
 * 			- Created March 21, 2015
 * 
 * 			- Updated July, 2015
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
		StateList stateList = new StateList();
		CountyList countyList = new CountyList();
//		countyList.printCounties();
//		
//		System.exit(0);
		
		Integer numNoState = 0; 
		Integer numNoCounty = 0; 
		Integer numFlags = 0; 
		Integer numFlagsLocal = 0; 
		Integer numNoFlagsLocal = 0; 
		
//		from 1987 to 2013, 
		for(Integer i = 0; i < 4; i++) {
		//for(Integer i = 26; i < 27; i++) {
			numFlagsLocal = 0;
			numNoFlagsLocal = 0; 
			
			System.out.println("Reading file " + (i + 1987) + " ...");
			//BufferedReader br = new BufferedReader(new FileReader("data/TRI_2013_US.txt"));
			BufferedReader br = new BufferedReader(new FileReader("project2/datafiles/TRI_" + (i + 1987) + "_US.csv"));
			//BufferedReader br = new BufferedReader(new FileReader("project2/datafiles/test.csv")); 
			
			DataLine d; 
			
		    try {
		    	String line = br.readLine();
		    	Integer lineNum = 0; 
		    	
		    	
		    	while((line = br.readLine()) != null) {
		    		lineNum++;
		    		d = new DataLine(line, i, stateList, countyList);
		    		
		    		if(d.flags == 0)
		    			numNoFlagsLocal++;

		    		if(d.flags == 1 || d.flags == 3) {
		    			numFlags++;
		    			numFlagsLocal++;
		    			continue;
		    		}
		    			
		    		fList.find_and_insert(d);
		    		//cList.find_and_insert(d);
		    		if(lineNum % 2500 == 0)
		    			System.out.println("Read " + lineNum + " lines. List is " + fList.length() + " facilities long.");
		    		
		    		
		    		
//		    		if (lineNum > 10)
//		    			break;
		    	}
		    	
		    	//System.out.println("Finished reading data file. Read " + lineNum + " lines for a total of " + fList.length() + " facilities and " + cList.length() + " chemicals.");
		       	    
			} catch (IOException ex) {
				System.out.println("Error reading from or opening file: " + ex);
		    } finally {
		        try {br.close();} catch (Exception ex) {}
		    }
		    System.out.println("Finished file from " + (i + 1987));
		    System.out.println("Number of times no state was present: " + numFlagsLocal);
		    System.out.println("Number of times a state was present: " + numNoFlagsLocal);
		    System.out.println("Number of facilities:  " + fList.length());
		    System.out.println("Failure rate: ----- " + (float) numFlagsLocal / numNoFlagsLocal);
		    
		}
		
		//Report on the number of times there was no state in a dataline over all files
		System.out.println("Number of times no state was present overall: " + numFlags);
	}
	
	/**	Print the data file **/
	public static void printFile(FacilityList fList, ChemicalList cList) throws IOException {
		//BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/output/facilityOutput.txt"), "utf-8"));
		//BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/output/chemicalOutput.txt"), "utf-8"));
		BufferedWriter JSONwriter1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/output/facilities.json"), "utf-8"));
		BufferedWriter JSONwriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/output/small.json"), "utf-8"));
		
		//BufferedWriter JSONwriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/output/chemicals.json"), "utf-8"));
		//BufferedWriter JSONwriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/data/special.json"), "utf-8"));
		//BufferedWriter CSVwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("project2/data/special.csv"), "utf-8"));
		
		try {
			System.out.println("printing...");
			
		 	Integer i, j, k;
		 	i = j = k = 0;
		 	
	    	fList.moveToStart();
	    	fList.next();
	    	cList.moveToStart();
	    	cList.next();
	    	
	    	
	    	JSONwriter1.write("{ \"facilities\" : [");
	    	JSONwriter2.write("{ \"facilities\" : [");
	   
	    	Integer numNotPrinting = 0; 
	    	
	    	while(i < fList.length()) {
	    		//System.out.println("Printing " + i);
//	    		if(!fList.getCurrent().element().shouldPrint()) {
//	    			System.out.println("Should not print?");
//	    			numNotPrinting++;
//	    			//System.out.println(fList.getCurrent().element().getData() + " (" + numNotPrinting + " )");
////	    			System.out.println("Number not to print " + numNotPrinting);
//	    			i++;
//	    			fList.next();
//	    			continue;
//	    		}
	    		//System.out.println(fList.getCurrent().element().getData());
		    	
	    		if(fList.getCurrent().element() == null)
		    		System.out.println("null facility element in list curr");
		    	
	    		//writer1.write(fList.getCurrent().element().getData() + "\n");

	    		//System.out.println(fList.getCurrent().element().getData());
	    		
		    	JSONwriter1.write("{" + fList.getCurrent().element().getJSON() + "}");
		    	
		    	if( i < 1000)
		    	JSONwriter2.write("{" + fList.getCurrent().element().getJSON() + "}");
		    	
		    	if(i < fList.length() - 1) {
		    		JSONwriter1.write(",");
		    		if(i < 999)
		    			JSONwriter2.write(",");
		    	}
		    	j++;
		    	i++;
		    	fList.next();
	    	}
	    	JSONwriter1.write("] }");
	    	JSONwriter2.write("] }");
    	
	    	System.out.println("Finished writing " + j + " of " + i + " facilities to the data file!");
	       	    
		} catch (IOException ex) {
			System.out.println("Error creating or writing to file: " + ex);
	    } finally {
	    	//try {writer1.close();} catch (Exception ex) {}
	    	try {JSONwriter1.close();} catch (Exception ex) {}
	    	try {JSONwriter2.close();} catch (Exception ex) {}
	    	//try {writer2.close();} catch (Exception ex) {}
	    	//try {JSONwriter2.close();} catch (Exception ex) {}
	    	//try {JSONwriter3.close();} catch (Exception ex) {}
	    	//try {CSVwriter.close();} catch (Exception ex) {}
	    }
	}
}
