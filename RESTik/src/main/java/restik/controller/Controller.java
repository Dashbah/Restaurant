package restik.controller;

import restik.Deserializer;
import restik.model.*;
import restik.view.TabloGotovnosty;
import restik.view.Terminal;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Контроллер приложения. Управляет работой системы обслуживания ресторана.
 */
public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());
    List<Cooker> cookers;
    Map<Integer, MenuDish> menuDishes;
    Map<Integer, DishCard> dishCards;
    Map<Integer, EquipmentBox> equipmentMap;
    List<EquipmentType> equipmentTypes;
    List<OperationType> operationTypes;
    List<ProductType> productTypes;
    Map<Integer, Product> products;
    List<VisitorOrder> visitorsOrders;
    Terminal terminal;

    /**
     * Конструктор класса Controller. Считывает JSON-файлы и создает объект терминала.
     */
    public Controller() {
        try {
            FileHandler fh = new FileHandler("fileRestik.log");
            log.addHandler(fh);
        } catch (IOException e) {
            log.info("error while creating log file" + e.getMessage());
        }

        try {
            readJsons();
        } catch (IOException e) {
            log.info("can't read JSONs \n" + e.getMessage());
            return;
        } catch (Exception ex) {
            log.info("Error while downloading data \n" + ex.getMessage());
            return;
        }

        terminal = new Terminal(menuDishes, dishCards);
        System.out.println(terminal);

        for (var visitorsOrder : visitorsOrders) {
            new Thread(new CookOrder(visitorsOrder)).start();
        }
    }

    /**
     * Внутренний класс CookOrder, представляющий собой поток для обработки заказа покупателя.
     */
    class CookOrder implements Runnable {
        VisitorOrder visitorsOrder;

        CookOrder(VisitorOrder visitorsOrder) {
            this.visitorsOrder = visitorsOrder;
        }

        @Override
        public void run() {
            List<DishCard> dishCardsOrdered = new ArrayList<>();
            for (Integer menuDishId : visitorsOrder.getVisOrdMenuDishesId()) {
                dishCardsOrdered.add(dishCards.get(menuDishes.get(menuDishId).getMenuDishCard()));
            }

            AgentOrder agentOrder = new AgentOrder(dishCardsOrdered);

            reserveProducts(dishCardsOrdered);

            TabloGotovnosty.displayReadyTime(visitorsOrder.getVisName(), agentOrder.countMinTime());

            try {
                new Kitchen(cookers.size(), cookers, equipmentMap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (var dish : agentOrder.getVisOrdDishes()) {
                new Thread(new Kitchen.DishThread(dish)).start();
            }
        }
    }

    private void reserveProducts(List<DishCard> dishCards) {
        for (var dishCard : dishCards) {
            for (var product : dishCard.getRequiredProducts()) {
                /*
                  if here we have an exception -> it means we don't have enough products
                   so -> if there is 0 products -> delete it from the menu
                   if less than 0 -> something strange happened -> delete the order
                 */
                products.get(product.getProdType()).take(product.getProdQuantity());
            }
        }
    }

    void readJsons() throws IOException {
        var resourceStream = getClass().getClassLoader().getResourceAsStream("input/cookers.json");
        var resourceStreamDishCards = getClass().getClassLoader().getResourceAsStream("input/dish_cards.json");
        var resourceStreamEquipment = getClass().getClassLoader().getResourceAsStream("input/equipment.json");
        var resourceStreamEquipmentType = getClass().getClassLoader().getResourceAsStream("input/equipment_type.json");
        var resourceStreamMenuDishes = getClass().getClassLoader().getResourceAsStream("input/menu_dishes.json");
        var resourceStreamOperationTypes = getClass().getClassLoader().getResourceAsStream("input/operation_types.json");
        var resourceStreamProductTypes = getClass().getClassLoader().getResourceAsStream("input/product_types.json");
        var resourceStreamProducts = getClass().getClassLoader().getResourceAsStream("input/products.json");
        var resourceStreamVisitorsOrders = getClass().getClassLoader().getResourceAsStream("input/visitors_orders.json");

        cookers = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStream,
                Cooker[].class)));

        var dishCardsList = List.of(Objects.requireNonNull(Deserializer.Deserialize(
                resourceStreamDishCards,
                DishCard[].class)));
        dishCards = new HashMap<>();
        for (var dishCard : dishCardsList) {
            dishCards.put(dishCard.getCardId(), dishCard);
        }

        var equipmentList = List.of(Objects.requireNonNull(Deserializer.Deserialize(
                resourceStreamEquipment,
                Equipment[].class)));
        equipmentMap = new HashMap<>();
        for (var eq : equipmentList) {
            if (equipmentMap.containsKey(eq.getEquipType())) {
                equipmentMap.get(eq.getEquipType()).add(eq);
            } else {
                equipmentMap.put(eq.getEquipType(), new EquipmentBox(eq));
            }
        }

        equipmentTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStreamEquipmentType,
                EquipmentType[].class)));

        var menuDishesList = List.of(Objects.requireNonNull(Deserializer.Deserialize(
                resourceStreamMenuDishes, MenuDish[].class)));
        menuDishes = new HashMap<>();
        for (var menuDish : menuDishesList) {
            menuDishes.put(menuDish.getMenuDishId(), menuDish);
        }

        operationTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStreamOperationTypes,
                OperationType[].class)));

        productTypes = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStreamProductTypes,
                ProductType[].class)));

        var productsList = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStreamProducts,
                Product[].class)));
        products = new HashMap<>();
        for (var product : productsList) {
            products.put(product.getProdItemType(), product);
        }

        visitorsOrders = List.of(Objects.requireNonNull(Deserializer.Deserialize(resourceStreamVisitorsOrders,
                VisitorOrder[].class)));
    }
}
