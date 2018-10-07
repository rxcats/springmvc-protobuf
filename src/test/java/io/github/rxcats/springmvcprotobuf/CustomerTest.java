package io.github.rxcats.springmvcprotobuf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springmvcprotobuf.domain.protobuf.CustomerProtos;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CustomerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void customer() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/customers/1").contentType("application/x-protobuf;charset=UTF-8"))
            .andExpect(status().is(200))
            .andDo(print())
            .andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        CustomerProtos.Customer customer = CustomerProtos.Customer.parseFrom(contentAsByteArray);

        log.info("customer : {}", customer);
        log.info("id : {}", customer.getId());
        log.info("firstName : {}", customer.getFirstName());
        log.info("lastName : {}", customer.getLastName());
        log.info("emails(size:{}) : {}", customer.getEmailCount(), customer.getEmailList());
        log.info("email(0) type :{} ", customer.getEmail(0).getType());
        log.info("email(1) type :{} ", customer.getEmail(1).getType());

    }

}
