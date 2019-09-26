import java.time.LocalDate;

public class HourlyEmployee1 extends Employee1 {

    private double hourlyWage;
    private double monthlyHours;

    public HourlyEmployee1(String name, LocalDate hireDate) {
        super(name, hireDate);
        // calls the constructor in Employee1 to
        // initialize HourlyEmployee instances

    }
}
