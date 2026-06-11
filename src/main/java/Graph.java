/**
 * File: Graph.java
 * Description: Represents the degree course
 * structure as a directed unweighted graph
 * using an adjacency list. Supports adding
 * courses and prerequisites.
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * AI Tool Used: Y
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents the degree course structure as a directed unweighted graph
 * using an adjacency list. Each course is a vertex and each prerequisite
 * relationship is a directed edge.
 */
public class Graph {

    // Adjacency list mapping each course to its list of prerequisites
    private Map<String, List<String>> adjacencyList;

    /**
     * Constructs an empty Graph.
     */
    public Graph() {
        adjacencyList = new HashMap<>();
    }


    /**
     * Adds a course to the graph as a vertex with no prerequisites.
     *
     * @param courseCode the course code to add
     */
    public void addCourse(String courseCode) {
        // Only add if not already present
        if (!adjacencyList.containsKey(courseCode)) {
            adjacencyList.put(courseCode, new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge from a prerequisite course to the given course.
     *
     * @param courseCode the course that has the prerequisite
     * @param prereq the prerequisite course code
     */
    public void addPrerequisite(String courseCode, String prereq) {
        // Ensure both courses exist in the graph
        addCourse(courseCode);
        addCourse(prereq);
        // Add the prerequisite to the course's list
        adjacencyList.get(courseCode).add(prereq);
    }

    /**
     * Returns a string representation of the graph showing each course
     * and its prerequisites.
     *
     * @return the adjacency list as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
