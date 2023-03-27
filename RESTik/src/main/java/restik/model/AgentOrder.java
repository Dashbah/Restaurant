package restik.model;

import java.util.List;

public class AgentOrder {
    List<DishCard> vis_ord_dishes;

    public List<DishCard> getVis_ord_dishes() {
        return vis_ord_dishes;
    }

    public AgentOrder(List<DishCard> menuDishOrdered) {
        vis_ord_dishes = menuDishOrdered;
    }

    public double countMinTime() {
        double totalTime = 0;
        for (var dishCard: vis_ord_dishes) {
            totalTime += dishCard.getCard_time();
        }
        return totalTime;
    }
}
