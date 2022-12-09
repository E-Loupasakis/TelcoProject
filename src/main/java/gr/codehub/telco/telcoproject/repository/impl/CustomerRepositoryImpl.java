package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomerRepositoryImpl extends RepositoryImpl<User, Long> implements CustomerRepository {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @Override
    public Class<User> getClassType() {
        return User.class;
    }

    @Override
    public String getClassName() {
        return User.class.getSimpleName();
    }


    @Override
    public User getCustomerByVat(int vat) throws NoResultException, NonUniqueResultException {
        return (User)  em.createQuery("Select u from "+getClassName()+" u where u.vatNumber="+vat).getSingleResult();
    }

    @Override
    public List<User> getCustomerByEmail(String emailAddress) {
        return  em.createQuery("Select u from "+getClassName()+" u join u.emailList p where p.email LIKE :emailAddress")
                .setParameter("emailAddress", emailAddress)
                .getResultList();
    }

    @Override
    public List<Ticket> GetTicketsByCustomerId(long id) {
       return read(id).getTickets();
    }


}
