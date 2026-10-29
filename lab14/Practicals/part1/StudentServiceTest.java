import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    void testAddStudent() {
        StudentService service = new StudentService();
        service.addStudent("Rahul");
        assertEquals(1, service.getCount());
    }

    @Test
    void testAddTwoStudents() {
        StudentService service = new StudentService();
        service.addStudent("Rahul");
        service.addStudent("Amit");
        assertEquals(2, service.getCount());
    }

    @Test
    void testInvalidName() {
        StudentService service = new StudentService();
        assertThrows(IllegalArgumentException.class, () -> service.addStudent(""));
    }
}
