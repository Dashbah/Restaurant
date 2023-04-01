package restik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DishCard {
    @SerializedName("card_id")
    private int cardId = 0;
    @SerializedName("dish_name")
    private String dishName = "";

    @SerializedName("card_descr")
    private String cardDescr = "";
    @SerializedName("card_time")
    private double cardTime = 0;
    @SerializedName("equip_type")
    private int equipType = 0;

    @SerializedName("operations")
    private List<Operation> operations = new ArrayList<>();

    public int getCardId() {
        return cardId;
    }

    public String getDishName() {
        return dishName;
    }

    public int getEquipType() {
        return equipType;
    }

    public double getCardTime() {
        return cardTime;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public List<ProductCard> getRequiredProducts() {
        List<ProductCard> product1s = new ArrayList<>();
        for (var operation : operations) {
            product1s.addAll(operation.getRequiredProducts());
        }
        return product1s;
    }
}
