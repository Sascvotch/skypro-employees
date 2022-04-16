package pro.sky.skypro_employees;

import java.util.List;

public interface EmployeeService {
    Employee employeeAdd (String firstName, String lastName);
    Employee employeeFind (String firstName, String lastName);
    Employee employeeContains (String firstName, String lastName);
    Employee employeeRemove (String firstName, String lastName);
    String employeeList();
    List <Employee> employeeListEmp();
}
