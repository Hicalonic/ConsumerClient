import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {
    private String name;
    private int platesAmount;
    private Restaurant restaurant;
    public Client (String name, int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
    }

    @Override
    public void run() {
        while (this.platesAmount > 0) {
            this.platesAmount--;
            Plates plateReceived = restaurant.receive();
            System.out.println("\u001B[36mClient " + this.name + " took a " + plateReceived.toString().concat("\u001B[0m"));
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2400));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

