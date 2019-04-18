package com.luxoft.reactive.services;

import com.luxoft.reactive.dto.EmployeeDto;
import com.luxoft.reactive.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<EmployeeDto> getEmployeeById(String id);

    Flux<EmployeeDto> getAllByDepartmentId(String departmentId);

    Mono<Void> removeEmployeeById(String id);

    Mono<Employee> saveEmployee(Employee employee);
}
