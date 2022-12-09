package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.model.Ticket;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerDto customerDto);
    CustomerDto read(long id);
    CustomerDto readByVat(int vat);

    List<CustomerDto> read(String email);
    List<CustomerDto> read();

    CustomerDto update( CustomerDto customerDto);
    void delete(long customerId);

    List<Ticket> findTicketsByCustomerId(long customerId);
}
