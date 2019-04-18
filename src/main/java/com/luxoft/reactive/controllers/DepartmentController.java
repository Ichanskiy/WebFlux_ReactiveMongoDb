package com.luxoft.reactive.controllers;

import com.luxoft.reactive.dto.DepartmentDto;
import com.luxoft.reactive.entity.Department;
import com.luxoft.reactive.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ControllerAPI.DEPARTMENT_CONTROLLER)
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.ALL)
    public Flux<DepartmentDto> getAllDepartments() {
        log.info("Get all departments");
        return departmentService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.BY_ID)
    public Mono<ResponseEntity<DepartmentDto>> getDepartmentById(@PathVariable(name = "id") String id) {
        log.info("Get department by id = " + id);
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping(value = ControllerAPI.GENERAL_REQUEST)
    public Mono<Department> saveDepartment(@RequestBody @Validated Department department) {
        return departmentService.saveDepartment(department);
    }

    @CrossOrigin
    @DeleteMapping(value = ControllerAPI.BY_ID)
    public Mono<ResponseEntity<Void>> removeDepartmentById(@PathVariable(name = "id") String id) {
        log.info("Remove department by id = " + id);
        id = "1111";
        return departmentService.removeDepartmentById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
