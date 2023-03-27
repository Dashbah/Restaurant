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
    // List<Equipment> equipment;
    Map<Integer, EquipmentBox> equipmentMap;
    List<EquipmentType> equipmentTypes;
    List<OperationType> operationTypes;
    List<ProductType> productTypes;
    Map<Integer, Product> products;
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

            reserveProducts(dishCardsOrdered);

            TabloGotovnosty.displayReadyTime(visitorsOrder.getVisName(), agentOrder.countMinTime());

            new Kitchen(cookers.size(), cookers, equipmentMap);

            for (var dish : agentOrder.getVis_ord_dishes()) {
                new Thread(new Kitchen.DishThread(dish)).start();
                Thread.sleep(400);
            }
        }
    }

    private void reserveProducts(List<DishCard> dishCards) {
        for (var dishCard : dishCards) {
            for (var product : dishCard.getRequiredProducts()) {
                // products.get(product.getProd_type()).num -= //
                products.get(product.getProd_type()).take(product.getProd_quantity());
            }
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

        var equipmentList = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/equipment.json",
                Equipment[].class)));
        equipmentMap = new HashMap<>();
        for (var eq : equipmentList) {
            if (equipmentMap.containsKey(eq.getEquip_type())) {
                equipmentMap.get(eq.getEquip_type()).add(eq);
            } else {
                equipmentMap.put(eq.getEquip_type(), new EquipmentBox(eq));
            }
        }

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

        var productsList =List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/products.json",
                Product[].class)));
        products = new HashMap<>();
        for (var product : productsList) {
            products.put(product.getProd_item_type(), product);
        }

        visitorsOrders = List.of(Objects.requireNonNull(Deserializer.Deserialize("src/main/resources/input/visitors_orders.json",
                VisitorOrder[].class)));
    }
}
