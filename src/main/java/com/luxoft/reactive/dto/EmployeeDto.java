package com.luxoft.reactive.dto;

import com.luxoft.reactive.entity.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private String id;
    private String email;
    private String name;
    private boolean married;
    private Date birthday;
    private Position position;
    private String departmentId;
}
