import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

   @Test
   void toStringNodeDataTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      System.out.println(testNode1.toString());
      assertEquals("e: 5.0", testNode1.toString());
   }

   @Test
   void symbolNodeTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      double weightValue = testNode1.getFreq();
      assertEquals('e', testNode1.getSymbol());

   }

   @SuppressWarnings("deprecation")
   @Test
   void weightNodeTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      testNode1.getFreq();
      assertEquals(5.0, testNode1.getFreq());

   }

   @Test
   public void buildTreeTest() {
      HuffmanTree ht = new HuffmanTree();
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      var testNode2 = new HuffmanTree.SymbolNodeData(22, 'f');
      var testNode3 = new HuffmanTree.SymbolNodeData(2, 'p');
      var testNode4 = new HuffmanTree.SymbolNodeData(8, 'l');
      var testNode5 = new HuffmanTree.SymbolNodeData(5, 't');
      HuffmanTree.SymbolNodeData[] testArray = { testNode2, testNode1,
            testNode3, testNode4, testNode5 };
      ht.buildTree(testArray);
      ht.printCode(System.out);
   }

   @Test
   public void decodeTest() {
      String testCode = "1010000111";
      HuffmanTree ht = new HuffmanTree();
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      var testNode2 = new HuffmanTree.SymbolNodeData(22, 'f');
      var testNode3 = new HuffmanTree.SymbolNodeData(2, 'p');
      var testNode4 = new HuffmanTree.SymbolNodeData(8, 'l');
      var testNode5 = new HuffmanTree.SymbolNodeData(5, 't');
      HuffmanTree.SymbolNodeData[] testArray = { testNode2, testNode1,
            testNode3, testNode4, testNode5 };
      ht.buildTree(testArray);
      assertTrue(ht.decode(testCode).equals("felt"),
            "The string does not match");
   }
}