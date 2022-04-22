package pro.sky.skypro_employees.service;

import pro.sky.skypro_employees.data.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee employeeAdd(String firstName, String lastName);

    Employee employeeFind(String firstName, String lastName);

    Employee employeeRemove(String firstName, String lastName);

    Void EmployeeIsAbsent(Map employee, Employee currentEmployee);

    Void EmployeeAlreadyExists(Map employee, Employee currentEmployee);
}