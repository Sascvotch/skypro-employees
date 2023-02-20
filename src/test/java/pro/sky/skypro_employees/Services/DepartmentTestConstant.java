package pro.sky.skypro_employees.Services;

import pro.sky.skypro_employees.data.Employee;

import java.util.AbstractMap;
import java.util.Map;

public class DepartmentTestConstant {

    public static final Map<String, Employee> EMPLOYEEMAP = Map.of(
            "IvanIvanov",
            new Employee("Ivan", "Ivanov", 1, 100000),
            "PetrPetrov",
            new Employee("Petr", "Petrov", 2, 100000),
            "SergeySergeev",
            new Employee("Sergey", "Sergeev", 2, 120000)

    );
    public static final Map<String, Employee> EMPLOYEEMAPOFDEPARTMENT = Map.of(
            "PetrPetrov",
            new Employee("Petr", "Petrov", 2, 100000),
            "SergeySergeev",
            new Employee("Sergey", "Sergeev", 2, 120000)

    );
    public static final Map<String, Employee> EMPLOYEEMAPOFDEPARTMENTINDEXSALARY = Map.of(
            "PetrPetrov",
            new Employee("Petr", "Petrov", 2, 250000),
            "SergeySergeev",
            new Employee("Sergey", "Sergeev", 2, 3000000)

    );
    public static final Long COUNTEMPLOYEEDEPARTMENT = Long.valueOf(2);
    public static final int SALARYDEPARTMENT = 220000;
    public static final int NUMBERDEPARTMENT = 2;
    public static final double LIMITSALARY = 110000;
    public static final double INDEXSALARY = 1.5;
    public static final Map<String, Employee> EMPLOYEEWITHMINSALARY1 = Map.of("PetrPetrov", new Employee("Petr", "Petrov", 2, 100000));
    public static final Map<String, Employee> EMPLOYEEWITHMAXSALARY1 = Map.of("SergeySergeev", new Employee("Sergey", "Sergeev", 2, 120000));
    public static final Map.Entry<String, Employee> EMPLOYEEWITHMINSALARY = new AbstractMap.SimpleEntry<>("PetrPetrov", new Employee("Petr", "Petrov", 2, 100000));
    public static final Map.Entry<String, Employee> EMPLOYEEWITHMAXSALARY = new AbstractMap.SimpleEntry<>("SergeySergeev", new Employee("Sergey", "Sergeev", 2, 120000));


}
