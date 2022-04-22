package pro.sky.skypro_employees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skypro_employees.service.EmployeeService;
import pro.sky.skypro_employees.data.Employee;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee employeeAdd(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.employeeAdd(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee employeeFind(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.employeeFind(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee employeeRemove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.employeeRemove(firstName, lastName);
    }
}
