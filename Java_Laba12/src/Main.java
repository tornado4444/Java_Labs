import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

class FileManager {
    private static void task1(Scanner scanner) {
        while (true) {
            System.out.println("Введіть шлях (або 'exit' для виходу): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            File file = new File(input);

            if (file.exists()) {
                if (file.isFile()) {
                    System.out.println(input + " - це файл");
                } else if (file.isDirectory()) {
                    System.out.println(input + " - це директорія");
                }
            } else {
                System.out.println("Шлях не існує");
            }
        }
    }

    private static void task2(Scanner scanner) {
        System.out.println("Введіть шлях до директорії:");
        String dirPath = scanner.nextLine();

        System.out.println("Введіть маску імені файла:");
        String mask = scanner.nextLine();

        File directory = new File(dirPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Директорія не існує");
            return;
        }

        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Директорія порожня");
            return;
        }

        String regex = maskToRegex(mask);
        Pattern pattern = Pattern.compile(regex);

        File[] matchedFiles = Arrays.stream(files)
                .filter(f -> f.isFile() && pattern.matcher(f.getName()).matches())
                .sorted((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()))
                .toArray(File[]::new);

        if (matchedFiles.length == 0) {
            System.out.println("Файлів, що відповідають масці, не знайдено");
            return;
        }

        for (int i = 0; i < matchedFiles.length; i++) {
            String oldName = matchedFiles[i].getName();
            String extension = "";
            int dotIndex = oldName.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = oldName.substring(dotIndex);
            }

            String newName = (i + 1) + extension;
            File newFile = new File(directory, newName);

            if (matchedFiles[i].renameTo(newFile)) {
                System.out.println(oldName + " -> " + newName);
            } else {
                System.out.println("Не вдалося перейменувати " + oldName);
            }
        }
    }

    private static String maskToRegex(String mask) {
        StringBuilder regex = new StringBuilder("^");
        for (char c : mask.toCharArray()) {
            if (c == '*') {
                regex.append(".*");
            } else if (c == '?') {
                regex.append(".");
            } else if ("[](){}+^$|\\".indexOf(c) >= 0) {
                regex.append("\\").append(c);
            } else {
                regex.append(c);
            }
        }
        regex.append("$");
        return regex.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Завдання 1.1");
        task1(scanner);

        System.out.println("\nЗавдання 2.1");
        task2(scanner);

        scanner.close();
    }
}