import java.util.Map;
import java.util.TreeMap;

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

      // Frequency assigned to a given symbol.
      private double freq;
      // char in the node
      private char symbol;

      /**
       * Constructor for the node data.
       * 
       * @param freq
       * @param symbol
       */
      public SymbolNodeData(double freq, char symbol) {
         this.freq = freq;
         this.symbol = symbol;
      }

      /**
       * Retriever
       * 
       * @return
       */
      public char getSymbol() {
         return symbol;
      }

      /**
       * Retriever
       * 
       * @return
       */
      public double getFreq() {
         return freq;
      }

      /**
       * Test for equality of nodes.
       */
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

      /**
       * Return String representation of data.
       */
      @Override
      public String toString() {
         return (Character.toString(symbol));
      }

      /**
       * String with frequency of the node symbol.
       * 
       * @return
       */
      public String getNodeData() {
         return (Character.toString(symbol) + ": " + this.freq);
      }
   }

   /**
    * A reference to the completed Huffman tree.
    */
   protected BinaryTree<SymbolNodeData> huffTree;
   protected String preOrderResult = "";
   protected TreeMap<Character, String> codeMap = new TreeMap<Character, String>();

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
   private void printCode(String code,
         BinaryTree<SymbolNodeData> tree) {
      SymbolNodeData theData = tree.getData();
      if (theData.symbol != '\u0000') {
         if (theData.symbol == ' ') {
            preOrderResult = (preOrderResult + ("space: " + code));
         }
         else {
            preOrderResult = (preOrderResult
                  + (theData.symbol + ": " + code + "\n"));
         }
      }
      else {
         printCode(code + "0", tree.getLeftSubtree());
         printCode(code + "1", tree.getRightSubtree());
      }
   }

   /**
    * Outputs the resulting code.
    *
    * @param out A PrintStream to write the output to
    */
   public String printCode() {
      preOrderResult = "";
      printCode("", huffTree);
      return preOrderResult;
   }

   private void printCodeWithFrequency(String code,
         BinaryTree<SymbolNodeData> tree) {
      SymbolNodeData theData = tree.getData();
      if (theData.symbol != '\u0000') {
         if (theData.symbol == ' ') {
            preOrderResult = (preOrderResult + ("space: " + code));
         }
         else {
            preOrderResult = (preOrderResult
                  + (theData.getNodeData() + ": " + code + "\n"));
         }
      }
      else {
         printCode(code + "0", tree.getLeftSubtree());
         printCode(code + "1", tree.getRightSubtree());
      }
   }

   public String printCodeWithFrequency() {
      preOrderResult = "";
      printCodeWithFrequency("", huffTree);
      return preOrderResult;
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

   /**
    * This helper method encodes a message passed in as a String using the codes in
    * the tree. It is activated by the encodeMessage method.
    * 
    * @param str   Message to be encoded.
    * @param codes Array of codes that match characters.
    * @return String of binary representations.
    */
   private static String encode(String str, String[] codes) {
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         int index = 0;
         if (c != ' ' && c != '.' && c != '?' && c != '!'
               && c != ',') {
            index = Character.toUpperCase(c) - 'A' + 1;
         }
         result.append(codes[index]);
      }
      return result.toString();
   }

   /**
    * Processes the String generated by traversal into a final array of codes. A
    * TreeMap from Java Collections is used to aid in the processing and sorting
    * alphabetically.
    * 
    * @return An array of binary Strings.
    */
   public String[] processCodes() {
      String[] codeArray = new String[27];
      codeArray[0] = "";
      String treeCode = printCode();

      // Split treeCode by newline and feed it into an array of Strings
      String[] arrayOfStrings1 = treeCode
            .split(System.lineSeparator());
      String[][] arrayOfStrings2 = new String[arrayOfStrings1.length][];
      // trim and test print statement
      int i = 0;
      for (String s : arrayOfStrings1) {
         s.trim();
         arrayOfStrings2[i] = s.split(":");
         arrayOfStrings2[i][0].replaceAll("\\s", "");
         arrayOfStrings2[i][1].replaceAll("\\s", "");
         i++;
      }
      // end testing

      for (String[] s : arrayOfStrings2) {
         char newChar = s[0].charAt(0);
         codeMap.put(Character.toUpperCase(newChar), s[1].trim());

      }

      i = 1;
//ITERATE THROUGH TREEMAP TO TEST
      for (Map.Entry<Character, String> e : codeMap.entrySet()) {
         Character key = e.getKey();
         String value = e.getValue();
         codeArray[i] = value;
         i++;
      }

      // test print statement
      for (String s : codeArray) {
      }
      // end testing
      return codeArray;
   }

   /**
    * This method calls the processCodes and encode methods in order to encode a
    * message passed in as an argument.
    * 
    * @param originalMessage The message to decode.
    * @return a binary String.
    */
   public String encodeMessage(String originalMessage) {
      String[] huffCodes = processCodes();
      return encode(originalMessage, huffCodes);
   }
}
