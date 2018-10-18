package dev.local.employeerest.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import dev.local.employeerest.entity.Employee;
import dev.local.employeerest.util.ConvertUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private EmployeeController employeeController;

	@Before
	public void setUp() {
		this.mockMvc = standaloneSetup(employeeController).build();
	}

	@Test
	public void testGetEmployees() throws Exception {
		this.mockMvc.perform(get("/api/employees")) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)) //
				.andDo(print());
	}

	@Test
	public void testGetEmployee() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/api/employees/{employeeId}", id)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)) //
				.andDo(print());
	}

	@Test
	public void testSaveEmployee() throws Exception {
		this.mockMvc.perform(post("/api/employees", this.mockEmployee()) //
				.accept(APPLICATION_JSON_UTF8)) //
				.andExpect(status().isOk()) //
				.andDo(print());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(delete("/api/employees/{employeeId}", id)) //
				.andExpect(status().isOk()) //
				.andDo(print());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = this.mockEmployee();
		employee.setDepartment("研发");

		this.mockMvc.perform(put("/api/employees/{employeeId}", ConvertUtil.toJson(employee), employee.getId()) //
				.accept(APPLICATION_JSON_UTF8)) //
				.andExpect(status().isOk()) //
				.andDo(print());
	}

	private Employee mockEmployee() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Test");
		employee.setSalary(300);
		employee.setDepartment("测试");
		return employee;
	}
}
