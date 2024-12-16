package com.deepak.prodReadyFeatures.prod_ready_features.clients;

import com.deepak.prodReadyFeatures.prod_ready_features.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
