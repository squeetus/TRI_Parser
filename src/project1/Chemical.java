package project1;
public class Chemical {
	
	/*********************************************************
	 * 		Private Data
	 *********************************************************/
	private String chemicalName, chemical;
	private String facilities;
	private Float air, water, underground, landfill, surface, other, offsite, totalReleases;
	private Integer flags = 0; 
	
	Chemical(DataLine d) {
		chemicalName = d.getChemicalName();
		if(chemicalName.toLowerCase().contains("nitrate"))
			chemical = "nitrate";
		else if(chemicalName.toLowerCase().contains("benzene"))
			chemical = "benzene";
		else if (chemicalName.toLowerCase().contains("nickel"))
			chemical = "nickel";
		else 
			chemical = "";
			
		facilities = d.getFacilityName();
		totalReleases = d.getTotalReleases();
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
	public String getChemicalName() {
		return chemicalName;
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
	
	public String getMoreJSON() {
		String json = "";
		String[] f = facilities.split("\t");
		json += "\"chemicalName\": \"" + chemicalName + "\", \"air\": \"" + air + "\", \"water\": \"" + water + "\", \"underground\": \"" + underground + "\", \"landfill\": \"" + landfill + "\", \"surface\": \"" + surface + "\", \"offsite\": \"" + offsite + "\", \"other\": \"" + other + "\", \"totalReleases\": \"" + totalReleases + "\"";//, \"facilities\": [";
//		for(Integer i = 0; i < f.length; i++ ) {
//			json += "\"" + f[i].replace("\"", "") + "\"";
//			if(i < f.length - 1)
//				json += ", ";
//		}
//		json += "]";
		
		return json;
	}
	
	public String getMoreCSV() {
		String csv = "";
		csv += chemicalName.replace(",", "-") + "," + chemical + "," + air + "," + water + "," + underground + "," + landfill + "," + surface + "," + offsite + "," + other + "," + totalReleases + ",";
		csv += facilities.replace("\t","_");
		return csv;
		
	}
	
}
