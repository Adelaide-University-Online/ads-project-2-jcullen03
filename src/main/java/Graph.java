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
import java.util.Collections;

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
     * @param prereq     the prerequisite course code
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

    /**
     * Checks if two graphs are equal based on their adjacency lists.
     *
     * @param obj the object to compare with
     * @return true if the adjacency lists match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Graph other = (Graph) obj;
        return adjacencyList.equals(other.adjacencyList);
    }

    /**
     * Returns a hash code for this graph based on its adjacency list.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return adjacencyList.hashCode();
    }

    /**
     * Calculates the in-degree of each course (number of prerequisites).
     *
     * @return a map of course code to its in-degree count
     */
    public Map<String, Integer> calculateInDegrees() {
        Map<String, Integer> inDegrees = new HashMap<>();

        // In-degree = number of prerequisites (size of each course's own list)
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            inDegrees.put(entry.getKey(), entry.getValue().size());
        }

        return inDegrees;
    }

    /**
     * Generates an optimal study plan using topological sort.
     * Groups courses into study periods based on the concurrent course limit.
     *
     * @param concurrent the maximum number of courses per study period
     * @return a list of study periods, each containing a list of course codes
     */
    public List<List<String>> generateStudyPlan(int concurrent) {
        Map<String, Integer> inDegrees = calculateInDegrees();
        List<List<String>> studyPlan = new ArrayList<>();
        List<String> available = new ArrayList<>();

        // Find all courses with no prerequisites
        for (String course : adjacencyList.keySet()) {
            if (inDegrees.get(course) == 0) {
                available.add(course);
            }
        }
        Collections.sort(available);

        while (!available.isEmpty()) {
            // Take up to concurrent courses this period
            List<String> taking = new ArrayList<>(
                    available.subList(0, Math.min(concurrent, available.size()))
            );
            List<String> remaining = new ArrayList<>(
                    available.subList(Math.min(concurrent, available.size()), available.size())
            );

            // Find newly unlocked courses after completing this period
            List<String> newlyUnlocked = new ArrayList<>();
            for (String course : taking) {
                for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
                    if (entry.getValue().contains(course)) {
                        String dependent = entry.getKey();
                        inDegrees.put(dependent, inDegrees.get(dependent) - 1);
                        if (inDegrees.get(dependent) == 0) {
                            newlyUnlocked.add(dependent);
                        }
                    }
                }
            }

            studyPlan.add(taking);

            // Next period: remaining deferred + newly unlocked
            Collections.sort(newlyUnlocked);
            available = new ArrayList<>(remaining);
            available.addAll(newlyUnlocked);
        }

        return studyPlan;
    }
}
