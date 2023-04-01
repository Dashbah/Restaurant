package restik.model;

import com.google.gson.annotations.SerializedName;

public class OrderDishes {
    @SerializedName("ord_dish_id")
    private int ordDishId;
    @SerializedName("menu_dish")
    private int menuDish;

    public int getMenuDish() {
        return menuDish;
    }
}
