package restik.model;

public class MenuDishes {
    private int menu_dish_id = 0;
    private int menu_dish_card = 0;
    private int menu_dish_price = 0;
    private boolean menu_dish_active = false;

    public int getMenu_dish_id() {
        return menu_dish_id;
    }

    public MenuDishes() {
    }

    @Override
    public String toString() {
        return menu_dish_id + " " + menu_dish_card + " " + menu_dish_price + " " + menu_dish_active;
    }

}
