package pro.sky.skypro_employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
 class ArrayIsFull extends RuntimeException {
}
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class EmployeeAlreadyExists extends RuntimeException {
}
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EmployeeIsAbsent extends RuntimeException {
}
