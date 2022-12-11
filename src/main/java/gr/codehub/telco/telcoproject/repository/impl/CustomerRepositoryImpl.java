package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class CustomerRepositoryImpl extends RepositoryImpl<User, Long> implements CustomerRepository {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;


    @Override
    public List<User> read(){
        return  em.createQuery("select u from " + getClassName() + " u left join fetch u.tickets").getResultList();
    }


    @Override
    public User read(long id) {
        try{
        return  em.createQuery("select u from " + getClassName() + " u left join fetch u.tickets where u.id=:id", getClassType())
                .setParameter("id", id)
                .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }

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
    @Transactional
    public User update(User customer){
        User user = read(customer.getId());
        if (user == null) return null;
        return em.merge(customer);
    }




}
