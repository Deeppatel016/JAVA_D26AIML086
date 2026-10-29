import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "id") int id;
    @Column(name = "name") String name;
    @Column(name = "email") String email;

    public String toString() {
        return id + " " + name + " " + email;
    }
}

public class Main3 {

    static void populate(Object obj, String[] header, String[] data)
            throws Exception {

        for (Field f : obj.getClass().getDeclaredFields()) {
            Column c = f.getAnnotation(Column.class);
            if (c == null) continue;

            int i = -1;
            for (int j = 0; j < header.length; j++)
                if (header[j].equals(c.name())) {
                    i = j;
                    break;
                }

            if (i == -1) continue;   // missing column

            f.setAccessible(true);

            if (f.getType() == int.class)
                f.setInt(obj, Integer.parseInt(data[i]));
            else
                f.set(obj, data[i]);
        }
    }

    public static void main(String[] args) throws Exception {

        String[] header = {"id", "name"};
        String[] data = {"101", "Deep"};

        Student s = new Student();
        populate(s, header, data);

        System.out.println(s);
    }
}