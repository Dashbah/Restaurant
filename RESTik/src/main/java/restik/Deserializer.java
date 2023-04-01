package restik;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Deserializer {
    public static <T> T[] Deserialize(InputStream inputStream, Class<T[]> clazz) throws IOException {
        T[] objects;

        Gson gson = new Gson();

        var stream = new InputStreamReader(inputStream);
        objects = gson.fromJson(stream, clazz);
        stream.close();

        return objects;
    }
}
