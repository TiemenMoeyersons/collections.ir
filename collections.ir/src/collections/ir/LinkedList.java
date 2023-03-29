package collections.ir;

public class LinkedList implements List {

	/**
	 * 
	 */
	private class Node{
		/**
		 * @invar | previous != null
		 * @invar | next != null
		 * @invar | previous.next == this
		 * @invar | next.previous == this
		 * @invar | (value == null) == (this == sentinel)
		 * @peerObject
		 */
		private Node previous;
		private Object value;
		/**
		 * @peerObject
		 */
		private Node next;
		
		private int getCount() {
			return this == sentinel ? 0 : 1 + next.getCount();
		}
	}
	/**
	 * @post | size() == 0
	 */
	public LinkedList() {
		sentinel = new Node();
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
	}
	
	
	/**
	 * @invar | sentinel != null
	 * @invar | size == sentinel.next.getCount()
	 * @representationObject
	 */
	private Node sentinel;
	private int size;
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node n = sentinel.next; n != sentinel; n = n.next)
			result[i++] = n.value;
		return result;
	}

	@Override
	public int size() {
		return size;
	}
	
	private Node getNode(int index) {
		if (index < size / 2) {
			Node n = sentinel.next;
			while (0 < index) {
				n = n.next;
				index--;
			}
			return n;
		} else {
			Node n = sentinel;
			while (index < size) {
				n = n.previous;
				index++;
			}
			return n;
		}
	}
	@Override
	public Object get(int index) {
		return getNode(index).value;
		
		
		
	}

	@Override
	public int indexOf(Object element) {
		int i = 0;
		for (Node n = sentinel.next; n != sentinel; n = n.next) {
			if (n.value.equals(element))
				return i;
			i++;
		}
		return -1;
	}

	@Override
	public void add(int index, Object element) {
		Node n = getNode(index);
		Node newNode = new Node();
		newNode.previous = n.previous;
		newNode.next = n;
		newNode.value = element;
		newNode.previous.next = newNode;
		newNode.next.previous = newNode;
		size++;
		
	}

	@Override
	public void set(int index, Object element) {
		getNode(index).value = element;
		
	}

	@Override
	public void remove(int index) {
		Node n = getNode(index);
		n.previous.next = n.next;
		n.next.previous = n.previous;
		size--;
		
	}

}
