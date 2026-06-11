/**
 * File: Main.java
 * Description: Entry point for the OptiTime
 * tool. Reads a degree structure from a text
 * file, constructs the graph, and outputs an
 * optimal study plan.
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * AI Tool Used: Y
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine();

        System.out.print("Enter the number of concurrent courses: ");
        int concurrent = scanner.nextInt();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            // Line 1: extract all course codes
            String firstLine = reader.readLine();
            String[] courseCodes = firstLine.split(", ");
            List<Course> courses = new ArrayList<>();
            for (String code : courseCodes) {
                courses.add(new Course(code.trim()));
            }

            // Remaining lines: extract course and its prerequisites
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String courseCode = parts[0].trim();
                List<String> prereqs = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    prereqs.add(parts[i].trim());
                }
                // Print to verify
                System.out.println("Course: " + courseCode + " | Prerequisites: " + prereqs);
            }

            // Print to verify
            System.out.println("Courses found: " + courses);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}
