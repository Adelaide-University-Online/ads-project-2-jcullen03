import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * File: CourseTest.java
 * Description: Unit tests for the Course class.
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/
public class CourseTest {

    @Test
    void testToString() {
        Course c = new Course("COMP1043");
        assertEquals("COMP1043", c.toString());
    }

    @Test
    void testEqualsSameCourse() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("COMP1043");
        assertEquals(c1, c2);
    }

    @Test
    void testEqualsDifferentCourse() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("INFT1024");
        assertNotEquals(c1, c2);
    }

    @Test
    void testEqualsNull() {
        Course c = new Course("COMP1043");
        assertNotEquals(c, null);
    }

    @Test
    void testEqualsDifferentType() {
        Course c = new Course("COMP1043");
        assertNotEquals(c, "COMP1043");
    }

    @Test
    void testHashCodeSameCourse() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("COMP1043");
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testHashCodeDifferentCourse() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("INFT1024");
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testToStringWithNumbers() {
        Course c = new Course("INFT1024");
        assertEquals("INFT1024", c.toString());
    }

    @Test
    void testToStringEmptyCode() {
        Course c = new Course("");
        assertEquals("", c.toString());
    }

    @Test
    void testEqualsSameObject() {
        Course c = new Course("COMP1043");
        assertEquals(c, c);
    }

    @Test
    void testEqualsSymmetric() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("COMP1043");
        assertEquals(c1, c2);
        assertEquals(c2, c1); // symmetry check
    }

    @Test
    void testHashCodeConsistent() {
        Course c = new Course("COMP1043");
        int hash1 = c.hashCode();
        int hash2 = c.hashCode();
        assertEquals(hash1, hash2); // same object always returns same hash
    }

    @Test
    void testEqualsTransitive() {
        Course c1 = new Course("COMP1043");
        Course c2 = new Course("COMP1043");
        Course c3 = new Course("COMP1043");
        assertEquals(c1, c2);
        assertEquals(c2, c3);
        assertEquals(c1, c3); // transitivity check
    }
}