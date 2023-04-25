package com.demo.assignment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.assignment.dto.EmployeeDTO;


public interface EmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO employee);
	
	EmployeeDTO uploadFileandEmployee(String id, MultipartFile file);

	EmployeeDTO findById(String id);

	List<EmployeeDTO> findAllEmp();
	
	EmployeeDTO updateEmployee(EmployeeDTO employee);

	String deleteEmployeeById(String id);
	
}
