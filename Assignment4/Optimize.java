import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program to find optimal hash function weights for a specific dataset.
 * This program tries all possible combinations of weights (0-4) for the hash
 * function and determines which combination(s) result in the minimum number
 * of probes when inserting all items from a dataset.
 * Abdullah Ali Mubarak ALMABD030
 */
public class Optimize {
    
    // Constants
    private static final int NUM_WEIGHTS = 9; // Number of weights in the hash function
    private static final int WEIGHT_RANGE = 5; // Weights can be 0-4
    private static final int TABLE_SIZE = 37; // Size of the hash table as specified in the assignment
    private static final String DATA_FILE = "mydata.txt"; // Input data file
    private static final String RESULTS_FILE = "results.txt"; // Output results file
    
    public static void main(String[] args) {
        // Step 1: Read the data from the file
        List<String> data = readDataFile();
        if (data.isEmpty()) {
            System.out.println("No data read from file. Exiting.");
            return;
        }
        
        // Step 2: Find the optimal weights using iteration instead of recursion
        int minProbes = Integer.MAX_VALUE;
        int countOfOptimalWeights = 0;
        
        // Using a more efficient iterative approach with an array to represent the current weights
        int[] currentWeights = new int[NUM_WEIGHTS];
        boolean done = false;
        
        while (!done) {
            // Create a hash table with the current weights
            LPHashTable table = new LPHashTable(TABLE_SIZE);
            table.setWeights(currentWeights.clone());
            
            // Insert all data items and count probes
            table.resetProbeCount();
            for (String item : data) {
                table.insert(item);
            }
            
            int probes = table.getProbeCount();
            
            // Update result if better or same as current best
            if (probes < minProbes) {
                minProbes = probes;
                countOfOptimalWeights = 1;
            } else if (probes == minProbes) {
                countOfOptimalWeights++;
            }
            
            // Generate the next weight combination (like counting in base 5)
            int pos = NUM_WEIGHTS - 1;
            while (pos >= 0) {
                currentWeights[pos]++;
                if (currentWeights[pos] < WEIGHT_RANGE) {
                    break;
                }
                currentWeights[pos] = 0;
                pos--;
            }
            
            // If we've gone through all combinations, we're done
            if (pos < 0) {
                done = true;
            }
        }
        
        // Step 3: Output the results both to console and file
        System.out.println(minProbes + " " + countOfOptimalWeights);
        writeResultsToFile(minProbes, countOfOptimalWeights);
    }
    
    /**
     * Reads the data from the file and returns it as a list of strings.
     */
    private static List<String> readDataFile() {
        List<String> data = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(DATA_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    data.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file " + DATA_FILE);
        }
        return data;
    }
    
    /**
     * Writes the results to the output file.
     */
    private static void writeResultsToFile(int minProbes, int countOfCombinations) {
        try {
            FileWriter writer = new FileWriter(RESULTS_FILE);
            writer.write(minProbes + " " + countOfCombinations);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to results file: " + e.getMessage());
        }
    }
}