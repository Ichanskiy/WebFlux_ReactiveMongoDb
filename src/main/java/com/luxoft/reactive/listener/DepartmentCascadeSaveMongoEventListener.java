//package com.luxoft.reactive.listener;
//
//import com.luxoft.reactive.entity.Department;
//import com.luxoft.reactive.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DepartmentCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
//
//    @Autowired
//    private MongoOperations mongoOperations;
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
//        Object source = event.getSource();
//        if ((source instanceof Department)) {
//            Department department = (Department) event.getSource();
//            for (Employee employee : department.getEmployees()) {
//                mongoOperations.save(employee);
//            }
//        }
//    }
//}
