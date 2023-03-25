package restik.model;

import java.sql.Time;
import java.util.List;

public class VisitorOrder {
        private String vis_name;
        private String vis_ord_started;
        private String vis_ord_ended;
        private int vis_ord_total;

        // ????
        List<MenuDish> vis_ord_dishes;

        public VisitorOrder() {
        }

        @Override
        public String toString() {
            return vis_name + " " + vis_ord_started + " " + vis_ord_ended
                    + " " + vis_ord_total + " " + vis_ord_dishes ;
        }
}
