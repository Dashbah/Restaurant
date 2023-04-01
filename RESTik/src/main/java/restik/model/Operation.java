package restik.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Operation {
    @SerializedName("oper_type")
    private int operationType = 0;
    @SerializedName("oper_time")
    private double operTime = 0;
    @SerializedName("oper_async_point")
    private int operAsyncPoint = 0;
    @SerializedName("oper_products")
    private List<ProductCard> operProducts;

    public Operation() {
    }

    public List<ProductCard> getRequiredProducts() {
        return operProducts;
    }

    void activate() throws InterruptedException {
        Thread.sleep((long) operTime * 1000);
    }

    @Override
    public String toString() {
        return operationType + " " + operTime + " " + operAsyncPoint + " " + operProducts;
    }
}
