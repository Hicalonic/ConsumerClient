import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        Deque<Plates> deque = new ArrayDeque<>();

        Restaurant restaurant = new Restaurant(deque);

        Thread chef = new Thread(new Chef("Robert",15, restaurant));
        Thread chef1 = new Thread(new Chef("Roger",15, restaurant));
        Thread client = new Thread(new Client("Sebastian",14, restaurant));
        Thread client1 = new Thread(new Client("Cornelio",16, restaurant));

        chef.start();
        chef1.start();
        client.start();
        client1.start();

    }
}
