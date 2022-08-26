package com.rocktech.model;

import com.rocktech.model.employee.Employee;
import com.rocktech.model.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class Response {

    List<Employee> employees;
    List<Product> products;
}
