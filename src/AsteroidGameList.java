
public class AsteroidGameList<E> implements GameLinkedList<E> {
   
   private ListNode<E> head;
   private int nodeCount;
   
   // constructor for an empty list
   // head is set to a node without data
   public AsteroidGameList() {
      head = new ListNode<E>();
      nodeCount = 0;
   } // end constructor
   
   // adds a new ListNode<E> with newData to the end of the list
   public void add(E newData)
   {
      ListNode<E> temp = new ListNode<E>(newData);
      ListNode<E> current = head;
      
      // find the end of the list
      while (current.hasNext()) {
         current = current.getNext();
      }
      
      // set the end ListNode's next to our new ListNode
      current.setNext(temp);
      nodeCount++;
   } // end add
   
   // removes the ListNode at the specified index and returns true if successful
   public boolean remove(int index)
   {
      // if index is out of range, there is an error: return false
      if (index < 1 || index > size())
         return false;
      
      // find the correct ListNode corresponding to index
      ListNode<E> current = head; // start at the head
      
      // loop only runs if index != head node
      // current ends up referring to the node prior to the one to remove
      for (int k = 1; k < index; k++) {
         if (!current.hasNext())
            return false; // something is wrong
         
         current = current.getNext();
      } // end for
      
      // current is now the ListNode to prior to the one to remove
      current.setNext(current.getNext().getNext());
      nodeCount--;
      return true;
   } // end remove
   
   // returns the data stored by the ListNode at the specified index 
   public E peek(int index) {
      // index for the first node is 1
      if (index <= 0)
         return null;
      
      ListNode<E> current = head.getNext();
      for (int i = 1; i < index; i++) {
         if (current.getNext() == null)
            return null;
         
         current = current.getNext();
      }
      return current.getData();
   } // end peek
   
   // returns the ListNode at the specified index 
   public ListNode<E> peekNode(int index) {
      // index for the first node is 1
      if (index <= 0)
         return null;
      
      ListNode<E> current = head.getNext();
      for (int i = 1; i < index; i++) {
         if (current.getNext() == null)
            return null;
         
         current = current.getNext();
      }
      return current;
   } // end peekNode
   
   // returns the size of the list (possibly unused in the game)
   public int size() {
      return nodeCount;
   } // end size
   
   // resets the list to empty
   public void clear() {
      head = null;
      nodeCount = 0;
   } // end clear
   
} // end class AsteroidGameList
