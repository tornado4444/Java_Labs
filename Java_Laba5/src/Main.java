import java.util.Scanner;

// 1.1 Завдання
class Device {
    private int x;
    private int y;

    public Device() {
        this(0, 0);
    }

    public Device(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int calculate(int a) {
        return a * 2;
    }

    public int calculate(int a, int b) {
        return a + b;
    }
}

class Microprocessor extends Device {
    public Microprocessor() {
        super();
    }

    public Microprocessor(int x, int y) {
        super(x, y);
    }

    @Override
    public int calculate(int a) {
        return (int)Math.pow(a, 2);
    }

    @Override
    public int calculate(int a, int b) {
        return a * b;
    }
}

class Calculator extends Device {
    public Calculator() {
        super();
    }

    public Calculator(int x, int y) {
        super(x, y);
    }

    @Override
    public int calculate(int a) {
        return -a;
    }

    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}

class Computer extends Device {
    public Computer() {
        super();
    }

    public Computer(int x, int y) {
        super(x, y);
    }

    @Override
    public int calculate(int a) {
        return a / 2;
    }

    @Override
    public int calculate(int a, int b) {
        return (a + b) / 2;
    }
}

// 1.2 Завдання
class Student{
    protected String surname;
    protected String name;
    protected String group;
    protected int studyYear;
    protected double scholarShip;
    protected double ratingScore;

    public Student() {
        this("", "", "", 0, 0, 0);
    }

    public Student(String surname, String name, String group, int studyYear, double scholarShip, double ratingScore) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.studyYear = studyYear;
        this.scholarShip = scholarShip;
        this.ratingScore = ratingScore;
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public int getStudyYear() { return studyYear; }
    public void setStudyYear(int studyYear) { this.studyYear = studyYear; }
    public double getMinScholarship() { return scholarShip; }
    public void setMinScholarship(double minScholarship) { this.scholarShip = minScholarship; }
    public double getRatingScore() { return ratingScore; }
    public void setRatingScore(double ratingScore) { this.ratingScore = ratingScore; }

    public double getScolarship() {
        if(ratingScore >= 0 && ratingScore <= 60) {
            return scholarShip;
        }
        else if (ratingScore >= 61 && ratingScore <= 74) {
            return scholarShip * 1.2;
        }
        else if (ratingScore >= 75 && ratingScore <= 89) {
            return scholarShip * 1.35;
        }
        else if (ratingScore >= 90 && ratingScore <= 100) {
            return scholarShip * 1.5;
        }

        return 0;
    }

    public void printInfo() {
        System.out.println("Прізвище: " + surname);
        System.out.println("Ім'я: " + name);
        System.out.println("Група: " + group);
        System.out.println("Рік навчання: " + studyYear);
        System.out.println("Рейтинговий бал: " + ratingScore);
        System.out.println("Стипендія: " + scholarShip);
        System.out.println("Розрахована стипендія: " + getScolarship());
    }
}

class Bachelor extends Student {
    public Bachelor() {
        super();
        this.scholarShip = 1400;
    }

    public Bachelor(String surname, String name, String group, int studyYear, double ratingScore) {
        super(surname, name, group, studyYear, 1400, ratingScore);
    }
}

class Master extends Student {
    protected boolean hasScientificWorks;
    protected boolean hasBudgetPlace;

    public Master() {
        super();
        this.scholarShip = 1800;
        this.hasScientificWorks = false;
        this.hasBudgetPlace = false;
    }
    public Master(String surname, String name, String group, int studyYear, double ratingScore, boolean hasScientificWorks, boolean hasBudgetPlace) {
        super(surname, name, group, studyYear, 1800, ratingScore);
        this.hasScientificWorks = hasScientificWorks;
        this.hasBudgetPlace = hasBudgetPlace;
    }

    public boolean isHasScientificWorks() { return hasScientificWorks; }
    public void setHasScientificWorks(boolean hasScientificWorks) { this.hasScientificWorks = hasScientificWorks; }
    public boolean isHasBudgetPlace() { return hasBudgetPlace; }
    public void setHasBudgetPlace(boolean hasBudgetPlace) { this.hasBudgetPlace = hasBudgetPlace; }

    public double getScholarship() {
        double adjustedRating = ratingScore;
        if (hasScientificWorks) {
            adjustedRating = ratingScore * 1.1;
        }

        if (adjustedRating >= 0 && adjustedRating <= 60) {
            return scholarShip;
        } else if (adjustedRating >= 61 && adjustedRating <= 74) {
            return scholarShip * 1.2;
        } else if (adjustedRating >= 75 && adjustedRating <= 89) {
            return scholarShip * 1.35;
        } else if (adjustedRating >= 90) {
            return scholarShip * 1.5;
        }
        return 0;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Наукові роботи: " + (hasScientificWorks ? "Так" : "Ні"));
        System.out.println("Бюджетне місце: " + (hasBudgetPlace ? "Так" : "Ні"));
    }
}

class Aspirant extends Student {
    protected boolean hasScientificWorks;
    protected boolean hasBudgetPlace;

    public Aspirant() {
        super();
        this.scholarShip = 2500;
        this.hasScientificWorks = false;
        this.hasBudgetPlace = false;
    }

    public Aspirant(String surname, String name, String group, int studyYear, double ratingScore, boolean hasScientificWorks, boolean hasBudgetPlace) {
        super(surname, name, group, studyYear, 2500, ratingScore);
        this.hasScientificWorks = hasScientificWorks;
        this.hasBudgetPlace = hasBudgetPlace;
    }

    public boolean isHasScientificWorks() { return hasScientificWorks; }
    public void setHasScientificWorks(boolean hasScientificWorks) { this.hasScientificWorks = hasScientificWorks; }
    public boolean isHasBudgetPlace() { return hasBudgetPlace; }
    public void setHasBudgetPlace(boolean hasBudgetPlace) { this.hasBudgetPlace = hasBudgetPlace; }

    public double getScholarship() {
        double forRating = ratingScore;
        if (hasScientificWorks) {
            forRating = ratingScore * 1.1;
        }

        if (forRating >= 0 && forRating <= 60) {
            return scholarShip;
        } else if (forRating >= 61 && forRating <= 74) {
            return scholarShip * 1.2;
        } else if (forRating >= 75 && forRating <= 89) {
            return scholarShip * 1.35;
        } else if (forRating >= 90) {
            return scholarShip * 1.5;
        }
        return 0;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Наукові роботи: " + (hasScientificWorks ? "Так" : "Ні"));
        System.out.println("Бюджетне місце: " + (hasBudgetPlace ? "Так" : "Ні"));
    }
}

class Main {
    public static void main(String[] args) {
        // 1.1 Завдання
        System.out.println("1.1 Завдання");
        System.out.println("1) Device");
        Device device = new Device();
        System.out.println("calculate(5): " + device.calculate(5));
        System.out.println("calculate(5, 3): " + device.calculate(5, 3));

        System.out.println("2) Microprocessor");
        Microprocessor micro = new Microprocessor();
        System.out.println("calculate(10): " + micro.calculate(10));
        System.out.println("calculate(8, 4): " + micro.calculate(8, 4));

        System.out.println("3) Calculator");
        Calculator calc = new Calculator();
        System.out.println("calculate(7): " + calc.calculate(7));
        System.out.println("calculate(5, 7): " + calc.calculate(5, 7));

        System.out.println("4) Computer");
        Computer comp = new Computer();
        System.out.println("calculate(8): " + comp.calculate(8));
        System.out.println("calculate(2, 4): " + comp.calculate(2, 4));
        // 1.2 Завдання
        Scanner scanner = new Scanner(System.in);

        System.out.print("Кількість студентів: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Студент " + (i + 1));
            System.out.print("Виберіть тип студента (1-Бакалавр, 2-Магістр, 3-Аспірант):");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Прізвище: ");
            String surname = scanner.nextLine();
            System.out.print("Ім'я: ");
            String name = scanner.nextLine();
            System.out.print("Група: ");
            String group = scanner.nextLine();
            System.out.print("Рік навчання: ");
            int year = scanner.nextInt();
            System.out.print("Рейтинговий бал (0-100): ");
            double rating = scanner.nextDouble();
            scanner.nextLine();

            switch(type) {
                case 1:
                    students[i] = new Bachelor(surname, name, group, year, rating);
                    break;
                case 2:
                    System.out.print("Наявність наукових робіт (true/false): ");
                    boolean works = scanner.nextBoolean();
                    System.out.print("Наявність бюджетного місця (true/false): ");
                    boolean budget = scanner.nextBoolean();
                    scanner.nextLine();
                    students[i] = new Master(surname, name, group, year, rating, works, budget);
                    break;
                case 3:
                    System.out.print("Наявність наукових робіт (true/false): ");
                    boolean works1 = scanner.nextBoolean();
                    System.out.print("Наявність бюджетного місця (true/false): ");
                    boolean budget1 = scanner.nextBoolean();
                    scanner.nextLine();
                    students[i] = new Aspirant(surname, name, group, year, rating, works1, budget1);
                    break;
                default:
                    System.out.println("Ви ввели неправильне значення!");
                    break;
            }
        }

        System.out.println("ІНФОРМАЦІЯ ПРО СТУДЕНТІВ");
        for (int i = 0; i < students.length; i++) {
            System.out.println("\nСтудент " + (i + 1) + ":");
            students[i].printInfo();
        }
    }
}