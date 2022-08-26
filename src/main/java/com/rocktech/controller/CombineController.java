package com.rocktech.controller;

import com.rocktech.model.Response;
import com.rocktech.repository.employee.EmployeeRepository;
import com.rocktech.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombineController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public Response getResponse() {
        Response response = new Response();
        response.setEmployees(employeeRepository.findAll());
        response.setProducts(productRepository.findAll());
        return response;
    }
}
