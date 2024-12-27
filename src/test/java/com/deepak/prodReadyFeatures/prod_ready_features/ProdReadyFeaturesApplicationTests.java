package com.deepak.prodReadyFeatures.prod_ready_features;

import com.deepak.prodReadyFeatures.prod_ready_features.clients.EmployeeClient;
import com.deepak.prodReadyFeatures.prod_ready_features.dto.EmployeeDto;
import com.deepak.prodReadyFeatures.prod_ready_features.entities.User;
import com.deepak.prodReadyFeatures.prod_ready_features.services.JwtService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user= new User(4L,"deepak@gmail.com","Deepak","1234");
		String token= jwtService.generateToken(user);

		System.out.println(token);

		Long id= jwtService.getUserIdFromToken(token);
		System.out.println(id);
	}

	@Test
	@Order(3)
	void getAllEmployees(){
		List<EmployeeDto> employeeDtoList= employeeClient.getAllEmployee();
		System.out.println(employeeDtoList);
	}

	@Test
	void getEmployeeByIdTest(){
		EmployeeDto employeeDto= employeeClient.getEmployeeById(1L);
		System.out.println(employeeDto);
	}

	@Test
	void createNewEmployeeTest(){
		EmployeeDto employeeDto= new EmployeeDto(null,"deepak","deepak@gmail.com",23, "USER", 5000.00, LocalDate.of(2020,11,10),true);
		EmployeeDto savedEmployeeDto= employeeClient.createNewEmployee(employeeDto);
		System.out.println(savedEmployeeDto);
	}

}
