package pro.sky.skypro_employees.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skypro_employees.service.impl.DepartmentServiceImpl;
import pro.sky.skypro_employees.service.impl.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static pro.sky.skypro_employees.Services.DepartmentTestConstant.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void getEmployeeDepartmentTest() {
        assertNotNull(employeeService.employeeGetAll());
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(EMPLOYEEMAPOFDEPARTMENT, departmentService.getEmployeeDepartment(NUMBERDEPARTMENT));
        assertEquals(EMPLOYEEMAP, departmentService.getEmployeeDepartment(0));
    }

    @Test
    public void employeeWithMinSalaryByDepartmentTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(EMPLOYEEWITHMINSALARY, departmentService.minSalaryDepartment(NUMBERDEPARTMENT));
    }

    @Test
    public void employeeWithMaxSalaryByDepartmentTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(EMPLOYEEWITHMAXSALARY, departmentService.maxSalaryDepartment(NUMBERDEPARTMENT));
    }

    @Test
    public void employeeSalaryLessTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAPOFDEPARTMENT);
        assertEquals(EMPLOYEEWITHMINSALARY1, departmentService.employeeSalaryLess(LIMITSALARY));
    }

    @Test
    public void employeeSalaryMoreTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAPOFDEPARTMENT);
        assertEquals(EMPLOYEEWITHMAXSALARY1, departmentService.employeeSalaryMore(LIMITSALARY));
    }

    @Test
    public void allSalaryDepartmentTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(SALARYDEPARTMENT, departmentService.allSalaryDepartment(NUMBERDEPARTMENT));
    }

    @Test
    public void countEmployeeDepartmentTest() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(COUNTEMPLOYEEDEPARTMENT, departmentService.countEmployeeDepartment(NUMBERDEPARTMENT));
    }

    @Test
    public void averageSalaryDepartment() {
        when(employeeService.employeeGetAll()).thenReturn(EMPLOYEEMAP);
        assertEquals(SALARYDEPARTMENT / COUNTEMPLOYEEDEPARTMENT, departmentService.averageSalaryDepartment(NUMBERDEPARTMENT));
    }

}
