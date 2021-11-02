import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrequencyWords {
    private static final String PATH = "src/Store/words.txt";

    public static void main(String[] args) {
        File file = new File(PATH);
        List<String> list = new ArrayList<>();
        Set<String> list1 = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        checkFile(file);
        read(list, file);
        writeToHashMap(list, list1, map);
    }
    private static void writeToHashMap(List<String> araylist, Set<String> setlist, Map<String, Integer> maplist) {
        int count;
        setlist.addAll(araylist);
        for (String a : setlist) {
            count = 1;
            for (String b : araylist) {
                if (b.equals(a)) {
                    maplist.put(a, count);
                    count++;
                }
            }
        }

        maplist = maplist.entrySet().stream().sorted(Comparator.comparing(
                Map.Entry<String, Integer>::getValue).reversed()).collect(
                LinkedHashMap<String, Integer>::new,
                (map1, e) -> map1.put(e.getKey(), e.getValue()),
                LinkedHashMap::putAll);

        maplist.forEach((k,v)->{System.out.println(k+" "+v);System.out.println();});

    }


    private static void read(List<String> list, File file) {

        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            String valueRead = read.readLine();

            while (valueRead != null) {
                String[] value = valueRead.split(" ");
                for (int i = 0; i < value.length; i++) {
                    if (!value[i].equals("")) {
                        list.add(value[i]);
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