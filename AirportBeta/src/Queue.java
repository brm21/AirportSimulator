


	public class Queue <T> implements QueueInterface<T> {

	    private int numItems;
	    private int front;
	    private int back;
	    private T[] queueArr;

	    public Queue() {
	        front = back = 0;
	        numItems = 0;
	        queueArr = (T[]) new Object [3];

	    }

	    @Override
	    public boolean isEmpty() {

	        return numItems == 0;
	    }

	    @Override
	    public void enqueue(Object newItem) throws QueueException {
	        if(numItems == queueArr.length) {
	            queueArr = resize();
	        }

	        queueArr[back] = (T) newItem;
	        back = (back + 1) % queueArr.length;

	        numItems++;

	    }

	    @Override
	    public T dequeue() throws QueueException {
	        if(!isEmpty()) {
	            T element = queueArr[front];
	            queueArr[front] = null;
	            front = (front+1) % queueArr.length;
	            numItems--;
	            return element;
	        }
	        else {
	            throw new QueueException("No items in Queue!");

	        }

	    }

	    @Override
	    public void dequeueAll() {
	        front = 0;
	        back = 0;
	        numItems = 0;
	        queueArr = (T[]) new Object[3];

	    }

	    @Override
	    public T peek() throws QueueException {
	        if(!isEmpty()) {

	            return queueArr[front];	
	        }
	        else {
	            throw new QueueException("No items in Queue!");
	        }

	    }

	    @Override 
	    public String toString() {
	        String itemString = "";
	        int index = back; 
	        //if(front == back || back < front) { //it is filled
	        //    back = queueArr.length; 
	        //}
	        for(int i = front, count = 0; count < queueArr.length; i = (i+1)%queueArr.length) {
	            //for(int i = front; i < back;i++){
	            if(queueArr[i] != null) {
	                System.out.println(queueArr[i].toString() + "\n"+ itemString); 
	            }
	            count++;
	        }
	        back = index; 
	        return itemString;
	    }

	    protected T[] resize() {
	        T[] temp = (T[]) new Object[queueArr.length * 2];
	        for(int i = 0; i < numItems;i++) {
	            temp[i] = queueArr[front];
	            front = (front+1) % queueArr.length;
	        }

	        front = 0;
	        back = numItems;
	        queueArr = temp;
	        return queueArr;
	    }

	    public void display() {
	        T[] temp = (T[]) new Object[queueArr.length];
	        if(isEmpty()) {
	            System.out.println("No items in queue!");
	        }
	        else {
	            for(int i = 0; i < numItems; i++) {
	                temp[i] = queueArr[front];
	                front = (front +1) % queueArr.length;
	                System.out.println(temp[i].toString());
	            }
	        }

	        return;
	    }

	}
