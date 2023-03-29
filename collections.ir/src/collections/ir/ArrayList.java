package collections.ir;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayList implements List {
	/**
	 * @invar | 0 <= size
	 * @invar | elements != null
	 * @invar | size <= elements.length
	 * @invar | IntStream.range(0, elements.length)
	 * 		  | .allMatch(i -> (elements[i] == null) == (size <= i))
	 * @representationObject
	 * 
	 */
	private Object[] elements;
	private int size;
	
	/**
	 * @post | size() == 0
	 */
	public ArrayList() {
		elements = new Object[10];
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) {
		return elements[index];
	}

	@Override
	public int indexOf(Object element) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element))
				return 1;
		return -1;
	}

	@Override
	public void add(int index, Object element) {
		if (size == elements.length) 
			elements = Arrays.copyOf(elements, elements.length);
		System.arraycopy(elements, index, element, index+1, size - index);
		elements[index] = element;
		size++;
	}

	@Override
	public void set(int index, Object element) {
		elements[index] = element;
	}

	@Override
	public void remove(int index) {
		System.arraycopy(elements, index + 1, elements, index, --size - index);
	}

}
