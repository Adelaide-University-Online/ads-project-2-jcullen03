/**
 * File: Course.java
 * Description: Represents a single course in the degree program
 * Author: Josie Cullen
 * Student ID: 2937800
 * Email ID: josie.cullen
 * AI Tool Used: Y
 * This is my own work as defined by
 * the University's Academic Integrity Policy.
 **/

/**
 * Represents a single course in the degree program.
 * Each course is identified by a unique course code.
 */
public class Course {

    private String courseCode;

    /**
     * Constructs a Course with the given course code.
     *
     * @param courseCode the unique code identifying this course
     */
    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Returns a string representation of this course.
     *
     * @return the course code as a string
     */
    @Override
    public String toString() {
        return courseCode;
    }

    /**
     * Checks if two courses are equal based on their course code.
     *
     * @param obj the object to compare with
     * @return true if the course codes match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course other = (Course) obj;
        return courseCode.equals(other.courseCode);
    }

    /**
     * Returns a hash code for this course based on the course code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}
