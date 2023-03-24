package restik.model;

import java.util.List;

public class MenuDishes {
    public class MenuDish {
        private int menu_dish_id = 0;
        private int menu_dish_card = 0;
        private int menu_dish_price = 0;
        private boolean menu_dish_active = false;

        public int getMenu_dish_id() {
            return menu_dish_id;
        }

        public MenuDish() {
        }

        @Override
        public String toString() {
            return menu_dish_id + " " + menu_dish_card + " " + menu_dish_price + " " + menu_dish_active;
        }

    }
    List<MenuDish> menu_dishes;
}
