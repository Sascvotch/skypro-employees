package pro.sky.skypro_employees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skypro_employees.service.EmployeeService;
import pro.sky.skypro_employees.data.Employee;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee employeeAdd(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                @RequestParam("numberDep") int numberDepartment, @RequestParam("salary") double salary) {
        return employeeService.employeeAdd(firstName, lastName, numberDepartment, salary);
    }

    @GetMapping(path = "/find")
    public Employee employeeFind(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                 @RequestParam("numberDep") int numberDepartment, @RequestParam("salary") double salary) {
        return employeeService.employeeFind(firstName, lastName, numberDepartment, salary);
    }

    @GetMapping(path = "/remove")
    public Employee employeeRemove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                   @RequestParam("numberDep") int numberDepartment, @RequestParam("salary") double salary) {
        return employeeService.employeeRemove(firstName, lastName, numberDepartment, salary);
    }
    @GetMapping(path = "/indexing-salary")
    public Map<String, Employee> indexingSalary(@RequestParam("index") double indexSalary) {
        return employeeService.indexingSalary(indexSalary);
    }
    @GetMapping(path = "/departments/max-salary")
    public Map.Entry<String, Employee> maxSalaryDepartment(@RequestParam("numberDep") int numberDepartment) {
        return employeeService.maxSalaryDepartment(numberDepartment);
    }
    @GetMapping(path = "/departments/min-salary")
    public Map.Entry<String, Employee> minSalaryDepartment(@RequestParam ("numberDep")   int numberDepartment) {
        return employeeService.minSalaryDepartment(numberDepartment);
    }
    @GetMapping(path = "/departments/all")
    public Map<String, Employee> getEmployeeDepartment(@RequestParam(required = false, name="numberDep", defaultValue = "0") int numberDepartment) {
          return employeeService.getEmployeeDepartment(numberDepartment);
    }

}
