package pro.sky.skypro_employees.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.service.DepartmentService;
import pro.sky.skypro_employees.service.EmployeeService;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public DepartmentServiceImpl (EmployeeServiceImpl employeeService) {
        this.employeeService=employeeService;
    }
    private final Map<String, Employee> employee = employeeService.employeeGetAll();

    public Map<String, Employee> getEmployeeDepartment(int numberDepartment) {
        Map<String, Employee> employeeDetartment;
        if (numberDepartment > 0) {
            employeeDetartment = employeeService.employeeGetAll().entrySet().stream()
                    .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                    .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        } else {
            employeeDetartment = employeeService.employeeGetAll().entrySet().stream()
                    .sorted(Comparator.comparing(e -> e.getValue().getNumberDepartment()))
                    .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        }
        return employeeDetartment;
    }
    public Map <String, Employee>  employeeSalaryMore(double limitSalary) {
      Map <String, Employee>  employeeSalaryMore = employeeService.employeeGetAll().entrySet().stream()
                .filter(e -> e.getValue().getSalary() > limitSalary)
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return employeeSalaryMore;
    }

    public Map<String, Employee> employeeSalaryLess(double limitSalary) {
        Map <String, Employee> employeeSalaryLess = employeeService.employeeGetAll().entrySet().stream()
                .filter(e -> e.getValue().getSalary() < limitSalary)
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return employeeSalaryLess;
    }

    public Map<String, Employee> indexingSalary(double indexSalary) {
        Map<String, Employee> indexingSalary = employeeService.employeeGetAll().entrySet().stream()
                .peek(e -> e.getValue().setSalary(e.getValue().getSalary() * (1 + indexSalary)))
                .collect(Collectors.toMap(p -> p.getKey(), t -> t.getValue()));
        return indexingSalary;
    }

    public Long countEmployeeDepartment(int numberDepartment) {
        Long countEmployeeDepartment = Long.valueOf(employeeService.employeeGetAll().entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .count());
        return countEmployeeDepartment;
    }

    public Double allSalaryDepartment(int numberDepartment) {
        Double allSalaryDepartment = Double.valueOf(employeeService.employeeGetAll().entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .map(e -> e.getValue().getSalary())
                .reduce((double) 0, (a, b) -> a + b));
        return allSalaryDepartment;
    }

    public Double averageSalaryDepartment(int numberDepartment) {
        Double averageSalaryDepartment = Double.valueOf(0);
        if (countEmployeeDepartment(numberDepartment) > 0) {
            averageSalaryDepartment = allSalaryDepartment(numberDepartment) / countEmployeeDepartment(numberDepartment);
        }
        return averageSalaryDepartment;
    }



    private String getFullName() {
        String getFullName = employeeService.employeeGetAll().entrySet().stream()
                .map(e -> e.getValue().getFirstName() + " " + e.getValue().getLastName())
                .findAny()
                .orElseThrow();
        return getFullName;
    }

    public Optional<Double> allSalary() {
        Optional<Double> allSalary = Optional.of(employeeService.employeeGetAll().entrySet().stream()
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
        Optional<Map.Entry<String, Employee>> maxSalaryDepartment = employeeService.employeeGetAll().entrySet().stream()
                .filter(e -> e.getValue().getNumberDepartment() == numberDepartment)
                .max(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return maxSalaryDepartment.get();
    }

    public Optional<Map.Entry<String, Employee>> minSalary() {
        Optional<Map.Entry<String, Employee>> minSalary =employeeService.employeeGetAll().entrySet().stream()
                .min(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return minSalary;
    }

    public Optional<Map.Entry<String, Employee>> maxSalary() {
        Optional<Map.Entry<String, Employee>> maxSalary = employeeService.employeeGetAll().entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().getSalary()));
        return maxSalary;
    }
}
