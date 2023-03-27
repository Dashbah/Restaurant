package restik.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Kitchen {
    private static int numOfCookers = 0;
    private static boolean[] COOKERS_IF_ACTIVE;
    private static List<Cooker> cookers;
    private static Map<Integer, EquipmentBox> equipmentMap;
    private static Semaphore SEMAPHORE;

    public Kitchen(Integer numOfCookers1, List<Cooker> cookers1, Map<Integer, EquipmentBox> equipmentMap1) {
        numOfCookers = numOfCookers1;
        COOKERS_IF_ACTIVE = new boolean[numOfCookers];
        SEMAPHORE = new Semaphore(numOfCookers, true);
        cookers = cookers1;
        equipmentMap = equipmentMap1;
    }

    public static class DishThread implements Runnable {
        private final DishCard dishCard;

        public DishThread(DishCard process) {
            this.dishCard = process;
        }

        @Override
        public void run() {
            try {
                SEMAPHORE.acquire();

                int cookerNumber = -1;

                synchronized (COOKERS_IF_ACTIVE) {
                    for (int i = 0; i < numOfCookers; i++)
                        if (!COOKERS_IF_ACTIVE[i]) {
                            COOKERS_IF_ACTIVE[i] = true;
                            cookerNumber = i;         //Наличие свободного места, гарантирует семафор
                            break;
                        }
                }

                System.out.println("Блюдо " + dishCard.getDish_name() + " выполняется поваром " +
                        cookers.get(cookerNumber).toString());

                Cook();

                synchronized (COOKERS_IF_ACTIVE) {
                    COOKERS_IF_ACTIVE[cookerNumber] = false; // Освобождаем место
                }

                SEMAPHORE.release();
                System.out.println("Блюдо " + dishCard.getDish_name() + " приготовлено поваром " +
                        cookers.get(cookerNumber).toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void Cook() throws InterruptedException {
            // Thread.sleep(2000);
            var eqBox = equipmentMap.get(dishCard.getEquip_type());

            Equipment equipment = eqBox.take();
            for (var operation : dishCard.getOperations()) {
                System.out.println("working with " + equipment.getEquip_name() + " equipment");
                operation.activate();
            }
            eqBox.put();
        }
    }
}

