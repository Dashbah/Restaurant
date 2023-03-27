package restik.view;

import restik.model.DishCard;
import restik.model.MenuDish;

import java.util.Map;
import java.util.Scanner;

/**
 * Terminal - класс для отображения меню блюд и взаимодействия с пользователем.
 */
public class Terminal {
    Scanner input = new Scanner(System.in);

    Map<Integer, MenuDish> menuDishes;
    Map<Integer, DishCard> dishCards;

    /**
     * Конструктор класса Terminal.
     * @param menuDishes - карта блюд меню.
     * @param dishCards
     */
    public Terminal(Map<Integer, MenuDish> menuDishes, Map<Integer, DishCard> dishCards) {
        this.menuDishes = menuDishes;
        this.dishCards = dishCards;
    }

    /**
     * Переопределение метода toString() для отображения меню блюд.
     * @return возвращает строку меню блюд.
     */
    @Override
    public String toString() {
        String result = "";
        result += "Menu:\n";
        for (MenuDish menuDish : menuDishes.values()) {
            DishCard dishCard = dishCards.get(menuDish.getMenu_dish_card());
            result += dishCard.getDish_name() + "\n";
        }
        result += "----------";
        return result;
    }

}
