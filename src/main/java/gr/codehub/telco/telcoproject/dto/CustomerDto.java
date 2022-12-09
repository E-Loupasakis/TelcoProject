package gr.codehub.telco.telcoproject.dto;


import gr.codehub.telco.telcoproject.enums.UserCategory;
import gr.codehub.telco.telcoproject.model.Email;
import gr.codehub.telco.telcoproject.model.Phone;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private int vatNumber;
    private UserCategory userCategory;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private List<Phone> phoneList;
    private List<Email> emailList;

    private List<Ticket> ticketList;

    public CustomerDto(User customer){
        if(customer!=null){
            id = customer.getId();
            vatNumber = customer.getVatNumber();
            userCategory = customer.getUserCategory();
            firstName = customer.getFirstName();
            lastName = customer.getLastName();
            userName = customer.getUsername();
            password = customer.getPassword();
            address = customer.getAddress();
            phoneList = customer.getPhones();
            emailList = customer.getEmailList();
            ticketList = customer.getTickets();
        }
    }

    public User asCustomer() {
        User customer = new User();
        customer.setId(id);
        customer.setVatNumber(vatNumber);
        customer.setUserCategory(userCategory);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUsername(userName);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setPhones(phoneList);
        customer.setEmailList(emailList);
        customer.setTickets(ticketList);
        return customer;
    }
}

