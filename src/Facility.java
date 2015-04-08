public class Facility {
	
	/*********************************************************
	 * 		Private Data
	 *********************************************************/
	private String facilityName;
	private String chemicals;
	private Float latitude, longitude, air, water, underground, landfill, surface, other, offsite, totalReleases;
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
		air = d.getAir();
		water = d.getWater();
		underground = d.getUnderground();
		landfill = d.getLandfill();
		surface = d.getSurface();
		other = d.getOther();
		offsite = d.getOffsite();
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
	
	public void addAir(Float a) {
		air += a;
	}
	
	public void addWater(Float w) {
		water += w;
	}
	
	public void addUnderground(Float u) {
		underground += u;
	}
	
	public void addLandfill(Float l) {
		landfill += l;
	}
	
	public void addSurface(Float s) {
		surface += s;
	}
	
	public void addOffsite(Float off) {
		offsite += off;
	}
	
	public void addOther(Float o) {
		other += o;
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
		json += "\"facilityName\": \"" + facilityName + "\", \"lat\": \"" + latitude + "\", \"long\": \"" + longitude + "\", \"air\": \"" + air + "\", \"water\": \"" + water + "\", \"underground\": \"" + underground + "\", \"landfill\": \"" + landfill + "\", \"surface\": \"" + surface + "\", \"offsite\": \"" + offsite + "\", \"other\": \"" + other + "\", \"totalReleases\": \"" + totalReleases + "\", \"chemicals\": [";
		for(Integer i = 0; i < c.length; i++ ) {
			json += "\"" + c[i].replace("\"", "") + "\"";
			if(i < c.length - 1)
				json += ", ";
		}
		json += "]";
		
		return json;
	}
	
}
