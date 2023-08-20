import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}

public class ToyStore {
    private PriorityQueue<Toy> toysQueue;

    public ToyStore() {
        toysQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getFrequency(), t1.getFrequency()));
    }

    public void addToy(Toy toy) {
        toysQueue.offer(toy);
    }

    public void getToysAndWriteToFile(String filePath) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < 10; i++) {
                Toy toy = toysQueue.poll();
                if (toy != null) {
                    writer.write("Toy ID: " + toy.getId() + " - Toy Name: " + toy.getName() + "\n");
                }
            }

            writer.close();
            System.out.println("Results written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Пример добавления новых игрушек
        toyStore.addToy(new Toy(1, "Action Figure", 5));
        toyStore.addToy(new Toy(2, "Doll", 3));
        toyStore.addToy(new Toy(3, "Teddy Bear", 10));

        toyStore.getToysAndWriteToFile("output.txt");
    }
}