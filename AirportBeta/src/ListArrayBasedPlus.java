public class ListArrayBasedPlus<T> extends ListArrayBased<T> {

    
    @SuppressWarnings("unchecked")
    public Object reverseList() {
                T[] items = getItems(); 
        T[] temp =(T[]) new Object[super.size()];
        for (int i = 0; i < super.size(); i++) {
            temp[i] = items[super.size() - 1 - i];
        }
        items = temp;
        return items;
    }

    @Override
    public String toString() {
                T[] items = getItems(); 
                        int numItems = size(); 
        String item = "";
        for (int i = 0;i < numItems; i++) {
            System.out.println(items[i].toString() +"\n");
        }
        return item;
    }
}