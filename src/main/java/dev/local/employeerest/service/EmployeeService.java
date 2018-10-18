package dev.local.employeerest.service;

import java.util.List;

import dev.local.employeerest.entity.Employee;

public interface EmployeeService {

	List<Employee> retrieveEmployees();

	Employee getEmployeeById(Long employeeId);

	void saveEmployee(Employee employee);

	void deleteEmployee(Long employeeId);

	void updateEmployee(Employee employee);

}
