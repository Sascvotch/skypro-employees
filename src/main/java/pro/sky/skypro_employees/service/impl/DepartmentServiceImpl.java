package pro.sky.skypro_employees.service.impl;

import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.service.DepartmentService;
import pro.sky.skypro_employees.service.EmployeeService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public DepartmentServiceImpl (EmployeeServiceImpl employeeService) {
        this.employeeService=employeeService;
    }
    private final Map<String, Employee> employee = employeeService.employeeGetAll();

    private Map<String, Employee> employeeSalaryMore(double limitSalary) {
        Map<String, Employee> employeeSalaryMore = employeeService.employeeGetAll().entrySet().stream()
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
        Optional<Map.Entry<String, Employee>> minSalaryDepartment = employeeService.employeeGetAll().entrySet().stream()
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
