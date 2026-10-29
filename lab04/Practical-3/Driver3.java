public class Driver3 {

    public static void main(String[] args) {

        String template = "Dear {name}, order {id} ships {date}.";

        String[] names = {
                "name",
                "id"
        };

        String[] values = {
                "DEEP",
                "086"
        };

        String output = TemplateFilter.FTemplate(template, names, values);

        System.out.println(output);
    }
}