package com.employee.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.model.Employee;
import com.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/api/")
public class EmployeeController {
	
	@Autowired(required=true)
	private EmployeeService employeeService;
	
	@RequestMapping(value= "/employee/all", method= RequestMethod.GET)
	public List<Employee> getEmployees() {
		System.out.println(this.getClass().getSimpleName() + " - Get all employees employeeService is invoked.");
		return employeeService.getEmployees();
	}

	@RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");

		Optional<Employee> emp =  employeeService.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		return emp.get();
	}

	@RequestMapping(value= "/employee/add", method= RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
		return employeeService.addNewEmployee(newemp);
	}

	@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable Integer id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");

		Optional<Employee> emp =  employeeService.getEmployeeById(id);
		if (!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(updemp.getFirstName() == null || updemp.getFirstName().isEmpty())
			updemp.setFirstName(emp.get().getFirstName());
		if(updemp.getProfile() == null || updemp.getProfile().isEmpty())
			updemp.setProfile(emp.get().getProfile());
		if(updemp.getSalary() == 0)
			updemp.setSalary(emp.get().getSalary());

		// Required for the "where" clause in the sql query template.
		updemp.setId(id);
		return employeeService.updateEmployee(updemp);
	}

	@RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");

		Optional<Employee> emp =  employeeService.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		employeeService.deleteEmployeeById(id);
	}

	@RequestMapping(value= "/employee/deleteall", method= RequestMethod.DELETE)
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " - Delete all employees is invoked.");
		employeeService.deleteAllEmployees();
	}
	
}
