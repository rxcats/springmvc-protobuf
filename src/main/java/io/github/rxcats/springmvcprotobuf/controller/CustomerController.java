package io.github.rxcats.springmvcprotobuf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.rxcats.springmvcprotobuf.domain.protobuf.CustomerProtos;

@RestController
public class CustomerController {

    @GetMapping(value = "/customers/{id}", consumes = "application/x-protobuf;charset=UTF-8")
    public CustomerProtos.Customer get(@PathVariable int id) {

        return CustomerProtos.Customer.newBuilder()
            .setId(1)
            .setFirstName("oh")
            .setLastName("my")
            .addEmail(CustomerProtos.Customer.EmailAddress.newBuilder().setEmail("1@email.com").setType(CustomerProtos.Customer.EmailType.PROFESSIONAL).build())
            .addEmail(CustomerProtos.Customer.EmailAddress.newBuilder().setEmail("2@email.com").setType(CustomerProtos.Customer.EmailType.PRIVATE).build())
            .build();

    }

}
