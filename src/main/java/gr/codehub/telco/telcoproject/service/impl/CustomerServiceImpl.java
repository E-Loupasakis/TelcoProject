package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.exception.CustomerPropertiesExistingException;
import gr.codehub.telco.telcoproject.exception.InvalidDeletionException;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public User create(User customer) {
        String exceptionMessage = checkProperties(customer);
        if(!exceptionMessage.isEmpty()){
            throw new CustomerPropertiesExistingException( exceptionMessage );
        }

        return customerRepository.create(customer);
    }


    @Override
    public User read(long id) {
        return customerRepository.read(id);
    }

    @Override
    public User readByVat(int vat) {
        return customerRepository.getCustomerByVat(vat);
    }
    @Override
    public List<User> read(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public List<User> read() {
        return customerRepository.read();
    }

    @Override
    public User update(User customer) {
        String exceptionMessage = checkPropertiesForUpdate(customer);
        if(!exceptionMessage.isEmpty()){
            throw new CustomerPropertiesExistingException( exceptionMessage );
        }
        return customerRepository.update(customer);
    }


    private String checkPropertiesForUpdate(User customer){
        StringBuilder exceptionMessage = new StringBuilder();
        Integer count = Integer.valueOf(String.valueOf(customerRepository.getVatUnique(customer.getVatNumber(), customer.getId()).get(0)));
        if(count>0){
            exceptionMessage.append("Vat exists");
        }
        customer.getEmailList().forEach( (email) -> {
            boolean checkDuplicate = Collections.frequency(customer.getEmailList(), email) > 1;
            if(checkDuplicate)throw new CustomerPropertiesExistingException("Email Exists");
            Integer countNew = Integer.valueOf(String.valueOf(customerRepository.checkUserEmailUnique(email.getEmail(), customer.getId()).get(0)));
            if(countNew>0) exceptionMessage.append("Email exists");

        });
        count = Integer.valueOf(String.valueOf(customerRepository.getUserNameUnique(customer.getUsername(), customer.getId()).get(0)));
        if(count>0){
            exceptionMessage.append("Username already exists");
        }
        customer.getPhones().forEach( (phone) -> {
            boolean checkDuplicate = Collections.frequency(customer.getPhones(), phone) > 1;
            if(checkDuplicate){throw new CustomerPropertiesExistingException("Email Exists");}
            Integer countNew = Integer.valueOf(String.valueOf(customerRepository.checkUserPhoneUnique(phone.getNumber(), customer.getId()).get(0)));
            System.out.println(countNew);
            if(countNew>0) exceptionMessage.append("Phone exists");
        });
        return exceptionMessage.toString();
    }

    @Override
    public boolean delete(long customerId) {

        if (customerRepository.delete(customerId)) {
            return true;
        }
            throw new InvalidDeletionException("The there is no item with id " + customerId + " in the database");
    }

    @Override
    public List<Ticket> findTicketsByCustomerId(long customerId) {
        return  customerRepository.read(customerId).getTickets();
    }

    @Override
    public User getCustomerByUserName(String username, String password) {
        return customerRepository.getCustomerByUserName(username, password);
    }

    private String checkProperties(User customer) {
        StringBuilder exceptionMessage = new StringBuilder();
        if(customerRepository.getCustomerByVat(customer.getVatNumber())!=null){
            exceptionMessage.append("Vat exists\n");
        }
        customer.getEmailList().forEach( (email) -> {
            boolean checkDuplicate = Collections.frequency(customer.getEmailList(), email) > 1;
            if(checkDuplicate){throw new CustomerPropertiesExistingException("Email Exists");}
            if(!(customerRepository.getCustomerByEmail(email.getEmail()).isEmpty())){
                exceptionMessage.append("Email "+email.getEmail()+" is already in use.\n");
            }
        });
        customer.getPhones().forEach( (phone) -> {
            if(!(customerRepository.getCustomerByTelephone(phone.getNumber()).isEmpty())){
                exceptionMessage.append("Phone "+phone.getNumber()+" is already in use.\n");
            }
        });
        if(customerRepository.getCustomerByUserName(customer.getUsername(), customer.getPassword())!=null){
            exceptionMessage.append("Username is already in use.\n");
        }
        return exceptionMessage.toString();
    }
}
