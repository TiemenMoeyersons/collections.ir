package collections.ir;

public class ArraySet implements Set {
	/**
	 * @invar | elements != null
	 * @invar | elements.stream().distinct().count() == elements.size()
	 * @representationObject
	 */
	private ArrayList elements = new ArrayList();
	/**
	 * @post | size() == 0
	 */
	public ArraySet() {}
	
	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public boolean contains(Object element) {
		return elements.contains(element);
	}

	@Override
	public void add(Object element) {
		if (!elements.contains(element))
				elements.add(element);
	}

	@Override
	public void remove(Object element) {
		elements.remove(element);
	}

}
