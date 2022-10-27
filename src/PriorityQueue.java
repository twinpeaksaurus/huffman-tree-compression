
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;

/**
 * The PriorityQueue class uses an ArrayList to store the data of the priority
 * queue. It implements the Queue interface and extends AbstractQueue.
 */

public class PriorityQueue<E> extends AbstractQueue<E>
      implements Queue<E> {

   // ArrayList object will hold the elements' data.
   private final ArrayList<E> theData;
   // Comparator can be used to set the order of sorting in the
   // queue.
   private final Comparator<E> comp;

   @SuppressWarnings("unchecked")
   public PriorityQueue() {
      theData = new ArrayList<>(10);
      comp = (left, right) -> ((Comparable<E>) left).compareTo(right);
   }

   /**
    * Creates a heap-based priority queue with the specified initial capacity that
    * orders its elements according to the specified Comparator.
    *
    * @param comp The comparator used to order this priority queue
    * @throws IllegalArgumentException if cap is less than 1
    */
   public PriorityQueue(Comparator<E> comp) {
      theData = new ArrayList<>(10);
      this.comp = comp;
   }

   /**
    * Insert an item into the priority queue.
    *
    * pre: The ArrayList theData is in heap order. post: The item is in the
    * priority queue, and theData is in heap order.
    * 
    * @param item The item to be inserted
    * @throws NullPointerException if the item to be inserted is null.
    */
   @Override
   public boolean offer(E item) {
      // Add the item to the heap.
      theData.add(item);
      // child is newly inserted item.
      int child = theData.size() - 1;
      // Find child's parent.
      int parent = (child - 1) / 2;
      // Reheap
      // REMINDER: compare returns 1 when first value (parent)
      // is greater than second value (child).
      while (parent >= 0 && comp.compare(theData.get(parent),
            theData.get(child)) > 0) {
         swap(parent, child);
         child = parent;
         parent = (child - 1) / 2;
      }
      return true;
   }

   /**
    * Remove an item from the priority queue
    *
    * pre: The ArrayList theData is in heap order. post: Removed smallest item, and
    * theData is in heap order.
    * 
    * @return The item with the smallest priority value or null if empty.
    */
   @Override
   public E poll() {
      if (isEmpty()) {
         return null;
      }
      // Save the top of the heap.
      E result = theData.get(0);
      // If only one item then remove it.
      if (theData.size() == 1) {
         theData.remove(0);
         return result;
      }
      // Remove the last item from the ArrayList and place it into
      // the first position.
      theData.set(0, theData.remove(theData.size() - 1));
      // The parent starts at the top.
      int parent = 0;
      while (true) {
         int leftChild = 2 * parent + 1;
         if (leftChild >= theData.size()) {
            break; // Out of heap.
         }
         int rightChild = leftChild + 1;
         int minChild = leftChild; // Assume leftChild is smaller.
         // See whether rightChild is smaller.
         if (rightChild < theData.size()
               && comp.compare(theData.get(leftChild),
                     theData.get(rightChild)) > 0) {
            minChild = rightChild;
         }
         // assert: minChild is the index of the smaller child.
         // Move smaller child up heap if necessary.
         if (comp.compare(theData.get(parent),
               theData.get(minChild)) > 0) {
            swap(parent, minChild);
            parent = minChild;
         }
         else { // Heap property is restored.
            break;
         }
      }
      return result;
   }

   /**
    * Defers to the Collections swap method to implement a swap of two elements in
    * the ArrayList-based priority queue object.
    * 
    * @param firstSwap  Index of the first item in the swap.
    * @param secondSwap Index of the first item in the swap.
    * 
    */
   private void swap(int firstSwap, int secondSwap) {
      Collections.swap(theData, firstSwap, secondSwap);
   }

   /**
    * Defers to the ArrayList.size() method.
    */
   public int size() {
      return theData.size();
   }

   /**
    * Returns the item with the smallest priority value without removing it from
    * the priority queue.
    */
   public E peek() {
      return (theData.get(0));
   }

   @Override
   public Iterator<E> iterator() {
      return theData.iterator();
   }

// Insert solution to programming exercise 1, section 6, chapter 06 here
}
/* </listing> */