package restik.model;

import java.util.ArrayList;
import java.util.List;

public class Operation {
    private int oper_type = 0;
    private double oper_time = 0;
    private int oper_async_point = 0;

    List<Product1> oper_products;

    public int getOper_type() {
        return oper_type;
    }

    public Operation() {
    }

    public List<Product1> getRequiredProducts() {
        return oper_products;
    }

    //    public double getProductsIds() {
//        List<Integer> productsId = new ArrayList<>();
//        for (var product : products) {
//            productsId.add(product.prod_type);
//        }
//    }

    void activate() throws InterruptedException {
        wait((long) oper_time * 100);
    }

    @Override
    public String toString() {
        return oper_type + " " + oper_time + " " + oper_async_point + " " + oper_products;
    }
}
