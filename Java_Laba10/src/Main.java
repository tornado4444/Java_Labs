import java.util.*;

class VectorTask {
    public static Vector<Integer> createAndFillVector(int initialCapacity, int finalSize, int min, int max) {
        Vector<Integer> vector = new Vector<>(initialCapacity);
        Random random = new Random();

        System.out.println(" Vector з початковою ємністю: " + initialCapacity);
        System.out.println(finalSize + " елементами (від " + min + " до " + max + ")\n");

        for (int i = 0; i < finalSize; i++) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            vector.add(randomNumber);
        }

        return vector;
    }

    public static void printVector(Vector<Integer> vector, String message) {
        System.out.println(message);
        System.out.println(vector);
        System.out.println();
    }

    public static void replaceNegativeWithZero(Vector<Integer> vector) {
        System.out.println("Обхід колекції за допомогою ітератора.");
        System.out.println("Заміна від'ємних елементів на 0:");

        ListIterator<Integer> iterator = vector.listIterator();
        int replacedCount = 0;

        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element < 0) {
                System.out.println("Позиція " + (iterator.previousIndex()) + ": " + element + " → 0");
                iterator.set(0);
                replacedCount++;
            }
        }

        System.out.println("Замінено елементів: " + replacedCount + "\n");
    }

    public static void printVectorInfo(Vector<Integer> vector) {
        System.out.println("Вектор:");
        System.out.println("Поточна ємність: " + vector.capacity());
        System.out.println("Поточний розмір: " + vector.size());
        System.out.println();
    }
}

class Box {
    protected double width;
    protected double height;
    protected double depth;

    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public double getVolume() {
        return width * height * depth;
    }

    @Override
    public String toString() {
        return String.format("Box[%.1f x %.1f x %.1f, об'єм: %.2f]", width, height, depth, getVolume());
    }
}

class HeavyBox extends Box {
    private double weight;

    public HeavyBox(double width, double height, double depth, double weight) {
        super(width, height, depth);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("HeavyBox[%.1f x %.1f x %.1f, об'єм: %.2f, вага: %.2f кг]",
                width, height, depth, getVolume(), weight);
    }
}

class Truck {
    private String number;
    private String driverName;
    private double maxVolume;
    private double maxWeight;

    public Truck(String number, String driverName, double maxVolume, double maxWeight) {
        this.number = number;
        this.driverName = driverName;
        this.maxVolume = maxVolume;
        this.maxWeight = maxWeight;
    }

    public String getNumber() {
        return number;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    @Override
    public String toString() {
        return String.format("Вантажівка №%s (Водій: %s, Об'єм: %.2f м^3, Макс. вага: %.2f кг)",
                number, driverName, maxVolume, maxWeight);
    }
}

class TruckLoadingTask {

    public static ArrayList<HeavyBox> createBoxes() {
        ArrayList<HeavyBox> boxes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            double width = 0.5 + random.nextDouble() * 1.5;
            double height = 0.5 + random.nextDouble() * 1.5;
            double depth = 0.5 + random.nextDouble() * 1.5;
            double weight = 10 + random.nextDouble() * 90;

            boxes.add(new HeavyBox(width, height, depth, weight));
        }

        return boxes;
    }

    public static Vector<Truck> createTrucks() {
        Vector<Truck> trucks = new Vector<>();

        trucks.add(new Truck("AA1234BB", "Василевич Олександр", 15.0, 1500.0));
        trucks.add(new Truck("BC5678DE", "Игнасіо Варго", 20.0, 2000.0));
        trucks.add(new Truck("KH9012FG", "Андрій Зіг", 12.0, 1200.0));
        trucks.add(new Truck("DP3456HI", "Пономарьов Ілля", 18.0, 1800.0));

        return trucks;
    }

    public static void loadTruck(Truck truck, ArrayList<HeavyBox> boxes) {
        System.out.println("\n" + truck);

        double currentVolume = 0;
        double currentWeight = 0;
        int loadedBoxes = 0;

        for (int i = 0; i < boxes.size(); i++) {
            HeavyBox box = boxes.get(i);

            if (currentVolume + box.getVolume() <= truck.getMaxVolume() &&
                    currentWeight + box.getWeight() <= truck.getMaxWeight()) {

                currentVolume += box.getVolume();
                currentWeight += box.getWeight();
                loadedBoxes++;

                System.out.printf("Завантажено коробку #%d: %s\n", i + 1, box);
            }
        }

        System.out.printf("\nЗавантажено коробок: %d / %d\n", loadedBoxes, boxes.size());
        System.out.printf("Використано об'єму: %.2f / %.2f м³ (%.1f%%)\n",
                currentVolume, truck.getMaxVolume(),
                (currentVolume / truck.getMaxVolume()) * 100);
        System.out.printf("Використано ваги: %.2f / %.2f кг (%.1f%%)\n",
                currentWeight, truck.getMaxWeight(),
                (currentWeight / truck.getMaxWeight()) * 100);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Завдання 1.1");

        int initialCapacity = 10;
        int finalSize = 15;
        int min = -100;
        int max = 100;

        Vector<Integer> vector = VectorTask.createAndFillVector(initialCapacity, finalSize, min, max);
        VectorTask.printVector(vector, "Оригінальна колекція:");
        VectorTask.replaceNegativeWithZero(vector);
        VectorTask.printVector(vector, "Колекція після заміни:");
        VectorTask.printVectorInfo(vector);

        System.out.println("Завдання 1.2");

        Vector<Truck> trucks = TruckLoadingTask.createTrucks();
        ArrayList<HeavyBox> boxes = TruckLoadingTask.createBoxes();

        System.out.println("Створено " + boxes.size() + " коробок для перевезення");
        System.out.println("Доступно " + trucks.size() + " вантажівок");

        for (Truck truck : trucks) {
            TruckLoadingTask.loadTruck(truck, boxes);
        }
    }
}