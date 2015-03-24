public class DataLine {
	
	/*********************************************************
	 * 		Parsed Data
	 *********************************************************/
	String facilityName, chemicalName; 
	Float latitude, longitude, totalReleases;
	Integer flags = 0; 
	
	DataLine(String line) {
		parseLine(line);
	}
	
	
	public void parseLine(String line) {   
        String[] splitLine = line.split("\t");
		
		facilityName = splitLine[2].replace("\"", "").replace("/",  "").replace("\\", "");
		
		chemicalName = splitLine[26].replace("\"", "");
		if(splitLine[10].equals("") || splitLine[10].equals(null)) {
			flags = 1;
			latitude = 0f;
			longitude = 0f;
		}
		else {
			 latitude = Float.valueOf(splitLine[10]);
			 longitude = Float.valueOf(splitLine[11]);
		} 
		totalReleases = Float.valueOf(splitLine[85]);
	}
	
	
	/*********************************************************
	 * 		Get/Set
	 *********************************************************/
	public String getFacilityName() {
		return facilityName;
	}
	
	public String getChemicalName() {
		return chemicalName;
	}
	
	public Float getLatitude() {
		return latitude;
	}
	
	public Float getLongitude() {
		return longitude;
	}
	
	public Float getTotalReleases() {
		return totalReleases;
	}
	
	
	/*********************************************************
	 * 		Methods
	 *********************************************************/
	
	public String getData() {
		String tab = "\t";
		return facilityName + tab + latitude + tab + longitude + tab + chemicalName + tab + totalReleases;
	}
	
}
