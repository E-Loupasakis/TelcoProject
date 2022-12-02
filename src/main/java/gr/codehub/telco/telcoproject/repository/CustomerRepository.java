package gr.codehub.telco.telcoproject.repository;

import gr.codehub.telco.telcoproject.model.User;

public interface CustomerRepository extends Repository<User, Long>{

    User getCustomerByVat(int vat);

    User getCustomerByEmail(String emailAddress);


}
