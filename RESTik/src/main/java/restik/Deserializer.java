package restik;
import com.google.gson.Gson;

import java.io.FileReader;

/**
 * Класс для десериализации JSON-файлов.
 */
public class Deserializer {

    /**
     * Десериализует данные из указанного JSON-файла в массив объектов заданного типа.
     *
     * @param filePath путь к JSON-файлу
     * @param clazz тип объектов, в которые нужно преобразовать данные из JSON
     * @param <T> тип объектов
     * @return массив объектов заданного типа, полученных из JSON-файла
     */
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
