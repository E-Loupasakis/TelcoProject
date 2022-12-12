package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.exception.CustomerPropertiesExistingException;
import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.exception.EmailExistsException;
import gr.codehub.telco.telcoproject.exception.UserNameExists;
import gr.codehub.telco.telcoproject.exception.VatExistsException;
import gr.codehub.telco.telcoproject.model.Email;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.CustomerRepository;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.inject.Inject;

import java.util.ArrayList;
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
        customer.getEmailList().forEach( (email) -> {
            if(!(customerRepository.getCustomerByEmail(email.getEmail()).isEmpty())){
                    throw new EmailExistsException("Email exists");
            }
        });

        if(customerRepository.getCustomerByUserName(customer.getUsername())!=null){
            throw new UserNameExists("Username exists");
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

        Integer count = Integer.valueOf(String.valueOf(customerRepository.getVatUnique(customer.getVatNumber(), customer.getId()).get(0)));
        System.out.println(count);
        if(count>0){
            throw new VatExistsException("Vat exists");
        }

        customer.getEmailList().forEach( (email) -> {
            Integer countNew = Integer.valueOf(String.valueOf(customerRepository.checkUserEmailUnique(email.getEmail(), customer.getId()).get(0)));
            System.out.println(countNew);
            if(countNew>0) throw new EmailExistsException("Email exists");
        });

        count = Integer.valueOf(String.valueOf(customerRepository.getUserNameUnique(customer.getUsername(), customer.getId()).get(0)));
        if(count>0){
            throw new UserNameExists("Username already exists");
        }

        return customerRepository.update(customer);
    }

    @Override
    public void delete(long customerId) {
        customerRepository.delete(customerId);
    }

    @Override
    public List<Ticket> findTicketsByCustomerId(long customerId) {
        return  customerRepository.read(customerId).getTickets();

    }

    private String checkProperties(User customer) {
        StringBuilder exceptionMessage = new StringBuilder();
        if(customerRepository.getCustomerByVat(customer.getVatNumber())!=null){
            exceptionMessage.append("Vat exists\n");
            //throw new VatExistsException("Vat exists");
        }
        customer.getEmailList().forEach( (email) -> {
            if(!(customerRepository.getCustomerByEmail(email.getEmail()).isEmpty())){
                exceptionMessage.append("Email "+email.getEmail()+" is already in use.\n");
                //throw new EmailExistsException("Email exists");
            }
        });
        customer.getPhones().forEach( (phone) -> {
            if(!(customerRepository.getCustomerByTelephone(phone.getNumber()).isEmpty())){
                exceptionMessage.append("Phone "+phone.getNumber()+" is already in use.\n");
                //throw new EmailExistsException("Phone exists");
            }
        });

        return exceptionMessage.toString();
    }
}
