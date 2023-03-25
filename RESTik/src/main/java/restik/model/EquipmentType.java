package restik.model;

import java.util.List;

// recept
public class EquipmentType {

    private class EqType {
        private int equip_type_id = 0;
        private String equip_type = "";


        public EqType() {
        }

        @Override
        public String toString() {
            return equip_type_id + " " + equip_type;
        }
    }

    List<EqType> equipment_type;
}
