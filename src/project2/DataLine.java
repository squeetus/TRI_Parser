package project2;
public class DataLine {
	
	/*********************************************************
	 * 		Parsed Data
	 *********************************************************/
	String facilityName, chemicalName, county, state; 
	Float latitude, longitude, air, water, underground, landfill, surface, other, offsite, totalReleases;
	Float recycling, treatment, recovery;
	Integer year;
	
	Integer flags = 0; 
	
	DataLine(String line, Integer year, StateList stateList, CountyList countyList) {
		this.year = year;
		parseLine(line, stateList, countyList);
	}
	
	
	public Integer parseLine(String line, StateList stateList, CountyList countyList) {   
        String[] splitLine = line.split(",");
        Integer offset = 0; 
        
       // if(splitLine.length > 122)
   
    	//for(Integer i = 0; i < splitLine.length; i++)
    		//System.out.println(year + " " + i + " " + splitLine[i]);
    	
    	// throw away lines without enough data
    	if(splitLine.length < 50) {
    		System.out.println("NOT ENOUGH DATA ATTRIBUTES");
    		flags++;
    		return 0;
    	}
    	
    	
	
    	
    	
    	
    	//Works for 2013
    	if(splitLine.length != 102) {
			flags++;
			//System.out.println(splitLine.length);
    	}
    	
		facilityName = splitLine[2]
				.replace("\"", "")
				.replace("/",  "")
				.replace("\\", "")
				//.replace(" ", "")
				.replace("(", "-")
				.replace(")","-")
				.replace("&","-")
				.replace("\'","")
				.replace("*", "")
				.replace("#", "");
		
		String tmpC, tmpS;

		tmpC = splitLine[5].replace("\"", "").replace("(CITY)", "").trim();
		tmpS = splitLine[6].replace("\"", "");
		
		if(tmpC.equals("MC DONOUGH"))
			tmpC = "MCDONOUGH";
		
		county = countyList.getCountyID(tmpC);
		state = stateList.getStateID(tmpS);
		
		if(county.equals("-1") && state.equals("-1"))
			return 1;
		else if(county.equals("-1")) {
//			System.out.println("ERROR " + county + " " + tmpC + " " + state + " " + tmpS);
//			System.out.println(line);
			return 2;
		} else if(state.equals("-1")) {
//			System.out.println("ERROR " + county + " " + tmpC + " " + state + " " + tmpS);
//			System.out.println(line);
			return 3;
			//System.exit(0);
		}
		
		
		
		chemicalName = splitLine[26].replace("\"", "").replace(",","-");
		if((splitLine[10].replace("\"", "").equals("") || splitLine[10].replace("\"", "").equals(null)) || (splitLine[11].replace("\"", "").equals("") || splitLine[11].replace("\"", "").equals(null)) ) {
			flags++;
			latitude = 0f;
			longitude = 0f;
		}
		else {
			try {
				//Integer.valueOf(splitLine[10]);
				Float.valueOf(splitLine[10].replace("\"", ""));
				latitude = Float.valueOf(splitLine[10].replace("\"", ""));
				longitude = Float.valueOf(splitLine[11].replace("\"", ""));
			} catch(NumberFormatException ex) {
				
				try {
					latitude = Float.valueOf(splitLine[11].replace("\"", ""));
					longitude = Float.valueOf(splitLine[12].replace("\"", ""));
				} catch(NumberFormatException ex1) {
					latitude = Float.valueOf(splitLine[12].replace("\"", ""));
					longitude = Float.valueOf(splitLine[13].replace("\"", ""));
				}
			}
		} 
		
		//air = Float.valueOf(splitLine[35].replace("\"", "")) + Float.valueOf(splitLine[36].replace("\"", ""));
		//water = Float.valueOf(splitLine[37].replace("\"", ""));
		//underground = Float.valueOf(splitLine[38].replace("\"", "")) + Float.valueOf(splitLine[39].replace("\"", ""));
		//landfill = Float.valueOf(splitLine[40].replace("\"", "")) + Float.valueOf(splitLine[41].replace("\"", "")) + Float.valueOf(splitLine[42].replace("\"", ""));
		//surface = Float.valueOf(splitLine[43].replace("\"", "")) + Float.valueOf(splitLine[44].replace("\"", "")) + Float.valueOf(splitLine[45].replace("\"", ""));
		//other = Float.valueOf(splitLine[46].replace("\"", ""));
		//offsite = Float.valueOf(splitLine[68].replace("\"", ""));
		
		totalReleases = Float.valueOf(splitLine[85].replace("\"", ""));
		if(totalReleases == null || totalReleases.isNaN()) {
			System.out.println("totalReleases is fucked");
			return 1;
		}
		
		
		recovery = Float.valueOf(splitLine[91].replace("\"", "")) + Float.valueOf(splitLine[92].replace("\"", ""));
		recycling = Float.valueOf(splitLine[93].replace("\"", "")) + Float.valueOf(splitLine[94].replace("\"", ""));
		treatment = Float.valueOf(splitLine[95].replace("\"", "")) + Float.valueOf(splitLine[96].replace("\"", ""));
		
		//System.out.println(Float.valueOf(splitLine[92].replace("\"", "")) + Float.valueOf(splitLine[92].replace("\"", "")));
		
//		System.out.println(recovery);
//		System.out.println(recycling);
//		System.out.println(treatment);
		return 0;
		
	}
	
	
	/*********************************************************
	 * 		Get/Set
	 *********************************************************/
	public String getFacilityName() {
		return facilityName;
	}
	
	public String getFacilityNameTitleCase() {
		return titleCase(facilityName);
	}
	
	
	public String getCountyName() {
		return county;
	}
	
	public String getStateName() {
		return state;
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
	
	public Float getRecycling() {
		return recycling;
	}
	
	public Float getTreatment() {
		return treatment;
	}
	
	public Float getRecovery() {
		return recovery;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public Integer getFlags() {
		return flags;
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
	
	
	public String getData() {
		String tab = "\t";
		return year + tab + facilityName + tab + latitude + tab + longitude + tab + chemicalName + tab + recovery + tab + recycling + tab + treatment + tab + totalReleases;
	}
	
}
