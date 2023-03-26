package restik.view;

import restik.model.MenuDish;

import java.util.Map;
import java.util.Scanner;

public class Terminal {
    Scanner input = new Scanner(System.in);

    Map<Integer, MenuDish> menuDishes;

    public Terminal(Map<Integer, MenuDish> menuDishes) {
        this.menuDishes = menuDishes;
        display();
    }

    private void display() {
        System.out.println(menuDishes);
    }

    void getOrderFromVisitor() {
        // hello my name is
        // what do you want
        // читаем с консольки
        // ! в конце
//        var line = input.nextLine();
//        List<OrderDishes>
//        while (!line.startsWith("!")) {
//
//        }
    }



    public void foo() {

    }

    // private
}
