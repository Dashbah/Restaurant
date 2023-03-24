package restik.model;

public class VisitorOrders {

    private String vis_name = "";
    private String vis_ord_started = "";
    private String vis_ord_ended = "";
    private int vis_ord_total = 0;

    MenuDishes[] menuDishes;



    public VisitorOrders() {
    }

    @Override
    public String toString() {
        return vis_name + " " + vis_ord_started + " " + vis_ord_ended
                + " " + vis_ord_total + " " + menuDishes ;
    }
}
