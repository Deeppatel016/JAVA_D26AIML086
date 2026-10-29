import java.util.ArrayList;

public class BankService {
    private ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Invalid name");

        accounts.add(new Account(name));
    }

    public int getCount() {
        return accounts.size();
    }

    public void invalidAction() {
        throw new IllegalArgumentException("Invalid action");
    }
}