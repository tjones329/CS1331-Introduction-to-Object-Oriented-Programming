import java.time.LocalDate;

public class EmployeeDemo1 {

    public static void main(String[] argss) {
        Employee1 emp = new Employee1("Robert Paulson",
                                     LocalDate.of(2018, 9, 24));
        System.out.println(emp.getName());
        System.out.println(emp.getHireDate());
    }
}
