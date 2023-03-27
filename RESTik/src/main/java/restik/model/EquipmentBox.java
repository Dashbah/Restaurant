package restik.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentBox {
    // Integer equipmentType;
    final Object lock = new Object();
    List<Equipment> equipmentList = new ArrayList<>();
    Integer counter = 0;
    public EquipmentBox(Equipment equipment) {
        equipmentList.add(equipment);
        counter = 1;
    }
    public void add(Equipment equipment) {
        // check a lot of
        equipmentList.add(equipment);
        ++counter;
    }
    public void get(Equipment equipment) {
        // check a lot of
        if (counter != 0) {
            equipmentList.remove(equipment);
            --counter;
        }
    }
}
