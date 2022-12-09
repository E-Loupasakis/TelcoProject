package gr.codehub.telco.telcoproject.repository;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;

import java.util.List;

public interface CustomerRepository extends Repository<User, Long>{

    User getCustomerByVat(int vat);

    List<User> getCustomerByEmail(String emailAddress);

    List<Ticket> GetTicketsByCustomerId(long id);

}
