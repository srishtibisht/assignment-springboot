package com.demo.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.assignment.entity.Employee;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
