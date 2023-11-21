package org.example;


public class Main {
    private static final String FILE_PATH = "src/main/java/org/example/datalake/1138_bus.mtx";

    public static void main(String[] args) {
        Controller controller = new Controller(FILE_PATH);
        controller.run();
    }
}

