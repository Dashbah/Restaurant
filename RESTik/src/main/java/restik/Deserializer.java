package restik;
import com.google.gson.Gson;
import restik.model.Cookers;

import java.io.FileReader;

public class Deserializer {

    public static Object[] Deserialize(String filePath) {
        Object[] cookers;
        try {
            // открываем файл для чтения
            FileReader reader = new FileReader(filePath);

            // Create a new instance of Gson
            Gson gson = new Gson();

            // преобразуем JSON данные в объект класса Data
            cookers = gson.fromJson(reader, Cookers.Cooker[].class);

            // закрываем файл
            reader.close();

            return cookers;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // throw ex???
        return null;
    }
}
