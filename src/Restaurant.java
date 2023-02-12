import java.util.Deque;

public class Restaurant {
    private Deque<Plates> deque;
    private int currentNumOfPlates;
    public Restaurant(Deque<Plates> deque) {
        this.deque = deque;
    }

    public synchronized void send(Plates plate) {
        while (currentNumOfPlates > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\u001B[32m [" +currentNumOfPlates+ " Current available dishes -->> " + this.deque.toString().concat("\u001B[0m"));
        this.deque.offerFirst(plate);
        currentNumOfPlates = this.deque.size();
        notifyAll();
    }

    public synchronized Plates receive() {
        while (currentNumOfPlates < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\u001B[32m [" +currentNumOfPlates+ " Current available dishes -->> " + this.deque.toString().concat("\u001B[0m"));
        Plates plate  = this.deque.removeLast();
        currentNumOfPlates = this.deque.size();
        notifyAll();
        return plate;
    }
}

