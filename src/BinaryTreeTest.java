import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class BinaryTreeTest {

   @Test
   public void buildTreeTest() {
      BinaryTree<Integer> testTree1 = new BinaryTree<Integer>(3, null, null);
      BinaryTree<Integer> testTree2 = new BinaryTree<Integer>(7, testTree1,
            null);
      BinaryTree<Integer> testTree3 = new BinaryTree<Integer>(15, testTree2,
            testTree1);
      BinaryTree<Integer> testTree4 = new BinaryTree<Integer>(4, testTree3,
            testTree2);
      BinaryTree<Integer> testTreeFinal = new BinaryTree<Integer>(3, testTree4,
            testTree3);

      String testString = testTreeFinal.printPreOrder();
      assertTrue(testString.contains(
            "3 -> 4 -> 15 -> 7 -> 3 -> 3 -> 7 -> 3 -> 15 -> 7 -> 3 -> 3"));
   }

   @Test
   public void buildTreeTestNull() {
      BinaryTree<Integer> testTree = new BinaryTree<Integer>(null, null, null);
      String testValue = testTree.printPreOrder();
      System.out.print(testValue);
      assertEquals(" ", testValue);

   }
}
