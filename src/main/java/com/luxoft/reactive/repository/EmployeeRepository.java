package com.luxoft.reactive.repository;

import com.luxoft.reactive.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> getAllByDepartmentIdOrderByEmail(String departmentId);

    Mono<Employee> getEmployeeByEmail(String email);

    Mono<Boolean> existsByEmail(String email);
}
