package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.model.User;

import java.util.List;

public interface CustomerService {

   User create(gr.codehub.telco.telcoproject.model.User customer);
    User read(long id);
    User readByVat(int vat);

    List<User> read(String email);
    List<User> read();

    User update(User customer);
    void delete(long customerId);
}
