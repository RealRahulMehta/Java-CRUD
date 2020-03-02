package com.crud.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.dao.EmployeeDAO;
import com.crud.demo.exception.MissingParameterException;
import com.crud.demo.exception.ResourceNotFoundException;
import com.crud.demo.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) throws Exception {
		
		if (emp.getEmailId() == null || emp.getEmailId().isEmpty())
			throw new MissingParameterException("EmailId can't be null");
		
		if (emp.getFirstName() == null || emp.getFirstName().isEmpty())
			throw new MissingParameterException("First name can't be null");
		
		if (emp.getLastName() == null || emp.getLastName().isEmpty())
			throw new MissingParameterException("Last name can't be null");
		
		return employeeDAO.save(emp);
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() throws Exception {
		return employeeDAO.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long empid) throws Exception {
			Optional<Employee> emp = employeeDAO.findOne(empid);
			
			if (!emp.isPresent())
				throw new ResourceNotFoundException("Employee not found");

			return emp.get();
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployeeById(@RequestBody Employee empDetails, @PathVariable("id") Long empid) throws Exception {

		Optional<Employee> emp = employeeDAO.findOne(empid);

		if (!emp.isPresent())
			throw new ResourceNotFoundException("Employee not found");

		if (empDetails.getFirstName() == null || empDetails.getFirstName().isEmpty())
			empDetails.setFirstName(emp.get().getFirstName());

		if (empDetails.getLastName() == null || empDetails.getLastName().isEmpty())
			empDetails.setLastName(emp.get().getLastName());

		if (empDetails.getEmailId() == null || empDetails.getEmailId().isEmpty())
			empDetails.setEmailId(emp.get().getEmailId());

		empDetails.setId(empid);

		return employeeDAO.save(empDetails);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable("id") Long empid) throws Exception {
			Optional<Employee> emp = employeeDAO.findOne(empid);
			if (!emp.isPresent())
				throw new Exception("Employee not found");

			employeeDAO.deleteById(empid);
	}
}
