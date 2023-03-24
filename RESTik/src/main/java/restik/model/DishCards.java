package restik.model;

public class DishCards {

    private int card_id = 0;

    private String dish_name = "";

    private String card_descr = "";
    private int card_time = 0;

    Equipment eq = new Equipment();
    private int equip_type = eq.getEquip_type();

    Operation[] operations;



    public DishCards() {
    }

    @Override
    public String toString() {
        return card_id + " " + dish_name + " " + card_descr + " " + card_time + " " + equip_type + " " + operations;
    }
}
