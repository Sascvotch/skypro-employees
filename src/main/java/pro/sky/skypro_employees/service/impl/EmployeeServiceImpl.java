package pro.sky.skypro_employees.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.exceptions.EmployeeAlreadyExists;
import pro.sky.skypro_employees.exceptions.EmployeeIsAbsent;
import pro.sky.skypro_employees.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employee = new HashMap<>();

    public Employee employeeAdd(String firstName, String lastName) {
        Employee employeeAdd = new Employee(firstName, lastName);
        EmployeeAlreadyExists(employee, employeeAdd);
        employee.put(firstName + lastName, employeeAdd);
        System.out.println("Сотрудник " + firstName + " " + lastName + " добавлен.");
        return employeeAdd;
    }

    public Employee employeeFind(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);
        EmployeeIsAbsent(employee, employeeFind);
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }

    public Employee employeeRemove(String firstName, String lastName) {
        Employee employeeRemove = new Employee(firstName, lastName);
        EmployeeIsAbsent(employee, employeeRemove);
        employee.remove(firstName + lastName);
        System.out.println("Сотрудник " + firstName + " " + lastName + " удален.");
        return employeeRemove;
    }

    public void EmployeeIsAbsent(Map employee, Employee currentEmployee) {
        if (!employee.containsKey(currentEmployee.getFirstName() + currentEmployee.getLastName())) {
            throw new EmployeeIsAbsent();
        }
    }

    public void EmployeeAlreadyExists(Map employee, Employee currentEmployee) {
        if (employee.containsKey(currentEmployee.getFirstName() + currentEmployee.getLastName())) {
            throw new EmployeeAlreadyExists();
        }
    }
}