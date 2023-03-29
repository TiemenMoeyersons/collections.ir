package collections.ir;

import java.util.Objects;

/**
 * @invar | entrySet() != null
 * @invar | entrySet().stream().allMatch(e -> e instanceof Entry)
 * @invar | entrySet().stream().map(e -> ((Entry)e).key()).distinct().count() == size()
 * 
 *
 */
public interface Map {
	/**
	 * @invar | key() != null
	 * @invar | value() != null
	 * @author tieme
	 */
	interface Entry {
		/**
		 * @inspects | this
		 */
		Object key();
		/**
		 * @inspects | this
		 */
		Object value();
		/**
		 * @post | result == (other instanceof Entry e && key().equals(e.key()) && value().equals(e.value()))
		 */
		boolean equals(Object other);
	}
	/**
	 * @inspects | this
	 * @creates | result
	 */
	Set entrySet();
	
	/**
	 * @inspects | this
	 * @post | result == entrySet().size()
	 */
	int size();
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream().anyMatch(e-> ((Entry)e).key().equals(key))
	 * @param key
	 * @return
	 */
	boolean containsKey(Object key);
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream().filter(e -> ((Entry)e).key().equals(key)).findFirst().orElse(null)
	 * @return
	 */
	Object get(Object key);
	/**
	 * @pre | key != null
	 * @pre | value != null
	 * @mutates | this
	 * @post | Objects.equals(key, get(key))
	 * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).key().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream().allMatch(e -> ((Entry)e).key().equals(key) || old(entrySet()).contains(e))
	 */
	void put(Object key, Object value);
	
	/**
	 * @pre | key != null
	 * @mutates | this
	 * @post | !containsKey(key)
	 * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).key().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream().allMatch(e -> old(entrySet()).contains(e))
	 */
	void remove(Object key);
}
