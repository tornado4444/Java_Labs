import java.util.*;
import java.io.*;

// 1.1 Завдання
class NonCloneableObject {
    private String data;

    public NonCloneableObject(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
        this.age = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Вік повинен бути від 0 до 120, отримано: " + age);
        }
        this.age = age;
    }
}

class ExceptionDemonstrator {

    public static void demonstrateIndexOutOfBounds(int index) {
        System.out.println("1. IndexOutOfBoundsException");
        try {
            int[] numbers = {10, 20, 30, 40, 50};

            System.out.println("Масив: " + Arrays.toString(numbers));
            System.out.println("Розмір масиву: " + numbers.length);
            System.out.println("Спроба доступу до індексу: " + index);

            int value = numbers[index];
            System.out.println("Значення: " + value);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ПОМИЛКА: Індекс виходить за межі масиву!");
            System.out.println("Деталі: " + e.getMessage());
        }
    }

    public static void demonstrateIllegalArgument(String name, int age) {
        System.out.println("2. IllegalArgumentException");
        try {
            Person person = new Person(name);
            System.out.println("Створено об'єкт Person: " + person.getName());
            System.out.println("Спроба встановити вік: " + age);

            person.setAge(age);
            System.out.println("Вік успішно встановлено: " + person.getAge());

        } catch (IllegalArgumentException e) {
            System.out.println("ПОМИЛКА: Некоректний аргумент!");
            System.out.println("Деталі: " + e.getMessage());
        }
    }

    public static void demonstrateCloneNotSupported(String data) {
        System.out.println("3. CloneNotSupportedException");
        try {
            NonCloneableObject obj = new NonCloneableObject(data);
            System.out.println("Створено об'єкт з даними: " + obj.getData());
            System.out.println("Клас НЕ реалізує інтерфейс Cloneable");
            System.out.println("Спроба клонувати об'єкт");

            Object clonedObj = obj.getClass().getMethod("clone").invoke(obj);
            System.out.println("Клонування успішне");

        } catch (Exception e) {
            System.out.println("ПОМИЛКА: Клонування неможливе!");
            System.out.println("Причина: Клас не реалізує інтерфейс Cloneable");
        }
    }

    public static void result(int index, String name, int age, String data) {
        System.out.println("Демонстрація");

        demonstrateIndexOutOfBounds(index);
        demonstrateIllegalArgument(name, age);
        demonstrateCloneNotSupported(data);

        System.out.println("Всі демонстрації завершено!");
    }
}

// 1.2 Завдання
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class InvalidLegsException extends Exception {
    public InvalidLegsException(String message) {
        super(message);
    }
}

class Animal {
    private String name;
    private int age;
    private int legs;

    public Animal(String name, int age, int legs) throws InvalidAgeException, InvalidLegsException {
        this.name = name;

        if (age <= 0) {
            throw new InvalidAgeException("Невідповідний вік: " + age);
        }

        if (!isValidLegs(name, legs)) {
            throw new InvalidLegsException("Невідповідна кількість ніг для " + name + ": " + legs);
        }

        this.age = age;
        this.legs = legs;
    }

    private boolean isValidLegs(String animalName, int legs) {
        switch (animalName.toLowerCase()) {
            case "страус":
                return legs == 2;
            case "змія":
                return legs == 0;
            case "кінь":
                return legs == 4;
            default:
                return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getLegs() {
        return legs;
    }

    @Override
    public String toString() {
        return name + ", вік: " + age + ", ніг: " + legs;
    }
}

class AnimalFileProcessor {
    private int damagedRecords = 0;
    private int invalidAgeRecords = 0;
    private int invalidLegsRecords = 0;
    private int totalLegs = 0;
    private int validRecords = 0;

    public void processFile(String inputFile, String outputFile) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));

            writer.write("Обробка файлу тварин\n");

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                processLine(line, lineNumber, writer);
            }

            writeStatistics(writer);

            System.out.println("Обробка файлу завершена успішно!");
            System.out.println("Результати записано у файл: " + outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("ПОМИЛКА: Файл не знайдено - " + inputFile);
        } catch (IOException e) {
            System.out.println("ПОМИЛКА читання/запису: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("ПОМИЛКА закриття файлу: " + e.getMessage());
            }
        }
    }

    private void processLine(String line, int lineNumber, BufferedWriter writer) throws IOException {
        try {
            String[] parts = line.split(",");

            if (parts.length != 3) {
                damagedRecords++;
                writer.write("Рядок " + lineNumber + ": ПОШКОДЖЕНИЙ - " + line + "\n");
                return;
            }

            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            int legs = Integer.parseInt(parts[2].trim());

            Animal animal = new Animal(name, age, legs);

            validRecords++;
            totalLegs += legs;
            writer.write("Рядок " + lineNumber + ": ПРАВИЛЬНИЙ - " + animal + "\n");

        } catch (NumberFormatException e) {
            damagedRecords++;
            writer.write("Рядок " + lineNumber + ": ПОШКОДЖЕНИЙ (неправильний формат) - " + line + "\n");
        } catch (InvalidAgeException e) {
            invalidAgeRecords++;
            writer.write("Рядок " + lineNumber + ": НЕВІДПОВІДНИЙ ВІК - " + line + "\n");
        } catch (InvalidLegsException e) {
            invalidLegsRecords++;
            writer.write("Рядок " + lineNumber + ": НЕВІДПОВІДНА КІЛЬКІСТЬ НІГ - " + line + "\n");
        }
    }

    private void writeStatistics(BufferedWriter writer) throws IOException {
        writer.write("СТАТИСТИКА: \n");
        writer.write("Кількість пошкоджених записів: " + damagedRecords + "\n");
        writer.write("Кількість записів з невідповідним віком: " + invalidAgeRecords + "\n");
        writer.write("Кількість записів з невідповідною кількістю ніг: " + invalidLegsRecords + "\n");
        writer.write("Кількість правильних записів: " + validRecords + "\n");
        writer.write("Сумарна кількість ніг у правильних записах: " + totalLegs + "\n");
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("1.1 ЗАВДАННЯ");

        try {
            System.out.println("Введення даних.");
            System.out.print("Введіть індекс для доступу до масиву (0-4): ");
            int index = scan.nextInt();
            scan.nextLine();

            System.out.print("Введіть ім'я персони: ");
            String name = scan.nextLine();

            System.out.print("Введіть вік персони: ");
            int age = scan.nextInt();
            scan.nextLine();

            System.out.print("Введіть дані для об'єкта: ");
            String data = scan.nextLine();

            ExceptionDemonstrator.result(index, name, age, data);

        } catch (InputMismatchException e) {
            System.out.println("ПОМИЛКА: Введено некоректний тип даних!");
            System.out.println("Очікувалось число, але отримано текст.");
        } finally {
            System.out.println("Завдання 1.1 завершено.\n");
        }

        System.out.println("1.2 ЗАВДАННЯ");

        AnimalFileProcessor processor = new AnimalFileProcessor();
        processor.processFile("task1.txt", "result.txt");

        scan.close();
        System.out.println("\nПрограма завершена.");
    }
}