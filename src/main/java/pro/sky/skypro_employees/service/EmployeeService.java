package pro.sky.skypro_employees.service;

import pro.sky.skypro_employees.data.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee employeeAdd(String firstName, String lastName, int numberDepartment, double salary);

    Employee employeeFind(String firstName, String lastName, int numberDepartment, double salary);

    Employee employeeRemove(String firstName, String lastName, int numberDepartment, double salary);

    void EmployeeIsAbsent(Map employee, Employee currentEmployee);

    void EmployeeAlreadyExists(Map employee, Employee currentEmployee);

    Map.Entry<String, Employee> maxSalaryDepartment(int numberDepartment);

    Map.Entry<String, Employee> minSalaryDepartment(int numberDepartment);

    Map<String, Employee> getEmployeeDepartment(int numberDepartment);

    Map<String, Employee> indexingSalary(double indexSalary);
}