import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class Chef implements Runnable {
    private String name;
    private int platesAmount;
    private Restaurant restaurant;

    public Chef(String name, int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
    }

    @Override
    public void run() {
//        while (this.platesAmount > 0) {
//            try {
//                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        Deque<Plates> platesToSend = platesCreated();
        System.out.println("\u001B[33mPlates sent by Chef " + this.name + " " + platesToSend.toString().concat("\u001B[0m"));
        restaurant.send(platesToSend);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Deque <Plates> platesCreated() {
        Deque <Plates> platesToSend = new ArrayDeque<>(this.platesAmount);
        for(int i = 0;i< this.platesAmount;i++) {
            platesToSend.add(cookSomething());
        }
        return platesToSend;
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

