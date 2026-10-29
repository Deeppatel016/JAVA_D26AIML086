import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    void testAddAccount() {
        BankService bank = new BankService();
        bank.addAccount("Deep");
        assertEquals(1, bank.getCount());
    }

    @Test
    void testAddTwoAccounts() {
        BankService bank = new BankService();
        bank.addAccount("Deep");
        bank.addAccount("Rahul");
        assertEquals(2, bank.getCount());
    }

    @Test
    void testInvalidAction() {
        BankService bank = new BankService();
        assertThrows(IllegalArgumentException.class, () -> bank.invalidAction());
    }

    @Test
    void testInvalidName() {
        BankService bank = new BankService();
        assertThrows(IllegalArgumentException.class, () -> bank.addAccount(""));
    }
}
