import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

   @Test
   void toStringNodeDataTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      assertEquals("e - 5.0", testNode1.toString());
   }

   @Test
   void symbolNodeTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      double weightValue = testNode1.getWeight();
      assertEquals('e', testNode1.getSymbol());

   }

   @Test
   void weightNodeTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      testNode1.getWeight();
      assertEquals(5.0, testNode1.getWeight());

   }

   @Test
   void buildTreeTest() {
      HuffmanTree ht = new HuffmanTree();
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      var testNode2 = new HuffmanTree.SymbolNodeData(22, 'f');
      var testNode3 = new HuffmanTree.SymbolNodeData(2, 'p');
      var testNode4 = new HuffmanTree.SymbolNodeData(8, 'l');
      HuffmanTree.SymbolNodeData[] testArray = { testNode2, testNode1,
            testNode3, testNode4 };
      ht.buildTree(testArray);
//      System.out.println(ht.preOrderTraversalHuffmanTree());

   }

   @Test
   void buildTreeTestEmptyArray() {
      fail("Not yet implemented");
   }

   @Test
   void buildTreeTestNonChar() {
      fail("Not yet implemented");
   }

//   @Test
//   void test() {
//      fail("Not yet implemented");
//   }
//
//   @Test
//   void test() {
//      fail("Not yet implemented");
//   }
//
//   @Test
//   void test() {
//      fail("Not yet implemented");
//   }
}
