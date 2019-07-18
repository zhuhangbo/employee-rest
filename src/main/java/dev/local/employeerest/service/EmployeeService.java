package dev.local.employeerest.service;

import dev.local.employeerest.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	List<EmployeeDTO> retrieveEmployees();

	Optional<EmployeeDTO> getEmployeeById(Long employeeId);

	void saveEmployee(EmployeeDTO employee);

	void deleteEmployee(Long employeeId);

	void updateEmployee(EmployeeDTO employee);

}
