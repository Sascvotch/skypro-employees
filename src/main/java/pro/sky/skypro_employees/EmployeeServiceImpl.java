package pro.sky.skypro_employees;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Employee[] employee = new Employee[5];
    int size=0;
    int i=0;

    public Employee employeeAdd (String firstName, String lastName){
        Employee employeeAdd=new Employee(firstName, lastName);
        if (size >=employee.length ) {
            throw new ArrayIsFull();
        }
        for (int i = 0; i <= size; i++) {
            if (employeeAdd.equals(employee[i])) {
                throw new EmployeeAlreadyExists();
            }
        }
        employee[size] =new Employee(firstName, lastName);
        System.out.println("Сотрудник " + firstName + " " + lastName + " добавлен.");
        size=size+1;
        return employee [size-1];
    }

    public Employee employeeFind (String firstName, String lastName) {
       Boolean find =false;
       Employee employeeFind=new Employee(firstName, lastName);
       for (int i = 0; i <=size; i++) {
          if (!find) {
               if (employeeFind.equals(employee[i])) {
                  find = true;
               }
           }
       }
      if (!find) {
          throw new EmployeeIsAbsent();
      }
        System.out.println("Сотрудник " + firstName + " " + lastName + " найден.");
        return employeeFind;
    }
    public Employee employeeRemove (String firstName, String lastName) {
        Boolean find =false;
        Employee employeeRemove=new Employee(firstName, lastName);
        for (int i = 0; i <= size; i++) {
           if (!find) {
               if (employeeRemove.equals(employee[i])) {
                   employee[i] = null;
                   if (i != employee.length - 1) {
                       System.arraycopy(employee, i + 1, employee, i, size - i);
                       employee[size] = null;
                   }
                   find = true;
                   size--;
                   System.out.println("Сотрудник " + firstName + " " + lastName + " удален.");
               }
           }
        }
        if (!find) {
            throw new EmployeeIsAbsent();
        }
        return employeeRemove;
    }

}
