package restik.model;

import java.util.List;

public class ProductTypes {
    class ProductType {
        private int prod_type_id = 0;
        private String prod_type_name = "";
        private boolean prod_is_food = false;

        public ProductType() {
        }

        @Override
        public String toString() {
            return prod_type_id + " " + prod_type_name + " " + prod_is_food;
        }
    }

    List<ProductTypes> product_types;
}
