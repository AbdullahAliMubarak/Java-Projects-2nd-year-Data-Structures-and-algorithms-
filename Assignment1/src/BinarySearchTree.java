/**
 * Binary Search Tree implementation for storing knowledge base entries.
 * Provides methods for inserting nodes and searching for terms.
 * 
 * @author Abdullah Ali Mubarak ALMABD030
 * @date 12/03/2025
 */
class BinarySearchTree {
   /** Root node of the Binary search tree created */
   private BSTNode root;
   
   /**
    * Inserts a new term and associated statement into the BST.
    * If the term already exists, updates the statement if the new confidence is higher.
    * 
    * @param term The term to insert
    * @param sentence The statement associated with the term
    * @param confidence The confidence level of the statement
    */
   public void insert(String term, String sentence, double confidence) {
       root = insertRec(root, term, sentence, confidence);
   }

   /**
    * Recursive helper method to insert nodes into the correct position in the BST.
    * 
    * @param node The current node in the recursion
    * @param term The term to insert
    * @param sentence The statement associated with the term
    * @param confidence The confidence level of the statement
    * @return The node after insertion/update
    */
   private BSTNode insertRec(BSTNode node, String term, String sentence, double confidence)
   {
      if (node == null) return new BSTNode(term, sentence, confidence); //base case to create the node 
      int compareTo = term.compareTo(node.term); //compare this node to node in the tree
      if (compareTo < 0) { //this node is smaller
         node.left = insertRec(node.left, term, sentence, confidence); // add to the left 
      } else if (compareTo > 0) { // this node is greater
         node.right = insertRec(node.right, term, sentence, confidence); // add to the right
      }
      else if (confidence > node.confidence) { // if the confidence is higher replace the statement and confidence
         node.sentence = sentence;
         node.confidence = confidence;
      }
      return node;
   }
   
   /**
    * Searches for a term in the BST and returns the node if found.
    * 
    * @param term The term to search for
    * @return The node containing the term if found, otherwise null
    */
   public BSTNode search(String term) {
      return searchRec(root, term); // pass root to search from the start
   }
   
   /**
    * Recursive helper method to search for a node with the given term.
    * 
    * @param node The current node in the recursion
    * @param term The term to search for
    * @return The node containing the term if found, otherwise null
    */
   private BSTNode searchRec(BSTNode node, String term) {
      if (node == null || node.term.equals(term)) return node; // either does not exist or has been found
      if (term.compareTo(node.term) < 0) {
         return searchRec(node.left, term); // Search in left subtree
      } else {
         return searchRec(node.right, term); // Search in right subtree
      }
   }
}