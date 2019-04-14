package com.luxoft.reactive.entity;

import com.luxoft.reactive.entity.enums.Position;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "employee")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee {

    @Id
    private String id;
    private String email;
    private String name;
    private boolean married;
    private Date birthday;
    private Position position;
    private Department department;
}
