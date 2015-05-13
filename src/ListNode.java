
public class ListNode<E>
{
   // protected data (allows AsteroidLList access)
   protected ListNode<E> next; // the next ListNode in the list
   
   // data carried by the ListNode may be of any type consistent with the list
   protected E data;
   
   // constructor if we want to specify the next ListNode
   public ListNode(E item, ListNode<E> nxtNode) {
      data = item;
      next = nxtNode;
   }
   
   // constructor for new ListNode with specified data
   public ListNode(E item) {
      this(item, null);
   }
   
   // default constructor (probably not used in our game)
   public ListNode() {
      this(null, null);
   }
   
   // returns the object stored by the ListNode
   public E getData() {
      return data;
   }
   
   // data modifier - changes the object stored by the node
   public void setData(E item) {
      data = item;
   }
   
   // returns the next ListNode in the list
   public ListNode<E> getNext() {
      return next;
   }
   
   // sets the next ListNode in the list (for after removal)
   public void setNext (ListNode<E> nxtNode) {
      next = nxtNode;
   }
   
   // tests if the ListNode has a tail
   public boolean hasNext() {
      return (next != null);
   }
   
} // end class ListNode
