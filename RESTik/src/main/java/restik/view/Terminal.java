package restik.view;

import restik.model.DishCard;
import restik.model.MenuDish;

import java.util.Map;

/**
 * Terminal - класс для отображения меню блюд и взаимодействия с пользователем.
 */
public class Terminal {
    private final Map<Integer, MenuDish> menuDishes;
    private final Map<Integer, DishCard> dishCards;

    public Terminal(Map<Integer, MenuDish> menuDishes, Map<Integer, DishCard> dishCards) {
        this.menuDishes = menuDishes;
        this.dishCards = dishCards;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Menu:\n";
        for (MenuDish menuDish : menuDishes.values()) {
            DishCard dishCard = dishCards.get(menuDish.getMenuDishCard());
            result += dishCard.getDishName() + "\n";
        }
        result += "----------";
        return result;
    }
}
