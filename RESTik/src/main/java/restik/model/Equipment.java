package restik.model;


import com.google.gson.annotations.SerializedName;

public class Equipment {
    @SerializedName("equip_type")
    private int equipType = 0;
    @SerializedName("equip_name")
    private String equipName = "";
    @SerializedName("equip_active")
    private boolean equipActive = false;

    public int getEquipType() {
        return equipType;
    }

    public String getEquipName() {
        return equipName;
    }
}

