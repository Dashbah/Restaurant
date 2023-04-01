package restik.model;

import com.google.gson.annotations.SerializedName;

public class Cooker {
    @SerializedName("cook_id")
    private int cookId = 0;
    @SerializedName("cook_name")
    private String cookName = "";

    @SerializedName("cook_active")
    private boolean cookActive = false;

    public Cooker() {
    }

    @Override
    public String toString() {
        return cookId + " " + cookName;
    }
}