public class Node {
	private Wall w;
	private Node next;
	
	public Node(Wall w) {
		this.w = w;
		next = null;
	}
	
	public Wall getData() {
		return w;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
	
	public String toString() {
		return ""+w;
	}
}
