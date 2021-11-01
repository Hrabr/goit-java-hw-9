import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WalidNamber {

    private final static String PATH = "src/main/resources/file1.txt";

    public static void main(String[] args) {

        File file = new File(PATH);
        CheckFile(file);
        String value = "";

        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            String valueRead = read.readLine();
            while (valueRead != null) {
                if (print(valueRead)) {

                    value = value + System.lineSeparator() + valueRead + System.lineSeparator();
                }
                valueRead = read.readLine();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        System.out.println(value.trim());
    }

    private static void CheckFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File: " + file.getName() + " not found!");
        }
    }

    private static boolean print(String a) {

        Pattern pattern1 = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}");
        Pattern pattern2 = Pattern.compile("\\d{3}\\-\\d{3}\\-\\d{4}");
        Matcher matcher1 = pattern1.matcher(a);
        Matcher matcher2 = pattern2.matcher(a);
        return matcher1.matches() || matcher2.matches();
    }
}
