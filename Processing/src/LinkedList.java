public class LinkedList {
	private Node head, tail;
	private int length;
	
	public LinkedList() {
		head = null;
		tail = null; //Second to last
		length = 0;
	}
	
	public void add(Wall w) { //Adds to end of list
		if (head == null && tail == null) {
			Node temp = new Node(w);
			head = temp;
			tail = temp;
		}
		else {
			Node temp = new Node(w);
			tail.setNext(temp);
			tail = temp;
		}
		length++;
	}
	
	public void remove() { //Removes head
		if (head != null) {
			head = head.getNext();
			length--;
			if (head == null)
				tail = null;
		}
	}
	
	public int addInt(int[] sent) {
		int c = 0;
		for (Node temp = head; temp != null; temp = temp.getNext(), c = c+5)
			temp.getData().addInt(sent, c);
		
		return c;
	}
	
	public void display() {
		for (Node temp = head; temp != null; temp = temp.getNext())
			if (!temp.getData().display())
				remove();
	}
	
	public int size() {
		return length;
	}
	
	public String toString() {
		String s = "";
		for (Node temp = head; temp != null; temp = temp.getNext())
			s = s + temp.getData() + ", ";
		return s;
	}
}
