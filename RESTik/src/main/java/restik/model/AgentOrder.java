package restik.model;

import java.util.List;

public class AgentOrder {
    private final List<DishCard> visOrdDishes;

    public List<DishCard> getVisOrdDishes() {
        return visOrdDishes;
    }

    public AgentOrder(List<DishCard> menuDishOrdered) {
        visOrdDishes = menuDishOrdered;
    }

    /**
     * @return минимальное время приготовления всех блюд в заказе
     */
    public double countMinTime() {
        double totalTime = 0;
        for (var dishCard: visOrdDishes) {
            totalTime += dishCard.getCardTime();
        }
        return totalTime;
    }
}
