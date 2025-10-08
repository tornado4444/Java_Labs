import java.util.Scanner;

abstract class MeansComn {
    private String name;
    private String provider;
    private boolean isAvailable;
    private double costPerUse;

    public MeansComn() {
        this("", "", false, 0.0F);
    }

    public MeansComn(String name, String provider, boolean isAvailable, double costPerUse) {
        setName(name);
        setProvider(provider);
        setIsAvailable(isAvailable);
        setCostPerUse(costPerUse);
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getProvider() {return provider;}
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public boolean getIsAvailable() {return isAvailable;}
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public double getCostPerUse() {return costPerUse;}
    public void setCostPerUse(double costPerUse) {
        this.costPerUse = costPerUse;
    }

    public void printInfo() {
        System.out.println("Назва засобу зв'язку: " + name);
        System.out.println("Компанія чи оператор: " + provider);
        System.out.println("Чи доступний зараз: " + ((isAvailable) ? "Так" : "Ні"));
        System.out.println("Вартість одного використання: " + costPerUse);
    }

    public abstract void communicate();
}

class Phone extends MeansComn {
    private String phoneNumber;

    public Phone(String name, String provider, boolean isAvailable, double costPerUse, String phoneNumber) {
        super(name, provider, isAvailable, costPerUse);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Номер телефону: " + phoneNumber);
    }

    @Override
    public void communicate() {
        System.out.println("Здійснюється дзвінок по телефону на номер " + phoneNumber);
    }
}

class Email extends MeansComn {
    private String emailAddress;

    public Email(String name, String provider, boolean isAvailable, double costPerUse, String emailAddress) {
        super(name, provider, isAvailable, costPerUse);
        this.emailAddress = emailAddress;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Email адреса: " + emailAddress);
    }

    @Override
    public void communicate() {
        System.out.println("Відправка повідомлення на email: " + emailAddress);
    }
}

class Internet extends MeansComn {
    private String connectionType;

    public Internet(String name, String provider, boolean isAvailable, double costPerUse, String connectionType) {
        super(name, provider, isAvailable, costPerUse);
        this.connectionType = connectionType;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Тип з'єднання: " + connectionType);
    }

    @Override
    public void communicate() {
        System.out.println("З'єднання через інтернет, тип: " + connectionType);
    }
}

// 2.1 Завдання
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class Reader {
    private String fullName;
    private String cardNumber;
    private String birthDate;
    private String phone;

    public Reader(String fullName, String cardNumber, String birthDate, String phone) {
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void takeBook(int count) {
        System.out.println(fullName + " взяв " + count + " книг(и).");
    }

    public void takeBook(String... bookNames) {
        System.out.print(fullName + " взяв книги: ");
        System.out.println(String.join(", ", bookNames));
    }

    public void takeBook(Book... books) {
        System.out.print(fullName + " взяв книги: ");
        for (int i = 0; i < books.length; i++) {
            System.out.print(books[i].getTitle() + " (" + books[i].getAuthor() + ")");
            if (i < books.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public void returnBook(int count) {
        System.out.println(fullName + " повернув " + count + " книг(и).");
    }

    public void returnBook(String... bookNames) {
        System.out.print(fullName + " повернув книги: ");
        System.out.println(String.join(", ", bookNames));
    }
    
    public void returnBook(Book... books) {
        System.out.print(fullName + " повернув книги: ");
        for (int i = 0; i < books.length; i++) {
            System.out.print(books[i].getTitle() + " (" + books[i].getAuthor() + ")");
            if (i < books.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        // 1.1 Завдання
        System.out.println("1.1 Завдання");
        MeansComn[] arr = new MeansComn[3];
        arr[0] = new Phone("Телефон", "Vodafone", true, 0.5, "+380501234567");
        arr[1] = new Email("Електронна пошта", "Gmail", false, 0.0, "kondradelapobando@gmail.com");
        arr[2] = new Internet("Широкосмуговий інтернет", "Kyivstar", true, 0.2, "Wi-Fi");

        for(MeansComn m : arr) {
            m.printInfo();
            m.communicate();
            System.out.println();
        }

        // 2.1 Завдання
        System.out.println("2.1 Завдання");
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть кількість читачів: ");
        int n = sc.nextInt();
        sc.nextLine();

        Reader[] readers = new Reader[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nЧитач №" + (i + 1));
            System.out.print("ПІБ: ");
            String name = sc.nextLine();
            System.out.print("Номер квитка: ");
            String card = sc.nextLine();
            System.out.print("Дата народження: ");
            String birth = sc.nextLine();
            System.out.print("Телефон: ");
            String phone = sc.nextLine();

            readers[i] = new Reader(name, card, birth, phone);
        }

        Book b1 = new Book("Пригоди жовтого чемоданчика", "Г. Остер");
        Book b2 = new Book("Словник іноземних слів", "А. Мельничук");
        Book b3 = new Book("Енциклопедія домогосподарки", "І. Коваль");

        System.out.println("Взяття книг");
        for (int i = 0; i < n; i++) {
            System.out.println("Читач №" + (i + 1));
            readers[i].takeBook(3);
            readers[i].takeBook("Пригоди жовтого чемоданчика", "Словник іноземних слів", "Енциклопедія домогосподарки");
            readers[i].takeBook(b1, b2, b3);
            System.out.println();
        }

        System.out.println("Повернення книг");
        for (int i = 0; i < n; i++) {
            System.out.println("Читач №" + (i + 1));
            readers[i].returnBook(3);
            readers[i].returnBook("Пригоди жовтого чемоданчика", "Словник іноземних слів", "Енциклопедія домогосподарки");
            readers[i].returnBook(b1, b2, b3);
            System.out.println();
        }

        sc.close();
    }
}