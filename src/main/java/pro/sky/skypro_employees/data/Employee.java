package pro.sky.skypro_employees.data;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    public int numberDepartment;
    public double salary;

    public Employee(String firstName, String lastName, int numberDepartment, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberDepartment = numberDepartment;
        this.salary = salary;
    }


    public int getNumberDepartment() {
        return numberDepartment;
    }

    public void setNumberDepartment(int numberDepartment) {
        this.numberDepartment = numberDepartment;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberDepartment=" + numberDepartment +
                ", salary=" + salary +
                '}';
    }

}