package gr.codehub.telco.telcoproject;


import gr.codehub.telco.telcoproject.model.Customer;

public class TestModels {

    public static void main(String[] args) {

        Customer customer = Customer.builder().ssn(654654).build();


        System.out.println(customer.getSsn());

    }
}
