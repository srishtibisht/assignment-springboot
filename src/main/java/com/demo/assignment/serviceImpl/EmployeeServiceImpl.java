package com.demo.assignment.serviceImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.entity.Employee;
import com.demo.assignment.exception.NotFoundException;
import com.demo.assignment.repository.EmployeeRepository;
import com.demo.assignment.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	

	private static final String empCache = "emp_name";

	private EmployeeRepository empRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRepository) {
		this.empRepository=empRepository;
	}

	@Override
	public EmployeeDTO uploadFileandEmployee(String id, MultipartFile file) {
		String fileURL = "";
		try {
			fileURL = Base64.getEncoder().encodeToString(file.getBytes());
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Employee employee = empRepository.findById(id).get();
		employee.setImageUrl(fileURL);
		Employee emp = empRepository.save(employee);
		return mapToDTO(emp);

	}

	@Override
	@Cacheable(value = empCache,key = "#id",unless = "#result=null")
	public EmployeeDTO findById(String id) {
		Optional<Employee> employee = empRepository.findById(id);
		if(! employee.isPresent()) {
			throw new NotFoundException("Id not found");
		}
		return mapToDTO(employee.get());
	}

	@Override
	public List<EmployeeDTO> findAllEmp(){
		if(empRepository.findAll().size() < 1) {
			throw new NotFoundException("Employee List is empty");
		}
	    List<Employee> empList = empRepository.findAll();
	  return empList.stream().map(EmployeeServiceImpl :: mapToDTO).collect(Collectors.toList());
	}

	@Override
	public String deleteEmployeeById(String id) {
		Optional<Employee> employee = empRepository.findById(id);
		if(! employee.isPresent()) {
			throw new NotFoundException("Id not present");
		}

		empRepository.deleteById(id);
		return "Employee : "+id+" has been deleted";
	}

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employee) {

		Employee employeeObj = mapToEntity(employee);
		Employee empObj = empRepository.save(employeeObj);
		return mapToDTO(empObj);

	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employee) {
		Employee emp = mapToEntity(employee);
		emp = empRepository.findById(emp.getId())
				.orElseThrow(() -> new NotFoundException("Id not present in database"));
		return mapToDTO(empRepository.save(emp));
	}
	
	private static  Employee mapToEntity(EmployeeDTO employee) {
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setDateOfBirth(employee.getDateOfBirth());
		emp.setImageUrl(employee.getImageUrl());

		return emp;
	}

	private static EmployeeDTO mapToDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setDateOfBirth(employee.getDateOfBirth());
		employeeDTO.setImageUrl(employee.getImageUrl());

		return employeeDTO;
	}


}
