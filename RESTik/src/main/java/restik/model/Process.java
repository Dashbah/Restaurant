package restik.model;

import java.sql.Time;
import java.util.List;

public class Process {
    private int proc_id;
    private int ord_dish;
    private Time proc_started;
    private Time proc_ended;
    private boolean proc_active;
    List<Process> proc_operations;
}
