package com.demo.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.entity.Employee;
import com.demo.assignment.service.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/hello")
	public String message() {
		return "Hello WOrld !!";
	}

	// controller -> service -> repo DB
//							 -> API service
	// controller -> processor/facade layer -> service -> repo/api service
	// post employee -> emplyoee object -> id

	// post file upload ?id=id file
	// base64

//	@PostMapping(path = "/file")
//	public Employee fileUpload(@RequestParam(value = "id") String id, @RequestParam("file") MultipartFile file) throws IOException {
//		String fileString = Base64.getEncoder().encodeToString(file.getBytes());
//		Employee employee = employeeService.findById(id);
//		employee.setImageUrl(fileString);
//		employee = employeeService.saveEmployee(employee);
//		return employee;
//
//	}s

	@PostMapping("/addfile")
	public ResponseEntity<EmployeeDTO> fileUpload(@RequestParam(value="id")String id, @RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>(employeeService.uploadFileandEmployee(id,file), HttpStatus.ACCEPTED);
	}

	@PostMapping(path="/create")
	public ResponseEntity<EmployeeDTO> createEmpployee(@RequestBody EmployeeDTO employee){

		return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/allEmployees")
	public List<EmployeeDTO> getEmployee() {
		return employeeService.findAllEmp();
	}

	@GetMapping("/empById/{id}")
	public EmployeeDTO getEmployeeId(@PathVariable String id) {
		return employeeService.findById(id);
	}

	@PostMapping("/updateEmp")
	public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee) {
		return employeeService.updateEmployee(employee);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable String id) {
		return employeeService.deleteEmployeeById(id);
	}

}
