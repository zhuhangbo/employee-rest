package dev.local.employeerest.service.impl;

import dev.local.employeerest.dao.repository.EmployeeRepository;
import dev.local.employeerest.dto.EmployeeDTO;
import dev.local.employeerest.entity.Employee;
import dev.local.employeerest.service.EmployeeService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> retrieveEmployees() {
        return employeeRepository.findAll().stream() //
                .map(this::mapEmployeeToEmployeeDTO) //
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(this::mapEmployeeToEmployeeDTO);
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = this.mapEmployeeDTOToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = this.mapEmployeeDTOToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    private Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanCopier copier = BeanCopier.create(EmployeeDTO.class, Employee.class, false);
        copier.copy(employeeDTO, employee, null);
        return employee;
    }

    private EmployeeDTO mapEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanCopier copier = BeanCopier.create(Employee.class, EmployeeDTO.class, false);
        copier.copy(employee, employeeDTO, null);
        return employeeDTO;
    }
}
