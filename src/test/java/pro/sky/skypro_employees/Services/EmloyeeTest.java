package pro.sky.skypro_employees.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.skypro_employees.data.Employee;
import pro.sky.skypro_employees.exceptions.InvalidArgumentException;
import pro.sky.skypro_employees.service.impl.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmloyeeTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();


    public static Stream<Arguments> provideParamsForEmployeeTest() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 100000),
                Arguments.of("Petr", "Petrov", 1, 100000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEmployeeTest")
    public void employeeFindTest(String firstName, String lastName, int numberDepartment, double salary) {
        Employee expected = out.employeeAdd(firstName, lastName, numberDepartment, salary);
        Employee actual = out.employeeFind(firstName, lastName, numberDepartment, salary);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEmployeeTest")
    public void employeeAddTest(String firstName, String lastName, int numberDepartment, double salary) {
        Employee expected = new Employee(firstName, lastName, numberDepartment, salary);
        Employee actual = out.employeeAdd(firstName, lastName, numberDepartment, salary);
        assertEquals(expected, actual);
    }

    @Test
    public void employeeGetAllTest() {
        out.employeeRemoveAll();
        Map<String, Employee> expected = Map.of("IvanIvanov", out.employeeAdd("Ivan", "Ivanov", 2, 100000));
        assertEquals(expected, out.employeeGetAll());
    }

    public static Stream<Arguments> provideParamsForEmployeeAlreadyExistsTest() {
        Employee currentEmployee = new Employee("Ivan", "Ivanov", 2, 100000);
        Map<String, Employee> employee = new HashMap<>(Map.of(
                "IvanIvanov", currentEmployee
        ));
        return Stream.of(Arguments.of(employee, currentEmployee));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEmployeeAlreadyExistsTest")
    public void EmployeeAlreadyExistsTest(Map employee, Employee currentEmployee) {
        assertThrows(RuntimeException.class,
                () -> out.EmployeeAlreadyExists(employee, currentEmployee)
        );
    }

    @Test
    public void EmployeeContainsIllegalCharacters() {
        assertThrows(InvalidArgumentException.class,
                () -> out.employeeAdd("ivan1", "IvanoV2", 1, 100000)
        );
    }
}
