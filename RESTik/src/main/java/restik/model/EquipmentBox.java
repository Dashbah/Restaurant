package restik.model;

import restik.controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class EquipmentBox {
    private final Logger log = Logger.getLogger(Controller.class.getName());
    private final Object lock = new Object();
    private final List<Equipment> equipmentList = new ArrayList<>();
    private Integer counter = 0;

    public EquipmentBox(Equipment equipment){
        equipmentList.add(equipment);
        counter = 1;

        try {
            FileHandler fh = new FileHandler("equipmentBox.log");
            log.addHandler(fh);
        } catch (IOException e) {
            log.info("error while creating log file" + e.getMessage());
        }
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
            log.info("equipment was taken");
            lock.notifyAll();
        }

        return equipment;
    }

    void put() {
        synchronized (lock) {
            //"Добавляем" значение в буфер и увеличиваем счетчик.
            ++counter;
            log.info("equipment was put");
            // System.out.println("equipment was put");
            lock.notifyAll();
        }
    }
}
