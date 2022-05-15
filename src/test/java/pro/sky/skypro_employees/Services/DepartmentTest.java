package pro.sky.skypro_employees.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.service.impl.DepartmentServiceImpl;
import pro.sky.skypro_employees.service.impl.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void EmployeeWithMinSalaryByDepartment() {

      final List < Employee> employee= List.of(

                new Employee("Ivan", "Ivanov", 1, 100000),

                new Employee("Petr", "Petrov", 2, 100000),

                new Employee("Sergey", "Sergeev", 2, 120000)

        );
        when(employeeService.employeeGetAllList())
                .thenReturn(employee);
        Map<String, Employee> expected = Map.of(
                employeeService.employeeGetAllList().get(1).getFirstName()
                        + employeeService.employeeGetAllList().get(1).getLastName(),
                employeeService.employeeGetAllList().get(1));

        Map.Entry<String, Employee> actual = departmentService.minSalaryDepartment(1);
        assertEquals(expected, actual);

    }


}
