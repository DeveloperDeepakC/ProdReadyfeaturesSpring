package com.deepak.prodReadyFeatures.prod_ready_features.clients.impl;

import com.deepak.prodReadyFeatures.prod_ready_features.advice.ApiResponse;
import com.deepak.prodReadyFeatures.prod_ready_features.clients.EmployeeClient;
import com.deepak.prodReadyFeatures.prod_ready_features.dto.EmployeeDto;
import com.deepak.prodReadyFeatures.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImplementation implements EmployeeClient {

    Logger log= LoggerFactory.getLogger(EmployeeClientImplementation.class);

    private final RestClient restClient;
    @Override
    public List<EmployeeDto> getAllEmployee() {

        log.trace("Trying to retrieve all employees");
        try {
            ApiResponse<List<EmployeeDto>> employeeDtoList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)-> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not get the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfult retrieved all employees");
            log.trace("Retrieved employees list in getAllEmployees: {}", employeeDtoList.getData());
            return employeeDtoList.getData();
        }catch (Exception e){
            log.error("Exception occured in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDto> employeeResponse= restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try{
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDtoApiResponse= restClient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)-> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoApiResponse.getBody().getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
