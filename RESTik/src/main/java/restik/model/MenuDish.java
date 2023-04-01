package restik.model;

import com.google.gson.annotations.SerializedName;

public class MenuDish {
    @SerializedName("menu_dish_id")
    private int menuDishId = 0;
    @SerializedName("menu_dish_card")
    private int menuDishCard = 0;
    @SerializedName("menu_dish_price")
    private int menuDishPrice = 0;
    @SerializedName("menu_dish_active")
    private boolean menuDishActive = false;

    public int getMenuDishId() {
        return menuDishId;
    }

    public int getMenuDishCard() {
        return menuDishCard;
    }

    public MenuDish() {
    }

    @Override
    public String toString() {
        return menuDishId + " " + menuDishCard + " " + menuDishPrice + " " + menuDishActive;
    }
}
