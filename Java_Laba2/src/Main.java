import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Завдання 1.1");
        int min = 1;
        int max = 9999;
        int maxNumber = 0;
        System.out.println("Результат: ");

        for(int i = 0; i < 3; i++) {
            int num = (int)(Math.random() * (double)(max - min + 1) + (double)min);
            System.out.println(num);
            if (num == 0 || num > maxNumber) {
                maxNumber = num;
            }
        }

        System.out.println("Найбільше число: " + maxNumber);
        System.out.println("Завдання 1.2");
        System.out.print("Введіть текст: ");
        Scanner scanner = new Scanner(System.in);
        String text1 = scanner.nextLine();
        System.out.println("text1 = " + text1);
        String text2 = "Як справи?";
        System.out.println(text1.startsWith(text2) ? "ТАК" : "НІ");
        System.out.println("Завдання 2.1");
        System.out.print("Введіть додатні числа (0 для завершення): ");
        int posNum = 0;
        int sum = 0;
        int oddCount = 0;

        int num;
        do {
            num = scanner.nextInt();
            if (num > 0) {
                if (num % 2 == 0) {
                    posNum++;
                } else {
                    sum += num;
                    oddCount++;
                }
            }
        } while(num != 0);

        System.out.println("Кількість парних: " + posNum);
        System.out.println("Середнє непарних: " + (oddCount > 0 ? (double)sum / (double)oddCount : (double)0.0F));
        System.out.println("Завдання 2.2");
        System.out.print("Введіть текст: ");
        Scanner scanner1 = new Scanner(System.in);
        String text3 = scanner1.nextLine();
        int count = 0;
        System.out.println("text3 = " + text3);

        for(int i = 0; i < text3.length(); ++i) {
            if (text3.charAt(i) == '.') {
                count++;
            }
        }

        System.out.print("Кількість крапок: " + count);
    }
}