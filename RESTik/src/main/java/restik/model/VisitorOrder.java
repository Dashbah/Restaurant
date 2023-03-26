package restik.model;

import java.util.ArrayList;
import java.util.List;

public class VisitorOrder {
    private String vis_name;
    private String vis_ord_started;
    private String vis_ord_ended;
    private int vis_ord_total;

    // ????
    List<OrderDishes> vis_ord_dishes;

    public String getVisName() {
        return vis_name;
    }

    public List<Integer> getVisOrdMenuDishesId() {
        List<Integer> menuDishList = new ArrayList<>();
        for (var visOrdDish : vis_ord_dishes) {
            menuDishList.add(visOrdDish.getMenu_dish());
        }
        return menuDishList;
    }

    public VisitorOrder() {
    }

    @Override
    public String toString() {
        return vis_name + " " + vis_ord_started + " " + vis_ord_ended
                + " " + vis_ord_total + " " + vis_ord_dishes;
    }
}
