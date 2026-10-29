import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank @MaxLength(20)
    String username;

    @NotBlank @MaxLength(30)
    String password;

    SignupForm(String u, String p) {
        username = u;
        password = p;
    }
}

public class Main {

    static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field f : obj.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                String value = (String) f.get(obj);

                if (f.isAnnotationPresent(NotBlank.class) &&
                    (value == null || value.trim().isEmpty()))
                    errors.add(f.getName() + " cannot be blank");

                MaxLength m = f.getAnnotation(MaxLength.class);
                if (m != null && value != null && value.length() > m.value())
                    errors.add(f.getName() + " exceeds " + m.value() + " characters");

            } catch (Exception e) {
                errors.add("Invalid field: " + f.getName());
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        SignupForm form = new SignupForm(
            "",
            "ThisPasswordIsTooLongForTheField"
        );

        List<String> errors = validate(form);

        for (String error : errors)
            System.out.println(error);
    }
}