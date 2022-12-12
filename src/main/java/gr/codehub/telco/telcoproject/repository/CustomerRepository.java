package gr.codehub.telco.telcoproject.repository;
import gr.codehub.telco.telcoproject.model.User;

import java.util.List;

public interface CustomerRepository extends Repository<User, Long>{

    User getCustomerByVat(int vat);

    List<User> checkUserEmailUnique(String email, long id);

    List<User> getCustomerByEmail(String emailAddress);
    List<User> getCustomerByTelephone(String telephone);

    User getCustomerByUserName(String userName);

    List<User> getVatUnique(int vat, long id);

    List<User> getUserNameUnique(String userName, long id);
}
