package restik.model;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Cookers {
    private static int numOfCookers = 0;
    private static boolean[] COOKERS_IF_ACTIVE;
    private static List<Cooker> cookers;
    private static Semaphore SEMAPHORE;

    Cookers(Integer numOfCookers1, List<Cooker> cookers1) {
        numOfCookers = numOfCookers1;
        COOKERS_IF_ACTIVE = new boolean[numOfCookers];
        SEMAPHORE = new Semaphore(numOfCookers, true);
        cookers = cookers1;
    }

    public static class ProcessThread implements Runnable {
        static int numOfProcess = 0;
        private Operation process;
//        private int proc_id;
//        private int ord_dish;
//        private Time proc_started;
//        private Time proc_ended;
//        private boolean proc_active;
//        List<restik.model.Process> proc_operations;

        public ProcessThread(Operation process) {
            ++numOfProcess;
            this.process = process;
        }

        @Override
        public void run() {
            try {
                SEMAPHORE.acquire();

                int cookerNumber = -1;

                //Ищем свободное место и паркуемся
                synchronized (COOKERS_IF_ACTIVE){
                    for (int i = 0; i < numOfCookers; i++)
                        if (!COOKERS_IF_ACTIVE[i]) {      //Если место свободно
                            COOKERS_IF_ACTIVE[i] = true;  //занимаем его
                            cookerNumber = i;         //Наличие свободного места, гарантирует семафор
                            System.out.printf("Процесс №%d выполняется поваром номер %d.\n", process.getOper_type(),
                                    cookers.get(i).getCookId());
                            break;
                        }
                }

                // process
                Thread.sleep(5000);       //Уходим за покупками, к примеру

                synchronized (COOKERS_IF_ACTIVE) {
                    COOKERS_IF_ACTIVE[cookerNumber] = false;//Освобождаем место
                }

                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Процесс №%d завершил работу\n", numOfProcess);
                System.out.printf("Процесс назывался №%d \n", process.getOper_type());
            } catch (InterruptedException e) {
            }
        }
    }
}
