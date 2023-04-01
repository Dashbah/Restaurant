package restik.model;

import com.google.gson.annotations.SerializedName;

public class OperationType {
    @SerializedName("oper_type_id")
    private int operTypeId = 0;
    @SerializedName("oper_type_name")
    private String operTypeName = "";

    public OperationType() {
    }

    @Override
    public String toString() {
        return operTypeId + " " + operTypeName;
    }
}
