package restik.model;

public class EquipmentType {

    private int equip_type_id = 0;
    private String equip_type = "";


    public EquipmentType() {
    }

    @Override
    public String toString() {
        return equip_type_id + " " + equip_type;
    }
}
