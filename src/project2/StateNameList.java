package project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StateList extends IOException {
	private String[] id;
	private String[] code;
	private String[] name;
	
	StateList() throws IOException {
		id = new String[60];
		code = new String[60];
		name = new String[60];
		
		setup();
	}
	
	public void setup() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("project2/datafiles/states.tsv"));
		try {
	    	String line = br.readLine();
	    	String[] splitLine = line.split("\t");
	    	Integer lineNum = 0; 
	    	while((line = br.readLine()) != null) {
	    		splitLine = line.split("\t");
	    		id[lineNum] = splitLine[0].trim();
	    		code[lineNum] = splitLine[1].trim();
	    		name[lineNum] = splitLine[2].toUpperCase().trim();
	    		lineNum++;
	    	}
	    		    
		} catch (IOException ex) {
			System.out.println("Error reading from or opening file: " + ex);
	    } finally {
	        try {br.close();} catch (Exception ex) {}
	    }
	}
	
	public String getStateID(String state) {
		for(int i = 0; i < id.length; i++) {
			if(code[i].equals(state) || name[i].equals(state))
				return id[i];
		}
		return "-1";
	}
}
