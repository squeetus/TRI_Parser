/*********************************************************
 * 		
 * 		FacilityList to extend SLList
 * 
 *********************************************************/

public class FacilityList extends SLList<Facility> {
	
//	/** Find a particular facility by name **/
//	public Boolean find_facility(String facilityName) { 
//		
//		Link<Facility> tcurr = getFront();
//
//		while (tcurr.next() != null) {
//			
//			// compare facility names
//			Facility f = tcurr.next().element();
//			int comparison_result = facilityName.compareTo(f.getFacilityName());
//
//			// found facility
//			if (comparison_result == 0) {	
//				setCurrent(tcurr);
//				System.out.println("Found " + tcurr.next().element().getFacilityName());
//				return true;
//            }
//			// facility does not exist
//			else if (comparison_result < 0) { 
//				return false;
//			}
//			else  tcurr = tcurr.next();
//		}
//		return false;
//	}

	/** find the position of a new facility and insert it where appropriate **/
	public void find_and_insert(DataLine d) { 
		
		this.moveToStart();
		Boolean found = false;
		//System.out.println(d.getData());
		Facility f = null;
		int comparison_result = 0;
		
		// empty list, insert
		if (length() == 0) {
			//System.out.println("inserting at start");
			super.insert(new Facility(d));
			return;
		}
		// Find position to insert or update (alphabetical order)
		else while (curr.next() != null && !found) {
			//System.out.println("searching list (size) " + this.length());
			// compare facility names
			f = curr.next().element();
			comparison_result = d.getFacilityName().compareTo(f.getFacilityName());

			// add chemical to facility if found
			if (comparison_result == 0) {
				//System.out.println("found facility");
				found = true;
				f = curr.next().element();
				f.addChemical(d.getChemicalName());
				f.addReleases(d.getTotalReleases());
				return;
			}
			// found insertion point, insert facility
			else if (comparison_result < 0) {
				//System.out.println("found insertion point");
				found = true;
				super.insert(new Facility(d));
				return;
			}
			else curr = curr.next();
		}
		
		// insertion point is the last node
		if (!found)	{
			//System.out.println("not found");
			insert(new Facility(d));
		}
	}
}
