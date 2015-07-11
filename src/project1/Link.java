package project1;

public class Link<E> {

	private E element; 
	private Link<E> next; 
	
	
	/*********************************************************
	 * 		Constructors
	 *********************************************************/
	Link(E it, Link<E> nextval) {
		element = it;
		next = nextval;
	}

	Link(Link<E> nextval) {
		next = nextval;
	}

	
	/*********************************************************
	 * 		Get/Set
	 *********************************************************/
	Link<E> next() {
		return next;
	}

	void setNext(Link<E> nxtval) {
		next = nxtval;
	}

	E element() {
		return element;
	}

	void setElement(E it) {
		element = it;
	}

	/*********************************************************
	 * 		Extension to support freelists
	 *********************************************************/
	static Link freelist = null; 

	/* return a new link */
	static <E> Link<E> get(E it, Link<E> nextval) {
		if (freelist == null)
			return new Link<E>(it, nextval); 
		Link<E> temp = freelist; 
		freelist = freelist.next();
		temp.setElement(it);
		temp.setNext(nextval);
		return temp;
	}

	/* Return a link to the freelist */
	void release() {
		element = null; 
		next = freelist;
		freelist = this;
	}

}
