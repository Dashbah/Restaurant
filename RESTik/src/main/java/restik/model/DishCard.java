package restik.model;

import java.util.ArrayList;
import java.util.List;


// recept
public class DishCard {

    private int card_id = 0;

    private String dish_name = "";

    private String card_descr = "";
    private double card_time = 0;

    private int equip_type = 0;
    List<Operation> operations;

    public int getCardId() {
        return card_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public int getEquip_type() {
        return equip_type;
    }

    public double getCard_time() {
        return card_time;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public List<ProductCard> getRequiredProducts() {
        List<ProductCard> product1s = new ArrayList<>();
        for (var operation : operations) {
            product1s.addAll(operation.getRequiredProducts());
        }
        return product1s;
    }
}
