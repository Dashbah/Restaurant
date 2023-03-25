package restik.view;

import restik.model.MenuDish;
import restik.model.OrderDishes;

import java.util.List;
import java.util.Scanner;

public class Terminal {
    Scanner input = new Scanner(System.in);

    List<MenuDish> menuDishes;

    public Terminal(List<MenuDish> menuDishes) {
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
