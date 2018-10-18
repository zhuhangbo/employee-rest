package dev.local.employeerest.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.local.employeerest.entity.Employee;
import dev.local.employeerest.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {
	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(value = "", produces = APPLICATION_JSON_UTF8_VALUE)
	public List<Employee> getEmployees() {
		return employeeService.retrieveEmployees();
	}

	@GetMapping(value = "/{employeeId}", produces = APPLICATION_JSON_UTF8_VALUE)
	public Employee getEmployee(@PathVariable Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@PostMapping(value = "")
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
		System.out.println("Employee Saved Successfully");
	}

	@DeleteMapping(value = "/{employeeId}")
	public void deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		System.out.println("Employee Deleted Successfully");
	}

	@PutMapping(value = "/{employeeId}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {
		LOGGER.info("update employee >>> {}, employeeId: {}", employee, employeeId);
		if (!Objects.isNull(employeeService.getEmployeeById(employeeId))) {
			employeeService.updateEmployee(employee);
		}
	}
}
