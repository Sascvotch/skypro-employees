package pro.sky.skypro_employees.service;

import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.exceptions.EmployeeIsAbsent;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee employeeAdd(String firstName, String lastName, int numberDepartment, double salary) ;

    Employee employeeFind(String firstName, String lastName, int numberDepartment, double salary);

    Employee employeeRemove(String firstName, String lastName, int numberDepartment, double salary);

    Map<String, Employee>  employeeGetAll();

    List<Employee> employeeGetAllList();

    void EmployeeIsAbsent(Map employee, Employee currentEmployee);

    void EmployeeAlreadyExists(Map employee, Employee currentEmployee);


}