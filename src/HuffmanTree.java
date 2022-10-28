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
      // Frequency assigned to a given symbol.
      private double weight;
      // Char in the node.
      private char symbol;

      /**
       * Constructor for a node with a symbol and its frequency.
       * 
       * @param weight The weight of the associated symbol.
       * @param symbol The character in the node.
       */
      public SymbolNodeData(double weight, char symbol) {
         this.weight = weight;
         this.symbol = symbol;
      }

      public double getWeight() {
         return this.weight;
      }

      public char getSymbol() {
         return symbol;
      }

      public String toString() {
         return (this.symbol + " - " + this.weight);
      }
   }

   // Data field to hold a built tree
   private BinaryTree<SymbolNodeData> huffmanTree;
   protected String traversalResult = " ";

   /**
    * Takes an array of SymbolNode objects as arguments and creates a Huffman
    * Tree from them.
    * 
    * @param symbols An array of SymbolNode objects.
    */
   public void buildTree(SymbolNodeData[] symbols) {
      /**
       * This instantiation statement is rather complicated. The type is a
       * priority queue containing binary trees made up of symbol nodes as data.
       * 
       * The comparator uses a lamba expression to make it so that the
       * comparison is based on the weight of the SymbolNodeDatas.
       * 
       */
      PriorityQueue<BinaryTree<SymbolNodeData>> huffQ = new PriorityQueue<>(
            (leftTree, rightTree) -> Double.compare(leftTree.getData().weight,
                  rightTree.getData().weight));

      // Iterate through the array of symbol nodes to load the
      // priority queue.
      for (SymbolNodeData s : symbols) {
         // Creates trees which all have only one SymbolNodeData to begin
         var binaryTreeItem = new BinaryTree<SymbolNodeData>(s, null, null);
         // Moves single-node tree of symbol data from above into the queue,
         // where it is sorted by weight.
         huffQ.offer(binaryTreeItem);
      }
      // Build the tree
      while (huffQ.size() > 1) {
         // Creates trees from the two lowest priority items.
         BinaryTree<SymbolNodeData> left = huffQ.poll();
         BinaryTree<SymbolNodeData> right = huffQ.poll();
         // takes weight of roots as double values
         double weightL = left.getData().weight;
         double weightR = right.getData().weight;
         // Sets a "sum" parent node with a null character symbol
         // and weight that is the sum of the two subtrees.
         SymbolNodeData sum = new SymbolNodeData((weightL + weightR),
               Character.MIN_VALUE);
         // Combines the sum root and its two subtrees into a tree and feeds
         // it into the priority queue, where it is then sorted by its weight
         // (ie, the root which is the sum).
         var combinedTree = new BinaryTree<SymbolNodeData>(sum, left, right);
         huffQ.offer(combinedTree);
      }
      // Final tree left will be the accumulated combination of all
      // subtrees
      huffmanTree = huffQ.poll();
   }

   private void printCode(PrintStream out, String code,
         BinaryTree<SymbolNodeData> tree) {
      SymbolNodeData huffQ = tree.getData();
      if (huffQ.symbol != Character.MIN_VALUE) {
         if (huffQ.symbol == ' ') {
            out.println("space: " + code);
         }
         else {
            out.println(huffQ.symbol + ": " + code);
         }
      }
   }

   public void printPreorderTraverse(PrintStream out) {
      if (huffmanTree == null) {
         System.out.println("The tree is empty.");
      }
      else {
         printCode(out, "", huffmanTree);
      }
   }
//
//   public String preOrderTraversalHuffmanTree() {
//      if (huffmanTree.getData() == null) {
//         return " ";
//      }
//      if (huffmanTree.getData() != null) {
//
//         traversalResult = (traversalResult
//               + (huffmanTree.getData().toString()) + " -> ");
//      }
//      preOrderTraversalHuffmanTree(huffmanTree.getLeftSubtree());
//      preOrderTraversalHuffmanTree(huffmanTree.getRightSubtree());
//      return traversalResult;
//      if (huffmanTree != null) {
//         return huffmanTree.printPreOrder();
//      }
//      else {
//         return "Empty tree.";
//      }

}
