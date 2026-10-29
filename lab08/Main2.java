import java.util.*;

class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String msg) {
        super(msg);
    }
}

class Warehouse {
    Map<String, Integer> stock = new HashMap<>();

    Warehouse() {
        stock.put("Pen", 10);
        stock.put("Book", 5);
        stock.put("Bag", 3);
    }

    void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0)
            throw new InvalidQuantityException("Quantity must be greater than 0");

        int available = stock.getOrDefault(item, 0);

        if (qty > available)
            throw new OutOfStockException(qty - available);

        stock.put(item, available - qty);
        System.out.println(qty + " " + item + " issued");
    }
}

public class Main2 {

    public static void main(String[] args) {

        Warehouse w = new Warehouse();

        String[][] requests = {
            {"Pen", "3"},
            {"Book", "10"},
            {"Bag", "0"},
            {"Pen", "5"}
        };

        for (String[] request : requests) {
            try {
                w.issue(request[0], Integer.parseInt(request[1]));

            } catch (OutOfStockException e) {
                System.out.println(request[0] +
                        " out of stock. Shortfall: " + e.shortfall);

            } catch (InvalidQuantityException e) {
                System.out.println("Invalid quantity: " + request[1]);
            }
        }

        System.out.println("All requests processed.");
    }
}