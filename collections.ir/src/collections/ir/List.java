package collections.ir;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Each instance of this interface stores a sequence of Object values.
 * @invar | toArray() != null
 * @invar | stream.allMatch(e -> e != null)
 */
public interface List {
	/**
	 * @inspects | this
	 * @creates | result
	 */
	Object[] toArray();
	
	default Stream<Object> stream() {return Arrays.stream(toArray());}
	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	int size();
	
	/**
	 * @pre | 0 <= index && index < size()
	 * @inspects | this
	 * @post | result == toArray()[index]
	 */
	Object get(int index);
	
	/**
	 * @pre | element != null
	 * @inspects | this
	 * @post | result == IntStream.range(0,size()).filter(i -> get(i).equals(element))
	 * 		 |	.findFirst().orElse(-1)
	 */
	int indexOf(Object element);
	
	/**
	 * @pre | element != null
	 * @inspects | this
	 * @post | result == (indexOf(element) != -1)
	 */
	
	default boolean contains(Object element) {return indexOf(element) != -1;}
	
	/**
	 * @pre | 0 <= index && index <= size()
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(),0, index, old(toArray()),0,index)
	 * @post | Arrays.equals(toArray(), index + 1, size(), old(toArray()),index,old(size()))
	 * @post | get(index) == element
	 */
	void add(int index, Object element);
	/**
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(),0, old(size()), old(toArray()),0,old(size()))
	 * @post | get(old(size())) == element 
	 */
	default void add(Object element) {add(size(),element);}

	/**
	 * @pre | 0 <= index && index < size()
	 * @mutates | this
	 * @post | size() == old(size())
	 * @post | IntStream.range(0,size()).allMatch(i -> get(i) == (i == index ? element: old(toArray())[i]))
	 * 
	 */
	void set(int index, Object element);
	
	/**
	 * @pre | 0 <= index && index < size()
	 * @mutates | this
	 * @post | size() == old(size()) - 1
	 * @post | Arrays.equals(toArray(),0,index,old(toArray()),0,index)
	 * @post | Arrays.equals(toArray(),index,size(),old(toArray()),index + 1, old(size()))
	 */
	void remove(int index);
	
	/**
	 * complexe spec; oefening voor thuis
	 */
	default void remove(Object element) {
		int index = indexOf(element);
		if (0 <= index)
			remove(index);
	}


}
