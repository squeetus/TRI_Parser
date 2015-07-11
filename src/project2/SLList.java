package project2;
/*********************************************************
 * 
 * 		Singly-linked List
 * 
 *********************************************************/

public class SLList<E> implements List<E>{
	private Link<E> head;					
	private Link<E> tail;				
	protected Link<E> curr;				
	private int cnt;							
	
	/*********************************************************
	 * 		Constructors
	 *********************************************************/
	public SLList(int i) {  
		this();
	}
	
	public SLList() {
		head = new Link<E>(null);
		tail = head;
		curr = head;
		cnt = 0;
	}
	
	/*********************************************************
	 * 		Methods from List
	 *********************************************************/
	@Override
	/** Remove all elements **/
	public void clear() {
		head.setNext(null);						
		head = new Link<E>(null);			
		tail = head;
		curr = head;
	}

	@Override
	/** Insert "it" at current position **/
	public void insert(E it) {
		curr.setNext(new Link<E>(it, curr.next()));	
		if(tail == curr) tail = curr.next(); 
		cnt++;
		
		//System.out.println("Inserting...");
	}

	@Override
	/** Append "it" to end of list **/
	public void append(E it) {
		tail.setNext(new Link<E>(it, null));	
		tail = tail.next();					
		cnt++;
	}

	@Override
	/** Remove and return current element **/
	public E remove() {
		if(curr.next() == null) return null; 
		E it = curr.next().element();		
		if(tail == curr.next()) tail = curr;	
		curr.setNext(curr.next().next()); 
		cnt--;					
		
		return it;							
	}

	@Override
	/** Set curr to list start **/
	public void moveToStart() { curr = head; }

	@Override
	/** Set curr to list end */
	public void moveToEnd() { curr = tail; }

	@Override
	/** Move curr one step left; no change if now at front **/
	public void prev() {
		Link<E> temp = head;				
		
		if(curr == head) {  
			
		} 
		else {
			while(temp.next() != curr)  {
				temp = temp.next();
			}
		}
		curr = temp;
	}

	@Override
	/** Move curr one step right; no change if now at end **/
	public void next() {
		if (curr != tail) 
			curr = curr.next();
	}

	@Override
	/** @return List length **/
	public int length() { return cnt; }

	@Override
	/** @return The position of the current element **/
	public int currPos() {
		Link<E> temp = head;
		int i;
		for(i = 0; curr != temp; i++) {
			temp = temp.next();
		}
		return i;
	}

	@Override
	/** Move down list to "pos" position **/
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <=cnt) : "Position out of range";
		curr = head;
		for(int i = 0; i < pos; i++) 
			curr = curr.next();
	}

	@Override
	/** @return Current element value **/
	public E getValue() {
		if(curr == tail)
			return curr.element();
		
		if(curr.next() == null) 
			return null;

		//return curr.next().element();
		return curr.element();
	}
	
	/*********************************************************
	 * 		Added Methods
	 *********************************************************/
	public Link<E> getFront() {
		return head;
	}
	public Link<E> getEnd() {
		return tail;
	}
	public Link<E> getCurrent() {
		return curr;
	}
	public void setCurrent(Link<E>  c) {
		curr = c;
	}
	
	public void print() {
		curr = head;
		while((curr = curr.next()) != null)
			System.out.println(curr.element());
		
		
	}
}
