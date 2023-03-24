package restik.model;

import java.util.List;

public class DishCards {
    public class DishCard {

        private int card_id = 0;

        private String dish_name = "";

        private String card_descr = "";
        private int card_time = 0;

        // Equipment eq = new Equipment();
        // private int equip_type = eq.getEquip_type();
        private int equip_type = 0;
        List<Operation> operations;


        public DishCard() {
        }

        @Override
        public String toString() {
            return card_id + " " + dish_name + " " + card_descr + " " + card_time + " " + equip_type + " " + operations;
        }
    }

    List<DishCard> dish_cards;
}
