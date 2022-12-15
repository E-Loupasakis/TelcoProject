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
        try{
            return em.createQuery("Select u from " + getClassName() + " u left join fetch u.tickets where u.vatNumber LIKE :vat", User.class)
                    .setParameter("vat", vat)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }


    @Override
    public User getCustomerByUserName(String userName, String password) throws NoResultException, NonUniqueResultException {
        try{
            return em.createQuery("Select u from " + getClassName() + " u left join fetch u.tickets where u.username LIKE :username and u.password LIKE :password", User.class)
                    .setParameter("username", userName)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> getVatUnique(int vat, long id) {
        try{

            List<User> user = em.createQuery(
                            "SELECT COUNT(c) FROM User c WHERE c.vatNumber = :vatNumber and c.id <> :id", User.class)
                    .setParameter("vatNumber", vat)
                    .setParameter("id", id)
                    .getResultList();
            return user;
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> getUserNameUnique(String userName, long id) {
        try{

            List<User> user = em.createQuery(
                            "SELECT COUNT(c) FROM User c WHERE c.username = :userName and c.id <> :id", User.class)
                    .setParameter("userName", userName)
                    .setParameter("id", id)
                    .getResultList();
            return user;
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> checkUserEmailUnique(String email, long id) {
        try{

           return em.createQuery(
                            "SELECT COUNT(c) FROM User c join c.emailList p WHERE p.email = :pemail and c.id <> :id", User.class)
                    .setParameter("pemail", email)
                    .setParameter("id", id)
                    .getResultList();

        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> checkUserPhoneUnique(String phone, long id) {
        try{

            return em.createQuery(
                            "SELECT COUNT(c) FROM User c join c.phones p WHERE p.number = :pphone and c.id <> :id", User.class)
                    .setParameter("pphone", phone)
                    .setParameter("id", id)
                    .getResultList();

        }catch(NoResultException e){
            return null;
        }
    }


    @Override
    public List<User> getCustomerByEmail(String emailAddress) {
        return  em.createQuery("Select u from "+getClassName()+" u left join fetch u.tickets join u.emailList p where p.email LIKE :emailAddress ")
                .setParameter("emailAddress", emailAddress)
                .getResultList();
    }

    @Override
    public List<User> getCustomerByTelephone(String telephone) {
        return  em.createQuery("Select u from "+getClassName()+" u join u.phones p where p.number LIKE :telephone")
                .setParameter("telephone", telephone)
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
