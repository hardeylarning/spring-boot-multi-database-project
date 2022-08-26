package com.rocktech;

import com.rocktech.model.employee.Employee;
import com.rocktech.model.product.Product;
import com.rocktech.repository.employee.EmployeeRepository;
import com.rocktech.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@EnableTransactionManagement
class MultipleDatabaseApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("employeeTransactionManager")
    public void whenCreatingUser_thenCreated() {
        Employee user = new Employee();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        user = employeeRepository.save(user);

        assertNotNull(employeeRepository.getById(user.getId()));
    }


    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);

        assertNotNull(productRepository.getById(product.getId()));
    }


}
