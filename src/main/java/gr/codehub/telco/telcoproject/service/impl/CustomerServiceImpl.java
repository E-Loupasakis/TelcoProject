package gr.codehub.telco.telcoproject.service.impl;


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
    public User create(gr.codehub.telco.telcoproject.model.User customer) {
       // User customer = customerDto.asCustomer();
       return customerRepository.create(customer);
        //return new Customer(customer);
    }

    @Override
    public User read(long id) {
        return customerRepository.read(id);
    }

    @Override
    public User readByVat(int vat) {

        return customerRepository.getCustomerByVat(vat);
    }
    @Override
    public List<User> read(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public List<User> read() {
        return customerRepository.read();
    }

    @Override
    public User update(User customer) {
        //User customer = customerDto.asCustomer();
       return customerRepository.update(customer);
        //return new Customer(customer);
    }

    @Override
    public void delete(long customerId) {
        customerRepository.delete(customerId);
    }
}
