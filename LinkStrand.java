//Natnael Adere
public class LinkStrand implements IDnaStrand{
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	//myFirst is the first node in a linked list
	//myLast is the last node in a linked list
	//mySize is the total number of CHARACTERS in all nodes together
	//myAppends is the number of times that the append method has been called
	//	basically one less than the length  of the list
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private Node myCurrent;
	private int myIndex;
	private int myLocalIndex;
	
	
	/**
	 * DEFAULT CONSTRUCTOR
	 */
	public LinkStrand() {
		this("");
	}
	
	/**
	 * CONSTRUCTOR
	 * @param string s that will be used to initialize the class
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		myAppends = 0;
		mySize = source.length();
		
		myCurrent = myFirst;
		myIndex = 0;
		myLocalIndex = 0;
		
	}
	
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends+=1;
		return this;
	}
	
	@Override
	public IDnaStrand reverse() {
		LinkStrand ret = new LinkStrand();
		Node pointer = myFirst;
		Node last = null;
		while(pointer!=null) {
			StringBuilder copy = new StringBuilder(pointer.info);
			System.out.println("POINTER.INFO: " + pointer.info);
			copy.reverse();
			Node newfirst = new Node(copy.toString());
			System.out.println("NEWFIRST.INFO: " + newfirst.info);
			newfirst.next = last;
			last = newfirst;
			pointer = pointer.next;
		}
				
		while(last!=null) {
			ret.append(last.info);
			last = last.next;
		}	
		
	return ret;
	}
	
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public char charAt(int index) {

		if (index>=mySize || index < 0) {
			throw new IndexOutOfBoundsException("Your specified index " + index + " is greater than the total number of indices "+ (mySize-1));
		}
		int count = 0;
		int dex = 0;
		Node list = myFirst;
		if (index > myIndex) {
			count = myIndex;
			dex = myLocalIndex;
			list = myCurrent;
		}


		while(count!=index) {
			count++;
			dex++;
			if(dex>=list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}

		myCurrent = list;
		myIndex = count;
		myLocalIndex = dex;

		return list.info.charAt(dex);
	}
	
	/**
	 * converts the list of nodes into a string and returns it
	 * @return the nodes in the form of a string
	 */
	public String toString() {
		StringBuilder ret  = new StringBuilder();
		Node current = myFirst;
		while (current != null) {
			ret.append(current.info);
			current = current.next;
		}
		return ret.toString();
	}
	
}
