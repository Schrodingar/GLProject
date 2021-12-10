import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Searcher {

    private List parse(String filePath) {
        String data = "";
        try {
            data = readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Gson gson = new Gson();
        int start = -1;
        final List<Object> objects = new LinkedList<>();

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '{') start = i;
            if (data.charAt(i) == '}' && start != -1) {
                objects.add(gson.fromJson(data.substring(start, i + 1), Object.class));
                start = -1;
            }
        }
        return objects;
    }

    public String countObjectsWithSpecificFieldOnly(String filePath, String fieldName) {
        int counter = 0;
        List objects = parse(filePath);
        for (Object object : objects) {
            Object[] obj = ((LinkedTreeMap) object).keySet().toArray();
            if (obj.length == 1 && obj[0].equals(fieldName)) {
                counter++;
            }
        }
        return  ("found " + counter + " objects with field \"" + fieldName + "\"");
    }

    public String countObjectsWithSpecificFieldAndSpecificValue(String filePath, String fieldName, String value) {
        int counter = 0;
        List objects = parse(filePath);
        for (Object object : objects) {
            Object[] obj = ((LinkedTreeMap) object).entrySet().toArray();
            for (int j = 0; j < obj.length; j++) {
                if (((LinkedTreeMap) object).keySet().toArray()[j].equals(fieldName) && ((LinkedTreeMap) object).values().toArray()[j].equals(value)) {
                    counter++;
                }
            }
        }
        return ("found " + counter + " objects where field \"" + fieldName + "\" equals \"" + value + "\"");
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }


}
