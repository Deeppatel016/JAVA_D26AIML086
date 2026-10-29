package lab06;

interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

@FunctionalInterface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class Main1 {
    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling devices:");

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchPermission permission1 = new SwitchPermission() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        System.out.println("\nUsing Anonymous Class:");
        System.out.println("Can switch ON at 10:00? "
                + permission1.maySwitchOn(new Fan(), 10));

        SwitchPermission permission2 =
                (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("\nUsing Lambda:");
        System.out.println("Can switch ON at 23:00? "
                + permission2.maySwitchOn(new Light(), 23));
    }
}