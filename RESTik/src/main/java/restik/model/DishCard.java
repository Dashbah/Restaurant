package restik.model;

import java.util.List;


// recept
public class DishCard {

    private int card_id = 0;

    private String dish_name = "";

    private String card_descr = "";
    private double card_time = 0;

    // Equipment eq = new Equipment();
    // private int equip_type = eq.getEquip_type();
    private int equip_type = 0;
    List<Operation> operations;

    public int getCardId() {
        return card_id;
    }

    public double getCard_time() {
        return card_time;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public DishCard() {
    }

    @Override
    public String toString() {
        return card_id + " " + dish_name + " " + card_descr + " " + card_time + " " + equip_type + " " + operations;
    }
}
