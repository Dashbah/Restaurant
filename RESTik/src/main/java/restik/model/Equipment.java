package restik.model;


public class Equipment {
    private int equip_type = 0;
    private String equip_name = "";
    private boolean equip_active = false;

    public Equipment() {
    }

    public int getEquip_type() {
        return equip_type;
    }

    public String getEquip_name() {
        return equip_name;
    }

    @Override
    public String toString() {
        return equip_type + " " + equip_name + " " + equip_active;
    }
}

