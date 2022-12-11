package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;

import java.util.List;

public class CustomerRepositoryImpl extends RepositoryImpl<User, Long> implements CustomerRepository {



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
        try{
            return em.createQuery("Select u from " + getClassName() + " u where u.vatNumber LIKE :vat", User.class)
                    .setParameter("vat", vat)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }



    @Override
    public List<User> getCustomerByEmail(String emailAddress) {
        return  em.createQuery("Select u from "+getClassName()+" u join u.emailList p where p.email LIKE :emailAddress")
                .setParameter("emailAddress", emailAddress)
                .getResultList();
    }

    @Override
    public List<User> getCustomerByTelephone(String telephone) {
        return  em.createQuery("Select u from "+getClassName()+" u join u.phones p where p.number LIKE :telephone")
                .setParameter("telephone", telephone)
                .getResultList();
    }


}
