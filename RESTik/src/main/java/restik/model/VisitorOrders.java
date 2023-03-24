package restik.model;

import java.sql.Time;
import java.util.List;

public class VisitorOrders {
    private class VisitorOrder {
        private String vis_name;
        private Time vis_ord_started;
        private Time vis_ord_ended;
        private int vis_ord_total;

        // ????
        List<MenuDishes.MenuDish> vis_ord_dishes;

        public VisitorOrder() {
        }

        @Override
        public String toString() {
            return vis_name + " " + vis_ord_started + " " + vis_ord_ended
                    + " " + vis_ord_total + " " + vis_ord_dishes ;
        }
    }

    List<VisitorOrder> visitors_orders;
}
