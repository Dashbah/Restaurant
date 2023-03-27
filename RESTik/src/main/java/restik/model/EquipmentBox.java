package restik.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentBox {
    final Object lock = new Object();
    List<Equipment> equipmentList = new ArrayList<>();
    Integer counter = 0;
    public EquipmentBox(Equipment equipment) {
        equipmentList.add(equipment);
        counter = 1;
    }
    public void add(Equipment equipment) {
        // check a lot of
        equipmentList.add(equipment);
        ++counter;
    }

    Equipment take() throws InterruptedException {
        Equipment equipment;
        synchronized (lock) {
            while (counter == 0) {
                lock.wait();
            }
            equipment = equipmentList.get(--counter);
            lock.notifyAll();
        }
        return equipment;
    }

    void put() {
        synchronized (lock) {
            //"Добавляем" значение в буфер и увеличиваем счетчик.
            ++counter;
            System.out.println("equipment was put");
            lock.notifyAll();
        }
    }
}
