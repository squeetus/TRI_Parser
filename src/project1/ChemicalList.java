package project1;
/*********************************************************
 * 		
 * 		ChemicalList to extend SLList
 * 
 *********************************************************/

public class ChemicalList extends SLList<Chemical> {
	
//	/** Find a particular Chemical by name **/
//	public Boolean find_Chemical(String chemicalName) { 
//		
//		Link<Chemical> tcurr = getFront();
//
//		while (tcurr.next() != null) {
//			
//			// compare Chemical names
//			Chemical f = tcurr.next().element();
//			int comparison_result = chemicalName.compareTo(f.getChemicalName());
//
//			// found Chemical
//			if (comparison_result == 0) {	
//				setCurrent(tcurr);
//				System.out.println("Found " + tcurr.next().element().getChemicalName());
//				return true;
//            }
//			// Chemical does not exist
//			else if (comparison_result < 0) { 
//				return false;
//			}
//			else  tcurr = tcurr.next();
//		}
//		return false;
//	}

	/** find the position of a new Chemical and insert it where appropriate **/
	public void find_and_insert(DataLine d) { 
		
		this.moveToStart();
		Boolean found = false;
		//System.out.println(d.getData());
		Chemical c = null;
		int comparison_result = 0;
		
		// empty list, insert
		if (length() == 0) {
			//System.out.println("inserting at start");
			super.insert(new Chemical(d));
			
			return;
		}
		// Find position to insert or update (alphabetical order)
		else while (curr.next() != null && !found) {
			//System.out.println("searching list (size) " + this.length());
			// compare Chemical names
			c = curr.next().element();
			comparison_result = d.getChemicalName().compareTo(c.getChemicalName());

			// add chemical to Chemical if found
			if (comparison_result == 0) {
				//System.out.println("found Chemical");
				found = true;
				c = curr.next().element();
				c.addFacility(d.getFacilityName());
				c.addReleases(d.getTotalReleases());
				c.addAir(d.getAir());
				c.addWater(d.getWater());
				c.addUnderground(d.getUnderground());
				c.addLandfill(d.getLandfill());
				c.addSurface(d.getSurface());
				c.addOffsite(d.getOffsite());
				c.addOther(d.getOther());
				
				return;
			}
			// found insertion point, insert Chemical
			else if (comparison_result < 0) {
				//System.out.println("found insertion point");
				found = true;
				super.insert(new Chemical(d));
				return;
			}
			else curr = curr.next();
		}
		
		// insertion point is the last node
		if (!found)	{
			//System.out.println("not found");
			insert(new Chemical(d));
		}
	}
}
