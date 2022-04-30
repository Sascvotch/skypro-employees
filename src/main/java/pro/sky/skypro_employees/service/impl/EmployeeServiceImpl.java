package pro.sky.skypro_employees.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.exceptions.EmployeeAlreadyExists;
import pro.sky.skypro_employees.exceptions.EmployeeIsAbsent;
import pro.sky.skypro_employees.exceptions.InvalidArgumentException;
import pro.sky.skypro_employees.service.EmployeeService;

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

    public Employee employeeAdd(String firstName, String lastName, int numberDepartment, double salary)  {
       String firstNameForAdd =firstName;
       String lastNameForAdd=lastName;
        if (!StringUtils.isAlpha(firstNameForAdd)||(!StringUtils.isAlpha(lastNameForAdd))) throw new InvalidArgumentException();
        Employee employeeAdd;
        firstNameForAdd=StringUtils.capitalize(StringUtils.lowerCase(firstNameForAdd));
        lastNameForAdd=StringUtils.capitalize(StringUtils.lowerCase(lastNameForAdd));
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

    private Map<String, Employee> employeeSalaryMore(double limitSalary) {
        Map<String, Employee> employeeSalaryMore = employee.entrySet().stream()
                .filter(e -> e.getValue().getSalary() > limitSalary)
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return employeeSalaryMore;
    }

    private Map<String, Employee> employeeSalaryLess(double limitSalary) {
        Map<String, Employee> employeeSalaryLess = employee.entrySet().stream()
                .filter(e -> e.getValue().getSalary() < limitSalary)
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return employeeSalaryLess;
    }

    public Map<String, Employee> indexingSalary(double indexSalary) {
        Map<String, Employee> indexingSalary = employee.entrySet().stream()
                .peek(e -> e.getValue().setSalary(e.getValue().getSalary() * (1 + indexSalary)))
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return indexingSalary;
    }

    public Long countEmployeeDepartment(int numberDepartment) {
        Optional<Long> countEmployeeDepartment = Optional.of(employee.entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .count());
        return countEmployeeDepartment.orElseThrow();
    }

    public Double allSalaryDepartment(int numberDepartment) {
        Optional<Double> allSalaryDepartment = Optional.of(employee.entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .map(e -> e.getValue().getSalary())
                .reduce((double) 0, (a, b) -> a + b));
        return allSalaryDepartment.orElseThrow();
    }

    public Double averageSalaryDepartment(int numberDepartment) {
        Double averageSalaryDepartment = Double.valueOf(0);
        if (countEmployeeDepartment(numberDepartment) > 0) {
            averageSalaryDepartment = allSalaryDepartment(numberDepartment) / countEmployeeDepartment(numberDepartment);
        }
        return averageSalaryDepartment;
    }

    public Map<String, Employee> getEmployeeDepartment(int numberDepartment) {
        Map<String, Employee> employeeDetartment;
        if (numberDepartment > 0) {
            employeeDetartment = employee.entrySet().stream()
                    .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                    .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        } else {
            employeeDetartment = employee.entrySet().stream()
                    .sorted(Comparator.comparing(e -> e.getValue().getNumberDepartment()))
                    .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        }
        return employeeDetartment;
    }

    private String getFullName() {
        String getFullName = employee.entrySet().stream()
                .map(e -> e.getValue().getFirstName() + " " + e.getValue().getLastName())
                .findAny()
                .orElseThrow();
        return getFullName;
    }

    public Optional<Double> allSalary() {
        Optional<Double> allSalary = Optional.of(employee.entrySet().stream()
                .map(e -> e.getValue().getSalary())
                .reduce(Double.valueOf(0), (a, b) -> a + b));
        return allSalary;
    }

    public Map.Entry<String, Employee> minSalaryDepartment(int numberDepartment) {
        Optional<Map.Entry<String, Employee>> minSalaryDepartment = employee.entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .min(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return minSalaryDepartment.get();
    }

    public Map.Entry<String, Employee> maxSalaryDepartment(int numberDepartment) {
        Optional<Map.Entry<String, Employee>> maxSalaryDepartment = employee.entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .max(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return maxSalaryDepartment.get();
    }

    public Optional<Map.Entry<String, Employee>> minSalary() {
        Optional<Map.Entry<String, Employee>> minSalary = employee.entrySet().stream()
                .min(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return minSalary;
    }

    public Optional<Map.Entry<String, Employee>> maxSalary() {
        Optional<Map.Entry<String, Employee>> maxSalary = employee.entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return maxSalary;
    }
}