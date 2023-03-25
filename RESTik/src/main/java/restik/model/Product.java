package restik.model;

public class Product {

    private int prod_item_id = 0;

    private int prod_item_type = 0;

    private String prod_item_name = "";

    private String prod_item_company = "";

    private String prod_item_unit = "";

    private double prod_item_quantity = 0;

    private double prod_item_cost = 0;

    private String prod_item_delivered = "";

    private String prod_item_valid_until = "";

    public Product() {
    }

    public int getProd_item_type() {
        return prod_item_type;
    }

    public double getProd_item_quantity() {
        return prod_item_quantity;
    }

    @Override
    public String toString() {
        return prod_item_id + " " + prod_item_type + " " + prod_item_name
                + " " + prod_item_company + " " + prod_item_unit + " " + prod_item_quantity
                + " " + prod_item_cost + " " + prod_item_delivered + " " + prod_item_valid_until;
    }

}
