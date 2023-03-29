package collections.ir;

public class ArrayMap implements Map {
	
	private record MyEntry(Object key, Object value) implements Map.Entry {}
	/**
	 * @representationObject
	 * @invar | entries != null
	 * @invar | entries.stream().allMatch(e -> e instanceof Entry)
	 * @invar | entries.stream().map(e -> ((Entry)e).key()).distinct().count() == entries.size()
	 */
	private ArrayList entries = new ArrayList();
	

	@Override
	public Set entrySet() {
		Set result = new hashSet(entries.size());
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}
	
	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean containsKey(Object key) {
		return entries.stream().anyMatch(e -> ((Entry)e).key().equals(key));

	}

	@Override
	public Object get(Object key) {
		 return entries.stream().filter(e -> ((Entry)e).key().equals(key)).findFirst().orElse(null);
	}

	@Override
	public void put(Object key, Object value) {
		for (int i = 0; i < entries.size(); i++) {
			if (((Entry)entries.get(i)).key().equals(key)){
				entries.set(i, new MyEntry(key,value));
				return;
			}
		}
		entries.add(new MyEntry(key,value));

	}

	@Override
	public void remove(Object key) {
		for (int i = 0; i < entries.size(); i++) {
			if (((Entry)entries.get(i)).key().equals(key)){
				entries.remove(i);
				return;
			}
		}
	}
}
