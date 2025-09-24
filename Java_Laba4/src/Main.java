// 1.1 Завдання
class Point {
    private double x;
    private double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public void length(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}

class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle() {
        this(new Point(0, 0), new Point(1, 0), new Point(0, 1));
    }

    public Triangle(Point a, Point b, Point c) {
        setA(new Point(a));
        setB(new Point(b));
        setC(new Point(c));
    }

    public Triangle(Triangle t) {
        this.a = new Point(t.a);
        this.b = new Point(t.b);
        this.c = new Point(t.c);
    }

    public Point getA() { return new Point(a); }
    public void setA(Point a) { this.a = new Point(a); }

    public Point getB() { return new Point(b); }
    public void setB(Point b) { this.b = new Point(b); }

    public Point getC() { return new Point(c); }
    public void setC(Point c) { this.c = new Point(c); }

    public double getArea() {
        return Math.abs((a.getX() * (b.getY() - c.getY()) +
                b.getX() * (c.getY() - a.getY()) +
                c.getX() * (a.getY() - b.getY())) * 0.5);
    }

    public void length(double dx, double dy) {
        a.length(dx, dy);
        b.length(dx, dy);
        c.length(dx, dy);
    }

    public String toString() {
        return String.format("Довільний трикутник: A(%.1f,%.1f) B(%.1f,%.1f) C(%.1f,%.1f), Площа: %.2f",
                a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), getArea());
    }
}

// 2.1 Завдання
class BankCard {
    private String type;
    private String number;
    private String Date;
    private String cvv2;
    private String pin;

    private static int cardCount = 0;
    private static double balance;
    public BankCard() {
        this("Visa", "5673572941627342", "12/25", "908", "6271", 1000.0);
    }

    public BankCard(String type, String number, String Date, String cvv2, String pin, double balance) {
        setType(type);
        setNumber(number);
        setDate(Date);
        setCvv2(cvv2);
        setPin(pin);
        setBalance(balance);
        cardCount++;
    }

    public String getType() { return type; }
    public void setType(String type) {
        if (type.equals("Visa") || type.equals("Mastercard")) {
            this.type = type;
        } else {
            this.type = "Visa";
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number != null && number.length() == 16) {
            this.number = number;
        } else {
            this.number = "5673572941627342";
        }
    }

    public String getDate() {
        return Date;
    }
    public void setDate(String expiryDate) {
        if (expiryDate != null && expiryDate.length() == 5 && expiryDate.charAt(2) == '/') {
            this.Date = expiryDate;
        } else {
            this.Date = "12/25";
        }
    }

    public String getCvv2() { return cvv2; }
    public void setCvv2(String cvv2) {
        if (cvv2 != null && cvv2.length() == 3) {
            this.cvv2 = cvv2;
        } else {
            this.cvv2 = "412";
        }
    }

    public String getPin() { return pin; }
    public void setPin(String pin) {
        if (pin != null && pin.length() == 4) {
            this.pin = pin;
        } else {
            this.pin = "5934";
        }
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public static int getCardCount() { return cardCount; }
    public static void resetCardCount() { cardCount = 0; }

    public String toString() {
        String formattedNumber = number.substring(0, 4) + " " + number.substring(4, 8) + " " + number.substring(8, 12) + " " + number.substring(12, 16);
        return "Номер картки: " + formattedNumber + "\n" + "Придатна до: " + Date + "\n" + "Тип картки: " + type + "\n" + "Баланс: " + String.format("%.2f", balance) + " грн";
    }
}

// 2.2 Завдання
class BankClient {
    private String name;
    private String birthDate;
    private BankCard[] cards;
    private int cardCount;

    public BankClient() {
        this("Шевченко Д.Д.", "20/01/2005", 2);
    }

    public BankClient(String fullName, String birthDate, int maxCards) {
        setFullName(fullName);
        setBirthDate(birthDate);
        this.cards = new BankCard[maxCards];
        this.cardCount = 0;
    }

    public BankClient(BankClient other) {
        this.name = other.name;
        this.birthDate = other.birthDate;
        this.cards = new BankCard[other.cards.length];
        this.cardCount = other.cardCount;
        for (int i = 0; i < cardCount; i++) {
            this.cards[i] = new BankCard(other.cards[i].getType(), other.cards[i].getNumber(), other.cards[i].getDate(), other.cards[i].getCvv2(), other.cards[i].getPin(), other.cards[i].getBalance());
        }
    }

    public String getFullName() { return name; }
    public void setFullName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty()) {
            this.name = fullName;
        } else {
            this.name = "Невідомий клієнт";
        }
    }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) {
        if (birthDate != null && birthDate.length() == 10 && birthDate.charAt(2) == '/' && birthDate.charAt(5) == '/') {
            this.birthDate = birthDate;
        } else {
            this.birthDate = "20/01/2005";
        }
    }

    public BankCard[] getCards() {
        BankCard[] result = new BankCard[cardCount];
        for (int i = 0; i < cardCount; i++) {
            result[i] = new BankCard(cards[i].getType(), cards[i].getNumber(),
                    cards[i].getDate(), cards[i].getCvv2(),
                    cards[i].getPin(), cards[i].getBalance());
        }
        return result;
    }

    public void setCards(BankCard[] cards) {
        if (cards != null && cards.length <= this.cards.length) {
            this.cardCount = 0;
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] != null) {
                    addCard(cards[i]);
                }
            }
        }
    }

    public boolean addCard(BankCard card) {
        if (cardCount < cards.length && card != null) {
            cards[cardCount] = new BankCard(card.getType(), card.getNumber(),
                    card.getDate(), card.getCvv2(),
                    card.getPin(), card.getBalance());
            cardCount++;
            return true;
        }
        return false;
    }

    public boolean purchaseInStore(double amount, String pin) {
        for (int i = 0; i < cardCount; i++) {
            if (cards[i].getPin().equals(pin) && cards[i].getBalance() >= amount) {
                return cards[i].withdraw(amount);
            }
        }
        return false;
    }

    public boolean purchaseOnline(double amount, String cvv2) {
        for (int i = 0; i < cardCount; i++) {
            if (cards[i].getCvv2().equals(cvv2) && cards[i].getBalance() >= amount) {
                return cards[i].withdraw(amount);
            }
        }
        return false;
    }

    public String toString() {
        String result = "Клієнт: " + name + "\n" + "Дата народження: " + birthDate + "\n" + "Картки: ";
        if (cardCount == 0) {
            result += "немає карток";
        } else {
            for (int i = 0; i < cardCount; i++) {
                result += "\n" + cards[i].toString();
                if (i < cardCount - 1) {
                    result += ",";
                }
            }
        }
        return result;
    }
}

class AllTests {
    public static void main(String[] args) {
        System.out.println("Завдання 1.1"); // Для базового рівня
        System.out.println("1. Конструктор без параметрів: ");
        Triangle t1 = new Triangle();
        System.out.println(t1);

        System.out.println("\n2. Конструктор з параметрами:");
        Triangle t2 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4));
        System.out.println(t2);

        System.out.println("\n3. Конструктор копіювання:");
        Triangle t3 = new Triangle(t2);
        System.out.println(t3);

        System.out.println("\n4. set/get методи:");
        t1.setA(new Point(1, 1));
        t1.setB(new Point(4, 1));
        t1.setC(new Point(2, 5));
        System.out.println("Після зміни вершин: " + t1);
        System.out.println(" A: (" + t1.getA().getX() + ", " + t1.getA().getY() + ")");
        System.out.println("B: (" + t1.getB().getX() + ", " + t1.getB().getY() + ")");
        System.out.println("C: (" + t1.getC().getX() + ", " + t1.getC().getY() + ")");

        System.out.println("\n5. Обрахунок площі:");
        System.out.println("Площа трикутника t1: " + t1.getArea());
        System.out.println("Площа трикутника t2: " + t2.getArea());

        System.out.println("\n6. Переміщення трикутника:");
        System.out.println("До переміщення: " + t2);
        t2.length(2, 3);
        System.out.println("Після переміщення: " + t2);

        System.out.println("\n7. Перевірка збереження розмірів після переміщення:");
        Triangle original = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4));
        Triangle moved = new Triangle(original);
        System.out.println("Площа: " + original.getArea());
        moved.length(10, 15);
        System.out.println("Площа після переміщення: " + moved.getArea());
        System.out.println("Площі однакові: " + (Math.abs(original.getArea() - moved.getArea()) < 0.001));

        System.out.println("Завдання 1.2"); // Ускладнене завдання(рівень 1)
        System.out.println("1. Конструктор без параметрів: ");
        BankCard card1 = new BankCard();
        System.out.println(card1);
        System.out.println("\n2. Конструктор з параметрами:");
        BankCard card2 = new BankCard("Mastercard", "5555666677778888", "08/27", "456", "9876", 1500.0);
        System.out.println(card2);

        System.out.println("\n3. Тестування set/get методів:");
        card1.setType("Mastercard");
        card1.setNumber("4111222233334444");
        card1.setDate("03/26");
        card1.setCvv2("789");
        card1.setPin("5555");
        System.out.println("Після зміни полів:");
        System.out.println(card1);
        System.out.println("PIN: " + card1.getPin());
        System.out.println("CVV2: " + card1.getCvv2());

        System.out.println("\n4. Статичні методи та поля:");
        System.out.println("Кількість створених карток: " + BankCard.getCardCount());

        BankCard card3 = new BankCard("Visa", "4000111122223333", "11/28", "321", "0000", 500.0);
        System.out.println("Після створення ще однієї картки: " + BankCard.getCardCount());

        System.out.println("\n5. Тестування валідації:");
        BankCard invalidCard = new BankCard("InvalidType", "123", "invalid", "12", "12345", 3);
        System.out.println("Картка правильна саме:");
        System.out.println(invalidCard);
        System.out.println("Завдання 1.3"); // Ускладнене завдання(рівень 2)
        System.out.println("1. Конструктор без параметрів:");
        BankClient client1 = new BankClient();
        System.out.println(client1);

        System.out.println("\n2. Конструктор з параметрами:");
        BankClient client2 = new BankClient("Шевченко Д.Д.", "23/01/2001", 3);
        System.out.println(client2);

        System.out.println("\n3. Додавання карток:");
        client2.addCard(new BankCard("Visa", "4111222233334444", "05/26", "123", "1111", 3000.0));
        client2.addCard(new BankCard("Mastercard", "5555666677778888", "08/27", "456", "2222", 1500.0));
        System.out.println("Після додавання карток:");
        System.out.println(client2);

        System.out.println("\n4. Тестування конструктора копіювання:");
        BankClient client3 = new BankClient(client2);
        System.out.println("Скопійований клієнт:");
        System.out.println(client3);

        System.out.println("\n5. Тестування покупок в магазині:");
        System.out.println("Баланс до покупки: " + client2.getCards()[0].getBalance());
        boolean success = client2.purchaseInStore(500.0, "1111");
        System.out.println("Покупка на 500 грн з PIN 1111: " + (success ? "успішна" : "невдала"));
        System.out.println("Баланс після покупки: " + client2.getCards()[0].getBalance());

        System.out.println("\n6. Тестування покупок онлайн:");
        System.out.println("Баланс до покупки: " + client2.getCards()[1].getBalance());
        success = client2.purchaseOnline(200.0, "456");
        System.out.println("Онлайн покупка на 200 грн з CVV2 456: " + (success ? "успішна" : "невдала"));
        System.out.println("Баланс після покупки: " + client2.getCards()[1].getBalance());

        System.out.println("\n7. Тестування невдалих покупок:");
        success = client2.purchaseInStore(10000.0, "1111");
        System.out.println("Покупка на 10000 грн: " + (success ? "успішна" : "невдала"));

        success = client2.purchaseInStore(100.0, "9999");
        System.out.println("Покупка з неправильним PIN: " + (success ? "успішна" : "невдала"));
    }
}

