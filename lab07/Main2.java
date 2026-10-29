import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class TestClass {

    @Run
    void test1() {
        System.out.println("Test 1 executed");
    }

    @Run
    void test2() {
        System.out.println("Test 2 executed");
    }

    void test3() {
        System.out.println("Test 3 executed");
    }
}

public class Main2 {

    public static void main(String[] args) throws Exception {

        TestClass obj = new TestClass();
        int count = 0;

        for (Method m : TestClass.class.getDeclaredMethods()) {

            if (m.isAnnotationPresent(Run.class)
                    && m.getParameterCount() == 0) {

                m.setAccessible(true);
                m.invoke(obj);
                count++;
            }
        }

        System.out.println("Tests executed: " + count);
    }
}