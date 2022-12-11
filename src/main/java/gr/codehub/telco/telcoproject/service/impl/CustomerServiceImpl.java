package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.exception.EmailExistsException;
import gr.codehub.telco.telcoproject.exception.VatExistsException;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public User create(User customer) {
        if(customerRepository.getCustomerByVat(customer.getVatNumber())!=null){
            throw new VatExistsException("Vat exists");
        }
        customer.getEmailList().forEach( (email) -> {
            if(!(customerRepository.getCustomerByEmail(email.getEmail()).isEmpty())){
                    throw new EmailExistsException("Email exists");
            }
        });
        return customerRepository.create(customer);
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
       return customerRepository.update(customer);
    }

    @Override
    public void delete(long customerId) {
        customerRepository.delete(customerId);
    }

    @Override
    public List<Ticket> findTicketsByCustomerId(long customerId) {
        return  customerRepository.read(customerId).getTickets();

    }
}
