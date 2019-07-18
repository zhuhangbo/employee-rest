package dev.local.employeerest.web.controller;

import dev.local.employeerest.dto.EmployeeDTO;
import dev.local.employeerest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<EmployeeDTO> getEmployees() {
        return employeeService.retrieveEmployees();
    }

    @GetMapping(value = "/{employeeId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public EmployeeDTO getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId).orElse(null);
    }

    @PostMapping(value = "")
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        if (logger.isInfoEnabled()) {
            logger.info("Employee Saved Successfully");
        }
    }

    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        if (logger.isInfoEnabled()) {
            logger.info("Employee Deleted Successfully");
        }
    }

    @PutMapping(value = "/{employeeId}")
    public void updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        if (logger.isInfoEnabled()) {
            logger.info("update employee >>> {}, employeeId: {}", employeeDTO, employeeId);
        }
        if (!Objects.isNull(employeeService.getEmployeeById(employeeId))) {
            employeeService.updateEmployee(employeeDTO);
        }
    }
}
