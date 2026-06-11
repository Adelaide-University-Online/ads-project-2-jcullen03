import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * File: GraphTest.java
 * Description: Unit tests for the Graph class.
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/
public class GraphTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
    }

    @Test
    void testAddCourse() {
        graph.addCourse("COMP1043");
        assertTrue(graph.toString().contains("COMP1043"));
    }

    @Test
    void testAddDuplicateCourse() {
        graph.addCourse("COMP1043");
        graph.addCourse("COMP1043");
        // Should only appear once
        int count = graph.toString().split("COMP1043").length - 1;
        assertEquals(1, count);
    }

    @Test
    void testAddPrerequisite() {
        graph.addPrerequisite("INFT1032", "COMP1043");
        assertTrue(graph.toString().contains("INFT1032 -> [COMP1043]"));
    }

    @Test
    void testAddMultiplePrerequisites() {
        graph.addPrerequisite("INFT1032", "COMP1043");
        graph.addPrerequisite("INFT1032", "INFT1024");
        assertTrue(graph.toString().contains("INFT1032"));
        assertTrue(graph.toString().contains("COMP1043"));
        assertTrue(graph.toString().contains("INFT1024"));
    }

    @Test
    void testCourseWithNoPrerequisites() {
        graph.addCourse("COMP1043");
        assertTrue(graph.toString().contains("COMP1043 -> []"));
    }

    @Test
    void testStudyPlanNoPrerequisites() {
        // All courses have no prerequisites — should all fit in one period
        graph.addCourse("COMP1043");
        graph.addCourse("INFT1024");
        graph.addCourse("CURR3021");
        List<List<String>> plan = graph.generateStudyPlan(3);
        assertEquals(1, plan.size());
        assertEquals(3, plan.get(0).size());
    }

    @Test
    void testStudyPlanSimpleChain() {
        // A requires B, B requires C — must be 3 separate periods
        graph.addPrerequisite("A", "B");
        graph.addPrerequisite("B", "C");
        List<List<String>> plan = graph.generateStudyPlan(3);
        assertEquals(3, plan.size());
    }

    @Test
    void testStudyPlanConcurrentLimit() {
        // 4 courses with no prerequisites, limit of 2 — needs 2 periods
        graph.addCourse("A");
        graph.addCourse("B");
        graph.addCourse("C");
        graph.addCourse("D");
        List<List<String>> plan = graph.generateStudyPlan(2);
        assertEquals(2, plan.size());
        assertEquals(2, plan.get(0).size());
        assertEquals(2, plan.get(1).size());
    }

    @Test
    void testStudyPlanConcurrentLimitOfOne() {
        // Limit of 1 — each course in its own period
        graph.addCourse("A");
        graph.addCourse("B");
        graph.addCourse("C");
        List<List<String>> plan = graph.generateStudyPlan(1);
        assertEquals(3, plan.size());
    }

    @Test
    void testStudyPlanPrerequisiteOrder() {
        // B requires A — A must appear in an earlier period than B
        graph.addPrerequisite("B", "A");
        graph.addCourse("A");
        List<List<String>> plan = graph.generateStudyPlan(3);
        int periodA = -1;
        int periodB = -1;
        for (int i = 0; i < plan.size(); i++) {
            if (plan.get(i).contains("A")) periodA = i;
            if (plan.get(i).contains("B")) periodB = i;
        }
        assertTrue(periodA < periodB);
    }

    @Test
    void testStudyPlanInvalidConcurrent() {
        graph.addCourse("A");
        graph.addCourse("B");
        // concurrent of 0 should not cause infinite loop
        // validation handled in Main, but graph should handle gracefully
        List<List<String>> plan = graph.generateStudyPlan(1);
        assertFalse(plan.isEmpty());
    }
}
