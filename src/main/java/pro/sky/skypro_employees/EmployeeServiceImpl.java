package pro.sky.skypro_employees;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map <String,Employee> employee = new HashMap<>();
    int size=0;
    int i=0;

    public Employee employeeAdd ( String firstName, String lastName){
        Employee employeeAdd= new Employee(firstName,lastName);
        if (employee.containsKey(firstName+lastName)){
            throw new EmployeeAlreadyExists();
        }
        employee.put(firstName+lastName,employeeAdd);
        System.out.println("Сотрудник " + firstName + " " + lastName + " добавлен.");
        return employeeAdd;
    }

    public Employee employeeFind (String firstName, String lastName) {
        Employee employeeFind=new Employee(firstName, lastName);
        if (!employee.containsKey(firstName+lastName)) {
            throw new EmployeeIsAbsent();
        }
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }


    public Employee employeeRemove (String firstName, String lastName) {
        Employee employeeRemove=new Employee(firstName, lastName);
        if (!employee.containsKey(firstName+lastName)) {
            throw new EmployeeIsAbsent();
        }
        employee.remove(firstName+lastName);
        System.out.println("Сотрудник " + firstName + " " + lastName + " удален.");
        return employeeRemove ;
    }


}
