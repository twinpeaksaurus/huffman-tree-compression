import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

   @Test
   void toStringNodeDataTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
//      System.out.println(testNode1.toString());
      assertEquals("e", testNode1.toString());
   }

   @Test
   void getNodeDataTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
//      System.out.println(testNode1.getNodeData());
      assertEquals("e: 5.0", testNode1.getNodeData());
   }

   @Test
   void symbolNodeTest() {
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
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
//      System.out.println(ht.printCode());
   }

   @Test
   public void buildTreeTestWithFrequency() {
      HuffmanTree ht = new HuffmanTree();
      var testNode1 = new HuffmanTree.SymbolNodeData(5, 'e');
      var testNode2 = new HuffmanTree.SymbolNodeData(22, 'f');
      var testNode3 = new HuffmanTree.SymbolNodeData(2, 'p');
      var testNode4 = new HuffmanTree.SymbolNodeData(8, 'l');
      var testNode5 = new HuffmanTree.SymbolNodeData(5, 't');
      HuffmanTree.SymbolNodeData[] testArray = { testNode2, testNode1,
            testNode3, testNode4, testNode5 };
      ht.buildTree(testArray);
//      System.out.println(ht.printCodeWithFrequency());
      System.out.println(ht.processCodes());

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

   @Test
   public void processTest() {
      HuffmanTree ht = new HuffmanTree();
      var testNodea = new HuffmanTree.SymbolNodeData(19, 'a');
      var testNodeb = new HuffmanTree.SymbolNodeData(16, 'b');
      var testNodec = new HuffmanTree.SymbolNodeData(17, 'c');
      var testNoded = new HuffmanTree.SymbolNodeData(11, 'd');
      var testNodee = new HuffmanTree.SymbolNodeData(42, 'e');
      var testNodef = new HuffmanTree.SymbolNodeData(12, 'f');
      var testNodeg = new HuffmanTree.SymbolNodeData(14, 'g');
      var testNodeh = new HuffmanTree.SymbolNodeData(17, 'h');
      var testNodei = new HuffmanTree.SymbolNodeData(16, 'i');
      var testNodej = new HuffmanTree.SymbolNodeData(5, 'j');
      var testNodek = new HuffmanTree.SymbolNodeData(10, 'k');
      var testNodel = new HuffmanTree.SymbolNodeData(20, 'l');
      var testNodem = new HuffmanTree.SymbolNodeData(19, 'm');
      var testNoden = new HuffmanTree.SymbolNodeData(24, 'n');
      var testNodeo = new HuffmanTree.SymbolNodeData(18, 'o');
      var testNodep = new HuffmanTree.SymbolNodeData(13, 'p');
      var testNodeq = new HuffmanTree.SymbolNodeData(1, 'q');
      var testNoder = new HuffmanTree.SymbolNodeData(25, 'r');
      var testNodes = new HuffmanTree.SymbolNodeData(35, 's');
      var testNodet = new HuffmanTree.SymbolNodeData(25, 't');
      var testNodeu = new HuffmanTree.SymbolNodeData(15, 'u');
      var testNodev = new HuffmanTree.SymbolNodeData(5, 'v');
      var testNodew = new HuffmanTree.SymbolNodeData(21, 'w');
      var testNodex = new HuffmanTree.SymbolNodeData(2, 'x');
      var testNodey = new HuffmanTree.SymbolNodeData(8, 'y');
      var testNodez = new HuffmanTree.SymbolNodeData(3, 'z');

      HuffmanTree.SymbolNodeData[] testArray = { testNodea, testNodeb,
            testNodec, testNoded, testNodee, testNodef, testNodeg,
            testNodeh, testNodei, testNodej, testNodek, testNodel,
            testNodem, testNoden, testNodeo, testNodep, testNodeq,
            testNoder, testNodes, testNodet, testNodeu, testNodev,
            testNodew, testNodex, testNodey, testNodez };
      ht.buildTree(testArray);
      System.out.println(ht.encodeMessage(
            "Peter Piper picked a peck of pickled peppers."));
      System.out.println(ht.decode(
            "1010001010000101001101001100010100010100110100110001101000100010011001111110100010110100010011110011011010011000110100010000010100110010100010101001010001010011110"));
   }
}