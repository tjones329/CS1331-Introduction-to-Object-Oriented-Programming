import java.time.LocalDate;

public class Employee1 {

    private String name;
    private LocalDate hireDate;

    public Employee1(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public String getName(){
        return name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
}
