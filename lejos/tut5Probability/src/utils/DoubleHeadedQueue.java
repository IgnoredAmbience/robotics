package utils;

import java.util.Iterator;

public class DoubleHeadedQueue <E> implements Iterable<E> {
	
	private Element<E> front;
	private Element<E> back;
	
	public DoubleHeadedQueue() {
		
	}
	
	public void pushToFront(E e) {
		Element<E> fr = new Element<E> (e);
		
		if (front == null) {
			front = fr;
			back = fr;
		} else {
		
			fr.next = front;
			front.prev = fr;
			
			front = fr;
		}
	}
	
	public void pushToBack(E e) {
		Element<E> bc = new Element<E> (e);
		
		if (back == null) {
			front = bc;
			back = bc;
		} else {
		
			this.back.next = bc;
			bc.prev = back;
		
		
			this.back = bc;
		}
	}
	
	
	public E popFront() {
		
		if (front == null) {
			return null;
		}
		
		E ret = front.o;
		
		if (back == front) {
			back = null;
			front = null;
		} else {
			front = front.next;
			
			if (front != null) {
				front.prev = null;
			}
		}
		
		return ret;
	}
	
	public E popBack() {
		
		if (back == null) {
			return null;
		}
		
		E ret = back.o;
		
		if (back == front) {
			back = null;
			front = null;
		} else {
			back = back.prev;
			
			if (back != null) {
				back.next = null;
			}
			
		}
		
		return ret;
		
	}
	
	public E peekFront() {
		
		return (front != null) ? front.o : null;
	}
	
	public E peekBack() {
		return (back != null) ? back.o : null;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	
	private class Element <T extends E> {
		
		public T o;
		public Element<T> next;
		public Element<T> prev;
		
		public Element(T object) {
			this.o = object;
			this.next = null;
			this.prev = null;
		}
		
	}

	private class DoubleIt implements Iterator<E> {
		
		private Element<E> current;
		
		public DoubleIt () {
			this.current = front;
		}
		
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public E next() {
			current = current.next;
			return current.o;
		}

		@Override
		public void remove() {
			
			if (current.next != null) {
				current.next.prev = current.prev;
			}
			if (current.prev != null) {
				current.prev.next = current.next;
			}
			
			current = current.next;
			
		}
		
	}


	@Override
	public Iterator<E> iterator() {
		return new DoubleIt();
	}
	
}
