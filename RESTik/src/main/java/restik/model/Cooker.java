package restik.model;

public class Cooker {
    private int cook_id = 0;
    private String cook_name = "";
    private boolean cook_active = false;

    public Cooker() {
    }

    @Override
    public String toString() {
        return cook_id + " " + cook_name + " " + cook_active;
    }
}
