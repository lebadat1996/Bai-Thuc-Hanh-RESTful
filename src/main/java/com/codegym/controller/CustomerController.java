package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("list")
    public ResponseEntity<List<Customer>> getListCustomer() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @GetMapping("customer/delete/{id}")
    public ResponseEntity<Customer> removeCustomer(@PathVariable Long id) {
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
