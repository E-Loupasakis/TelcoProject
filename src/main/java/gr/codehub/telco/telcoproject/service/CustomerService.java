package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;

import java.util.List;

public interface CustomerService {

    User create(User customer);
    User read(long id);
    User readByVat(int vat);

    List<User> read(String email);
    List<User> read();

    User update(User customer);
    boolean delete(long customerId);

    List<Ticket> findTicketsByCustomerId(long customerId);

    User getCustomerByUserName(String username, String password);
}
