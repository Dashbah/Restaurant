package restik.model;

import java.util.List;

public class Operation {

    private int oper_type = 0;
    private double oper_time = 0;
    private int oper_async_point = 0;

    List<Product1> products;


    public Operation() {
    }

    @Override
    public String toString() {
        return oper_type + " " + oper_time + " " + oper_async_point + " " + products;
    }
}
