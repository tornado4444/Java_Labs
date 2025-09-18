import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        // 1.1 Завдання
        System.out.println("Завдання 1.1");
        double min = 0.0F;
        double max = 5.0F;
        System.out.print("Введіть кількість елементів в масиві: ");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        System.out.println("Масив чисел: ");
        float[] array = new float[count];
        double maxElem =  Double.MIN_VALUE;
        int lastMaxIndex = 0;
        double sum = 0.0F;
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)ThreadLocalRandom.current().nextDouble(min, max);
            System.out.println(array[i]);

            sum += array[i];

            if(array[i] >= maxElem) {
                maxElem = array[i];
                lastMaxIndex = i;
            }
        }

        System.out.printf("Останній найбільший елемент: %.5f (індекс: %d)%n", maxElem, lastMaxIndex);

        double average = sum / array.length;
        System.out.println("Середнє значення: " + average);
        // 1.2 Завдання
        System.out.println("Завдання 1.2");
        double startX = 1.0F;
        double endX = 3.0F;
        double step = 0.2F;

        System.out.println("x          | y");
        System.out.println("-".repeat(24));
        int pCount = (int)((endX - startX) / step) + 1;
        double[] xValues = new double[pCount];
        double[] yValues = new double[pCount];
        for (double x = startX; x <= endX; x += step) {
            double y = Math.cos(x) / Math.log(x);
            System.out.printf("%-10.1f | %-15.6f%n", x, y);
        }

        System.out.println();
        // 2.1 Завдання
        System.out.println("Завдання 2.1");
        System.out.print("Введіть кількість точок: ");
        int pointCount = scanner.nextInt();
        double[][] points = new double[pointCount][2];
        System.out.println("\nВведіть координати точок:");
        for (int i = 0; i < pointCount; i++) {
            System.out.printf("Точка %d:\n", i + 1);
            System.out.print("  x = ");
            points[i][0] = scanner.nextDouble();
            System.out.print("  y = ");
            points[i][1] = scanner.nextDouble();
        }

        System.out.println("Конкретні координати:");
        for (int i = 0; i < pointCount; i++) {
            System.out.printf("Точка %d: (%.2f, %.2f)\n",
                    i + 1, points[i][0], points[i][1]);
        }

        for (int i = 0; i < pointCount; i++) {
            points[i][1] = -points[i][1];
        }

        System.out.println("Відзеркальні координати (відносно осі X): ");
        for (int i = 0; i < pointCount; i++) {
            System.out.printf("Точка %d: (%.2f, %.2f)\n",
                    i + 1, points[i][0], points[i][1]);
        }
    }
}