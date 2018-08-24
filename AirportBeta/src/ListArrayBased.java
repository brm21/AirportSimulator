
public class ListArrayBased<T> {
    private final int MAX_LIST = 3;
    private T [] items; // an array of list items //fixes programming style
    private int numItems; // number of items in list

    @SuppressWarnings("unchecked")
    public ListArrayBased() {

        items = (T[]) new Object[MAX_LIST];
        numItems = 0;

    } // end default constructor
    
    public T[] getItems() {
        return items; 
    }
    
    public int getMaxListSize() 
    {
        return MAX_LIST; 
       }

    public boolean isEmpty() {

        return numItems == 0;

    } // end isEmpty

    public int size() {
        return numItems;
    } // end size

    @SuppressWarnings("unchecked")
    public void removeAll() {
        // Creates a new array; marks old array for
        // garbage collection.

        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll

    
    public void add(int index, Object item) throws ListIndexOutOfBoundsException {
        if(numItems == MAX_LIST) {
            items = increaseSize();
        }
        if (index >= 0 && index <= numItems) {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems - 1; pos >= index; pos--) // textbook code modified to eliminate logic error causing
                                                                // ArrayIndexOutOfBoundsException
            {
                items[pos + 1] = items[pos];
            } // end for
                // insert new item
            items[index] =  (T) item;
            numItems++;
        } else {

            // index out of range
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on add");
        } // end if
    } // end add

    public T get(int index) throws ListIndexOutOfBoundsException {
        if (index >= 0 && index < numItems) {
            return items[index];
        } else {
            // index out of range
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on get");
        } // end if
    } // end get

    public void remove(int index) throws ListIndexOutOfBoundsException {
        if (index >= 0 && index < numItems) {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index + 1; pos < numItems; pos++) // textbook code modified to eliminate logic error causing
                                                                // ArrayIndexOutOfBoundsException

            {
                items[pos - 1] = items[pos];
                items[pos] = null;// fixes memory leak
            } // end for
            numItems--;
        } else {
            // index out of range
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on remove");
        } // end if
    } // end remove

        public T[] increaseSize() {


        
        T[] temp = (T[]) new Object[numItems + MAX_LIST];
        for (int i = 0; i < numItems; i++) {
            temp[i] = items[i];

        }
        items = temp;
        return items;
    }
    
}
