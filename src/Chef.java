import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class Chef implements Runnable {
    private String name;
    private int platesAmount;
    private Restaurant restaurant;
    private Deque<Plates> chefPlates;

    public Chef(String name, int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
        this.chefPlates = new ArrayDeque<>(platesAmount);
        createPlates();
    }

    @Override
    public void run() {
        while (!chefPlates.isEmpty()) {
            Plates plate = chefPlates.removeLast();
            System.out.println("\u001B[33mChef " + this.name + " sent a " + plate.toString().concat("\u001B[0m"));
            restaurant.send(plate);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPlates() {
        for(int i = 0;i< this.platesAmount;i++) {
            chefPlates.add(cookSomething());
        }
    }

    private Plates cookSomething() {
        return switch ((int) (Math.random() * 6 + 1)) {
            case 1: yield Plates.RICE;
            case 2: yield Plates.PASTA;
            case 3: yield Plates.BEEF;
            case 4: yield Plates.FISH;
            case 5: yield Plates.SOUP;
            default: yield Plates.DESERT;
        };
    }
}

