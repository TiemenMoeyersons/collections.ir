package collections.ir;

import java.util.Arrays;
import java.util.stream.IntStream;

public class hashSet implements Set {
	/**
	 * @representationObject
	 * @representationObjects
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i -> buckets[i] != null
	 * 		  | 	&& buckets[i].stream().allMatch(e -> Math.floorMod(e.hashCode(),buckets.length) == i))
	 */
	private Set[] buckets;
	
	@Override
	public Object[] toArray() {
		return Arrays.stream(buckets).flatMap(b -> b.stream()).toArray();
	}

	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(b -> b.size()).sum();
	}

	@Override
	public boolean contains(Object element) {
		return getBucket(element).contains(element);
	}
	/**
	 * @pre | 1 <= capacity
	 * @post | size() == 0
	 */
	public hashSet(int capacity) {
		buckets = new Set[capacity];
		for (int i = 0; i < capacity; i++)
			buckets[i] = new ArraySet();
	}

	private Set getBucket(Object element) {
		return buckets[Math.floorMod(element.hashCode(), buckets.length)];
	}

	@Override
	public void add(Object element) {
		getBucket(element).add(element);
	}

	@Override
	public void remove(Object element) {
		getBucket(element).remove(element);

	}

}
