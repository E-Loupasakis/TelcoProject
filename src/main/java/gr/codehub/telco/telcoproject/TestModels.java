package gr.codehub.telco.telcoproject;


import gr.codehub.telco.telcoproject.enums.UserCategory;
import gr.codehub.telco.telcoproject.model.User;

public class TestModels {

    public static void main(String[] args) {

        User customer = User.builder().userCategory(UserCategory.CUSTOMER).build();
        System.out.println(customer);


    }
}
