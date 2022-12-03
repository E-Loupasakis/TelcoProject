package gr.codehub.telco.telcoproject.dto;


import gr.codehub.telco.telcoproject.enums.UserCategory;
import gr.codehub.telco.telcoproject.model.Email;
import gr.codehub.telco.telcoproject.model.Phone;
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

    public CustomerDto(User customer){
        if(customer!=null){
            id = customer.getUserId();
            vatNumber = customer.getVatNumber();
            userCategory = customer.getUserCategory();
            firstName = customer.getFirstName();
            lastName = customer.getLastName();
            userName = customer.getUserName();
            lastName = customer.getPassword();
            address = customer.getAddress();
            phoneList = customer.getPhones();
            emailList = customer.getEmailList();
        }
    }

    public User asCustomer() {
        User customer = new User();
        customer.setUserId(id);
        customer.setVatNumber(vatNumber);
        customer.setUserCategory(userCategory);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUserName(userName);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setPhones(phoneList);
        customer.setEmailList(emailList);
        return customer;
    }
}

