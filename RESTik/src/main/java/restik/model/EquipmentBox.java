package restik.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentBox {
    // Integer equipmentType;
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
    public void get(Equipment equipment) {
        // check a lot of
        if (counter != 0) {
            equipmentList.remove(equipment);
            --counter;
        }
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
//                //Пока буфер полный, ждем
//                while (count == buffer.length) {
//                    lock.wait();
//                }
            //"Добавляем" значение в буфер и увеличиваем счетчик.
            ++counter;
            // eqBox.add(equipment);
            // buffer[count++] = value;
            // System.out.println("Produced " + value);
            //Уведомляем другой поток, что можно продолжать работу.

            // System.out.println(equipment.getEquip_name() + "was put");
            System.out.println("equipment was put");
            lock.notifyAll();
        }
    }
}
