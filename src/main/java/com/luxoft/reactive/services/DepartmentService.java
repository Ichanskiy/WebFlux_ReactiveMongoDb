package com.luxoft.reactive.services;

import com.luxoft.reactive.dto.DepartmentDto;
import com.luxoft.reactive.entity.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Flux<DepartmentDto> getAll();

    Mono<DepartmentDto> getDepartmentById(String id);

    Mono<Department> saveDepartment(Department department);

    Mono<Void> removeDepartmentById(String id);
}
