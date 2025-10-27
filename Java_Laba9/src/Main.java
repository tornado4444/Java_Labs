// 1.1 Завдання
class GenericMatrix<Type> {
    public int searchKey(Type[][] M, int m, int n, Type key) {
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] != null && M[i][j].equals(key)) {
                    count++;
                }
            }
        }

        return count;
    }

    public void print(Type[][] M, int m, int n) {
        System.out.println("Матриця");

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(M[i][j] + "\t");
            }

            System.out.println();
        }
        System.out.println();
    }
}

// 1.2 Завдання
abstract class ClothingItem {
    protected String name;
    protected int mainWashTemp;

    public ClothingItem(String name, int mainWashTemp) {
        this.name = name;
        this.mainWashTemp = mainWashTemp;
    }

    public String getName() {
        return name;
    }

    public int getMainWashTemp() {
        return mainWashTemp;
    }

    public abstract void wash();

    @Override
    public String toString() {
        return name;
    }
}

interface PreWashable {
    int getPreWashTemp();
    void preWash();
}

class WhiteClothing extends ClothingItem implements PreWashable {
    private int preWashTemp;

    public WhiteClothing(String name, int preWashTemp, int mainWashTemp) {
        super(name, mainWashTemp);
        this.preWashTemp = preWashTemp;
    }

    @Override
    public int getPreWashTemp() {
        return preWashTemp;
    }

    @Override
    public void preWash() {
        System.out.println("Попереднє прання білої речі \"" + name +
                "\" при температурі " + preWashTemp + "°C");
    }

    @Override
    public void wash() {
        System.out.println("Основне прання білої речі \"" + name +
                "\" при температурі " + mainWashTemp + "°C");
    }
}

class ColoredClothing extends ClothingItem implements PreWashable {
    private int preWashTemp;

    public ColoredClothing(String name, int preWashTemp, int mainWashTemp) {
        super(name, mainWashTemp);
        this.preWashTemp = preWashTemp;
    }

    @Override
    public int getPreWashTemp() {
        return preWashTemp;
    }

    @Override
    public void preWash() {
        System.out.println("Попереднє прання кольорової речі \"" + name +
                "\" при температурі " + preWashTemp + "°C");
    }

    @Override
    public void wash() {
        System.out.println("Основне прання кольорової речі \"" + name +
                "\" при температурі " + mainWashTemp + "°C");
    }
}

class WoolClothing extends ClothingItem {

    public WoolClothing(String name, int mainWashTemp) {
        super(name, mainWashTemp);
    }

    @Override
    public void wash() {
        System.out.println("Делікатне прання вовняної речі \"" + name +
                "\" при температурі " + mainWashTemp + "°C");
        System.out.println("(без попереднього прання)");
    }
}

class WashingMachine<T extends ClothingItem> {
    private String model;

    public WashingMachine(String model) {
        this.model = model;
    }

    public void washItem(T item) {
        System.out.println("\nПральна машина: " + model);
        System.out.println("Прання речі: " + item.getName());

        if (item instanceof PreWashable) {
            PreWashable preWashItem = (PreWashable) item;
            System.out.println("\nЕТАП 1 - Попереднє прання:");
            preWashItem.preWash();
            System.out.println("\nЕТАП 2 - Основне прання:");
        } else {
            System.out.println("\nРЕЖИМ - Делікатне прання (вовна):");
        }

        item.wash();
        System.out.println("\nПрання завершено!\n");
    }

    public void washBatch(T[] items) {
        System.out.println("\nЗАПУСК ПАКЕТНОГО ПРАННЯ");
        System.out.println("Машина: " + model);
        System.out.println("Кількість речей: " + items.length + "\n");

        for (T item : items) {
            washItem(item);
        }

        System.out.println("ВСІ РЕЧІ ВИПРАНІ!\n");
    }

    public void printItemsInfo(T[] items) {
        System.out.println("\nІНФОРМАЦІЯ ПРО РЕЧІ:");
        for (T item : items) {
            System.out.print(item.getName() + " - ");

            if (item instanceof PreWashable) {
                PreWashable pw = (PreWashable) item;
                System.out.println("Попереднє: " + pw.getPreWashTemp() +
                        "°C, Основне: " + item.getMainWashTemp() + "°C");
            } else {
                System.out.println("Тільки основне прання: " +
                        item.getMainWashTemp() + "°C (вовна)");
            }
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("1.1 Завдання");
        System.out.println("Робота з Integer");
        GenericMatrix<Integer> intMatrix = new GenericMatrix<>();

        Integer[][] intM = {
                {1, 2, 3, 4},
                {5, 2, 7, 2},
                {9, 2, 11, 12}
        };

        int intRows = 3;
        int intCols = 4;
        Integer intKey = 2;

        intMatrix.print(intM, intRows, intCols);
        int intCount = intMatrix.searchKey(intM, intRows, intCols, intKey);
        System.out.println("Кількість входжень числа " + intKey + ": " + intCount);
        System.out.println();

        System.out.println("Робота з Double");
        GenericMatrix<Double> doubleMatrix = new GenericMatrix<>();

        Double[][] doubleM = {
                {1.5, 2.7, 3.14},
                {2.7, 5.0, 2.7},
                {8.9, 2.7, 10.1}
        };

        int doubleRows = 3;
        int doubleCols = 3;
        Double doubleKey = 2.7;

        doubleMatrix.print(doubleM, doubleRows, doubleCols);
        int doubleCount = doubleMatrix.searchKey(doubleM, doubleRows, doubleCols, doubleKey);
        System.out.println("Кількість входжень числа " + doubleKey + ": " + doubleCount);
        System.out.println();

        System.out.println("Робота з String");
        GenericMatrix<String> stringMatrix = new GenericMatrix<>();

        String[][] stringM = {
                {"Java", "C", "C++"},
                {"Python", "JavaScript", "Python"},
                {"Ruby", "Python", "Go"}
        };

        int stringRows = 3;
        int stringCols = 3;
        String stringKey = "Python";

        stringMatrix.print(stringM, stringRows, stringCols);
        int stringCount = stringMatrix.searchKey(stringM, stringRows, stringCols, stringKey);
        System.out.println("Кількість входжень слова \"" + stringKey + "\": " + stringCount);
        System.out.println();

        System.out.println("Робота з Character");
        GenericMatrix<Character> charMatrix = new GenericMatrix<>();

        Character[][] charM = {
                {'A', 'B', 'C', 'A'},
                {'D', 'A', 'F', 'G'},
                {'A', 'I', 'J', 'K'}
        };

        int charRows = 3;
        int charCols = 4;
        Character charKey = 'A';

        charMatrix.print(charM, charRows, charCols);
        int charCount = charMatrix.searchKey(charM, charRows, charCols, charKey);
        System.out.println("Кількість входжень символу '" + charKey + "': " + charCount);
        System.out.println("1.2 Завдання");
        System.out.println("РОБОТИЗОВАНА ПРАЛЬНА МАШИНА v2.0");
        System.out.println("Інтелектуальна система прання\n");

        System.out.println("ДЕМОНСТРАЦІЯ 1: ПРАННЯ БІЛИХ РЕЧЕЙ");

        WashingMachine<WhiteClothing> whiteMachine =
                new WashingMachine<>("WhiteWash Pro");

        WhiteClothing[] whiteItems = {
                new WhiteClothing("Біла сорочка", 40, 60),
                new WhiteClothing("Білі простирадла", 50, 90),
                new WhiteClothing("Біла футболка", 30, 40)
        };

        whiteMachine.printItemsInfo(whiteItems);
        whiteMachine.washBatch(whiteItems);

        System.out.println("ДЕМОНСТРАЦІЯ 2: ПРАННЯ КОЛЬОРОВИХ РЕЧЕЙ");

        WashingMachine<ColoredClothing> colorMachine =
                new WashingMachine<>("ColorCare 3000");

        ColoredClothing[] coloredItems = {
                new ColoredClothing("Червона куртка", 30, 40),
                new ColoredClothing("Сині джинси", 40, 60)
        };

        colorMachine.printItemsInfo(coloredItems);
        colorMachine.washBatch(coloredItems);
        ;
        System.out.println("ДЕМОНСТРАЦІЯ 3: ПРАННЯ ВОВНЯНИХ РЕЧЕЙ");

        WashingMachine<WoolClothing> woolMachine =
                new WashingMachine<>("WoolGentle 500");

        WoolClothing[] woolItems = {
                new WoolClothing("Вовняний светр", 30),
                new WoolClothing("Шерстяний шарф", 25),
                new WoolClothing("Вовняна кофта", 30)
        };

        woolMachine.printItemsInfo(woolItems);
        woolMachine.washBatch(woolItems);

        System.out.println("ДЕМОНСТРАЦІЯ 4: УНІВЕРСАЛЬНА МАШИНА");

        WashingMachine<ClothingItem> universalMachine =
                new WashingMachine<>("UniversalWash X1");

        ClothingItem[] mixedItems = {
                new WhiteClothing("Біла блузка", 35, 50),
                new ColoredClothing("Зелена футболка", 30, 40),
                new WoolClothing("Вовняні рукавички", 20)
        };

        universalMachine.printItemsInfo(mixedItems);
        universalMachine.washBatch(mixedItems);

    }
}