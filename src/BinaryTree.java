
/**
 * The BinaryTree class
 * 
 * @author jam
 *
 * @param <E>
 */
public class BinaryTree<E> {

   /**
    * The nested static class creates a tree node to build upon. It can be accessed
    * by the BinaryTree class. The generic allows for flexibility in the data type.
    * 
    * @author jam
    */
   protected static class Node<E> {
      public E data;
      public Node<E> left;
      public Node<E> right;

      /**
       * Constructor sets the data of the node to E value of data and the left and
       * right subnodes to null.
       * 
       * @param data
       */
      public Node(E data) {
         this.data = data;
         left = null;
         right = null;
      }

      public String toString() {
         return "" + this.data;
      }
   }

   private String traversalResult = " ";
   protected boolean addReturn;
   protected int size;
   protected Node<E> root;

   /**
    * Default constructor with no data (root is null).
    */
   public BinaryTree() {
      root = null;
   }

   /**
    * Constructor with root as object Node<E>.
    * 
    * @param root
    */
   public BinaryTree(Node<E> root) {
      this.root = root;
   }

   /**
    * More complex constructor with E root and two subtrees, left and right.
    * 
    * @param data      Data in the root node.
    * @param leftTree  Left BinaryTree composed of nodes.
    * @param rightTree Right BinaryTree composed of nodes.
    */
   public BinaryTree(E data, BinaryTree<E> leftTree,
         BinaryTree<E> rightTree) {
      root = new Node<>(data);
      if (leftTree != null) {
         root.left = leftTree.root;
      }
      else {
         root.left = null;
      }
      if (rightTree != null) {
         root.right = rightTree.root;
      }
      else {
         root.right = null;
      }
   }

   public BinaryTree<E> getLeftSubtree() {
      // If root is null or there is no left node/subtree, return null
      if (root != null && root.left != null) {
         return new BinaryTree<>(root.left);
      }
      else {
         return null;
      }
   }

   public BinaryTree<E> getRightSubtree() {
      // If root is null or there is no right node/subtree, return null
      if (root != null && root.right != null) {
         return new BinaryTree<>(root.right);
      }
      else {
         return null;
      }
   }

   // THIS WAS HANDLED IN HUFFMAN CLASS
   /**
    * 
    * @param root
    */
//   private void preOrder(Node<E> root) {
//      if (root == null) {
//         return;
//      }
//      System.out.print(root.data + " ");
//      preOrder(root.left);
//      preOrder(root.right);
//   }

   private String preOrder(Node<E> root) {
      if (root == null) {
         return " ";
      }
      if (root.data != null) {
         traversalResult = (traversalResult + root.data.toString()
               + " -> ");
      }
      preOrder(root.left);
      preOrder(root.right);
      return traversalResult;
   }

   public String printPreOrder() {
      return preOrder(this.root);
   }

   /**
    * Determine whether this tree is a leaf.
    *
    * @return true if the root has no children
    */
   public boolean isLeaf() {
      return (root == null
            || (root.left == null && root.right == null));
   }

   public E getData() {
      if (root != null) {
         return root.data;
      }
      else {
         return null;
      }
   }
}