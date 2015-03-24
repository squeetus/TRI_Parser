public class Facility {
	
	/*********************************************************
	 * 		Private Data
	 *********************************************************/
	private String facilityName;
	private String chemicals;
	private Float latitude, longitude, totalReleases;
	private Integer flags = 0; 
	
	Facility(DataLine d) {
		facilityName = d.getFacilityName();
		chemicals = d.getChemicalName();
		totalReleases = d.getTotalReleases();
		if(d.flags == 0) {
			latitude = d.getLatitude();
			longitude = d.getLongitude();
		} else {
			latitude = 0f;
			longitude = 0f;
		}
	}
	
	/*********************************************************
	 * 		Get/Set
	 *********************************************************/
	public String getFacilityName() {
		return facilityName;
	}
	
	public void addChemical(String cName) {
		chemicals += "\t" + cName;
	}
	
	public void addReleases(Float release) {
		totalReleases += release; 
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
		//System.out.println("Get Data from Facility...");
		String tab = "\t";
		return facilityName + tab + latitude + tab + longitude + tab + totalReleases + tab + chemicals;
	}
	
	public String getJSON() {
		String json = "";
		String[] c = chemicals.split("\t");
		json += "\"facilityName\": \"" + facilityName + "\", \"lat\": \"" + latitude + "\", \"long\": \"" + longitude + "\", \"totalReleases\": \"" + totalReleases + "\", \"chemicals\": [";
		for(Integer i = 0; i < c.length; i++ ) {
			json += "\"" + c[i].replace("\"", "") + "\"";
			if(i < c.length - 1)
				json += ", ";
		}
		json += "]";
		
		return json;
	}
	
}
