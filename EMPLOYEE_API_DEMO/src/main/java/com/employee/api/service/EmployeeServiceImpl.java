package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    EmployeeRepository dao;

	@Override
	public List<Employee> getEmployees() {
		return dao.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer empid) {
		return dao.findById(empid);
	}

	@Override
	public Employee addNewEmployee(Employee emp) {
		return dao.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return dao.save(emp);
	}

	@Override
	public void deleteEmployeeById(int empid) {
		dao.deleteById(empid);
	}

	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}
}
