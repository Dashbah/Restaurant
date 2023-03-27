package restik.model;

import java.util.List;

public class AgentOrder {
    static int numOfOrders = 0;
    private final int agentId;
    List<DishCard> vis_ord_dishes;

    public List<DishCard> getVis_ord_dishes() {
        return vis_ord_dishes;
    }

    public AgentOrder(List<DishCard> menuDishOrdered) {
        vis_ord_dishes = List.copyOf(menuDishOrdered);
        ++numOfOrders;
        // numeration from 1;
        agentId = numOfOrders;
    }

    public Integer countMinTime() {
        int totalTime = 0;
        for (var dishCard: vis_ord_dishes) {
            totalTime += dishCard.getCard_time();
            // totalTime += dishCard.getCard_time();
        }
        return totalTime;
    }
}
