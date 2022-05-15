package pro.sky.skypro_employees.service;

import pro.sky.skypro_employees.data.Employee;

import java.util.Map;

public interface DepartmentService {
    Map.Entry<String, Employee> maxSalaryDepartment(int numberDepartment);

    Map.Entry<String, Employee> minSalaryDepartment(int numberDepartment);

    Map<String, Employee> getEmployeeDepartment(int numberDepartment);

    Map<String, Employee> indexingSalary(double indexSalary);
}
