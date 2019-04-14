package com.luxoft.reactive.entity;

import com.luxoft.reactive.annotation.CascadeSave;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "department")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@NoArgsConstructor
public class Department {

    @Id
    private String id;
    private String name;
    private String address;

    @DBRef(lazy = true)
    @CascadeSave
    private List<Employee> employees = new ArrayList<>();

    public Department addEmployee(Employee employee) {
        employee.setDepartment(this);
        employees.add(employee);
        return this;
    }

    public void removeEmployee(Employee employee) {
        employee.setDepartment(null);
        employees.remove(employee);
    }
}
