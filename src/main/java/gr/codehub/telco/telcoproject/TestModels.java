package gr.codehub.telco.telcoproject;


import gr.codehub.telco.telcoproject.model.Customer;

public class TestModels {

    public static void main(String[] args) {

        Customer customer = Customer.builder().
                firstName("Thanasis").lastName("Bimis").email("thanasisbimis@yahoo.com").
                address("3hs taxistxhias").phoneNumber("654654").
                userName("scyfer").password("1234").ssn(654654).build();

        System.out.println(customer);
        System.out.println(customer.getEmail());

    }
}
