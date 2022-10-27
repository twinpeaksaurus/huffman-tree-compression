import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriorityQueueTest {

   @Test
   public void pollTestNotEmpty() {
      var tq = new PriorityQueue<Integer>();
      tq.offer(5);
      tq.offer(2);
      tq.offer(8);
      tq.poll();
      Object testValue = tq.poll();
      assertEquals(5, testValue);
   }

   @Test
   public void pollTestOneItem() {
      PriorityQueue<Integer> tq = new PriorityQueue();
      tq.offer(1);
      Object testValue = tq.poll();
      assertEquals(1, testValue);

   }

   @Test
   public void pollTestIsEmpty() {
      PriorityQueue<Integer> tq = new PriorityQueue();
      Object testValue = tq.poll();
      assertEquals(null, testValue);
   }

   /**
    * Test of default constructor
    */
   @Test
   public void peekTestNotEmpty() {
      PriorityQueue<Integer> tq = new PriorityQueue();
      tq.offer(5);
      tq.offer(2);
      tq.offer(8);
      Object testValue = tq.peek();
      assertEquals(2, testValue);

   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void peekTestEmpty() {
      PriorityQueue<Integer> tq = new PriorityQueue();
      System.out.println(tq.peek());
      Object testValue = tq.peek();
   }

}
