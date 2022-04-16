package pro.sky.skypro_employees;
import java.util.Objects;

public class Employee {
    private final String key;
    private String firstName;
    private String lastName;

    public Employee( String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.key= firstName + lastName;
    }

    public String getKey() {return key;}
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
        return key.equals(employee.key);

    }

    @Override
    public int hashCode() {
        return Objects.hash(key,firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", key='" + key + '\'' +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
