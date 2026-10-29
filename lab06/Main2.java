package lab06;
@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

public class Main2 {
    public static void main(String[] args) {
        Notifier emailSender = message ->
                System.out.println("Email: " + message);
        Notifier smsSender = (Notifier & Urgent) (message) ->
                System.out.println("SMS: " + message);
        Notifier[] senders = {emailSender, smsSender};
        String message = "I AM RUNNING LATE.";
        for (Notifier sender : senders) {
            sender.send(message);
            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}
