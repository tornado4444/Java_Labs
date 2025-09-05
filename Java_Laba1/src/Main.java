public class Main {
    public static void main(String[] argv){
        // 1.1 Завдання
        int i;
        char c = 'A';
        boolean f1 = false;
        boolean f2 = true;
        byte b = 1;
        // 1.2 Завдання
        System.out.println("Завдання 1.2");
        i = b;
        System.out.println("1) Значення i = " + i);
        f2 = f1;
        System.out.println("2) Значення f2 = " + f2);

        int min = 5; int max = 15;
        i = (int) ((Math.random() * (max - min)) + min);
        System.out.println("3) Результат: " + i);

        b = (byte) i;
        System.out.println("4) Значення b = " + b);

        // 1.3 Завдання
        System.out.println("Завдання 1.3");
        double y = Math.sin(Math.pow(i, 2)) + (Math.pow(Math.cos(i), 2) / (i + Math.sqrt(i)));
        System.out.println("Результат y = " + y);

        // 2.1 Завдання
        System.out.println("Завдання 2.1");
        String emoji1 = "\uD83D\uDE80";
        String emoji2 = "\uD83D\uDE02";
        String emoji3 = "\uD83D\uDE08";
        System.out.println(emoji1);
        System.out.println(emoji2);
        System.out.println(emoji3);

        // 2.2 Завдання
        System.out.println("Завдання 2.2");
        String text = "Лабораторна робота";
        String num = "1";
        System.out.println(text + " " + num);
    }
}