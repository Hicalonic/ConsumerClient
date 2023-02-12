import java.util.ArrayDeque;
import java.util.Deque;

public class Restaurant {
    private Deque<Plates> deque;
    private int currentNumOfPlates;
    boolean flag = true;

    public Restaurant(Deque<Plates> deque) {
        this.deque = deque;
    }

    public synchronized void send(Deque<Plates> plates) {
        while (currentNumOfPlates > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        plates.stream().forEach(e -> this.deque.offerFirst(e));
        System.out.println("\u001B[32m [ Current available dishes -->> " + this.deque.toString().concat("\u001B[0m"));
        currentNumOfPlates = this.deque.size();
        System.out.println(currentNumOfPlates);
        notifyAll();
    }

    public synchronized Deque<Plates> receive(int amount) {
        Deque<Plates> platesTaken = new ArrayDeque<>();
        while (currentNumOfPlates < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < amount; i++) {
            platesTaken.addFirst(this.deque.removeLast());
        }
        currentNumOfPlates = this.deque.size();
        notifyAll();
        return platesTaken;
    }
}

