import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {
    private String name;
    private int platesAmount;
    private Plates plates;
    private Restaurant restaurant;
    public Client (String name, int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
//        while (this.platesAmount > 0) {
//         try {
//            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
//         } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//          }
//        }
        Deque<Plates> platesReceived = restaurant.receive(this.platesAmount);
        System.out.println("\u001B[36m Plates Taken by Client " + this.name + " " + platesReceived.toString().concat("\u001B[0m"));
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 4000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

