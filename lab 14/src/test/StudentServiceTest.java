package test;

import service.StudentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class StudentServiceTest {

    @Test
    public void testAddStudent() {
        StudentService service = new StudentService();
        service.addStudent("Rahul");
        assertEquals(1, service.getCount());
    }

    @Test
    public void testAddTwoStudents() {
        StudentService service = new StudentService();
        service.addStudent("Rahul");
        service.addStudent("Amit");
        assertEquals(2, service.getCount());
    }

    @Test
    public void testInvalidName() {
        StudentService service = new StudentService();
        assertThrows(IllegalArgumentException.class, () -> service.addStudent(""));
    }

    @Test
    public void testSaveAndReloadData() throws Exception {
        StudentService service1 = new StudentService();
        service1.addStudent("Rahul");
        service1.addStudent("Amit");

        String filename = "students_test.dat";
        service1.saveToFile(filename);

        StudentService service2 = new StudentService();
        service2.loadFromFile(filename);

        // Verify count is preserved
        assertEquals(service1.getCount(), service2.getCount());

        // Verify reloaded items match original items (Student.equals)
        assertEquals(service1.getStudents(), service2.getStudents());

        // Clean up temporary test file
        new File(filename).delete();
    }
}
