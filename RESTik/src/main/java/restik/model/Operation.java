package restik.model;

public class Operation {

    private int oper_type = 0;
    private double oper_time = 0;
    private int oper_async_point = 0;

    Products[] products;


    public Operation() {
    }

    @Override
    public String toString() {
        return oper_type + " " + oper_time + " " + oper_async_point + " " + products;
    }
}
