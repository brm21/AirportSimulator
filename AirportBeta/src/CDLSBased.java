
public class CDLSBased<T> {
	private DNode<T> tail;
	private int numItems;

	public CDLSBased() {
		tail = null;
		numItems = 0;
	}

	
	public boolean isEmpty() {
		return numItems == 0;
	}

	
	public int size() {

		return numItems;
	}

	private DNode<T> find(int index) {
		DNode<T> curr = tail;

		if (index > numItems / 2) {
			for (int skip = numItems; skip > index; skip--) {
				curr = curr.getPrev();
			}
		} else {

			for (int skip = 0; skip < index; skip++) {
				curr = curr.getNext();
			}
		}
		return curr;
	}

	
	public void add(int index, T item) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems + 1) {
			DNode<T> newNode = new DNode<T>(item, null, null);
			if (tail == null) {

				newNode.setNext(newNode);
				newNode.setPrev(newNode);
				tail = newNode;
			}

			if (index == 0) {
				DNode<T> curr = tail.getNext();

				newNode.setPrev(tail);
				tail.setNext(newNode);
				curr.setPrev(newNode);
				newNode.setNext(curr);

			}

			else {

				DNode<T> prev = find(index - 1);
				DNode<T> temp = prev.getNext();
				prev.setNext(newNode);
				newNode.setPrev(prev);
				newNode.setNext(temp);
				temp.setPrev(newNode);
				tail = find(size() - 1);
			}
			numItems++;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds exception on add");
		}
	}

	
	public T get(int index) throws ListIndexOutOfBoundsException {

		if (index >= 0 && index < numItems) {
			// get reference to node, then data in node
			DNode<T> curr = find(index);
			T dataItem = curr.getItem();
			return dataItem;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds exception on get");
		} // end if
	}

	
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems) {
			if (index == 0) {
				DNode<T> curr = tail.getNext();
				DNode<T> prev = tail.getPrev();
				curr.setPrev(prev);
				prev.setNext(curr);
				tail = prev;
			} else {
				DNode<T> prev = find(index - 1);
				DNode<T>curr = find(index + 1);
				curr.setPrev(prev);
				prev.setNext(curr);
				tail = find(size() - 1);
			}
			numItems--;
		}

		else {
			throw new ListIndexOutOfBoundsException("List index out of bounds exception on remove");
		} // end if
	}

	
	public void removeAll() {
		tail = null;
		numItems = 0;

	}

}
