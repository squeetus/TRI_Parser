package project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountyList extends IOException {
	private String[] id;
	private String[] name;
	
	CountyList() throws IOException {
		id = new String[3354];
		name = new String[3354];
		
		setup();
	}
	
	public void setup() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("project2/datafiles/counties.tsv"));
		try {
	    	String line = br.readLine();
	    	String[] splitLine = line.split("\t");
	    	
	    	Integer lineNum = 0; 
	    	while((line = br.readLine()) != null) {
	    		splitLine = line.split("\t");
	    		
	    		id[lineNum] = splitLine[0].trim();
	    		name[lineNum] = splitLine[1].toUpperCase()
	    				.replace(".", "")
	    				.replace("(CITY)", "")
	    				.replace("'", "")
	    				.trim();
	    		
	    		if(name[lineNum].equals("LA PORTE")) 
	    			name[lineNum] = "LAPORTE";
	    		
	    		if(name[lineNum].equals("MC DONOUGH"))
	    			name[lineNum] = "MCDONOUGH";
	    		
	    		if(name[lineNum].equals("MCCLAIN"))
	    			name[lineNum] = "MC CLAIN";
	    		
	    		if(name[lineNum].equals("MCCREARY"))
	    			name[lineNum] = "MC CREARY";
	    		
	    		if(name[lineNum].equals("MCKENZIE"))
	    			name[lineNum] = "MC KENZIE";
	    		
	    		if(id[lineNum].equals("12025"))
	    			name[lineNum] = "MIAMI-DADE";
	    		
	    		
	    			
	    		lineNum++;
	    	}
	    
	    		    
		} catch (IOException ex) {
			System.out.println("Error reading from or opening file: " + ex);
	    } finally {
	        try {br.close();} catch (Exception ex) {}
	    }
	}
	
	public void printCounties() {
		for(int i = 0; i < 3354; i++){
			System.out.println(id[i] + " " + name[i]);
		}
	}
	
	public String getCountyID(String county) {
		for(int i = 0; i < id.length; i++) {
			if(name[i].equals(county))
				return id[i];
		}
		return "-1";
	}
}
