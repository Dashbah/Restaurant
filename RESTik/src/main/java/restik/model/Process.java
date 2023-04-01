package restik.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.List;

public class Process {
    @SerializedName("proc_id")
    private int procId;
    @SerializedName("ord_dish")
    private int ordDish;
    @SerializedName("proc_started")
    private Time procStarted;
    @SerializedName("proc_ended")
    private Time procEnded;
    @SerializedName("proc_active")
    private boolean procActive;
    @SerializedName("proc_operations")
    private List<Process> procOperations;
}