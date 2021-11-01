import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Json {
    private final static String PATH = "src/main/resources/file2.txt";
    private final static String TO_PATH = "src/main/resources/user.json";

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        File file = new File(PATH);
        checkFile(file);
        read(people, file);
        write(TO_PATH, people);
    }

    private static void write(String TO_PATH, List<Person> people) {

        try (BufferedWriter write = new BufferedWriter(new FileWriter(TO_PATH))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(people);
            write.write(json);
            System.out.println(json);
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    private static void read(List<Person> people, File file) {

        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            String valueRead = read.readLine();

            while (valueRead != null) {
                String[] value = valueRead.split(" ");
                if (!value[0].equals("name") || !value[1].equals("age")) {
                    if (!value[0].equals("")) {
                        Person person = new Person(value[0], parseInt(value[1]));
                        people.add(person);

                    }
                }

                valueRead = read.readLine();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    private static void checkFile(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
        }
    }
}

class Person {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
