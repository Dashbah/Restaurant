package restik.model;

import com.google.gson.annotations.SerializedName;

public class ProductCard {
    @SerializedName("prod_type")
    private int prodType = 0;
    @SerializedName("prod_quantity")
    private double prodQuantity = 0;

    public int getProdType() {
        return prodType;
    }

    public double getProdQuantity() {
        return prodQuantity;
    }
}
