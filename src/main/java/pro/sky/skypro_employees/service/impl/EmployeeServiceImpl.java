package pro.sky.skypro_employees.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.exceptions.EmployeeAlreadyExists;
import pro.sky.skypro_employees.exceptions.EmployeeIsAbsent;
import pro.sky.skypro_employees.exceptions.InvalidArgumentException;
import pro.sky.skypro_employees.service.EmployeeService;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employee = new HashMap<>(Map.of(
            "Ivan0Ivanov",
            new Employee("Ivan0", "Ivanov", 1, 100000),
            "Ivan1Ivanov",
            new Employee("Ivan1", "Ivanov", 2, 100000),
            "Ivan2Ivanov",
            new Employee("Ivan2", "Ivanov", 1, 120000),
            "Ivan3Ivanov",
            new Employee("Ivan3", "Ivanov", 2, 100000),
            "Ivan4Ivanov",
            new Employee("Ivan4", "Ivanov", 2, 150000)
    ));

    public Map<String, Employee> employeeGetAll() {
        return Collections.unmodifiableMap(employee);
    }

    public List<Employee> employeeGetAllList() {
        return new ArrayList<Employee>(employee.values());
    }

    public Employee employeeAdd(String firstName, String lastName, int numberDepartment, double salary) {
        String firstNameForAdd = firstName;
        String lastNameForAdd = lastName;

        if (!StringUtils.isAlpha(firstNameForAdd) || (!StringUtils.isAlpha(lastNameForAdd)))
            throw new InvalidArgumentException();
        Employee employeeAdd;
        firstNameForAdd = StringUtils.capitalize(StringUtils.lowerCase(firstNameForAdd));
        lastNameForAdd = StringUtils.capitalize(StringUtils.lowerCase(lastNameForAdd));
        employeeAdd = new Employee(firstNameForAdd, lastNameForAdd, numberDepartment, salary);
        EmployeeAlreadyExists(employee, employeeAdd);
        employee.put(firstNameForAdd + lastNameForAdd, employeeAdd);
        System.out.println("Сотрудник " + firstNameForAdd + " " + lastNameForAdd + " добавлен.");
        return employeeAdd;
    }

    public Employee employeeFind(String firstName, String lastName, int numberDepartment, double salary) {
        Employee employeeFind = new Employee(firstName, lastName, numberDepartment, salary);
        EmployeeIsAbsent(employee, employeeFind);
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }

    public Employee employeeRemove(String firstName, String lastName, int numberDepartment, double salary) {
        Employee employeeRemove = new Employee(firstName, lastName, numberDepartment, salary);
        EmployeeIsAbsent(employee, employeeRemove);
        employee.remove(firstName + lastName);
        System.out.println("Сотрудник " + firstName + " " + lastName + " удален.");
        return employeeRemove;
    }

    public void employeeRemoveAll() {
        employee.clear();
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