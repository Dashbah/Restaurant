package restik.model;

public class OperationType {
    private int oper_type_id = 0;
    private String oper_type_name = "";

    public OperationType() {
    }

    @Override
    public String toString() {
        return oper_type_id + " " + oper_type_name;
    }
}
