package restik.controller;

import restik.Deserializer;
import restik.model.*;
import restik.model.Process;
import restik.view.TabloGotovnosty;
import restik.view.Terminal;

import java.util.*;

public class Controller {
    List<Cooker> cookers;
    Map<Integer, MenuDish> menuDishes;
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

        agentOrders = new ArrayList<>();
        for (var visitorsOrder : visitorsOrders) {
            List<DishCard> dishCardsOrdered = new ArrayList<>();
            for (Integer menuDishId : visitorsOrder.getVisOrdMenuDishesId()) {
                dishCardsOrdered.add(dishCards.get(menuDishes.get(menuDishId).getMenu_dish_card()));
            }

            AgentOrder agentOrder = new AgentOrder(dishCardsOrdered);
            agentOrders.add(agentOrder);
            TabloGotovnosty.displayReadyTime(visitorsOrder.getVisName(), agentOrder.countMinTime());

            new Cookers(cookers.size(), cookers);
            // Cooker currentCooker;
            // dish <-> operation
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

    void readJsons() {
        cookers = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/cookers.json",
                Cooker[].class)));

        var dishCardsList = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/dish_cards.json",
                DishCard[].class)));
        dishCards = new HashMap<>();
        for (var dishCard : dishCardsList) {
            dishCards.put(dishCard.getCardId(), dishCard);
        }

        equipment = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/equipment.json",
                Equipment[].class)));
        equipmentTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/equipment_type.json",
                EquipmentType[].class)));

        var menuDishesList = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/menu_dishes.json",
                MenuDish[].class)));
        menuDishes = new HashMap<>();
        for (var menuDish : menuDishesList) {
            menuDishes.put(menuDish.getMenu_dish_id(), menuDish);
        }

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
