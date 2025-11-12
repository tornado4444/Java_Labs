import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Dates {
    public static void printClassDates(DayOfWeek... classDays) {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksAgo = today.minusWeeks(2);

        System.out.println("Дати занять з Java за останні 2 тижні");
        System.out.println("Дні занять: " + Arrays.toString(classDays));
        System.out.println();

        Set<DayOfWeek> classDaysSet = new HashSet<>(Arrays.asList(classDays));
        List<LocalDate> classDates = new ArrayList<>();

        for (LocalDate date = twoWeeksAgo; !date.isAfter(today); date = date.plusDays(1)) {
            if (classDaysSet.contains(date.getDayOfWeek())) {
                classDates.add(date);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy (EEEE)", new Locale("uk", "UA"));

        for (LocalDate date : classDates) {
            System.out.println(date.format(formatter));
        }
        System.out.println("\nВсього занять: " + classDates.size());
    }
}

enum TimeZone {
    WESTERN("GMT+0", 0),
    CENTRAL("GMT+1", 1),
    EASTERN("GMT+2", 2);

    private String name;
    private int offset;

    TimeZone(String name, int offset) {
        this.name = name;
        this.offset = offset;
    }

    public String getName() { return name; }
    public int getOffset() { return offset; }
}

class TVProgram {
    private String name;
    private LocalDateTime startTime;
    private int durationMinutes;
    private Set<TimeZone> regions;

    public TVProgram(String name, LocalDateTime startTime, int durationMinutes, TimeZone... regions) {
        this.name = name;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.regions = new HashSet<>(Arrays.asList(regions));
    }

    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return startTime.plusMinutes(durationMinutes); }
    public int getDuration() { return durationMinutes; }
    public Set<TimeZone> getRegions() { return regions; }

    public boolean overlaps(TVProgram other, TimeZone region) {
        if (!this.regions.contains(region) || !other.regions.contains(region)) {
            return false;
        }

        LocalDateTime thisStart = this.startTime;
        LocalDateTime thisEnd = this.getEndTime();
        LocalDateTime otherStart = other.startTime;
        LocalDateTime otherEnd = other.getEndTime();

        return thisStart.isBefore(otherEnd) && otherStart.isBefore(thisEnd);
    }

    public String toString(TimeZone region) {
        int offsetDiff = region.getOffset();
        LocalDateTime localTime = startTime.plusHours(offsetDiff);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return String.format("[%s] %s - %s (%d хв)",
                localTime.format(formatter),
                localTime.plusMinutes(durationMinutes).format(DateTimeFormatter.ofPattern("HH:mm")),
                name,
                durationMinutes);
    }
}

class TVScheduleEditor {
    private List<TVProgram> schedule;

    public TVScheduleEditor() {
        this.schedule = new ArrayList<>();
    }

    public boolean addProgram(TVProgram program) {
        for (TimeZone region : program.getRegions()) {
            for (TVProgram existing : schedule) {
                if (program.overlaps(existing, region)) {
                    System.out.println("ПОМИЛКА: Програма \"" + program.getName() +
                            "\" конфліктує з \"" + existing.getName() +
                            "\" у регіоні " + region.getName());
                    return false;
                }
            }
        }

        schedule.add(program);
        System.out.println("Програму \"" + program.getName() + "\" успішно додано!");
        return true;
    }

    public void printSchedule(TimeZone region) {
        System.out.println("Розклад програм для регіону: " + region.getName());

        List<TVProgram> regionPrograms = new ArrayList<>();
        for (TVProgram program : schedule) {
            if (program.getRegions().contains(region)) {
                regionPrograms.add(program);
            }
        }

        regionPrograms.sort(Comparator.comparing(TVProgram::getStartTime));

        if (regionPrograms.isEmpty()) {
            System.out.println("Немає запланованих програм для цього регіону.");
        } else {
            for (TVProgram program : regionPrograms) {
                System.out.println(program.toString(region));
            }
        }
    }

    public void printAllSchedules() {
        for (TimeZone region : TimeZone.values()) {
            printSchedule(region);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("ЗАВДАННЯ 1: Дати останніх занять\n");
        Dates.printClassDates(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);

        System.out.println("ЗАВДАННЯ 2: Редактор розкладу ТБ\n");

        TVScheduleEditor editor = new TVScheduleEditor();

        LocalDateTime baseTime = LocalDateTime.of(2025, 11, 12, 18, 0);

        editor.addProgram(new TVProgram("Новини", baseTime, 30, TimeZone.WESTERN, TimeZone.CENTRAL, TimeZone.EASTERN));
        editor.addProgram(new TVProgram("Серіал 'Друзі'", baseTime.plusMinutes(30), 45, TimeZone.WESTERN, TimeZone.CENTRAL));
        editor.addProgram(new TVProgram("Документальний фільм", baseTime.plusMinutes(30), 60, TimeZone.EASTERN));
        editor.addProgram(new TVProgram("Спортивні новини", baseTime.plusMinutes(75), 15, TimeZone.WESTERN, TimeZone.CENTRAL, TimeZone.EASTERN));
        editor.addProgram(new TVProgram("Фільм 'Інтерстеллар'", baseTime.plusMinutes(30), 120, TimeZone.WESTERN));

        editor.printAllSchedules();

        System.out.println("\nПриклад виведення розкладу для Центральної Європи:");
        editor.printSchedule(TimeZone.CENTRAL);
    }
}