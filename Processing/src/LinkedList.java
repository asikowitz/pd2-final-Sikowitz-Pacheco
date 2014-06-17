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
		}
	}
	
	public Wall get(int i) {
		Node n = head;
		for (int x=0; x<i; x++)
			n = n.getNext();
		return n.getData();
	}
	
	public int size() {
		return length;
	}
}
