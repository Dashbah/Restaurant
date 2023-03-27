package restik.model;

import java.util.List;

public class Operation {
    private int oper_type = 0;
    private double oper_time = 0;
    private int oper_async_point = 0;

    List<ProductCard> oper_products;

    public Operation() {
    }

    public List<ProductCard> getRequiredProducts() {
        return oper_products;
    }

    void activate() throws InterruptedException {
        Thread.sleep((long) oper_time * 1000);
    }

    @Override
    public String toString() {
        return oper_type + " " + oper_time + " " + oper_async_point + " " + oper_products;
    }
}
