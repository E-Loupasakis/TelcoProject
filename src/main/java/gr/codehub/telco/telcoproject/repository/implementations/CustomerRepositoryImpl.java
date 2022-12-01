package gr.codehub.telco.telcoproject.repository.implementations;

import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;

public class CustomerRepositoryImpl extends RepositoryImpl<User, Long> implements CustomerRepository {


    @Override
    public Class<User> getClassType() {
        return User.class;
    }

    @Override
    public String getClassName() {
        return User.class.getSimpleName();
    }
}
