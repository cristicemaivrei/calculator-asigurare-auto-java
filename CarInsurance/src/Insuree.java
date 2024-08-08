public class Insuree {
    private String name;
    private int age;
    private int accidentCount;
    private int yearsDriving;

    public Insuree(String name, int age, int accidentCount, int yearsDriving) {
        this.name = name;
        this.age = age;
        this.accidentCount = accidentCount;
        this.yearsDriving = yearsDriving;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getAccidentCount() {
        return accidentCount;
    }

    public int getYearsDriving() {
        return yearsDriving;
    }
}
