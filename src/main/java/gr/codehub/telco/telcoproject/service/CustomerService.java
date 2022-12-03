package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerDto customerDto);
    CustomerDto read(long id);
    List<CustomerDto> read();

    void delete(long customerId);
}
