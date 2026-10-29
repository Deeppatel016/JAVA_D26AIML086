class FileResource implements AutoCloseable {

    FileResource() {
        System.out.println("Resource opened");
    }

    void read() {
        System.out.println("Reading resource...");
        throw new RuntimeException("Error while reading");
    }

    @Override
    public void close() {
        System.out.println("Resource closed");
    }
}

public class Main3 {

    public static void main(String[] args) {

        try (FileResource resource = new FileResource()) {

            resource.read();

        } catch (Exception e) {
            System.out.println("Reported error: " + e.getMessage());
        }
    }
}