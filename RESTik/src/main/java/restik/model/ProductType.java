package restik.model;

import com.google.gson.annotations.SerializedName;

public class ProductType {
    @SerializedName("prod_type_id")
    private int prodTypeId = 0;
    @SerializedName("prod_type_name")
    private String prodTypeName = "";
    @SerializedName("prod_is_food")
    private boolean prodIsFood = false;

    public ProductType() {
    }

    @Override
    public String toString() {
        return prodTypeId + " " + prodTypeName + " " + prodIsFood;
    }
}

