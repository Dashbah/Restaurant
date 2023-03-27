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
        static int numOfDishes = 0;
        private final DishCard dishCard;

        public DishThread(DishCard process) {
            ++numOfDishes;
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
                            System.out.println("Блюдо " + dishCard.getDish_name() + " выполняется поваром " +
                                    cookers.get(i).toString());
                            break;
                        }
                }

                //
                // process
                Cook();

                Thread.sleep(5000);       //Уходим за покупками, к примеру

                synchronized (COOKERS_IF_ACTIVE) {
                    COOKERS_IF_ACTIVE[cookerNumber] = false;//Освобождаем место
                }

                SEMAPHORE.release();
                System.out.println("Блюдо " + dishCard.getDish_name() + " приготовлено поваром " +
                        cookers.get(cookerNumber).toString());
            } catch (InterruptedException e) {
            }
        }

        void Cook() throws InterruptedException {
            var eqBox = equipmentMap.get(dishCard.getEquip_type());
            Equipment equipment;
            // take equipment
            synchronized (eqBox.lock) {
                while (eqBox.counter == 0) {
                    wait();
                }
                equipment = eqBox.equipmentList.get(--eqBox.counter);
                System.out.println(equipment.getEquip_name() + "was taken");
                eqBox.lock.notifyAll();
            }
            // process
            for (var operation : dishCard.getOperations()) {
                operation.activate();
            }
            synchronized (eqBox.lock) {
//                //Пока буфер полный, ждем
//                while (count == buffer.length) {
//                    lock.wait();
//                }
                //"Добавляем" значение в буфер и увеличиваем счетчик.
                ++eqBox.counter;
                // eqBox.add(equipment);
                // buffer[count++] = value;
                // System.out.println("Produced " + value);
                //Уведомляем другой поток, что можно продолжать работу.
                System.out.println(equipment.getEquip_name() + "was put");
                eqBox.lock.notifyAll();
            }
        }
    }
}

