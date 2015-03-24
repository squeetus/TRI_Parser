public class Chemical {
	
	/*********************************************************
	 * 		Private Data
	 *********************************************************/
	private String chemicalName;
	private String facilities;
	private Float totalReleases;
	private Integer flags = 0; 
	
	Chemical(DataLine d) {
		chemicalName = d.getChemicalName();
		facilities = d.getFacilityName();
		totalReleases = d.getTotalReleases();
	}
	
	/*********************************************************
	 * 		Get/Set
	 *********************************************************/
	public String getChemicalName() {
		return chemicalName;
	}
	
	public void addFacility(String fName) {
		facilities += "\t" + fName;
	}
	
	public void addReleases(float release) {
		totalReleases += release;
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
		return chemicalName + tab + totalReleases + tab + facilities;
	}
	
	public String getJSON() {
		String json = "";
		String[] f = facilities.split("\t");
		json += "\"chemicalName\": \"" + chemicalName + "\", \"totalReleases\": \"" + totalReleases + "\", \"facilities\": [";
		for(Integer i = 0; i < f.length; i++ ) {
			json += "\"" + f[i].replace("\"", "") + "\"";
			if(i < f.length - 1)
				json += ", ";
		}
		json += "]";
		
		return json;
	}
	
}
