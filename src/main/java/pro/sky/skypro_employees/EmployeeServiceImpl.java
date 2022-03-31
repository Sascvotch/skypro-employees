package pro.sky.skypro_employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employee = new ArrayList<>();

    public List<Employee> employeeListEmp() {
        return employee;

    }

    public Employee employeeAdd(String firstName, String lastName) {
        Employee employeeAdd = new Employee(firstName, lastName);

        for (int i = 0; i < employee.size(); i++) {
            if (employee.get(i).equals(employeeAdd)) {
                throw new EmployeeAlreadyExists();
            }
        }
        employee.add(employeeAdd);
        System.out.println("Сотрудник " + firstName + " " + lastName + " добавлен.");
        return employeeAdd;
    }

    public Employee employeeFind(String firstName, String lastName) {
        Boolean find = false;
        Employee employeeFind = new Employee(firstName, lastName);
        for (int i = 0; i < employee.size(); i++) {
            if (!find) {
                if (employee.get(i).equals(employeeFind)) {
                    find = true;
                }
            }
        }
        if (!find) {
            throw new EmployeeIsAbsent();
        }
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }

    public Employee employeeContains(String firstName, String lastName) {
        Boolean find = false;
        Employee employeeFind = new Employee(firstName, lastName);

        if (employee.contains(employeeFind)) {
            find = true;
        }


        if (!find) {
            throw new EmployeeIsAbsent();
        }
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }

    public Employee employeeRemove(String firstName, String lastName) {
        Boolean find = false;
        Employee employeeRemove = new Employee(firstName, lastName);
        for (int i = 0; i < employee.size(); i++) {
            if (!find) {
                if (employee.get(i).equals(employeeRemove)) {
                    employee.remove(i);
                    find = true;
                    System.out.println("Сотрудник " + firstName + " " + lastName + " удален.");
                }
            }
        }
        if (!find) {
            throw new EmployeeIsAbsent();
        }
        return employeeRemove;
    }

    @Override
    public String employeeList() {
        return employee.toString();
    }

}
