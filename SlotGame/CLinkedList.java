import java.awt.image.BufferedImage;

public class CLinkedList {
	
	private class Node {
		BufferedImage data;
		Node next;
		Node prev;
	}
	
	private Node head;
	private Node tail;
	private int totalElements;
	
	public void add(BufferedImage x) {
		Node newNode = new Node();
		newNode.data = x;
		
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			/*Node runner, previous;
			runner = head.next;
			previous = head;
			
			while (runner != tail)
			{
				previous = runner;
				runner = runner.next;
			}*/
			
			tail.next = newNode;
			newNode.prev = tail;
			newNode.next = head;
			tail = newNode;
			head.prev = tail;
		}
		totalElements++;
	}
	
	public BufferedImage get(int index){
		if (totalElements == 0) {
			throw new IllegalStateException("List is empty.");
		}
		int temp = 0;
		Node runner = head;
		
		if (index == -1){
			return head.prev.data;
		} else if (index == totalElements){
			return tail.next.data;
		}
		
		while (temp != index && index <= totalElements) {
			runner = runner.next;
			temp++;
			}
		return runner.data;
	}
	
	public int size() {
		return totalElements;
	}
}
