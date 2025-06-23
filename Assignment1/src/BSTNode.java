/**
 * Node class for binary search tree implementation of knowledge base.
 * Creates node for binary tree consisting of data sections and pointers.
 * Each node contains a term, a sentence, and a confidence level.
 * 
 * @author Abdullah Ali Mubarak ALMABD030
 */
class BSTNode 
{
   /** The term or concept that the statement is about */
   String term;
   
   /** The statement or sentence associated with the term */
   String sentence;
   
   /** Confidence level of the statement (between 0 and 1) */
   double confidence;
   
   /** References to left and right child nodes in the binary search tree */
   BSTNode left, right;

   /**
    * Constructor for creating a new BST node
    * 
    * @param term The term or concept
    * @param sentence The statement associated with the term
    * @param confidence The confidence level of the statement
    */
   public BSTNode(String term, String sentence, double confidence) 
   {
      this.term = term;
      this.sentence = sentence;
      this.confidence = confidence;
      this.left = this.right = null;
   }
}