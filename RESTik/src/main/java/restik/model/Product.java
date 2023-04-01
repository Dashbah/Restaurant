package restik.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("prod_item_id")
    private int prodItemId = 0;
    @SerializedName("prod_item_type")
    private int prodItemType = 0;
    @SerializedName("prod_item_name")
    private String prodItemName = "";
    @SerializedName("prod_item_company")
    private String prodItemCompany = "";
    @SerializedName("prod_item_unit")
    private String prodItemUnit = "";
    @SerializedName("prod_item_quantity")
    private double prodItemQuantity = 0;
    @SerializedName("prod_item_cost")
    private double prodItemCost = 0;
    @SerializedName("prod_item_delivered")
    private String prodItemDelivered = "";
    @SerializedName("prod_item_valid_until")
    private String prodItemValidUntil = "";

    public Product() {
    }

    public int getProdItemType() {
        return prodItemType;
    }

    public void take(double quantity) {
        prodItemQuantity -= quantity;
    }

    @Override
    public String toString() {
        return prodItemId + " " + prodItemType + " " + prodItemName
                + " " + prodItemCompany + " " + prodItemUnit + " " + prodItemQuantity
                + " " + prodItemCost + " " + prodItemDelivered + " " + prodItemValidUntil;
    }

}
