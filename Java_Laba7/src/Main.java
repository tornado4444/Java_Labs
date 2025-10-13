// 1.1 Завдання
abstract class Vehicle {
    private String name;
    private int speed;
    private int weight;
    private int year;

    public Vehicle(){
        this("", 0, 0, 0);
    }

    public Vehicle(String name, int speed, int weight, int year) {
        this.name = name;
        this.speed = speed;
        this.weight = weight;
        this.year = year;
    }

    public String getName() {return name; }
    public void setName(String name) {
        this.name = name;
    }
    public int getSpeed() {return speed; }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getWeight() {return weight; }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getYear() {return year; }
    public void setYear(int year) {
        this.year = year;
    }

    public abstract void move();
    public abstract void stop();

    public void displayInfo() {
        System.out.println("Назва: " + name);
        System.out.println("Швидкість: " + speed + " км/год");
        System.out.println("Вага: " + weight + " кг");
        System.out.println("Рік: " + year);
    }
}

interface CargoCatable {
    void loadCargo(int cargoWeight);
    void unloadCargo();
    int getCargoCapacity();
}

interface PassengerCapable {
    void boardPassengers(int count);
    void disembarkPassengers();
    int getPassengerCapacity();
}

abstract class Machine extends Vehicle {
    private int wheels;

    public Machine() {
        super();
        this.wheels = 4;
    }

    public Machine(String name, int speed, int weight, int year, int wheels) {
        super(name, speed, weight, year);
        this.wheels = wheels;
    }

    public int getWheels() {return wheels;}
    public void setWheels(int wheels) {this.wheels = wheels;}

    @Override
    public void move() {
        System.out.println(getName() + " їде дорогою");
    }

    @Override
    public void stop() {
        System.out.println(getName() + " зупиняється");
    }
}

abstract class Railway extends Vehicle {
    private String trackType;

    public Railway() {
        super();
        this.trackType = "стандартна";
    }

    public Railway(String name, int speed, int weight, int year, String trackType) {
        super(name, speed, weight, year);
        this.trackType = trackType;
    }

    public String getTrackType() {return trackType;}
    public void setTrackType(String trackType) {this.trackType = trackType;}

    @Override
    public void move() {
        System.out.println(getName() + " їде рейками");
    }

    @Override
    public void stop() {
        System.out.println(getName() + " гальмує на станції");
    }
}

class Bus extends Machine implements PassengerCapable, CargoCatable {
    private int passengerCapacity;
    private int currentPassengers;
    private int cargoCapacity;
    private int currentCargo;

    public Bus() {
        super();
        this.passengerCapacity = 50;
        this.cargoCapacity = 500;
        this.currentPassengers = 0;
        this.currentCargo = 0;
    }

    public Bus(String name, int speed, int weight, int year, int wheels, int passengerCapacity, int cargoCapacity) {
        super(name, speed, weight, year, wheels);
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.currentPassengers = 0;
        this.currentCargo = 0;
    }

    @Override
    public void boardPassengers(int count) {
        if (currentPassengers + count <= passengerCapacity) {
            currentPassengers += count;
            System.out.println("Посадили " + count + " пасажирів. Всього: " + currentPassengers);
        } else {
            System.out.println("Недостатньо місць! Максимум: " + passengerCapacity);
        }
    }

    @Override
    public void disembarkPassengers() {
        System.out.println("Висадили " + currentPassengers + " пасажирів");
        currentPassengers = 0;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public void loadCargo(int cargoWeight) {
        if (currentCargo + cargoWeight <= cargoCapacity) {
            currentCargo += cargoWeight;
            System.out.println("Завантажили " + cargoWeight + " кг. Всього: " + currentCargo + " кг");
        } else {
            System.out.println("Перевантаження! Максимум: " + cargoCapacity + " кг");
        }
    }

    @Override
    public void unloadCargo() {
        System.out.println("Вивантажили " + currentCargo + " кг вантажу");
        currentCargo = 0;
    }

    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }
}

class Train extends Railway implements PassengerCapable, CargoCatable {
    private int passengerCapacity;
    private int currentPassengers;
    private int cargoCapacity;
    private int currentCargo;

    public Train() {
        super();
        this.passengerCapacity = 500;
        this.cargoCapacity = 5000;
        this.currentPassengers = 0;
        this.currentCargo = 0;
    }

    public Train(String name, int speed, int weight, int year, String trackType, int passengerCapacity, int cargoCapacity) {
        super(name, speed, weight, year, trackType);
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.currentPassengers = 0;
        this.currentCargo = 0;
    }

    @Override
    public void boardPassengers(int count) {
        if (currentPassengers + count <= passengerCapacity) {
            currentPassengers += count;
            System.out.println("Посадили " + count + " пасажирів. Всього: " + currentPassengers);
        } else {
            System.out.println("Недостатньо місць! Максимум: " + passengerCapacity);
        }
    }

    @Override
    public void disembarkPassengers() {
        System.out.println("Висадили " + currentPassengers + " пасажирів");
        currentPassengers = 0;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public void loadCargo(int cargoWeight) {
        if (currentCargo + cargoWeight <= cargoCapacity) {
            currentCargo += cargoWeight;
            System.out.println("Завантажили " + cargoWeight + " кг. Всього: " + currentCargo + " кг");
        } else {
            System.out.println("Перевантаження! Максимум: " + cargoCapacity + " кг");
        }
    }

    @Override
    public void unloadCargo() {
        System.out.println("Вивантажили " + currentCargo + " кг вантажу");
        currentCargo = 0;
    }

    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }
}

// 1.2 Завдання
interface Printable {
    void print();
}

class Book implements Printable {
    private String title;
    private String author;
    private int pages;
    private int year;

    public Book() {
        this("", "", 0, 0);
    }

    public Book(String title, String author, int pages, int year) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public int getPages() {return pages;}
    public void setPages(int pages) {this.pages = pages;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    @Override
    public void print() {
        System.out.println("Книга: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Сторінок: " + pages);
        System.out.println("Рік: " + year);
    }

    public static void printBooks(Printable[] printable) {
        System.out.println("КНИГИ");
        for (Printable item : printable) {
            if (item instanceof Book) {
                Book book = (Book)item;
                System.out.println(book.getTitle());
            }
        }
    }
}

class Magazine implements Printable {
    private String title;
    private int issue;
    private String month;
    private int year;

    public Magazine() {
        this("", 0, "", 0);
    }

    public Magazine(String title, int issue, String month, int year) {
        this.title = title;
        this.issue = issue;
        this.month = month;
        this.year = year;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public int getIssue() {return issue;}
    public void setIssue(int issue) {this.issue = issue;}

    public String getMonth() {return month;}
    public void setMonth(String month) {this.month = month;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    @Override
    public void print() {
        System.out.println("Журнал: " + title);
        System.out.println("Випуск: " + issue);
        System.out.println("Місяць: " + month);
        System.out.println("Рік: " + year);
    }

    public static void printMagazines(Printable[] printable) {
        System.out.println("ЖУРНАЛИ");
        for (Printable item : printable) {
            if (item instanceof Magazine) {
                Magazine magazine = (Magazine) item;
                System.out.println(magazine.getTitle());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("1.1 Завдання");
        Bus bus = new Bus("Богдан", 80, 5000, 2020, 4, 50, 500);
        Train train = new Train("Інтерсіті", 160, 500000, 2019, "швидкісна", 400, 3000);

        System.out.println("АВТОБУС!");
        bus.displayInfo();
        bus.move();
        bus.boardPassengers(30);
        bus.loadCargo(200);
        bus.stop();
        bus.disembarkPassengers();
        bus.unloadCargo();

        System.out.println("ПОТЯГ!");
        train.displayInfo();
        train.move();
        train.boardPassengers(250);
        train.loadCargo(2000);
        train.stop();
        train.disembarkPassengers();
        train.unloadCargo();
        System.out.println("\n1.2 Завдання");
        Printable[] library = new Printable[6];

        library[0] = new Book("Кобзар", "Тарас Шевченко", 320, 1840);
        library[1] = new Magazine("National Geographic", 5, "Травень", 2024);
        library[2] = new Book("Тіні забутих предків", "Михайло Коцюбинський", 80, 1911);
        library[3] = new Magazine("Forbes Україна", 3, "Березень", 2024);
        library[4] = new Book("Маруся", "Григорій Квітка-Основ'яненко", 120, 1834);
        library[5] = new Magazine("Vogue", 10, "Жовтень", 2024);

        for(Printable item : library) {
            item.print();
        }

        Book.printBooks(library);
        Magazine.printMagazines(library);
    }
}