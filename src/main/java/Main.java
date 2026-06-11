/**
 * File: Main.java
 * Description: Entry point for the OptiTime tool. Reads a degree structure
 * from a text file, constructs the graph, and outputs an optimal study plan.
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * AI Tool Used: Y
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/

import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    /**
     * Entry point for the OptiTime tool.
     * Reads a degree structure from a text file, constructs the graph,
     * and outputs an optimal study plan.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine();

        System.out.print("Enter the number of concurrent courses: ");
        int concurrent = scanner.nextInt();

        // Create the graph
        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            // Line 1: extract all course codes and add to graph
            String firstLine = reader.readLine();
            String[] courseCodes = firstLine.split(", ");
            for (String code : courseCodes) {
                graph.addCourse(code.trim());
            }

            // Remaining lines: extract prerequisites and add edges
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String courseCode = parts[0].trim();
                for (int i = 1; i < parts.length; i++) {
                    graph.addPrerequisite(courseCode, parts[i].trim());
                }
            }

            // Validate concurrent courses input
            while (concurrent <= 0) {
                System.out.println("Number of concurrent courses must be greater than 0. Please try again.");
                System.out.print("Enter the number of concurrent courses: ");
                concurrent = scanner.nextInt();
            }

        } catch (IOException e) {
            System.out.println("Error: Could not find or read file '" + filename + "'. Please check the filename and try again.");
        }

        System.out.println("Graph constructed successfully!");

        // Generate and print the study plan
        List<List<String>> studyPlan = graph.generateStudyPlan(concurrent);

        System.out.println("\n=== Optimal Study Plan ===");
        int period = 1;
        for (List<String> periodCourses : studyPlan) {
            System.out.println("Study Period " + period + ": " + periodCourses);
            period++;
        }
        System.out.println("\nTotal study periods: " + (period - 1));
    }
}


