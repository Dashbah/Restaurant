package restik.model;

import java.util.List;

/**
 *
 * Класс агента заказа.
 *
 */
public class AgentOrder {

    /**
     * Список {@link DishCard}, представляющий блюда в заказе.
     */
    List<DishCard> vis_ord_dishes;

    public List<DishCard> getVis_ord_dishes() {
        return vis_ord_dishes;
    }
    /**
     * Конструктор класса.
     *
     * @param menuDishOrdered список {@link DishCard}, представляющий блюда в заказе.
     */
    public AgentOrder(List<DishCard> menuDishOrdered) {
        vis_ord_dishes = menuDishOrdered;
    }

    /**
     * Вычисляет минимальное время приготовления всех блюд в заказе.
     *
     * @return время приготовления всех блюд в заказе.
     */
    public double countMinTime() {
        double totalTime = 0;
        for (var dishCard: vis_ord_dishes) {
            totalTime += dishCard.getCard_time();
        }
        return totalTime;
    }
}
