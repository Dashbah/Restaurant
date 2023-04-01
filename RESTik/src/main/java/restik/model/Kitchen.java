package restik.model;

import restik.controller.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Kitchen {
    private static int numOfCookers = 0;
    private static boolean[] cookersIfActive;
    private static List<Cooker> cookers;
    private static Map<Integer, EquipmentBox> equipmentMap;
    private static Semaphore semaphore;

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    public Kitchen(Integer numOfCookers1, List<Cooker> cookers1, Map<Integer, EquipmentBox> equipmentMap1)
            throws IOException {
        numOfCookers = numOfCookers1;
        cookersIfActive = new boolean[numOfCookers];
        semaphore = new Semaphore(numOfCookers, true);
        cookers = cookers1;
        equipmentMap = equipmentMap1;

        FileHandler fh = new FileHandler("kitchen.log");
        log.addHandler(fh);
    }

    public static class DishThread implements Runnable {
        private final DishCard dishCard;

        public DishThread(DishCard process) {
            this.dishCard = process;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();

                int cookerNumber = -1;

                synchronized (cookersIfActive) {
                    for (int i = 0; i < numOfCookers; i++)
                        if (!cookersIfActive[i]) {
                            cookersIfActive[i] = true;
                            cookerNumber = i;         //Наличие свободного места, гарантирует семафор
                            break;
                        }
                }

                log.info("Блюдо " + dishCard.getDishName() + " выполняется поваром " +
                        cookers.get(cookerNumber).toString());

                cook();

                synchronized (cookersIfActive) {
                    cookersIfActive[cookerNumber] = false; // Освобождаем место
                }

                semaphore.release();
                log.info("Блюдо " + dishCard.getDishName() + " приготовлено поваром " +
                        cookers.get(cookerNumber).toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * Выполняет готовку блюда, используя оборудование, соответствующее типу блюда.
         * @throws InterruptedException если выполнение потока было прервано.
         */
        void cook() throws InterruptedException {
            var eqBox = equipmentMap.get(dishCard.getEquipType());

            Equipment equipment = eqBox.take();
            for (var operation : dishCard.getOperations()) {
                operation.activate();
            }
            eqBox.put();
        }
    }
}

