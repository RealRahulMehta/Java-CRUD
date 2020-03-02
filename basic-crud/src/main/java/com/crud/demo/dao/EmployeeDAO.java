package com.crud.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.repository.EmployeeRepository;
import com.crud.demo.model.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDAO {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findOne(Long empid) {
		return employeeRepository.findById(empid);
	}

	public void deleteById(Long empid) {
		employeeRepository.deleteById(empid);
	}
}
