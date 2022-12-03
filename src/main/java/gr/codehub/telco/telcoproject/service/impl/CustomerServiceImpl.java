package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        User customer = customerDto.asCustomer();
        customerRepository.create(customer);
        return new CustomerDto(customer);
    }

    @Override
    public CustomerDto read(long id) {
        return new CustomerDto(customerRepository.read(id));
    }

    @Override
    public List<CustomerDto> read() {
        return customerRepository.read().stream().map(CustomerDto::new).collect(Collectors.toList());
    }
}
