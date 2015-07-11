package project2;
public class Facility {
	
	/*********************************************************
	 * 		Private Data
	 *********************************************************/
	private String facilityName, chemicals, county, state;

	private Float latitude, longitude, air, water, underground, landfill, surface, other, offsite;
	
	private Float[] recovery;
	private Float[] recycling;
	private Float[] treatment;
	private Float[] totalReleases;
	
	private Integer flags = 0; 
	
	Facility(DataLine d) {
		recovery = new Float[27];
		recycling = new Float[27];
		treatment = new Float[27];
		totalReleases = new Float[27];
		
		for(Integer i = 0; i < 27; i++) {
			recovery[i] = 0.0f;
			recycling[i] = 0.0f;
			treatment[i] = 0.0f;
			totalReleases[i] = 0.0f;	
		}
		
		facilityName = titleCase(d.getFacilityName());
		county = d.getCountyName();
		state = d.getStateName();
		//chemicals = d.getChemicalName();
		totalReleases[d.getYear()] = d.getTotalReleases();
		if(d.flags == 0) {
			latitude = d.getLatitude();
			longitude = d.getLongitude();
		} else {
			latitude = 0f;
			longitude = 0f;
		}
		//air = d.getAir();
		//water = d.getWater();
		//underground = d.getUnderground();
		//landfill = d.getLandfill();
		//surface = d.getSurface();
		//other = d.getOther();
		//offsite = d.getOffsite();
		recovery[d.getYear()] = d.getRecovery();
		recycling[d.getYear()] = d.getRecycling();
		treatment[d.getYear()] = d.getTreatment();
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
	
	public void addReleases(Float release, Integer year) {
		if(release != null && release > 0.0)
			totalReleases[year] += release; 
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
	
	public void addRecovery(Float r, Integer year) {
		if(r != null && !(r.isNaN()))
			recovery[year] += r;
	}
	
	public void addRecycling(Float r, Integer year) {
		if(r != null && !(r.isNaN()))
			recycling[year] += r;
	}
	
	public void addTreatment(Float t, Integer year) {
		if(t != null && !(t.isNaN()))
			treatment[year] += t;
	}
	
	public Float getTotalReleases() {
		return totalReleases[2013];
	}
	
	
	/*********************************************************
	 * 		Methods
	 *********************************************************/
	public static String titleCase(String realName) {
	    String space = " ";
	    String[] names = realName.split(space);
	    StringBuilder b = new StringBuilder();
	    for (String name : names) {
	        if (name == null || name.isEmpty()) {
	            b.append(space);
	            continue;
	        }
	        b.append(name.substring(0, 1).toUpperCase())
	                .append(name.substring(1).toLowerCase())
	                .append(space);
	    }
	    return b.toString();
	}
	
	public Boolean shouldPrint() {
		
		for(Integer i = 17; i < 27; i++) {
			if(totalReleases[i] != null && treatment[i] != null && recycling[i] != null && recovery[i] != null) {
				if(totalReleases[i] == 0.0 && treatment[i] == 0.0 && recycling[i] == 0.0 && recovery[i] == 0.0)
					return false; 
			} else {
				return false;
			}
		}
		return true;
	}
	
	public String printArrays() {
		String newString = "";
		for(Integer i = 0; i < 27; i++) {
			//json += "[" + totalReleases[i] + "," + recycling[i] + "," + treatment[i] + "," + recovery[i] + "]"; 
			newString += totalReleases[i];
			
			if(i < 26)
				newString += ",";
		}
		return newString;
	}
	
	public String getData() {
		//System.out.println("Get Data from Facility...");
		String tab = "\t";
		return facilityName + tab + latitude + tab + longitude + tab + county + tab + state + tab + printArrays();
	}
	
	public String getJSON() {

		String json = "";
		//String[] c = chemicals.split("\t");
		json += "\"facilityName\": \"" + titleCase(facilityName) + "\", \"county\": " + county + ", \"state\": " + state + ",  \"lat\": \"" + latitude + "\", \"long\": \"" + longitude + "\", \"releases\": [";//, \"air\": \"" + air + "\", \"water\": \"" + water + "\", \"underground\": \"" + underground + "\", \"landfill\": \"" + landfill + "\", \"surface\": \"" + surface + "\", \"offsite\": \"" + offsite + "\", \"other\": \"" + other + "\", \"totalReleases\": \"" + totalReleases + "\", \"chemicals\": [";
//		for(Integer i = 0; i < c.length; i++ ) {
//			json += "\"" + c[i].replace("\"", "") + "\"";
//			if(i < c.length - 1)
//				json += ", ";
//		}
		//json += "], \"temporal\": [";
		//json += "\"temporal\": [";
		
		// add an array of release, recycling, treatment, and recovery for each year
		for(Integer i = 0; i < 27; i++) {
			//json += "[" + totalReleases[i] + "," + recycling[i] + "," + treatment[i] + "," + recovery[i] + "]"; 
			json += totalReleases[i];
			
			if(i < 26)
				json += ",";
		}
		json += "], \"recycling\": [";
		for(Integer i = 0; i < 27; i++) {
			json += recycling[i];
			if(i < 26)
				json += ",";
		}
		json += "], \"treatment\": [";
		for(Integer i = 0; i < 27; i++) {
			json += treatment[i];
			if(i < 26)
				json += ",";
		}
		json += "], \"recovery\": [";
		for(Integer i = 0; i < 27; i++) {
			json += recovery[i];
			if(i < 26)
				json += ",";
		}
		json += "]";
		
		return json;
	}
	
}
