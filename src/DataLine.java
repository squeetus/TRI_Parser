public class DataLine {
	
	/*********************************************************
	 * 		Parsed Data
	 *********************************************************/
	String facilityName, chemicalName; 
	Float latitude, longitude, air, water, underground, landfill, surface, other, offsite, totalReleases;
	
	Integer flags = 0; 
	
	DataLine(String line) {
		parseLine(line);
	}
	
	
	public void parseLine(String line) {   
        String[] splitLine = line.split("\t");
		
		facilityName = splitLine[2]
				.replace("\"", "")
				.replace("/",  "")
				.replace("\\", "")
				.replace(" ", "")
				.replace("(", "-")
				.replace(")","-")
				.replace("&","-")
				.replace("\'","")
				.replace("*", "")
				.replace("#", "");
		
		chemicalName = splitLine[26].replace("\"", "").replace(",","-");
		if(splitLine[10].equals("") || splitLine[10].equals(null)) {
			flags = 1;
			latitude = 0f;
			longitude = 0f;
		}
		else {
			 latitude = Float.valueOf(splitLine[10]);
			 longitude = Float.valueOf(splitLine[11]);
		} 
		
		air = Float.valueOf(splitLine[35]) + Float.valueOf(splitLine[36]);
		water = Float.valueOf(splitLine[37]);
		underground = Float.valueOf(splitLine[38]) + Float.valueOf(splitLine[39]);
		landfill = Float.valueOf(splitLine[40]) + Float.valueOf(splitLine[41]) + Float.valueOf(splitLine[42]);
		surface = Float.valueOf(splitLine[43]) + Float.valueOf(splitLine[44]) + Float.valueOf(splitLine[45]);
		other = Float.valueOf(splitLine[46]);
		offsite = Float.valueOf(splitLine[68]);
		
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
	
	public Float getAir() {
		return air;
	}
	
	public Float getWater() {
		return water;
	}
	
	public Float getUnderground() {
		return underground;
	}
	
	public Float getLandfill() {
		return landfill;
	}
	
	public Float getSurface() {
		return surface;
	}
	
	public Float getOffsite() {
		return offsite;
	}
	
	public Float getOther() {
		return other;
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
