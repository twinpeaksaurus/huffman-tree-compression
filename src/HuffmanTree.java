
import java.io.PrintStream;

/**
 * The HuffmanTree class provides methods for building a Huffman tree to encode
 * an array of symbols in a binary string, printing the tree in preorder
 * traversal (deployed to the BinaryTree class) as well as decoding a binary
 * message.
 * 
 * @author jam
 *
 */
public class HuffmanTree {

   /*
    * The SymbolNodeData object will serve as the data contained within a
    * BinaryTree node. Attributes can be accessed with dot notation by the
    * enclosing HuffmanTree class.
    */
   public static class SymbolNodeData {
      // Data Fields

      // Frequency assigned to a given symbol.
      private double freq;
      // char in the node
      private char symbol;

      public SymbolNodeData(double freq, char symbol) {
         this.freq = freq;
         this.symbol = symbol;
      }

      public char getSymbol() {
         return symbol;
      }

      public double getFreq() {
         return freq;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o)
            return true;
         if (o == null)
            return false;
         if (this.getClass() == o.getClass()) {
            SymbolNodeData other = (SymbolNodeData) o;
            return freq == other.freq && symbol == other.symbol;
         }
         else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         int hash = 7;
         hash = 97 * hash + (int) (Double.doubleToLongBits(this.freq)
               ^ (Double.doubleToLongBits(this.freq) >>> 32));
         hash = 97 * hash + this.symbol;
         return hash;
      }

      @Override
      public String toString() {
         return (Character.toString(symbol) + ": " + freq);
      }
   }

   /**
    * A reference to the completed Huffman tree.
    */
   protected BinaryTree<SymbolNodeData> huffTree;

   /**
    * Builds the Huffman tree using the given alphabet and freqs.
    *
    * posthuffTree contains a reference to the Huffman tree.
    * 
    * @param symbols An array of SymbolNodeData objects
    */
   public void buildTree(SymbolNodeData[] symbols) {
      PriorityQueue<BinaryTree<SymbolNodeData>> huffQ = new PriorityQueue<>(
            (lt, rt) -> Double.compare(lt.getData().freq,
                  rt.getData().freq));
      // Load the queue with the leaves.
      for (SymbolNodeData nextSymbol : symbols) {
         var aBinaryTree = new BinaryTree<>(nextSymbol, null, null);
         huffQ.offer(aBinaryTree);
      }

      // Build the tree.
      while (huffQ.size() > 1) {
         var left = huffQ.poll();
         var right = huffQ.poll();
         double wl = left.getData().freq;
         double wr = right.getData().freq;
         var sum = new SymbolNodeData(wl + wr, '\u0000');
         var newTree = new BinaryTree<>(sum, left, right);
         huffQ.offer(newTree);
      }

      // The queue should now contain only one item.
      huffTree = huffQ.poll();
   }

   /**
    * Outputs the resulting code.
    *
    * @param out  A PrintStream to write the output to
    * @param code The code up to this node
    * @param tree The current node in the tree
    */
   private void printCode(PrintStream out, String code,
         BinaryTree<SymbolNodeData> tree) {
      SymbolNodeData theData = tree.getData();
      if (theData.symbol != '\u0000') {
         if (theData.symbol == ' ') {
            out.println("space: " + code);
         }
         else {
            out.println(theData.symbol + ": " + theData.freq + " : "
                  + code);
         }
      }
      else {
         printCode(out, code + "1", tree.getLeftSubtree());
         printCode(out, code + "0", tree.getRightSubtree());
      }
   }

   /**
    * Outputs the resulting code.
    *
    * @param out A PrintStream to write the output to
    */
   public void printCode(PrintStream out) {
      printCode(out, "", huffTree);
   }

   /**
    * Decodes a message using a binary String as input (encoded text). Uses a
    * StringBuilder to build a decoded message as a String.
    * 
    * @param code A binary string that represents the coded message.
    * @return The decoded message as a String
    */
   public String decode(String code) {
      StringBuilder result = new StringBuilder();
      var currentTree = huffTree;
      // uses the binary as a guide to "climb down" the tree while
      // iterating through
      for (int i = 0; i < code.length(); i++) {
         if (code.charAt(i) == '1') {
            currentTree = currentTree.getRightSubtree();
         }
         else {
            currentTree = currentTree.getLeftSubtree();
         }
         // if it is a leaf, it is a symbol
         if (currentTree.isLeaf()) {
            SymbolNodeData theData = currentTree.getData();
            result.append(theData.symbol);
            currentTree = huffTree;
         }
      }
      return result.toString();
   }
}
