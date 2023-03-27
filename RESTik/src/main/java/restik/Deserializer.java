package restik;
import com.google.gson.Gson;

import java.io.FileReader;

public class Deserializer {

    public static <T> T[] Deserialize(String filePath, Class<T[]> clazz) {
        T[] objects;
        try {
            // открываем файл для чтения
            FileReader reader = new FileReader(filePath);

            // Create a new instance of Gson
            Gson gson = new Gson();

            // преобразуем JSON данные в объект класса Data
            objects = gson.fromJson(reader, clazz);

            // закрываем файл
            reader.close();

            return objects;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
