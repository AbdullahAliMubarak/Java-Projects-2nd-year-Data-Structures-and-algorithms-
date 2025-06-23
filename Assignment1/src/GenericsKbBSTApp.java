/**
 * Binary Search Tree-based implementation of the knowledge base.
 * This class provides functionality to load, update, and search a knowledge base
 * using a Binary Search Tree data structure.
 * 
 * @author Abdullah Ali Mubarak ALMABD030
 * @date 11/03/2025
 */

 import java.util.Scanner;

 /**
  * Main application class for the BST-based knowledge base implementation.
  * Provides a menu-driven interface for interacting with the knowledge base.
  */
 public class GenericsKbBSTApp {
    /** Binary search tree to store knowledge base entries */
    private static BinarySearchTree bst = new BinarySearchTree();
    
    /** Flag to track if knowledge base has been loaded */
    private static boolean isKnowledgeBaseLoaded = false;
    
    /**
     * Main method that initializes the BST to store data from knowledge base
     * and provides a menu for user interaction.
     * 
     */
    public static void main(String[] args) {
       Scanner inKey = new Scanner(System.in);
       
       while (true) {
          // Output options to user
          System.out.println("\nChoose an action:");
          System.out.println("1. Load knowledge base from file");
          System.out.println("2. Add a new statement");
          System.out.println("3. Search by term");
          System.out.println("4. Search by term and sentence");
          System.out.println("5. Quit");
          System.out.print("Enter choice: ");
          
          int choice = inKey.nextInt();
          inKey.nextLine(); // Consume the newline character
          
          switch (choice) {
             case 1: // Load data from text file
                System.out.print("Enter file name: ");
                String fileName = inKey.nextLine();
                loadKnowledgeBase(fileName);
                isKnowledgeBaseLoaded = true;
                break;
                
             case 2: // Add data to existing knowledge base
                System.out.print("Enter term: ");
                String term = inKey.nextLine();
                System.out.print("Enter sentence: ");
                String sentence = inKey.nextLine();
                System.out.print("Enter confidence score: ");
                double confidence = inKey.nextDouble();
                inKey.nextLine(); // Consume the newline character
                
                BSTNode existingNode = bst.search(term);
                if (existingNode != null) {
                   if (confidence > existingNode.confidence) {
                      bst.insert(term, sentence, confidence);
                      System.out.println("Statement for term '" + term + "' has been updated.");
                   } else {
                      System.out.println("Existing statement has higher confidence. No update performed.");
                   }
                } else {
                   bst.insert(term, sentence, confidence);
                   System.out.println("New statement added for term '" + term + "'.");
                }
                break;
                
             case 3: // Search for data by term
                if (!isKnowledgeBaseLoaded) {
                   System.out.println("Please load knowledge base first (option 1).");
                   break;
                }
                
                System.out.print("Enter term to search: ");
                term = inKey.nextLine();
                BSTNode result = bst.search(term);
                
                if (result != null)
                   System.out.println("Statement found: " + result.sentence + " (Confidence: " + result.confidence + ")");
                else
                   System.out.println("Statement not found.");
                break;
                
             case 4: // Search by term and sentence
                if (!isKnowledgeBaseLoaded) {
                   System.out.println("Please load knowledge base first (option 1).");
                   break;
                }
                
                System.out.print("Enter the term: ");
                term = inKey.nextLine();
                System.out.print("Enter the statement to search for: ");
                sentence = inKey.nextLine();
                
                result = bst.search(term);
                if (result != null && result.sentence.equals(sentence)) {
                   System.out.println("The statement was found and has a confidence score of " + result.confidence + ".");
                } else {
                   System.out.println("Statement not found.");
                }
                break;
                
             case 5: // Exit
                System.out.println("Exiting...");
                inKey.close();
                return;
                
             default: // Output if none of the available options are chosen
                System.out.println("Invalid choice. Try again.");
          }
       }
    }
    
    /**
     * Loads data from a knowledge base file into the Binary Search Tree.
     * Reads data line by line, splits each line into term, sentence, and confidence.
     * Updates existing entries if a term already exists with a higher confidence level.
     * 
     * @param fileName Name of the file containing knowledge base data
     */
    private static void loadKnowledgeBase(String fileName) {
       try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName))) {
          String line;
          int count = 0;
          while ((line = br.readLine()) != null) { // If the file is not empty read all the data
             String[] parts = line.split("\t"); // Split the data into the 3 required parts
             if (parts.length == 3) {
                try {
                   String term = parts[0];
                   String sentence = parts[1];
                   double confidence = Double.parseDouble(parts[2]);
                   
                   // Check if term already exists with lower confidence
                   BSTNode existingNode = bst.search(term);
                   if (existingNode == null || confidence > existingNode.confidence) {
                      bst.insert(term, sentence, confidence);
                      count++;
                   }
                } catch (NumberFormatException e) {
                   System.out.println("Error parsing confidence value in line: " + line);
                }
             } else {
                System.out.println("Skipping invalid line (does not have 3 tab-separated fields): " + line);
             }
          }
          System.out.println("Knowledge base loaded successfully. " + count + " entries processed.");
       } catch (java.io.IOException e) {
          System.out.println("Error reading file: " + e.getMessage());
       }
    }
 }