package restik.view;

public class TabloGotovnosty {
    public static void displayReadyTime(String orderId, Integer time) {
        System.out.println("Order " + orderId + " will be ready in " + time + " minutes");
    }
}
