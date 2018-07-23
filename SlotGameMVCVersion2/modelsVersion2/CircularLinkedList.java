package modelsVersion2;

//Modified Circular Linked List Data Structure to simulate the reels
public class CircularLinkedList {
	
	private class Node {
		SymbolEnum data;
		Node next;
		Node prev;
	}
	
	private Node head;
	private Node tail;
	private int totalElements;
	
	public void add(SymbolEnum x) {
		Node newNode = new Node();
		newNode.data = x;
		
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		else {			
			tail.next = newNode;
			newNode.prev = tail;
			newNode.next = head;
			tail = newNode;
			head.prev = tail;
		}
		totalElements++;
	}
	
	public SymbolEnum get(int index){
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
	
	public SymbolEnum getPreviousElement(int index) {
		if (totalElements == 0) {
			throw new IllegalStateException("List is empty.");
		}
		int temp = 0;
		Node runner = head;
		
		while (temp != index && index <= totalElements) {
			runner = runner.next;
			temp++;
			}
		return runner.prev.data;
	}
	
	public SymbolEnum getNextElement(int index) {
		if (totalElements == 0) {
			throw new IllegalStateException("List is empty.");
		}
		int temp = 0;
		Node runner = head;
		
		while (temp != index && index <= totalElements) {
			runner = runner.next;
			temp++;
			}
		return runner.next.data;
	}
	
	public int size() {
		return totalElements;
	}
}
