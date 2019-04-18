package com.luxoft.reactive.services;

import com.luxoft.reactive.dto.EmployeeDto;
import com.luxoft.reactive.entity.Employee;
import com.luxoft.reactive.exeptions.DuplicateEmailException;
import com.luxoft.reactive.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Predicate;

@Service
@Transactional(readOnly = true)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Mono<EmployeeDto> getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .flatMap(employee ->
                        Mono.just(new EmployeeDto(employee.getId(),
                                employee.getEmail(),
                                employee.getName(),
                                employee.isMarried(),
                                employee.getBirthday(),
                                employee.getPosition(),
                                employee.getDepartment().getId())));
    }

    public Flux<EmployeeDto> getAllByDepartmentId(String id) {
        return employeeRepository.getAllByDepartmentIdOrderByEmail(id)
                .flatMap(employee -> Flux.just(new EmployeeDto(employee.getId(),
                        employee.getEmail(),
                        employee.getName(),
                        employee.isMarried(),
                        employee.getBirthday(),
                        employee.getPosition(),
                        employee.getDepartment().getId())));
    }

    @Transactional
    public Mono<Void> removeEmployeeById(String id) {
        return employeeRepository.deleteById(id);
    }

    @Transactional
    public Mono<Employee> saveEmployee(Employee employee) {
        Predicate<Employee> idIsNull = e -> e.getId() == null;
        return employeeRepository.existsByEmail(employee.getEmail())
                .flatMap(isExist ->
                        isExist ?
                                employeeRepository.getEmployeeByEmail(employee.getEmail())
                                        .flatMap(employeeByEmail -> {
                                            Predicate<Employee> nonNull =
                                                    Objects::nonNull;
                                            Predicate<Employee> differentIds =
                                                    (e) -> !e.getId().equals(employee.getId());
                                            return nonNull.and(differentIds).test(employeeByEmail) ?
                                                    createDuplicateEmailException() : updateEmployee(employee);
                                        })
                                :
                                idIsNull.test(employee) ? employeeRepository.save(employee) : updateEmployee(employee)
                );
    }

    private Mono<Employee> updateEmployee(Employee employee) {
        return employeeRepository.findById(employee.getId()).flatMap(employeeDb -> {
            employeeDb.setEmail(employee.getEmail());
            employeeDb.setName(employee.getName());
            employeeDb.setBirthday(employee.getBirthday());
            employeeDb.setMarried(employee.isMarried());
            employeeDb.setPosition(employee.getPosition());
            return employeeRepository.save(employeeDb);
        });
    }

    private static Mono<Employee> createDuplicateEmailException() {
        log.info("Duplicate email");
        throw new DuplicateEmailException();
    }
}
