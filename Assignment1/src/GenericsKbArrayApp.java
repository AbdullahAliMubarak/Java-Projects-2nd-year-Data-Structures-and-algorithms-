/**
 * Array-based implementation of the knowledge base.
 * This class provides functionality to load, update and search a knowledge base
 * using a simple array data structure.
 * 
 * @author Abdullah Ali Mubarak ALMABD030
 * @date 15/03/2025
 */

 import java.util.Scanner;

 /**
  * Class to represent a knowledge base entry for the array implementation.
  * Creates a variable to store a term, a sentence, and a confidence level.
  * The array will have this object type and will store data from the knowledge base.
  * 
  * @author Abdullah Ali Mubarak ALMABD030
  * @version 1.0
  */
 class KBEntry {
    /** The term or concept that the statement is about */
    String term;
    
    /** The statement or sentence associated with the term */
    String sentence;
    
    /** Confidence level of the statement (between 0 and 1) */
    double confidence;
    
    /**
     * Constructor for creating a new knowledge base entry
     * 
     * @param term The term or concept
     * @param sentence The statement associated with the term
     * @param confidence The confidence level of the statement
     */
    public KBEntry(String term, String sentence, double confidence) {
       this.term = term;
       this.sentence = sentence;
       this.confidence = confidence;
    }
 }
 
 /**
  * Main application class for the array-based knowledge base implementation.
  * Provides a menu-driven interface for interacting with the knowledge base.
  */
 public class GenericsKbArrayApp {
    /** Maximum size of the knowledge base */
    private static final int MAX_ENTRIES = 100000;
    
    /** Array to store knowledge base entries */
    private static KBEntry[] knowledgeBase = new KBEntry[MAX_ENTRIES];
    
    /** Current number of entries in the knowledge base */
    private static int entryCount = 0;
    
    /** Flag to track if knowledge base has been loaded */
    private static boolean isKnowledgeBaseLoaded = false;
    
    /**
     * Main method that initializes the array to store data from knowledge base
     * and provides a menu for user interaction.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
       Scanner inKey = new Scanner(System.in);
       
       while (true) {
          // Output options to user
          System.out.println("\nChoose an action:");
          System.out.println("1. Load knowledge base from file");
          System.out.println("2. Update an existing statement");
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
                
             case 2: // Update existing statement
                if (!isKnowledgeBaseLoaded) {
                   System.out.println("Please load knowledge base first (option 1).");
                   break;
                }
                
                System.out.print("Enter term: ");
                String term = inKey.nextLine();
                System.out.print("Enter sentence: ");
                String sentence = inKey.nextLine();
                System.out.print("Enter confidence score: ");
                double confidence = inKey.nextDouble();
                inKey.nextLine(); // Consume the newline character
                
                
                int index = searchIndexByTerm(term);
                if (index != -1) {
                   if (confidence > knowledgeBase[index].confidence) { // only update if confidence is higher
                      knowledgeBase[index].sentence = sentence;
                      knowledgeBase[index].confidence = confidence;
                      System.out.println("Statement for term '" + term + "' has been updated.");
                   } else {
                      System.out.println("Existing statement has higher confidence. No update performed.");
                   }
                } else {
                   System.out.println("Term not found in knowledge base. Cannot update.");
                }
                break;
                
             case 3: // Search for data by term
                if (!isKnowledgeBaseLoaded) {
                   System.out.println("Please load knowledge base first (option 1).");
                   break;
                }
                
                System.out.print("Enter term to search: ");
                term = inKey.nextLine();
                
                index = searchIndexByTerm(term);
                if (index != -1) {
                   System.out.println("Statement found: " + knowledgeBase[index].sentence + 
                                     " (Confidence: " + knowledgeBase[index].confidence + ")");
                } else {
                   System.out.println("Statement not found.");
                }
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
                
                index = searchIndexByTerm(term);
                if (index != -1 && knowledgeBase[index].sentence.equals(sentence)) {
                   System.out.println("The statement was found and has a confidence score of " + 
                                     knowledgeBase[index].confidence + ".");
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
     * Performs a linear search for a term in the knowledge base array.
     * 
     * @param term The term to search for
     * @return The index of the term if found, otherwise -1
     */
    private static int searchIndexByTerm(String term) {
       for (int i = 0; i < entryCount; i++) { //linear search
          if (knowledgeBase[i].term.equals(term)) { 
             return i;
          }
       }
       return -1; // Not found
    }
    
    /**
     * Loads data from a knowledge base file into the array.
     * Reads data line by line, splits each line into term, sentence, and confidence.
     * Updates existing entries if a term already exists with a higher confidence level.
     * 
     * @param fileName Name of the file containing knowledge base data
     */
    private static void loadKnowledgeBase(String fileName) {
       try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName))) {
          entryCount = 0; // Reset the entry count
          String line;
          while ((line = br.readLine()) != null && entryCount < MAX_ENTRIES) {
             String[] parts = line.split("\t");
             if (parts.length == 3) {
                try {
                   String term = parts[0];
                   String sentence = parts[1];
                   double confidence = Double.parseDouble(parts[2]);
                   
                   // Check if term already exists
                   int existingIndex = searchIndexByTerm(term);
                   if (existingIndex != -1) {
                      // Update only if new confidence is higher
                      if (confidence > knowledgeBase[existingIndex].confidence) {
                         knowledgeBase[existingIndex].sentence = sentence;
                         knowledgeBase[existingIndex].confidence = confidence;
                      }
                   } else {
                      // Add new entry
                      knowledgeBase[entryCount] = new KBEntry(term, sentence, confidence);
                      entryCount++;
                   }
                } catch (NumberFormatException e) {
                   System.out.println("Error parsing confidence value in line: " + line);
                }
             } else {
                System.out.println("Skipping invalid line (does not have 3 tab-separated fields): " + line);
             }
          }
          
          if (entryCount >= MAX_ENTRIES) {
             System.out.println("Warning: Knowledge base capacity reached. Some entries may not have been loaded.");
          }
          
          System.out.println("Knowledge base loaded successfully. " + entryCount + " entries in memory.");
       } catch (java.io.IOException e) {
          System.out.println("Error reading file: " + e.getMessage());
       }
    }
 }