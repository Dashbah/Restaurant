package restik.controller;

import restik.Deserializer;
import restik.model.*;
import restik.model.Process;
import restik.view.TabloGotovnosty;
import restik.view.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Controller {
    List<Cooker> cookers;
    List<MenuDish> menuDishes;
    Map<Integer, DishCard> dishCards;
    List<Equipment> equipment;
    List<EquipmentType> equipmentTypes;
    List<OperationType> operationTypes;
    List<ProductType> productTypes;
    List<Product> products;
    List<VisitorOrder> visitorsOrders;
    List<AgentOrder> agentOrders;
    List<Process> processes;
    Terminal terminal;

    public Controller() throws InterruptedException {
        readJsons();
        terminal = new Terminal(menuDishes);
        for (var visitorsOrder : visitorsOrders) {
            List<DishCard> dishCardsOrdered = new ArrayList<>();
            for (Integer dishId : visitorsOrder.getVisOrdDishesCardId()) {
                dishCardsOrdered.add(dishCards.get(dishId));
            }

            AgentOrder agentOrder = new AgentOrder(dishCardsOrdered);
            agentOrders.add(agentOrder);
            TabloGotovnosty.displayReadyTime(visitorsOrder.getVisName(), agentOrder.countMinTime());

            // Cooker currentCooker;
            for (var dish : agentOrder.getVis_ord_dishes()) {
                // new Thread();
                for (var operation : dish.getOperations()) {
                    new Thread(new Cookers.ProcessThread(operation)).start();
                    Thread.sleep(400);
                }
            }

            //            var process = new Process(visitorsOrder);
            //            processes.add(process);
            // process.work(visitorsOrder);
            // reserve
        }
    }

    // Cookers.Cooker selectCooker() {
//        for (var cooker : cookers) {
//            if (!cooker.isCook_active()) {
//                cooker.setCook_active(true);
//                currentCooker = cooker;
//            }
//        }
   //  }
    void readJsons() {
        cookers = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/cookers.json",
                Cooker[].class)));

        var dishCardsList = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/dish_cards.json",
                DishCard[].class)));
        for (var dishCard : dishCardsList) {
            dishCards.put(dishCard.getCardId(), dishCard);
        }

        equipment = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/equipment.json",
                Equipment[].class)));
        equipmentTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/equipment_type.json",
                EquipmentType[].class)));
        menuDishes = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/menu_dishes.json",
                MenuDish[].class)));
        operationTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/operation_types.json",
                OperationType[].class)));
        productTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/product_types.json",
                ProductType[].class)));
        products = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/products.json",
                Product[].class)));
        visitorsOrders = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/visitors_orders.json",
                VisitorOrder[].class)));
    }
}
