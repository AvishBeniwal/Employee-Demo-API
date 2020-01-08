package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.api.model.Employee;

@Service
public interface EmployeeService {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(Integer empid);
	public Employee addNewEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);
	public void deleteAllEmployees();
}
