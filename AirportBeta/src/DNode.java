

public class DNode<T> {
	 private T item;
	  private DNode<T> next;
	  private DNode<T> back;
	  public DNode(T newItem)
	  {
	    this.item = newItem;
	    next = back = this;
	    
	  } // end constructor

	  public DNode(T newItem, DNode<T> nextNode, DNode<T> prevNode)
	  {
	    this.item = newItem;
	    this.next = nextNode;
	    this.back = prevNode;
	  } // end constructor

	  public void setItem(T newItem)
	  {
	    this.item = newItem;
	  } // end setItem

	  public T getItem()
	  {
		    return item;
		  } // end getItem

	  public void setNext(DNode<T> nextNode)
		  {
		    this.next = nextNode;
		  } // end setNext
	  
	  public void setPrev(DNode<T> prevNode) {
			  this.back = prevNode;
		  } // end setPrev
      
	  public DNode<T> getNext()
		  {
		    return next;
		  } // end getNext
      
      public DNode<T> getPrev() {
			  return back;
		  } // end getPrev
	
}