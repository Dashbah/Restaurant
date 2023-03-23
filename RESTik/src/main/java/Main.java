import restik.Deserializer;

public class Main {
    public static void main(String[] args) {
        var list = Deserializer.Deserialize("/Users/dashbah/Desktop/repo/Restoran/RESTik/src/main/resources/cookers.json");
        assert list != null;
        for (var data : list) {
            System.out.println(data.toString());
        }
    }
}