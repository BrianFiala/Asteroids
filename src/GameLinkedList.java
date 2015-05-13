
interface GameLinkedList<E>
{
   // adds a new ListNode<E> with newData to the end of the list
   public void add(E newData);
   
   // removes the ListNode at the specified index and returns true if successful
   public boolean remove(int index);
   
   // returns the data stored by the ListNode at the specified index 
   public E peek(int index);
   
   // returns the ListNode at the specified index 
   public ListNode<E> peekNode(int index);
   
   // returns the size of the list (possibly unused in the game)
   public int size();
   
   // resets the list to empty
   public void clear();
   
} // end interface GameLinkedList
