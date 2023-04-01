package restik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VisitorOrder {
    @SerializedName("vis_name")
    private String visName;
    @SerializedName("vis_ord_started")
    private String visOrdStarted;
    @SerializedName("vis_ord_ended")
    private String visOrdEnded;
    @SerializedName("vis_ord_total")
    private int visOrdTotal;
    @SerializedName("vis_ord_dishes")
    private List<OrderDishes> visOrdDishes;

    public String getVisName() {
        return visName;
    }

    public List<Integer> getVisOrdMenuDishesId() {
        List<Integer> menuDishList = new ArrayList<>();
        for (var visOrdDish : visOrdDishes) {
            menuDishList.add(visOrdDish.getMenuDish());
        }
        return menuDishList;
    }

    public VisitorOrder() {
    }

    @Override
    public String toString() {
        return visName + " " + visOrdStarted + " " + visOrdEnded
                + " " + visOrdTotal + " " + visOrdDishes;
    }
}
