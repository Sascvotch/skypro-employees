package pro.sky.skypro_employees.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.service.DepartmentService;
import pro.sky.skypro_employees.service.EmployeeService;
import pro.sky.skypro_employees.service.impl.EmployeeServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/indexing-salary")
    public Map<String, Employee> indexingSalary(@RequestParam("index") double indexSalary) {
        return departmentService.indexingSalary(indexSalary);
    }

    @GetMapping(path = "/max-salary")
    public Map.Entry<String, Employee> maxSalaryDepartment(@RequestParam("numberDep") int numberDepartment) {
        return departmentService.maxSalaryDepartment(numberDepartment);
    }

    @GetMapping(path = "/min-salary")
    public Map.Entry<String, Employee> minSalaryDepartment(@RequestParam("numberDep") int numberDepartment) {
        return departmentService.minSalaryDepartment(numberDepartment);
    }

    @GetMapping(path = "/all")
    public Map<String, Employee> getEmployeeDepartment(@RequestParam(required = false, name = "numberDep", defaultValue = "0") int numberDepartment) {
        return departmentService.getEmployeeDepartment(numberDepartment);
    }

}
