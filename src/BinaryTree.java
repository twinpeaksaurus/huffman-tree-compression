
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

   // NEED TO ADD A PREORDER TRAVERSAL OF A TREE USING RECURSION

   public BinaryTree<E> getRightSubtree() {
      // If root is null or there is no right node/subtree, return null
      if (root != null && root.right != null) {
         return new BinaryTree<>(root.right);
      }
      else {
         return null;
      }
   }

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

//
//   /**
//    * Starter method add.
//    *
//    * @param item The object being inserted
//    * @return true if the object is inserted, false if the object already exists in
//    *         the tree
//    */
//   public boolean add(E item) {
//      root = add(root, item);
//      if (addReturn) {
//         size++;
//      }
//      return addReturn;
//   }
//
//   /**
//    * Recursive add method.
//    *
//    * postThe data field addReturn is set true if the item is added to the tree,
//    * false if the item is already in the tree.
//    * 
//    * @param localRoot The local root of the subtree
//    * @param item      The object to be inserted
//    * @return The new local root that now contains the inserted item
//    */
//   protected Node<E> add(Node<E> localRoot, E item) {
//      if (localRoot == null) {
//         // item is not in the tree � insert it.
//         addReturn = true;
//         return new Node<>(item);
//      }
//      else if (item.compareTo(localRoot.data) == 0) {
//         // item is equal to localRoot.data
//         addReturn = false;
//         return localRoot;
//      }
//      else if (item.compareTo(localRoot.data) < 0) {
//         // item is less than localRoot.data
//         localRoot.left = add(localRoot.left, item);
//         return localRoot;
//      }
//      else {
//         // item is greater than localRoot.data
//         localRoot.right = add(localRoot.right, item);
//         return localRoot;
//      }
//   }

}
