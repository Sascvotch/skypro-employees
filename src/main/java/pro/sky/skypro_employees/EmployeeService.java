package pro.sky.skypro_employees;

public interface EmployeeService {
    Employee employeeAdd (String firstName, String lastName);
    Employee employeeFind (String firstName, String lastName);
    Employee employeeRemove (String firstName, String lastName);
}
